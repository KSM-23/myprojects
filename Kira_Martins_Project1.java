package project0;

import java.util.Random;
import java.util.Scanner;

public class Kira_Martins_Project1 {

	public static void main(String[] args) {
		/*
		Declare variables.
	Create objects for Scanner (input) and Random (random) classes.
	Prompt for and read-in user’s name and chosen operation using Scanner.
	Print the welcome message and rules of the game.
	Prompt for player to roll the dice using Scanner by entering any character.
	Generate the dice values using Random.
	Display the dice values in sequence from low to high.
	Calculate and display the dice sum, the bonus points, and the total points.
	Print a message, thanking the user by name, for playing the Dice Game.
		 */
		
		
		System.out.print("Enter your name: ");
		
		// Create scanner
		Scanner input = new Scanner(System.in); 
		String name = input.next(); 
	
		System.out.println("                 Hello, " + name + "." + 
				                           " Welcome to the Dice Game! Playing is a breeze, for the rules are quite simple. "
				                           + "All you have to do is roll the die and your computer does the rest. If you roll a 7 or 11 you'll get 4"
				                           + "bonus points. If you roll a pair/double you'll get 5 bonus points. The sum of the dice are also worth points."
				                           + "That is all! Now let us begin - enter any character key to start.");
		
		System.out.print("Enter any character:");
		
		// Create Scanner
		Scanner character = new Scanner(System.in);
		String letter = character.next();
		
		System.out.println();
		
		
		Random random = new Random();  // Our vars for Die1 and Die2
		int die1 = random.nextInt(6) + 1;
		int die2 = random.nextInt(6) + 1;
		int tempDie = random.nextInt(6) + 1; 
		
		
		Random ran = new Random();
		Object number = ran.nextInt(6) + 1; 
		
		System.out.print("die1: ");
		System.out.println(die1 = die2);
		System.out.print("die2: "); 
		System.out.println(die2 = tempDie);
		
		
		
		int sum = die1 + die2; // This var "sum" calculates the Die1 plus Die2
		System.out.print("sum: ");
		System.out.println(die1 + " + " + die2  +" = " + sum);
		
		
		 // Number of points awarded if a pair/double is "rolled"
		int pairBonus;
		if (die1 == die2) {
			pairBonus = 5;
			System.out.println("pairBonus: 5");
		}else {
			pairBonus = 0;
			System.out.println("pairBonus: 0");
		}
		
		// Number of points awarded if a 7 or 11 is "rolled" from Die1 and Die2
		int seven11Bonus;
		if(sum == 7 || sum == 11) {
			seven11Bonus = 4;
			System.out.println("seven11Bonus: 4");
		}else {
			seven11Bonus = 0;
			System.out.println("sevenllBonus: 0");
		}
		
		int total = sum + pairBonus + seven11Bonus;  // Total points at the end
		System.out.print("total: ");
		System.out.println(sum + " + " + pairBonus + " + " + seven11Bonus +" = " + total);
		
		System.out.println();
		
		String goodbye = input.nextLine();   // Goodbye/ending message
		System.out.print("Thank you, " + name + " for playing the Dice Game. Hope you enjoyed it, come back and play again okay?!");
		
	
	}
	}
		

	

	



