package bg.sofia.uni.fmi.mjt.poll.client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    private static final int SERVER_PORT = 7777;
    private static final String DISCONNECT_COMMAND = "disconnect";

    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open();
             BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, "UTF-8"));
             PrintWriter writer = new PrintWriter(Channels.newWriter(socketChannel, "UTF-8"), true);
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(new InetSocketAddress("localhost", SERVER_PORT));
            System.out.println("Connected to the server.");

            while (true) {
                System.out.println("Enter command: ");
                String input = scanner.nextLine();

                if (input.equals(DISCONNECT_COMMAND)) {
                    break;
                }

                System.out.println("Sending command <" + input + "> to the server...");
                writer.println(input);

                String response = reader.readLine();
                System.out.println("Server response: " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
