package main.java;

import main.java.config.Config;
import main.java.config.ConfigFactory;
import main.java.serializers.ResponseSerializerFactory;
import main.java.services.RequestParser;
import main.java.services.RequestParserFactory;
import main.java.services.SocketServiceFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);

        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            System.out.printf("Server started at port: %d", config.getPort());

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new Thread(new RequestHandler(
                        SocketServiceFactory.createSocketService(socket),
                        RequestParserFactory.createRequestParser(),
                        ResponseSerializerFactory.createResponseSerializer(),
                        config)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
