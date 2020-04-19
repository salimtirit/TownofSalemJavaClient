import java.io.*;
import java.net.*;
 // Write to Server
public class WriteThread  extends Thread{
    private PrintWriter writer;
    private Socket socket;
    private Client client;
    private GUI Interface;  
    public WriteThread(Socket socket,Client client,GUI Interface){
        this.socket = socket;
        this.client = client;
        this.Interface = Interface;
        try{
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        }
        catch(IOException ex){
            System.out.println("Error getting OutputStream or PrintWriter" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void run(){
        Console console = System.console();
        String userName = console.readLine("\nEnter your name ");
        client.setUserName(userName);
        writer.println(userName);
        String text;
        do {
            text = console.readLine();//Bunu sildim userName+"> "
            
            //writer.println(text);
        } while (!text.equals("bye"));
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error writing to server " + ex.getMessage());
        }
    }
    public  void SendToServer(String text){
        this.writer.println(text);
    }

}