package main.java.domain;

import java.util.Map;

public class HttpRequest {

    private String method;
    private String path;

    private Map<String, String> headers;

    private String body;
}
