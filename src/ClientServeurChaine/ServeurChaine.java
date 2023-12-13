package ClientServeurChaine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurChaine {
    public static void main(String[] args) throws IOException {
        ServerSocket s_server = new ServerSocket(9999);
        Socket s = s_server.accept();

        InputStream in = s.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inr);

        OutputStream outputStream = s.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream, true);


        System.out.println(" Npm et Prenom");

        String ch = br.readLine();
        System.out.printf("Message : " + ch);

        String rep = " Bien recu votre message est : " + ch;

        pw.println(rep);
        System.out.println("Fermeture de la socket ");
        s.close();
        s_server.close();

    }
}
