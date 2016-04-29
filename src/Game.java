import java.util.ArrayList;
import java.util.Scanner;

/* Name: Caroline Yao & Horng-Bin Justin Wei
 * EID: Chy253 & Hjw396
 * Section: Thursday 3:30-5:30pm & Friday 2-3:30pm
 * EE 422C Extra Credit Assignment
 */

public class Game {

	private boolean testMode;
	private Scanner scan;
	private ArrayList<String> history;

	public Game(boolean testmode) {
		this.testMode = testmode;
		scan = new Scanner(System.in);
		System.out.println("Welcome to Mastermind. Here are the rules.\n\n"
				+ "This is a text version of the classic board game Mastermind.\n"
				+ "The computer will think of a secret code. The code consists of 4 colored pegs.\n"
				+ "The pegs may be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear\n"
				+ "more than once in the code. You try to guess what colored pegs are in the code and what order they are\n"
				+ "in. After you make a valid guess the result (feedback) will be displayed.\n"
				+ "The result consists of a black peg for each peg you have guessed exactly correct (color and position) in\n"
				+ "your guess. For each peg in the guess that is the correct color, but is out of position, you get a white\n"
				+ "peg. For each peg, which is fully incorrect, you get no feedback.\n\n"
				+ "Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.\n"
				+ "When entering guesses you only need to enter the first character of each color as a capital letter.");
	}

	public void startGame() {
		history = new ArrayList<>();
		
		String start;

		System.out.println(
				"You have 12 guesses to figure out the secret code or you lose the game. Are you ready to play? (Y/N): ");
		start = scan.next();

		while (!start.equalsIgnoreCase("y") && (!start.equalsIgnoreCase("n"))) {
			System.out.println("Invalid input. Please answer Y/N.");
			start = scan.next();
		}

		if (start.equalsIgnoreCase("n")) {
			System.out.println("Are you sure? Please answer N to play.");
			start = scan.next();

			while (!start.equalsIgnoreCase("y") && (!start.equalsIgnoreCase("n"))) {
				System.out.println("Invalid input. Please answer Y/N.");
				start = scan.next();
			}

			if (start.equalsIgnoreCase("y")) {
				System.out.println("Are you sure? Please choose to play this time by pressing N.");
				start = scan.next();

				while (!start.equalsIgnoreCase("y") && (!start.equalsIgnoreCase("n"))) {
					System.out.println("Invalid input. Please answer Y/N.");
					start = scan.next();
				}

				if (start.equalsIgnoreCase("y")) {
					System.out.println("Fine. Goodbye.");
					System.exit(0);
				}
			}

		}

		Key.setSize(4);
		Key.createRandomKey();
		
		System.out.println("Generating your challenge, young grasshopper...");
		System.out.println(Key.getKey() + "\n");

		int numTries = 12;
		
		while (numTries > 0){
//			if(testMode)
//				System.out.println(Key.getKey());
			System.out.println("\nYou have " + numTries + " guesses left.\n"
					+ "What is your next guess?\n"
					+ "Type in the characters for your guess and press enter.\n"
					+ "Enter guess:");
			String s = scan.next();
			if(s.equalsIgnoreCase("history")) {
				gameHistory();
				continue;
			}
			else if(!Key.validKey(s)) {
				System.out.println("Invalid guess\n");
				continue;
			}
			Key k = new Key(s);
			int[] result = k.checkKey();
			String output = s + " -> Result: ";
			if(result[0] > 0 && result[1] > 0)
				output += result[0] + " black peg" + ((result[0]>1)?"s":"") + " and " + result[1] + " white peg" + ((result[1]>1)?"s":"");	
			else if(result[0] > 0)
				output += result[0] + " black peg" + ((result[0]>1)?"s":"");
			else if(result[1] > 0)
				output += result[1] + " white peg" + ((result[1]>1)?"s":"");
			else
				output += "No pegs";
			System.out.println(output + "\n");
			history.add(output);
			
			if(result[0] == 4) {
				gameWon();
				break;
			}
			numTries--;
		}
		if(numTries == 0)
			gameLost();
		

		System.out.println("Are you ready for another game (Y/N):");
		start = scan.next();

		while (!start.equalsIgnoreCase("y") && (!start.equalsIgnoreCase("n"))) {
			System.out.println("Invalid input. Please answer Y/N.");
			start = scan.next();
		}
		
		if(start.equalsIgnoreCase("n")) {
			System.exit(0);
		} else {
			startGame();
		}

	}

	public void gameWon() {
		System.out.println("Congratulations you have won.  Life is now complete.");
	}
	
	public void gameLost() {
		System.out.println("What doesn't kill you makes you stronger. Try again!");
	}
	
	public void gameHistory() {
		for(int i = 0; i < history.size(); i += 1) {
			System.out.println(history.get(i));
		}
	}
}
