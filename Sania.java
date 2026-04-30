
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.*;
import java.net.NetworkInterface;

public class Sania{
  public static DataOutputStream dos;
  public static BufferedReader br;
  public static void main(String argv[])throws Exception{
      String user="s2112576115@ru.ac.bd";
      String pass="qowjgdpakbgncfic";
      String username=new String(Base64.getEncoder().encode(user.getBytes()));
      String password=new String(Base64.getEncoder().encode(pass.getBytes()));
      SSLSocket s=(SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com",465);
      dos=new DataOutputStream(s.getOutputStream());
      br=new BufferedReader(new InputStreamReader(s.getInputStream()));
      InetAddress ip = InetAddress.getLocalHost();
      String ipAddress = ip.getHostAddress();
      String systemTime = LocalDateTime.now().toString();
      NetworkInterface network = NetworkInterface.getByInetAddress(ip);
      byte[] macBytes = network.getHardwareAddress();
      StringBuilder mac = new StringBuilder();
      if (macBytes != null) {
          for (int i = 0; i < macBytes.length; i++) {
              mac.append(String.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : ""));
          }
      }
      String macAddress = mac.toString();

      send("EHLO smtp.gmail.com\r\n");
      for(int i=0;i<9;i++){
        System.out.println("Server: "+br.readLine());
      }
      send("AUTH LOGIN\r\n");
      System.out.println("Server: "+br.readLine());
      send(username+"\r\n");
      System.out.println("Server: "+br.readLine());
      send(password+"\r\n");
      System.out.println("Server: "+br.readLine());
      send("MAIL FROM:<s2112576115@ru.ac.bd>\r\n");
      System.out.println("Server: "+br.readLine());
      send("RCPT TO:<s2111076101@ru.ac.bd>\r\n");
      System.out.println("Server: "+br.readLine());
      send("DATA\r\n");
      System.out.println("Server: "+br.readLine());
      send("FROM:s2112576115@ru.ac.bd\r\n");
      send("TO:s2111076101@ru.ac.bd\r\n");
      send("Subject:mon valo nai\r\n");
      send("\r\n");
      send("Machine IP Address: " + ipAddress + "\r\n");
      send("MAC Address: " + macAddress + "\r\n");
      send("System Time: " + systemTime + "\r\n");
      send("\r\n.\r\n");
      System.out.println("Server: "+br.readLine());
      send("QUIT\r\n");
      System.out.println("Server: "+br.readLine());

  }
public static void send(String s)throws Exception{
      dos.writeBytes(s);
      Thread.sleep(1000);
    System.out.println("Client : "+s);
}



}