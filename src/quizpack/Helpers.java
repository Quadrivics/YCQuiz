package quizpack;

/**
 * Convert invoer naar een andere vorm/variable.
 */
public final class Helpers {
	
/**
 * Zet een integer als invoer om naar een character.
 * @param int id
 * @return String prefix
 */
	public static String idToPrefix(int id)
	{
		String alphabet = "abcdefghijklmnopqrstuvxyz";	
		Character c = alphabet.charAt(id);
		String prefix = c.toString(); 
		return prefix;
	}
	
	/**
	 * Zet een string als invoer om naar een integer.
	 * @param String prefix
	 * @return int
	 */
	public static int prefixToID(String prefix)
	{
		String alphabet = "abcdefghijklmnopqrstuvxyz";	
		int id = alphabet.indexOf(prefix);
		return id;
	}
}