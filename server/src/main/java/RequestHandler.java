package main.java;

import main.java.config.Config;
import main.java.domain.HttpRequest;
import main.java.domain.HttpResponse;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;

public class RequestHandler implements Runnable {
    private final SocketService socketService;
    private final RequestParser requestParser;
    private final Config config;

    public RequestHandler(SocketService socketService, RequestParser requestParser, Config config) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.config = config;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parseRequest(rawRequest);
        ResponseSerializer responseSerializer = new ResponseSerializerImpl();
        HttpResponse httpResponse;

        StringBuilder response = new StringBuilder();
        if (httpRequest.getMethod().equals("GET")) {
            Path path = Paths.get(config.getWwwHome(), httpRequest.getPath());

            if (!Files.exists(path)) {
                //TODO use implementation of ResponseSerializer
                httpResponse = new HttpResponse("HTTP/1.1 404 NOT FOUND", "Content-Type: text/html; charset=utf-8", "<h1>File not found</h1>");
//                response.append("HTTP/1.1 404 NOT FOUND\n");
//                response.append("Content-Type: text/html; charset=utf-8\n");
//                response.append("\n");
                socketService.writeResponse(responseSerializer.serialize(httpResponse),
                        new StringReader(httpResponse.getBody()));
//                        socketService.writeResponse(responseSerializer.serialize(httpResponse),
//                        new StringReader("<h1>File not found</h1>\n"));
                return;
            }
            //TODO use implementation of ResponseSerializer
            httpResponse = new HttpResponse("HTTP/1.1 200 OK", "Content-Type: text/html; charset=utf-8", null);
//            response.append("HTTP/1.1 200 OK\n");
//            response.append("Content-Type: text/html; charset=utf-8\n");
//            response.append("\n");
//            try {
//                Files.readAllLines(path).forEach(response::append);
//            } catch (IOException e) {
//                throw new IllegalStateException(e);
//            }
            try {
                socketService.writeResponse(responseSerializer.serialize(httpResponse), Files.newBufferedReader(path));
//                socketService.writeResponse(response.toString(), Files.newBufferedReader(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //TODO use implementation of ResponseSerializer
            httpResponse = new HttpResponse("HTTP/1.1 405 METHOD_NOT_ALLOWED", "Content-Type: text/html; charset=utf-8", "<h1>Method not allowed</h1>\n");
//            response.append("HTTP/1.1 405 METHOD_NOT_ALLOWED\n");
//            response.append("Content-Type: text/html; charset=utf-8\n");
//            response.append("\n");
//            socketService.writeResponse(response.toString(), new StringReader("<h1>Method not allowed</h1>\n"));
            socketService.writeResponse(responseSerializer.serialize(httpResponse), new StringReader(httpResponse.getBody()));
            return;
        }
        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    }


//        List<String> request = socketService.readRequest();
//
//        String[] parts = request.get(0).split(" ");
//
//        Path path = Paths.get(config.getWwwHome(), parts[1]);
//        if (!Files.exists(path)) {
//            socketService.writeResponse(
//                    """
//                            HTTP/1.1 404 NOT_FOUND
//                            Content-Type: text/html; charset=utf-8
//
//                            """,
//                    new StringReader("<h1>Файл не найден!</h1>\n")
//            );
//            return;
//        }
//
//        try {
//            socketService.writeResponse("""
//                            HTTP/1.1 200 OK
//                            Content-Type: text/html; charset=utf-8
//
//                            """,
//                    Files.newBufferedReader(path));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        logger.info("Client disconnected!");
//    }
//}
