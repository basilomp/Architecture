package main.java.services;

public class RequestParserFactory {
    public static RequestParser createRequestParser() {
        return new RequestParserImpl();
    }
}