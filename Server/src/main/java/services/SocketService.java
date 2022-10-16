package main.java.services;

import main.java.logger.ConsoleLogger;
import main.java.logger.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;

public class SocketService implements Closeable {

    private static final Logger logger = new ConsoleLogger();

    private final Socket socket;

    private SocketService(Socket socket) {
        this.socket = socket;
    }

    public static SocketService createSocketService(Socket socket) {
        return new SocketService(socket);
    }

    public Deque<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready());

            Deque<String> request = new ArrayDeque<>();
//            Deque<String> request = new ArrayList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeResponse(String headers, Reader reader) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(headers);
            if (reader != null) {
                reader.transferTo(output);
            }
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}

