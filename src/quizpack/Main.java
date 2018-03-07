package quizpack;
/**
 * Multiple Choice Quiz
 *
 * @version V0.4
 * @author Serge Bosman
 * @since   2018-14-02
 */
public class Main {	
/**
 * Main method die met quizaankondigingen de quiz toont en resources sluit.
 */
	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		Console cnsl = new Console();
		cnsl.println("Welkom bij de Serge quiz! \nBeantwoordt alle vragen correct en vergaar eeuwige roem!");	
		quiz.run();
		quiz.destruct();
		cnsl.println("\nDit is het einde van de test. \nJe hebt "+ quiz.score + " van de " + quiz.maxScore + " punten gescoord!");
	}
}