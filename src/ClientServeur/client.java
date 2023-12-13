package ClientServeur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        Socket s_client = new Socket("127.0.0.1", 1234);
        InputStream input = s_client.getInputStream();
        OutputStream output = s_client.getOutputStream();

        //lire lq valeur q enveoyer ay serveru

        System.out.println("Donner une valeur");

        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();

        output.write(nb);
        System.out.println("Valuer envoyer au serveur");

        int result = input.read();
        System.out.println("le resultat du traitement est :" + result);
        sc.close();
        s_client.close();
    }
}
