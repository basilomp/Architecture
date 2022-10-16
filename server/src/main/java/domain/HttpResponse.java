package main.java.domain;

public class HttpResponse {
//TODO implement Builder
    private String statusCode;
    private String header;
    private String body;

    private HttpResponse() {}
//    public HttpResponse(String statusCode, String header, String body) {
//        this.statusCode = statusCode;
//        this.header = header;
//        this.body = body;
//    }
    public String getStatusCode() {
        return statusCode;
    }
    public String getHeader() {
        return header;
    }
    public String getBody() {
        return body;
    }
    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
    public static class Builder {
        private final HttpResponse httpResponse;

        private Builder() {
            this.httpResponse = new HttpResponse();
        }

        public Builder withStatusCode(String statusCode) {
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder withBody(String body) {
            this.httpResponse.body = body;
            return this;
        }

        public Builder withHeader(String header) {
            this.httpResponse.header = header;
            return this;
        }

        public HttpResponse build() {
            return this.httpResponse;
        }
    }
    public static Builder createBuilder() {
        return new Builder();
    }
}
