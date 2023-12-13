package ScoketsWIthThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Repartitor implements Runnable {
    private final Socket socket;
    private final int secretNumber;
    private boolean gameActive;

    public Repartitor(Socket socket) {
        this.socket = socket;
        this.secretNumber = new Random().nextInt(100) + 1;
        this.gameActive = true;
    }

    @Override
    public void run() {
        try (Scanner input = new Scanner(socket.getInputStream());
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            output.println("Welcome to devinette!");
            output.println("Guess the secret number between 1 and 100:");

            while (gameActive && input.hasNextLine()) {
                int guess;
                try {
                    guess = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    output.println("Invalid input. Please enter a valid number!");
                    continue;
                }

                if (guess == secretNumber) {
                    String clientIPAddress = socket.getInetAddress().getHostAddress();

                    output.println("Félicitation vous êtes le gagnant, la valeur secrète est..." + secretNumber + " and the Winner's IP Address: " + clientIPAddress);
                   
                    output.println("!!!Game Finished!!!.");
                    break;
                } else {
                    output.println(guess < secretNumber ? "Try something higher than the number you entered:" : "Try something lower than the number you entered:");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error finishing client's socket: " + e.getMessage());
            }
        }
    }
}