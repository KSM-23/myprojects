package Project3;

import java.util.Random;
import java.util.Scanner;

public class Kira_Martins_Project3 {

	public static void main(String[] args) {
		
			// Declare Vars
			String name;
			int die1;
			int die2;
			int sum;
			int pairBonus = 0;
			int seven11Bonus = 0;
			int total;
			int tempDie;
			
			int turns;
			String playAgain = "N";
			
			
			// Create the utility objects you need - Scanner and Random
			Scanner input = new Scanner(System.in);
			Random random = new Random();
			
			// Get player's name
			System.out.print("Please enter your name: ");
			name = input.next();
			System.out.println();
			
			// Print Rules and Welcome message
			System.out.println("                  Hello, " + name + "." + " Welcome to the Dice Game! Playing is a breeze, for the rules are quite simple. "
			                                    + "All you have to do is roll the die and your computer does the rest. If you roll a 7 or 11 you'll get 4"
					                            + "bonus points. If you roll a pair/double you'll get 5 bonus points. The sum of the dice are also worth points."
			                                    + "That is all! Now let us begin - enter any character key to start.");
				input.next();
			System.out.println();
			
			// Start the do-while loop for playAgain
			do {
				
				// Prompt for and read in the number of games to be played
				System.out.println();
				System.out.print("Enter the number of turns you wish to play: ");
				turns = input.nextInt();
				System.out.println();
			
				// Start the loop for the number of turns
				for (int i = 1; i <= turns; i++) {
					
					// Re-initialize the bonuses
					pairBonus = 0;
					seven11Bonus = 0;
				
					// Roll the dice, sort the dice, display the roll
					die1 = random.nextInt(6) + 1;
					die2 = random.nextInt(6) + 1;
					
					
					if (die1 > die2) {
						tempDie = die1;
						die1 = die2;
						die2 = tempDie;
					}
					
					// Evaluate the roll, calculate points
					sum = die1 + die2;
					if (die1 == die2) {
						pairBonus = 5;
					}
					if (sum == 7 || sum == 11) {
						seven11Bonus = 4;
					}
					total = sum + pairBonus + seven11Bonus;
					
					// Display roll (sorted), sum, bonus points, total points
					System.out.println("** Turn " + i + " **");
					System.out.println("Dice Roll:  " + die1 + "," + die2);
					System.out.println("Dice Sum:   " + sum);
					System.out.println("Pair Bonus: " + pairBonus);
					System.out.println("Seven11 Bonus: " + seven11Bonus);
					System.out.println("Total Pts:  " + total);
					System.out.println();
					
					} // End of turns loop
				
				// Prompt for and read in playAgain y/n
				System.out.print("Would you like to play again?  Enter Y or N: ");
				playAgain = input.next();
				playAgain = playAgain.toUpperCase();
			
			} while(playAgain.equals("Y"));  // If playAgain not equal to Y, session over
			
			// Display the goodbye/ending message
			System.out.println();
			System.out.println();
			System.out.println("                    Thank you, " + name + " for playing the Dice Game. Hope you enjoyed it, come back and play again okay?!");
			
			input.close();
			

	}   // End of main method. Class methods below.*******
	
	
	
	// Name: prtIntro
	// Return type: void
	// Parms: String name
	public static void prtIntro(String name) {
		System.out.println("                        Hello, " + name + "." + " Welcome to the Dice Game! Playing is a breeze, for the rules are quite simple. "
                                                    + "All you have to do is roll the die and your computer does the rest. If you roll a 7 or 11 you'll get 4"
                                                    + "bonus points. If you roll a pair/double you'll get 5 bonus points. The sum of the dice are also worth points."
                                                    + "That is all! Now let us begin - enter any character key to start.");
	}
	
	// Name: getDieValue
	// Desc: when invoked rolls & receives two random dice values 
	// Return Type: int 
	// Parms: int die1, int die2
	
	public static int getDieValue(int die1, int die2) {
		Random random = new Random();
		int Die1 = random .nextInt(6) + 1;
		int Die2 = random.nextInt(6) + 1;
		int dieValue = 0;
		if (die1 > die2) {
			dieValue = die1;
			die1 = die2;
			die2 = dieValue; 	
		}
		else {
			dieValue =die2;
		}
		return dieValue;
	} 
	
	// Name: getPairBonus
	// Desc: when invoked adds points if die1 and die2 are equal
	// Return Type: int
	// Parms: int pairBonus
	
	public static int getPairBonus(int pairBonus) {
		int x = 0;
		int y = 0;
		if ((int) x == (int) y) {
			pairBonus = 5;
		}
		else {
			pairBonus = 0;
		}
		return pairBonus;
	}
	
	// Name: getSeven11Bonus
	// Desc: when invoked adds points if die1 and die2's sum equals 7 or 11
	// Return Type: int
	// Parms: int sum, int seven11Bonus
	
	public static int getSeven11Bonus(int seven11Bonus) {
		int die1 = 0;
		int die2 = 0;
		int sum = die1 + die2;
		if (sum == 7 || sum == 11) {
			seven11Bonus = 4;
		}
		else {
			seven11Bonus = 0;
		}
		return seven11Bonus;
	}
	
	// Name: prtOutro
	//Return type: void
	//Parms: String name
	public static void prtOutro (String name) {
		System.out.println("Thank you, " + name + " for playing the Dice Game. Hope you enjoyed it, come back and play again okay?!");
	}

}
