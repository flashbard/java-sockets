import java.net.*;
import java.io.*;

public class EchoServer{
    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try(
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            ){
            String inputLine;
            while((inputLine = in.readLine()) != null){
                out.println(inputLine);
                System.out.println("Received: " + inputLine);
            }
        } catch(IOException e){
            System.out.println("Error while trying to listen: " + e.getMessage());
        }
    }
}