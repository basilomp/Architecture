import config.Config;
import config.ConfigFactory;
import handlers.AnnotatedHandlerFactory;
import handlers.RequestHandler;
import serializers.ResponseSerializer;
import serializers.ResponseSerializerFactory;
import services.RequestParserFactory;
import services.SocketService;
import services.SocketServiceFactory;

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
                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();
                new Thread(new RequestHandler(
                        socketService,
                        RequestParserFactory.createRequestParser(),
                        AnnotatedHandlerFactory.create(socketService, responseSerializer, config)
                        )).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
