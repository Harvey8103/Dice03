/**
 * V.0.1.0 - Finished the basic version of the game, where the user can guess the dice rolls and receive feedback on their guesses.
 * Published Date: 2026-02-11
 * To do: GUI can be added to make the game more interactive and visually appealing. Additionally, a scoring system can be implemented to track the user's performance over multiple rounds of the game.
 * Line 90 and 133 are the return counters for the number of attempts it took for the user to guess correctly, or -1 if they failed to guess within 10 attempts. These can be used to implement a scoring system.
*/

import java.util.Random;
import java.util.Scanner;

public class Dice {
    // This array will hold the values of the rolled dice
    private int[] answer = new int[4];
    // Random object to generate random numbers for the dice rolls
    private Random random = new Random();
    // This variable will track whether the user's guesses are correct
    private static boolean correct = false;
    
    //roll the dices
    private void rollDices() {
        // Generate random numbers between 1 and 6 for each of the four dice
        answer[0] = random.nextInt(6) + 1;
        answer[1] = random.nextInt(6) + 1;
        answer[2] = random.nextInt(6) + 1;
        answer[3] = random.nextInt(6) + 1;
    }

    // This method checks if a given string can be parsed as an integer
    private boolean numberCheck(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // The main method where the game logic is implemented
    public void main(String[] args) {
        Dice game = new Dice();
        game.rollDices();
        Scanner scanner = new Scanner(System.in);
        
        // This counter will track the number of attempts the user has made to guess the dice rolls
        int counter = 0;
        
        // This array will hold the user's guesses for the dice rolls
        int[] userInput = new int[4]; // This array will hold the user's guesses for the dice rolls
        
        // This array will hold the user's guesses as strings to check for valid input
        String [] userInputStr = new String[4]; // This array will hold the user's guesses as strings to check for valid input
        
        // The main game loop continues until the user guesses correctly or exhausts 10 attempts
        while (!correct && counter < 10) {
            // Increment the attempt counter and prompt the user for their guesses
            counter++;
            System.out.println("Attempt " + counter + ": Please enter your guesses for the four dice rolls (separated by spaces):");
            
            // Read user input
            userInputStr[0] = scanner.next();
            userInputStr[1] = scanner.next();
            userInputStr[2] = scanner.next();
            userInputStr[3] = scanner.next();
            
            // Validate user input and convert to integers
            for (int i = 0; i < 4; i++) {
                // Check if the input is a valid number
                if (game.numberCheck(userInputStr[i])) {
                    userInput[i] = Integer.parseInt(userInputStr[i]);
                } else {
                    System.out.println("Dice " + i + " " + userInput[i] + " is a valid number");   
                    while (!game.numberCheck(userInputStr[i])) {
                        System.out.println("Please input a valid number between 1 and 6 for dice " + (i + 1) + ". You entered: " + userInputStr[i]);
                        userInputStr[i] = scanner.next();
                    }
                    userInput[i] = Integer.parseInt(userInputStr[i]);
                }

                // Check if the input number is between 1 and 6
                while (userInput[i] > 6 || userInput[i] < 1) {
                    System.out.println("Please input a valid number between 1 and 6 for dice " + (i + 1) + ". You entered: " + userInputStr[i]);
                    
                    userInput[i] = scanner.nextInt();
                }
            }
            
            // Check if the user's guesses are correct
            if (userInput[0] == game.answer[0] && userInput[1] == game.answer[1] && userInput[2] == game.answer[2] && userInput[3] == game.answer[3]) {
                correct = true;
//                return counter;
                System.out.println("Congratulations! You guessed correctly!");
            }else {
                int correctPositionCounter = 0;
                int wrongPositionCounter = 0;

                boolean[] answerUsed = new boolean[4];
                boolean[] inputUsed = new boolean[4];

                // First pass to count correct numbers in the correct position
                for (int i = 0; i < 4; i++) {
                    if (userInput[i] == game.answer[i]) {
                        correctPositionCounter++;
                        answerUsed[i] = true;
                        inputUsed[i] = true;
                    }
                }

                // Second pass to count correct numbers in the wrong position
                for (int i = 0; i < 4; i++) {
                    if (!inputUsed[i]) {
                        for (int j = 0; j < 4; j++) {
                            if (!answerUsed[j] && userInput[i] == game.answer[j]) {
                                wrongPositionCounter++;
                                answerUsed[j] = true;
                                break;
                            }
                        }
                    }
                }
                // Provide feedback to the user about their guess
                System.out.println(wrongPositionCounter + " Dice Correct number but wrong position");
                System.out.println(correctPositionCounter + " Dice Correct number and correct position");   
                System.out.println("Incorrect guess. Try again.");
            }
        }

        // If the user fails to guess correctly within 10 attempts, reveal the correct answers
        System.out.println("Dice 1: " + game.answer[0]);
        System.out.println("Dice 2: " + game.answer[1]);    
        System.out.println("Dice 3: " + game.answer[2]);
        System.out.println("Dice 4: " + game.answer[3]);
        // Set counter to -1 to indicate that the user failed to guess correctly within 10 attempts
//        return -1;
    }
}
