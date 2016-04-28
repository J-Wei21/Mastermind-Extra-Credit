/* Name: Caroline Yao & Horng-Bin Justin Wei
 * EID: Chy253 & Hjw396
 * Section: Thursday 3:30-5:30pm & Friday 2-3:30pm
 * EE 422C Extra Credit Assignment
 */

public class Key {

	private static int size;
	private String key;
	public final static String[] VALID_COLORS = {"B", "G", "O", "P", "R", "Y"};
	
	public static void setSize(int i) {
		size = i;
	}
	
	public Key(){
		key = "";
		for(int i = 0; i < size; i += 1) {
			key += VALID_COLORS[(int) (Math.random() * VALID_COLORS.length)];
		}
	}
	
	public Key(String input) {
		key = input;
	}
	
	public String getKey() {
		return key;
	}
	
	public int[] checkKey(Key key2) {
		int[] result = new int[2];
		char[] temp1 = this.key.toCharArray();
		char[] temp2 = key2.getKey().toCharArray();
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
