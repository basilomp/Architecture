package assignment_1;

import assignment_1.handlers.RequestHandler;
import assignment_1.messages.Messager;
import assignment_1.messages.MessagerImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {
    private static final Messager messager = MessagerImpl.create();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            messager.print("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                messager.print("New client connected!");

//                new Thread(() -> handleRequest(socket)).start();
                new Thread(new RequestHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

