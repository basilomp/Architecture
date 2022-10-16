package main.java.domain;

import java.util.Map;

public class HttpRequest {
//TODO implement Builder
    private String method;
    private String path;

    private Map<String, String> headers;

    private String body;

    private HttpRequest() {}
//    public HttpRequest(String method, String path, Map<String, String> headers, String body) {
//        this.method = method;
//        this.path = path;
//        this.headers = headers;
//        this.body = body;
//    }
    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    public static class Builder {
        private final HttpRequest httpRequest;

        private Builder() {
            this.httpRequest = new HttpRequest();
        }

        public Builder withMethod(String method) {
            this.httpRequest.method = method;
            return this;
        }

        public Builder withPath(String path) {
            this.httpRequest.path = path;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.httpRequest.headers = headers;
            return this;
        }

        public Builder withBody(String body) {
            this.httpRequest.body = body;
            return this;
        }



    public HttpRequest build() {
        return this.httpRequest;
    }
    }
    public static Builder createBuilder() {
        return new Builder();
    }

}
