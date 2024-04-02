import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("ALL")
public class MultiClientServer {
    public static void main(String[] args) {
        int portNumber = 12345;
        try(ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is running on port "+portNumber);
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: "+clientSocket);

                // handling client request in a separate thread
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        }catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}

class ClientHandler implements Runnable{
    private final Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try(PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            String inputLine;
            while((inputLine = in.readLine()) != null){
                System.out.println("Received from client: "+inputLine);

                // process client request
                String response = "Server response: "+inputLine.toUpperCase();

                // sending response to client
                out.println(response);
            }
        } catch(IOException e){
            System.err.println("Error handling client: "+e.getMessage());
        }
    }
}
