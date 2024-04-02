import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        int portNumber = 12345;
        String serverAddress = "localhost";

        try(Socket socket = new Socket(serverAddress,portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ) {
           String userInputLine;
           while((userInputLine = userInput.readLine()) != null){
               out.println(userInputLine);
               System.out.println("Server response: "+in.readLine());
           }
        } catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
