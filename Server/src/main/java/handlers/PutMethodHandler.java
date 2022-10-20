package handlers;


import config.Config;
import domain.HttpRequest;
import domain.HttpResponse;
import domain.ResponseCode;
import handlers.annotations.Handler;
import serializers.ResponseSerializer;
import services.SocketService;

@Handler(order = 2)
public class PutMethodHandler extends MethodHandler{
    public PutMethodHandler(String method,
                            MethodHandler next,
                            SocketService socketService,
                            ResponseSerializer responseSerializer,
                            Config config) {
        super(method, next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse internalHandle(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatus(ResponseCode.OK)
                .withHeader("Content-Type: ", "text/html; charset=utf-8")
                .withBody("<h1>PUT method handled</h1>")
                .build();
    }
}
