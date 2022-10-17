package main.java;

import main.java.domain.ResponseCode;
import main.java.services.RequestParser;
import main.java.services.SocketService;
import main.java.config.Config;
import main.java.domain.HttpRequest;
import main.java.domain.HttpResponse;
import main.java.serializers.ResponseSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;

public class RequestHandler implements Runnable {
    private final SocketService socketService;
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;
    private final Config config;

    public RequestHandler(SocketService socketService,
                          RequestParser requestParser,
                          ResponseSerializer responseSerializer,
                          Config config) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
        this.config = config;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parseRequest(rawRequest);

        if (httpRequest.getMethod().equals("GET")) {
            Path path = Paths.get(config.getWwwHome(), httpRequest.getUrl());

            if (!Files.exists(path)) {
                HttpResponse httpResponse = HttpResponse.createBuilder()
                        .withStatus(ResponseCode.NOT_FOUND)
                        .withHeader("Content-Type: ", "text/html; charset=utf-8")
                        .withBody("<h1>File not found</h1>")
                        .build();
                String rawResponse = responseSerializer.serialize(httpResponse);
                socketService.writeResponse(rawResponse);
                return;
            }

            StringBuilder sb = new StringBuilder();
            try {
                Files.readAllLines(path).forEach(sb::append);
            }catch (IOException e) {
                throw new IllegalStateException(e);
            }
            HttpResponse httpResponse = HttpResponse.createBuilder()
                    .withStatus(ResponseCode.OK)
                    .withHeader("Content-Type: ", "text/html; charset=utf-8")
                    .withBody(sb.toString())
                    .build();
            String rawResponse = responseSerializer.serialize(httpResponse);
            socketService.writeResponse(rawResponse);
        } else {
            HttpResponse httpResponse = HttpResponse.createBuilder()
                    .withStatus(ResponseCode.METHOD_NOT_ALLOWED)
                    .withHeader("Content-Type: ", "text/html; charset=utf-8")
                    .withBody("<h1>Method not allowed</h1>").build();
            String rawResponse = responseSerializer.serialize(httpResponse);
            socketService.writeResponse(rawResponse);
            return;
        }
        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}

