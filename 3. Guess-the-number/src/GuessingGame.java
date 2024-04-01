import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private static final Random RANDOM = new Random();
    private static final List<Integer> GUESSES = new ArrayList<>();
    private static final int DELAY_MS = 1000; // Delay time in milliseconds


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            playAgain(scanner);
            System.out.print("Do you want to play again? (yes/no): ");
            String choice = scanner.next();
            playAgain = choice.equalsIgnoreCase("yes");
        }
        System.out.println("Thank you for playing!");
    }

    private static void playAgain(Scanner scanner) throws InterruptedException {
        System.out.println("Welcome to the Number Guessing Game!");
        Thread.sleep(DELAY_MS);
        System.out.print("Choose difficulty level (easy/medium/hard): ");
        Thread.sleep(DELAY_MS);

        String difficulty = scanner.next().toLowerCase();
        int maxGuesses;
        int maxNumber;

        switch (difficulty) {
            case "easy" -> {
                maxGuesses = 10;
                maxNumber = 50;
            }
            case "medium" -> {
                maxGuesses = 7;
                maxNumber = 100;
            }
            case "hard" -> {
                maxGuesses = 5;
                maxNumber = 150;
            }
            default -> {
                maxGuesses = 10;
                maxNumber = 100;
            }
        }

        int number = RANDOM.nextInt(maxNumber) + 1;
        int guessCount = 0;
        GUESSES.clear();

        while (guessCount < maxGuesses) {
            System.out.println("Guesses left: " + (maxGuesses - guessCount));
            Thread.sleep(DELAY_MS);

            System.out.print("Enter your guess (between 1 and  " + maxNumber + "): ");
//            Thread.sleep(DELAY_MS);

            if (scanner.hasNextInt()) {
                int guess = scanner.nextInt();
                if (guess < 1 || guess > maxNumber) {
                    System.out.println("Please enter a number between 1 and " + maxNumber + ".");
                    Thread.sleep(DELAY_MS);
                    continue;
                }
                GUESSES.add(guess);
                guessCount++;

                if (guess == number) {
                    System.out.println("Congrats! You guessed the number!");
                    System.out.println("Your guesses: " + GUESSES);
                    Thread.sleep(DELAY_MS);
                    return;
                } else if (guess < number) {
                    System.out.println("Too low. Try again.");
                    Thread.sleep(DELAY_MS);
                } else {
                    System.out.println("Too high. Try again");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                Thread.sleep(DELAY_MS);
                scanner.next();
            }
        }
        System.out.println("Sorry, you've run out of guesses. The number was: " + number);
        System.out.println("Your guesses: " + GUESSES);
        Thread.sleep(DELAY_MS);
        System.exit(0);
    }
}
