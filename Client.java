import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main (String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7878);
        // Socket s = new Socket("192.168.1.5", 7878);
        System.out.println("Client connected");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        while (true) { 
            System.out.print("You: ");
            String msg = sc.nextLine().trim();

            if(msg.isEmpty()) continue;

            out.writeUTF(msg);
            System.out.println("Server: " + in.readUTF());

            if(msg.equalsIgnoreCase("quit")) {
                break;
            }
        }

        sc.close();
        socket.close();

    }
}





































// import java.io.*;
// import java.net.*;
// import java.util.*;

// public class Client {
//     public static void main(String [] args) throws Exception {
//         Socket s = new Socket("localhost", 7878);
//         System.out.println("Server connected.");

//         DataInputStream in = new DataInputStream(s.getInputStream());
//         DataOutputStream out = new DataOutputStream(s.getOutputStream());
//         Scanner sc = new Scanner(System.in);

//         while (true) { 
//             System.out.print("You: ");
//             String msg = sc.nextLine();

//             out.writeUTF(msg);
//             System.out.println("Server: " + in.readUTF());

//             if(msg.equalsIgnoreCase("quit")) break;
//         }

//         s.close();
//         sc.close();
//     }
// }


// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.net.*;
// import java.util.Scanner;

// public class Client {
//     public static void main(String[] args) throws Exception {
//         Socket s = new Socket("localhost", 7878);
//         DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//         DataInputStream din = new DataInputStream(s.getInputStream());

//         Scanner scan = new Scanner(System.in);
//         String msg;
//         do {
//             String str = "Client: ";
//             System.out.print(str);
//             msg = scan.next();
//             dout.writeUTF(msg);
//             String server_response_string = (String) din.readUTF();
//             System.out.println("Server: " + server_response_string);
//         } while (!msg.toLowerCase().equals("quit"));

//         scan.close();
//         dout.close();
//         s.close();
//     }
// }




// import java.io.*;
// import java.net.*;
// import java.time.*;
// import java.time.format.*;

// public class Server {
//     public static void main(String[] args) throws Exception {

//         ServerSocket server = new ServerSocket(7878);
//         System.out.println("Server started...");

//         Socket socket = server.accept();
//         System.out.println("Client connected: " + socket.getInetAddress());

//         DataInputStream in = new DataInputStream(socket.getInputStream());
//         DataOutputStream out = new DataOutputStream(socket.getOutputStream());

//         String msg;

//         while (true) {
//             msg = in.readUTF();
//             System.out.println("Client: " + msg);

//             msg = msg.toLowerCase();

//             if (msg.equals("hi") || msg.equals("hello")) {
//                 out.writeUTF("Hello!");

//             } else if (msg.equals("time")) {
//                 out.writeUTF(LocalTime.now()
//                         .format(DateTimeFormatter.ofPattern("HH:mm:ss")));

//             } else if (msg.equals("date")) {
//                 out.writeUTF(LocalDate.now()
//                         .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

//             } else if (msg.equals("ip")) {
//                 out.writeUTF(InetAddress.getLocalHost().getHostAddress());

//             } else if (msg.equals("hostname")) {
//                 out.writeUTF(InetAddress.getLocalHost().getHostName());

//             } else if (msg.equals("os")) {
//                 out.writeUTF(System.getProperty("os.name"));

//             } else if (msg.equals("java")) {
//                 out.writeUTF(System.getProperty("java.version"));

//             } else if (msg.equals("help")) {
//                 out.writeUTF("hi, time, date, ip, hostname, os, java, help, quit");

//             } else if (msg.equals("quit")) {
//                 out.writeUTF("Bye!");
//                 break;

//             } else {
//                 out.writeUTF("Unknown command");
//             }
//         }

//         socket.close();
//         server.close();
//     }
// }
