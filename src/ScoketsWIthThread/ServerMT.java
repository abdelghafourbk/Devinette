package ScoketsWIthThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMT {
    private static final int PORT = 5000;
    private static final int MAX_CLIENTS = 50;
    private ExecutorService clientPool = Executors.newFixedThreadPool(MAX_CLIENTS);

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected succesfully!");

                    Repartitor clientHandler = new Repartitor(clientSocket);
                    clientPool.submit(clientHandler);
                } catch (IOException e) {
                    System.err.println("Error accepting client connection, retry again: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        } finally {
            clientPool.shutdown();
        }
    }

    public static void main(String[] args) {
        new ServerMT().startServer();
    }
}