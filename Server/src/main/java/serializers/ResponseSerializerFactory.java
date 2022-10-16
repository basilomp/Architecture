package main.java.serializers;

import main.java.serializers.ResponseSerializer;

public class ResponseSerializerFactory {

    public static ResponseSerializer createResponseSerializer() {
        return new ResponseSerializerImpl();
    }
}
