package ScoketsWIthThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;


public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             Scanner socketInput = new Scanner(socket.getInputStream());
             Scanner userInput = new Scanner(System.in);
             PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to server at " + SERVER_HOST + ":" + SERVER_PORT);

            while (true) {
                if (socketInput.hasNextLine()) {
                    String serverMessage = socketInput.nextLine();
                    System.out.println(serverMessage);

                    if (serverMessage.contains("!!!Game Finished!!!.") || serverMessage.contains("Félicitation")) {
                        break;
                    }
                }

                if (userInput.hasNextLine()) {
                    String userGuess = userInput.nextLine();
                    socketOutput.println(userGuess);
                }
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}