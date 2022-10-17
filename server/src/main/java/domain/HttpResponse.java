package main.java.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private ResponseCode status;
    private final Map<String, String> headers;
    private String body;

    private HttpResponse(ResponseCode status, Map<String, String> headers, String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public ResponseCode getStatus() {
        return status;
    }

    public Map<String, String> getHeader() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {

        private ResponseCode status;
        private Map<String, String> headers = new HashMap<>();

        private String body;

        private Builder() {
        }

        public Builder withStatus(ResponseCode status) {
            this.status = status;
            return this;
        }

        public Builder withHeader(String header, String value) {
            this.headers.put(header, value);
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }


        public HttpResponse build() {
            if(this.status == null) {
                throw new IllegalArgumentException("Status is obligatory for Response");
            }
            return new HttpResponse(status, headers, body);
        }
    }

    public static Builder createBuilder() {
        return new Builder();
    }
}
