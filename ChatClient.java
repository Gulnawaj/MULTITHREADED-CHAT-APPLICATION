import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to the chat server.");

            new ReadThread(socket).start();
            new WriteThread(socket).start();

        } catch (IOException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        }
    }
}

class ReadThread extends Thread {
    private BufferedReader reader;

    public ReadThread(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                if (response == null) break;
                System.out.println(response);
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}

class WriteThread extends Thread {
    private PrintWriter writer;
    private Scanner scanner;

    public WriteThread(Socket socket) {
        scanner = new Scanner(System.in);
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            String text = scanner.nextLine();
            writer.println(text);
            if (text.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
