package p2;

import java.util.Random;
import java.util.Scanner;

public class Kira_Martins_Project_2 {

	public static void main(String[] args) { 
		
		// Instantiate objects
				Scanner input = new Scanner(System.in);
				Session s = new Session();
				Game g = new Game();
				
				// Declare vars
				String playAgain = "N";
				
				// Game flow *******************************************************************
				
				// Print intro_1 - instructions and rules
				s.prtIntro();
				
				// Get name, rounds, print intro_2
				System.out.print("Before we start, I need to get your name.  Please enter it here: ");
				s.setName(input.next());
				
				// Start the loop for playAgain
				playAgain = "Y";
				while(playAgain.equals("Y")) {
					g.setPoints(50);
					System.out.print("How many rounds do you wish to play in this game? ");
					g.setRounds(input.nextInt());
					System.out.println();
					System.out.printf("GREAT " + s.getName() + "!  You have selected %d rounds.", g.getRounds());
					System.out.println();
					System.out.println();
				
					// Start of the loop for the number of rounds
					for (int i = 0; i < g.getRounds(); i++) {
					
						g.setRound(i); // Sets the var g.round to i+1
						
						// Roll_1 
						g.setRollNum(1);
						System.out.println("******* Round " + g.getRound() + " *******");
						System.out.print("Enter the letter R to initiate Roll_1: ");
						input.next();
						g.setR1();
						
						// Print Roll_1
						g.prtRoll(g.getRollNum());
						
						// Set bet and wager
						System.out.print("Enter the letter O or U to indicate if you believe Roll_2 will be over (O) or under (U) Roll_1: ");
						g.setBet(input.next());
						//bet = bet.toUpperCase();
						System.out.print("Now enter the number of points you wish to wager: ");
						g.setWager(input.nextInt());
						System.out.println();
						
						// Roll_2
						g.setRollNum(2);
						System.out.print("Enter the letter R to initiate Roll_2: ");
						input.next();
						g.setR2();
						
						g.prtRoll(g.getRollNum());
						
						// Evaluate roll2 vs roll1, determine OUDS and if the round was won/lost
						// First and second evaluations are for the bet over/under and the outcome over/under.  
						// Third and fourth evaluations are for the double and same outcomes.
						// The evaluation of the bet and outcome yields eval = W or L.
						
						// evalBetO - evaluate rolls for a bet of O or OVER
						g.evalBetO();
						
						// evalBetU - evaluate rolls for a bet of U or UNDER
						g.evalBetU();
						
						// evalBetDoubles - evaluate rolls for a roll2 of doubles of any value
						g.evalBetDoubles();
						
						// evalBetSame - evaluate rolls for sum of roll1 equal to sum of roll2
						g.evalBetSame();
						
						// Switch for eval == W or L
						g.prtWL();
						
						// End of round processing
						g.prtEndOfRound(i);
						
						// Test for bankruptcy (points <= 0)
						g.testBankrupt();
					
					} // End of for loop for the number of rounds. Go up to next round or end game.
					
					// End of game processing
					g.prtEndOfGame(s.getName());
					
					
					// Ask if player wants to playAgain and read-in the response
					System.out.print("Would you like to play again?  Enter Y or N: ");
					playAgain = input.next();
					playAgain = playAgain.toUpperCase();
					
				}  // End of while loop for playAgain
				
				// Print outro
				s.prtOutro();
				
				input.close();
				
			}  // End of main method ************************************************************
			
			// Class methods are below **********************************************************
			
			// prtOutro
			public static void prtOutro(String name) {
				System.out.println();
				System.out.println("Thank you for playing OVER UNDER DOUBLE SAME.");
				System.out.printf("Have a great day, %s!", name);
			}
			
		}  // End of main/driver class

		// Game class begins here *********************************************************************************

		class Game {
			
			// Declare vars
			private int rounds;
			private int round;
			private int rollNum = 0;
			private int r1d1;
			private int r1d2;
			private int r1Sum;
			private int r2d1;
			private int r2d2;
			private int r2Sum;
			private String bet = "X";	// Either O or U 
			private int wager = 0;
			private String outcome = "X";  // One of O, U, D, S
			private String eval = "X";  // Either W or L
			private int points = 50;
			private String history[];
			
			// Define methods
			// setPoints
			public void setPoints(int points) {
				this.points = points;
			}
			
			// getPoints
			public int getPoints() { return points; }
			
			// setRounds
			public void setRounds(int rounds) {
				this.rounds = rounds;
				history = new String[rounds];
			}
			
			// getRounds
			public int getRounds() { return rounds; }
				
			// setRound
			public void setRound(int i) {
				round = i + 1;
			}
			
			// getRound
			public int getRound() { return round; }
			
			// setRollNum
			public void setRollNum(int rollNum) {
				this.rollNum = rollNum;
			}
			
			// getRollNum
			public int getRollNum() { return rollNum; }
			
			// getDieValue
			public int getDieValue() {
				Random random = new Random();
				return random.nextInt(6) + 1;
			}
			
			// setR1 - sets die values for both die
			public void setR1() {
				r1d1 = getDieValue();
				r1d2 = getDieValue();
				r1Sum = r1d1 + r1d2;
			}
			
			// setR2
			public void setR2() {
				r2d1 = getDieValue();
				r2d2 = getDieValue();
				r2Sum = r2d1 + r2d2;
			}
			
			// prtRoll
			public void prtRoll(int rollNum) {
				System.out.println();
				if (rollNum == 1) {
					System.out.printf("Roll_%d was %d,%d for a sum of %d", rollNum, r1d1, r1d2, r1Sum);
				}
				else {
					System.out.printf("Roll_%d was %d,%d for a sum of %d", rollNum, r2d1, r2d2, r2Sum);
				}
				System.out.println();
			}
			
			// setBet
			public void setBet(String bet) {
				bet = bet.toUpperCase();
				this.bet = bet;
			}
			
			// setWager
			public void setWager(int wager) {
				this.wager = wager;
			}
			
			// evalBetO - First evaluation for a bet of OVER
			public void evalBetO() {
				if (bet.equals("O")) {
					if (r2Sum > r1Sum) {
						outcome = "OVER";
						eval = "W";
					}
					else {
						outcome = "UNDER";
						eval = "L";
					}
				}
			}
			
			// evalBetU - Second evaluation for a bet of UNDER
			public void evalBetU() {
				if (bet.equals("U")) {
					if (r2Sum < r1Sum) {
						outcome = "UNDER";
						eval = "W";
					}
					else {
						outcome = "OVER";
						eval = "L";
					}
				}
			}
			
			// evalBetDoubles - Third evaluation is for roll2 to be doubles.  This evaluation will override the over/under eval.
			public void evalBetDoubles() {
				if (r2d1 == r2d2) {
					outcome = "DOUBLE";
					eval = "L";
				}
			}
			
			// evalBetSame - Fourth evaluation is for SAME, roll2 == roll1.  This evaluation will override previous evals.
			public void evalBetSame() {
				if (r2Sum == r1Sum) {
					outcome = "SAME";
					eval = "L";
				}
			}
			
			// prtWL
			public void prtWL() {
				switch(eval) {
				
				case("W") : 
					System.out.println(outcome + "! Congratulations, you win!");
					points = points + wager;
					break;
					
				case("L") : 
					System.out.println(outcome + "! Sorry, you lose.");
					points = points - wager;
					break;
				
				default : 
					outcome = "X";
					eval = "X";
					System.out.println("default");
				}
			}
			
			// prtEndOfRound - End of round processing
			public void prtEndOfRound(int i) {
				System.out.println();
				System.out.println();
				System.out.printf("Round: %d- Roll_1: %d, Roll_2: %d, Bet: %s, Wager: %d, Outcome: %s, Points: %d", 
						round, r1Sum, r2Sum, bet, wager, outcome, points);
				System.out.println();
				System.out.println();
				
				// Set history array so a record of the rounds in the game can be printed at the end of the game
				history[i] = round + " " + r1Sum + " " + r2Sum + " " + bet + " " + wager + " " + outcome + " " + points;
			}
			
			// testBankrupt
			public void testBankrupt() {
				if (points <= 0) {
					System.out.println();
					System.out.println("************************************************************");
					System.out.println();
					System.out.println("You are bankrupt.  Game over.");
					System.out.println();
					System.exit(1);
				}
			}
			
			// prtEndOfGame
			public void prtEndOfGame(String name) {
				System.out.println("************************************************************");
				System.out.println();
				
				System.out.printf("Game over!  Final points were %d.  Thank you for playing, %s.", points, name);
				System.out.println();
				System.out.println();
				
				System.out.println("Here is your history of rounds for this game.");
				for (int i = 0; i < rounds; i++) {
					System.out.println(history[i]);
				}
				
			}
			
			
		}  // End of Game class

		// Session class begins here ******************************************************************************

		class Session {
			
			// Declare vars
			private String name;
			
			// Define methods
			// prtIntro
			public void prtIntro() {
				System.out.println();
				System.out.println();
				System.out.println("Welcome to 3312 Game of OVER UNDER DOUBLE SAME!");
				System.out.println("The game is played by rolling 2 dice twice.");
				System.out.println("Every player begins the game with 50 points.  The object of the game is to accumlate more points.");
				System.out.println();
				System.out.println("******* Game Instructions and Rules *******");
				System.out.println("When the first roll occurs, the Roll_1 dice values and sum are set.");
				System.out.println("After Roll_1, the player decides if the Roll_2 sum is likely to be over or under the Roll_1 sum.");
				System.out.println("The player makes a wager based on their decision of over or under.  Wagers can be from 0 to 10 points.");
				System.out.println("If the player bets OVER or UNDER correctly, the player wins the amount of the wager, ");
				System.out.println("and that value is added to their point total.");
				System.out.println("If the player bets OVER or UNDER incorrectly, the player loses points in the amount of the wager, ");
				System.out.println("and that value is subtracted from the player's point total.");
				System.out.println();
				System.out.println("*** Special Cases ***");
				System.out.println("If Roll_2 yields a double (e.g., 2,2 or 5,5), OR Roll_2 sum is the same as the Roll_1 sum, ");
				System.out.println("the round is lost and the amount of the wager is subtracted from the player's point total.");
				System.out.println("Note that the round is lost irrespective of the outcome of the over/under bet.");
				System.out.println();
				System.out.println("And that's the name of the game - OVER UNDER DOUBLE SAME!");
				System.out.println();
			}
			
			// setName
			public void setName(String name) {
				this.name = name;
				System.out.println("Now, let's get this wild and crazy game underway!");
				System.out.println();
				System.out.println();
			}
			
			// getName
			public String getName() { return name; }
			
			// prtOutro
			public void prtOutro() {
				System.out.println();
				System.out.println("Thank you for playing OVER UNDER DOUBLE SAME.");
				System.out.printf("Have a great day, %s!", name);
			}

} // End of Session class

