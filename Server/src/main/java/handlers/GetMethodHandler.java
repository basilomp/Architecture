package handlers;

import config.Config;
import domain.HttpRequest;
import domain.HttpResponse;
import domain.ResponseCode;
import handlers.annotations.Handler;
import handlers.annotations.MethodName;
import serializers.ResponseSerializer;
import services.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Handler(order = 0, method = "GET")
@MethodName(name = "GET")
public class GetMethodHandler extends MethodHandler{


    public GetMethodHandler(String method,
                            MethodHandler next,
                            SocketService socketService,
                            ResponseSerializer responseSerializer,
                            Config config) {
        super(method, next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse internalHandle(HttpRequest request) {
        Path path = Paths.get(config.getWwwHome(), request.getUrl());

        if (!Files.exists(path)) {
            return HttpResponse.createBuilder()
                    .withStatus(ResponseCode.NOT_FOUND)
                    .withHeader("Content-Type: ", "text/html; charset=utf-8")
                    .withBody("<h1>File not found</h1>")
                    .build();
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path).forEach(sb::append);
        }catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return HttpResponse.createBuilder()
                .withStatus(ResponseCode.OK)
                .withHeader("Content-Type: ", "text/html; charset=utf-8")
                .withBody(sb.toString())
                .build();
    }
}
