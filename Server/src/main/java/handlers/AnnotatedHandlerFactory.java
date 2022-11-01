package handlers;

import config.Config;
import handlers.annotations.Handler;
import org.reflections.Reflections;
import serializers.ResponseSerializer;
import services.SocketService;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnnotatedHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, Config config) {

        Reflections reflections = new Reflections("handlers");
        List<Class<?>> annotatedClasses = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));
        MethodHandler prev = null;
        annotatedClasses.sort(Comparator.comparingInt(AnnotatedHandlerFactory::getOrder).reversed());

        try {
            for (Class<?> annotatedClass : annotatedClasses) {
                Constructor<?> constructor = annotatedClass.getConstructor(String.class, MethodHandler.class, SocketService.class, ResponseSerializer.class, Config.class);
                prev = (MethodHandler) constructor.newInstance(getMethod(annotatedClass), prev, socketService, responseSerializer, config);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prev;
    }

    private static int getOrder(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).order();
    }

    private static String getMethod(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).method();
    }
}
