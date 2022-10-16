package main.java;

import main.java.config.Config;
import main.java.config.ConfigFactory;
import main.java.handlers.RequestHandler;
import main.java.parsers.RequestParser;
import main.java.services.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);

        try(ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            System.out.printf("Server started at port: %d", config.getPort());
            RequestParser requestParser = new RequestParser();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new Thread(new RequestHandler(SocketService.createSocketService(socket), requestParser, config)).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static final Logger logger = new ConsoleLogger();
//
//    public static void main(String[] args) {
//        try (ServerSocket serverSocket = new ServerSocket(8088)) {
//            logger.info("Server started!");
//            while (true) {
//                Socket socket = serverSocket.accept();
//                logger.info("New client connected");
//                new Thread(new RequestHandler(new SocketService(socket))).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
