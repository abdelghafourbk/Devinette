package ClientServeurChaine;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChaine {
    public static void main(String[] args) throws IOException {

        Socket s_client = new Socket("127.0.0.1", 9999);
        OutputStream out = s_client.getOutputStream();
        PrintWriter pw = new PrintWriter(out, true);
        Scanner sc = new Scanner(System.in);
        System.out.println("Donner votre nom et prenom");
        String msg = sc.next();
        pw.println(msg);

        InputStream in = s_client.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inr);
        String rep = br.readLine();
        System.out.println("Message du serveur est : \n " + rep);
        s_client.close();
        sc.close();


    }
}
