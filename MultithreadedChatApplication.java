import java.io.*;
import java.net.*;
import java.util.*;

public class MultithreadedChatApplication {

    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected.");

                ClientHandler handler = new ClientHandler(socket);
                clientHandlers.add(handler);
                handler.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void broadcastMessage(String message, ClientHandler excludeUser) {
        for (ClientHandler handler : clientHandlers) {
            if (handler != excludeUser) {
                handler.sendMessage(message);
            }
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);

                writer.println("Enter your name:");
                String name = reader.readLine();
                System.out.println(name + " joined the chat.");
                broadcastMessage(name + " has joined the chat.", this);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(name + ": " + message);
                    broadcastMessage(name + ": " + message, this);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                clientHandlers.remove(this);
                System.out.println("A user has left the chat.");
            }
        }

        void sendMessage(String message) {
            writer.println(message);
        }
    }
}
