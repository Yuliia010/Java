package Chat;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static final Set<ClientService> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server running in port " + PORT);

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    broadcastMessage("Server: " + message, null);
                }
            }).start();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);
                ClientService client = new ClientService(socket);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message, ClientService sender) {
        for (ClientService client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientService client) {
        clients.remove(client);
    }
}
