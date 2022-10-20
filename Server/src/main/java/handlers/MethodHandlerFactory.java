package handlers;

import config.Config;
import serializers.ResponseSerializer;
import services.SocketService;

public class MethodHandlerFactory {
//TODO implement COR through annotations with the help of Reflections API
    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        PutMethodHandler putMethodHandler = new PutMethodHandler("PUT", null, socketService, responseSerializer, config);
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST", putMethodHandler, socketService, responseSerializer, config);
        return new GetMethodHandler("GET", postMethodHandler, socketService, responseSerializer, config);
    }



}
