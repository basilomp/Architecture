package handlers;

import config.Config;
import domain.HttpRequest;
import domain.HttpResponse;
import domain.ResponseCode;
import serializers.ResponseSerializer;
import services.SocketService;

public abstract class MethodHandler {

    private final String method;

    private final MethodHandler next;
    protected final SocketService socketService;
    protected final ResponseSerializer responseSerializer;

    protected final Config config;

    public MethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
        this.config = config;
    }

    public void handle(HttpRequest request) {
        HttpResponse httpResponse;
        if(method.equals(request.getMethod())) {
            httpResponse = internalHandle(request);
        } else if(next != null) {
            next.handle(request);
            return;
        } else {
            httpResponse = HttpResponse.createBuilder()
                    .withStatus(ResponseCode.METHOD_NOT_ALLOWED)
                    .withHeader("Content-Type: ", "text/html; charset=utf-8")
                    .withBody("<h1>Method not allowed</h1>")
                    .build();
        }
        String rawResponse = responseSerializer.serialize(httpResponse);
        socketService.writeResponse(rawResponse);

    }

    protected abstract HttpResponse internalHandle(HttpRequest request);
}
