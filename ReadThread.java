import java.io.*;
import java.net.*;
//Read from Server 
public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private Client client;
    private GUI Interface;
    
    public ReadThread(Socket socket,Client client,GUI Interface){
        this.socket = socket;
        this.client = client;
        this.Interface = Interface;
        
        try{
            InputStream input = socket.getInputStream();
            reader= new BufferedReader(new InputStreamReader(input));
            
        }
        catch(IOException ex){
            System.out.println("Error getting input stream"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void run() {
        while(true){
            try {
                
                /*if(client.getUserName() != null){
                    System.out.println(client.getUserName()+"> ");
                }*/

                //System.out.print("\b\b\b\b\b");
                String response = reader.readLine();
                System.out.println( response);
                this.Interface.push(response);
                
            } catch (IOException ex) {
                System.out.println("Error getting from server"+ ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }

}