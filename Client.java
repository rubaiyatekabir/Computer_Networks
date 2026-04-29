
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost",7878);
        System.out.println("Server Connected !");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        while (true) { 
            System.out.println("Client : ");
            String msg = sc.nextLine().trim();

            if(msg.isEmpty()) continue;;

            out.writeUTF(msg);

            System.out.println("Server " + in.readUTF());

            if(msg.equalsIgnoreCase("quit")) {
                break;
            }
        }
        sc.close();
        socket.close();
    }
}
