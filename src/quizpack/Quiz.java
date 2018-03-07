package quizpack;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Verwerkt database input tot een multiple choice quiz.
 */
public class Quiz {
	Database db;
	Console cnsl;
	ArrayList<Integer> correctAnswerIDs = new ArrayList<Integer>();
	ResultSet questions, answers;
	int questionID, answerAmount, correctAnswerIndex, score, amountAnswers, maxScore;

	/**
	 * Geeft alle benodigde waardes aan global variables.
	 */
	Quiz() {
		try {
			db = new Database();
			cnsl = new Console();
			questions = db.buildQuery("SELECT * FROM Questions");
			maxScore = db.buildQuery("SELECT COUNT(*) AS aantalVragen FROM Questions").getInt("aantalVragen");
		} catch (Exception SQLException) {
			cnsl.println("Quiz() error: " + SQLException.getMessage());
		}
		questionID = answerAmount = correctAnswerIndex = score = amountAnswers = 0;
	}

	/**
	 * Genereert een vraag zolang er vragen beschikbaar zijn en print deze naar de console.
	 */
	public void run() {
		try {
			while (questions.next()) {
				printQuestion();
				processUserInput();
			}
		} catch (Exception SQLException) {
			cnsl.println("run() error: " + SQLException.getMessage());
		}
	}

	/**
	 * Haalt vraag op uit database en print een vraag naar de console.
	 */
	public void printQuestion() {
		try {
			String question = questions.getString("question");
			questionID = questions.getInt("id");
			cnsl.println("\nVraag " + questionID + ": " + question);
		} catch (Exception SQLException) {
			cnsl.println("buildQuestion() error: " + SQLException.getMessage());
		}
		printAnswers();
	}

	/**
	 * Haalt antwoorden en correcte antwoord ID's op uit database en print antwoorden naar de console.
	 */
	public void printAnswers() {
		try {
			String answer;
			String prefix;
			answers = db.buildQuery("SELECT * FROM Answers WHERE question_id=" + questionID);
			amountAnswers = db
					.buildQuery("SELECT COUNT(*) AS aantalAntwoorden FROM Answers WHERE question_id = " + questionID)
					.getInt("aantalAntwoorden");
			int correctAnswerID = 0;
			while (answers.next()) {
				answer = answers.getString("answer");
				prefix = Helpers.idToPrefix(correctAnswerID);

				if (answers.getInt("correctanswer") == 1) {
					correctAnswerIDs.add(correctAnswerID); // Hier wordt een array gebruikt zodat meerdere goede
															// antwoorden mogelijk zijn per vraag.
				}

				cnsl.println(prefix.toUpperCase() + ") " + answer);
				correctAnswerID++;
			}
		} catch (Exception SQLException) {
			cnsl.println("printAnswers() error: " + SQLException.getMessage());
		}
	}

	/**
	 * Vraagt de gebruiker om input om de desbetreffende vraag te beantwoorden.
	 * Daarna wordt er aan de hand van de gegeven input een score punt
	 * toegekend of niet.
	 */
	public void processUserInput() {
		cnsl.print("Antwoord: ");
		String userIn = cnsl.getUserInput().toLowerCase();
		int id = Helpers.prefixToID(userIn);
		while (id < 0 || id >= amountAnswers) {
			cnsl.println(
					"Dit is geen geldige invoer.\nVul één letter in die overeenkomt met de letters van de bovenstaande antwoorden. \nAntwoord: ");
			userIn = cnsl.getUserInput();
			id = Helpers.prefixToID(userIn);
		}
		if (correctAnswerIDs.indexOf(id) > -1) {
			cnsl.println("Antwoord " + userIn.toUpperCase() + " is correct! +1");
			score++;
		} else {
			cnsl.println("Helaas... antwoord " + userIn.toUpperCase() + " is niet correct!");
		}
	}

	/**
	 * Sluit interfaces en classes om alle mogelijke resourceleaks voorkomen.
	 */
	public void destruct() {
		db.disconnect();
	}
}
