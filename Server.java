import java.io.*;
import java.net.*;
import java.time.*;

public class Server {
    public static void main(String[] args) throws  Exception {
        ServerSocket server = new ServerSocket(7878);
        System.out.println("Server started...");

        Socket socket = server.accept();
        System.out.println("Client connected: " + socket.getInetAddress());

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String msg;
        while (true) { 
            msg = in.readUTF();
            System.out.println("Client: " + msg);

            msg = msg.toLowerCase();
            if(msg.equals("hi") || msg.equals("hello")) {
                out.writeUTF("Hello!");
            }
            else if(msg.equals("time")) {
                out.writeUTF(LocalTime.now().toString());
            }
            else if(msg.equals("data")) {
                out.writeUTF(LocalDate.now().toString());
            }
            else if(msg.equals("hostname")) {
                out.writeUTF(InetAddress.getLocalHost().getHostName());
            }
            else if(msg.equals("ip")) {
                out.writeUTF(InetAddress.getLocalHost().getHostAddress());
            }
            else if(msg.equals("quit")) {
                out.writeUTF("Bye!");
                break;
            }
            else if(msg.equals("datetime")) {
                out.writeUTF(LocalDateTime.now().toString());
            }
            else {
                out.writeUTF("unknown command");
            }
        }

        socket.close();
        server.close();
    }
}