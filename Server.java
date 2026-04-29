
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(7878);
        System.out.println("Server Started !");

        Socket socket = server.accept();
        System.out.println("Client Connected :" + socket.getInetAddress());

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out  =new DataOutputStream((socket.getOutputStream()));

        String msg ;
        while (true) { 
            msg = in.readUTF();
            System.out.println("Client :" + msg);

            msg = msg.toLowerCase();

            if(msg.equals("hi") || msg.equals("hello") ) {
                out.writeUTF("Hello !");
            }
            else if(msg.equals("time")) {
                out.writeUTF(LocalTime.now().toString());
            }
            else if(msg.equals("date")) {
                out.writeUTF(LocalDate.now().toString());
            }
            else if(msg.equals("date time")) {
                out.writeUTF(LocalDateTime.now().toString());
            }
            else if(msg.equals("hostname")){
                out.writeUTF(InetAddress.getLocalHost().getHostName());
            }
             else if(msg.equals("ip")){
                out.writeUTF(InetAddress.getLocalHost().getHostAddress());
            }
             else if (msg.equalsIgnoreCase("quit")) {
                out.writeUTF("Quitting...");
                break;
            } 
            else {
                out.writeUTF("Unknown Command");
            }
        }
        socket.close();
        server.close();

    }
    
}
