package bg.sofia.uni.fmi.mjt.poll.server;

import bg.sofia.uni.fmi.mjt.poll.server.command.CommandExecutor;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.JsonParser;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.OutputParser;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class PollServer {

    private static final int BUFFER_SIZE = 1024;
    private static final String SERVER_HOST = "localhost";

    private final int port;
    private PollRepository pollRepository;
    private OutputParser outputParser;
    private CommandExecutor commandExecutor;

    private boolean isRunning;
    private ByteBuffer buffer;
    private Selector selector;

    public PollServer(int port, PollRepository pollRepository) {
        this.port = port;
        this.pollRepository = pollRepository;
        this.outputParser = new JsonParser();
        this.commandExecutor = new CommandExecutor(pollRepository, this.outputParser);
    }

    public void start() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            selector = Selector.open();
            configureServerSocketChannel(serverSocketChannel, selector);
            this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
            this.isRunning = true;

            while (isRunning) {
                try {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) {
                        continue;
                    }

                    iterateOverSelectedKeys();
                } catch (IOException e) {
                    System.out.println("An error occurred while processing client request: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while starting the server: " + e.getMessage());
        }
    }

    public void stop() {
        this.isRunning = false;
        if (this.selector.isOpen()) {
            this.selector.wakeup();
        }
    }

    private void iterateOverSelectedKeys() throws IOException {
        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isReadable()) {
                SocketChannel clientChannel = (SocketChannel) key.channel();
                String clientInput = getClientInput(clientChannel);
                if (clientInput == null) {
                    continue;
                }

                String response = commandExecutor.executeCommand(clientInput);
                writeClientOutput(clientChannel, response);
            } else if (key.isAcceptable()) {
                accept(selector, key);
            }

            keyIterator.remove();
        }
    }

    private void configureServerSocketChannel(ServerSocketChannel channel, Selector selector) throws IOException {
        channel.bind(new InetSocketAddress(SERVER_HOST, this.port));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private String getClientInput(SocketChannel clientChannel) throws IOException {
        buffer.clear();

        int readBytes = clientChannel.read(buffer);
        if (readBytes < 0) {
            clientChannel.close();
            return null;
        }

        buffer.flip();

        byte[] clientInputBytes = new byte[buffer.remaining()];
        buffer.get(clientInputBytes);

        return new String(clientInputBytes, StandardCharsets.UTF_8);
    }

    private void writeClientOutput(SocketChannel clientChannel, String output) throws IOException {
        buffer.clear();
        buffer.put(output.getBytes());
        buffer.flip();

        clientChannel.write(buffer);
    }

    private void accept(Selector selector, SelectionKey key) throws IOException {
        ServerSocketChannel sockChannel = (ServerSocketChannel) key.channel();
        SocketChannel accept = sockChannel.accept();

        accept.configureBlocking(false);
        accept.register(selector, SelectionKey.OP_READ);
    }
    
}
