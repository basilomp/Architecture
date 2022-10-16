package main.java.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String url;

    private Map<String, String> headers;

    private String body;


    private HttpRequest(String method, String url, Map<String, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
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
                ", path='" + url + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    public static class Builder {

        private String method;
        private String url;

        private Map<String, String> headers = new HashMap<>();

        private String body;

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withHeader(String header, String value) {
            this.headers.put(header,value);
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }



    public HttpRequest build() {
        return new HttpRequest(method, url, headers, body);
    }
    }
    public static Builder createBuilder() {
        return new Builder();
    }

}
