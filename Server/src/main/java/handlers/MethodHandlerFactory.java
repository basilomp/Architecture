package handlers;

import config.Config;
import handlers.annotations.Handler;
import handlers.annotations.MethodName;
import org.reflections.Reflections;
import serializers.ResponseSerializer;
import services.SocketService;

import java.util.*;

public class MethodHandlerFactory {
    //TODO implement COR through annotations with the help of Reflections API
    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        PutMethodHandler putMethodHandler = new PutMethodHandler("PUT", null, socketService, responseSerializer, config);
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST", putMethodHandler, socketService, responseSerializer, config);
        return new GetMethodHandler("GET", postMethodHandler, socketService, responseSerializer, config);
    }


    //Initial attempts
//    public static Set<Class<?>> searchForClassesByAnnotation() {
//        return new Reflections("handlers").getTypesAnnotatedWith(Handler.class);
//    }
//
//    public static Map<Integer, Class<?>> prepareAnnotatedClassMap() {
//        Set<Class<?>> annotatedClasses = searchForClassesByAnnotation();
//        Map<Integer, Class<?>> sortedAnnotatedClasses = new TreeMap<>();
//        for (Class<?> aClass : annotatedClasses) {
//            System.out.println(aClass.getAnnotation(Handler.class).order());
//            sortedAnnotatedClasses.put(aClass.getAnnotation(Handler.class).order(), aClass);
//        }
//        return sortedAnnotatedClasses;
//    }
//
//    public static MethodHandler generate(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
//        MethodHandler methodHandler = null;
//        Map<Integer, Class<?>> classMap = prepareAnnotatedClassMap();
//        for (int i = 0; i < classMap.size(); i++) {
//            Class<?> aClass = classMap.get(i);
//            Class<?> aClass = null;
//            Object o;
//            if(i == 0) {
//                o = null;
//            } else {
//                o = classMap.get(i-1);
//            }
//            MethodHandler aClass1 = (MethodHandler) new aClass(aClass.getAnnotation(MethodName.class).name(), o, socketService, responseSerializer, config);
//        }
//    }
//
//}
//
//
//    public static void main(String[] args) {
//        System.out.println(Arrays.asList(searchForClassesByAnnotation()));
//        Set<Class<?>> annotatedClasses = searchForClassesByAnnotation();
//        Map<Integer, Class<?>> clazz = new TreeMap<>();
//        for (Class<?> aClass : annotatedClasses) {
//            System.out.println(aClass.getAnnotation(Handler.class).order());
//            clazz.put(aClass.getAnnotation(Handler.class).order(), aClass);
//        }
//        System.out.println(clazz.toString());
//
//        for (int i = 0; i < clazz.size(); i++) {
//            if (i > 1) {
//                System.out.println(clazz.get(i - 1));
//            } else System.out.println(clazz.get(i));
//        }
//    }
}
