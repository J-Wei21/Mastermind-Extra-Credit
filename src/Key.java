/* Name: Caroline Yao & Horng-Bin Justin Wei
 * EID: Chy253 & Hjw396
 * Section: Thursday 3:30-5:00pm & Friday 2-3:30pm
 * EE 422C Extra Credit Assignment
 */

public class Key {

	private static int size;
	private static String key;
	
	private String guess;
	public final static char[] VALID_COLORS = {'B', 'G', 'O', 'P', 'R', 'Y', 'L', 'M'};
	private static int colorNum;
	
	public static void setSize(int i) {
		size = i;
	}
	
	public static void createRandomKey(int numColors){
		colorNum = numColors;
		key = "";
		for(int i = 0; i < size; i += 1) {
			key += VALID_COLORS[(int) (Math.random() * numColors)];
		}
	}

	public static boolean validKey(String s) {
		if(s.length() != size) return false;
		for(char c: s.toCharArray()) {
			boolean temp = false;
			for(int i = 0; i < colorNum; i+=1) {
				if(c == VALID_COLORS[i]) temp = true;
			}
			if(!temp) return false;
		}
		return true;
	}
	
	public static String getKey() {
		return key;
	}
	
	public Key(String input) {
		guess = input;
	}
	
	public String getGuess() {
		return guess;
	}
	
	public int[] checkKey() {
		int[] result = new int[2];
		char[] temp1 = guess.toCharArray();
		char[] temp2 = key.toCharArray();
		for(int i = 0; i < size; i += 1) {
			if(temp1[i] == temp2[i]) {
				temp1[i] = ' ';
				temp2[i] = ' ';
				result[0] += 1;
			}
		}
		for(int i = 0; i < size; i += 1) {
			for(int j = 0; j < size; j += 1) {
				if (temp1[i] != ' ' && temp2[j] != ' ' && temp1[i] == temp2[j]) {
					temp1[i] = ' ';
					temp2[j] = ' ';
					result[1] += 1;
				}
			}
		}
		
		return result;
	}
}
