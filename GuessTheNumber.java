import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;//number of attempts per round.
        int numberOfRounds = 3;
        int score = 0;//initial score is 0.

        for (int round = 1; round <= numberOfRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Round " + round + ":");
            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Higher!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Lower!");
                } else {
                    System.out.println("Congratulations! You guessed the number.");
                    hasGuessedCorrectly = true;
                    score += (maxAttempts - attempts + 1);  // More points for fewer attempts
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The number was: " + numberToGuess);
            }
        }

        System.out.println("Game Over. Your score is: " + score);
        scanner.close();
    }
}
