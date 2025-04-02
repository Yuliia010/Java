package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientService implements Runnable
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientService(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);


            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("The client says: " + message);
                ChatServer.broadcastMessage("Client: " + message, this);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatServer.removeClient(this);
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
