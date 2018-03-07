package quizpack;

import java.util.Scanner;
/**
 * Verwerkt gebruiker invoer en console output.
 */
public class Console {
	Scanner scan;
	/**
	 * Print parameter string naar een regel in de console om tekst voor de quiz te tonen in de console.
	 * @param String printlnString
	 */
	public void println(String printlnText) {
		System.out.println(printlnText);		
	}
	
	/**
	 * Print parameter string naar console.
	 * @param String printString
	 */
	public void print(String printtext) {
		System.out.print(printtext);		
	}
	
	/**
	 * Leest de eerstvolgende consoleregel en returned deze regel.
	 * @return String userInput
	 */
	public String getUserInput() {
		scan = new Scanner(System.in);
		String userInput = scan.nextLine();		
		return userInput;
	}

}
