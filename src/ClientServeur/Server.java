package ClientServeur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)throws IOException{
        ServerSocket s_server= new ServerSocket(1234);
        System.out.println("attend le clien");
        Socket s = s_server.accept();
        System.out.println("un clien a connecte");
        InputStream input = s.getInputStream();
        OutputStream output = s.getOutputStream();

        int nb = input.read();
        int res = nb*3;

        System.out.println("resulet est nb:" +nb);
        output.write(res);
        System.out.println("envoye la resulet");
        s_server.close();


    }


}
