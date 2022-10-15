package main.java.domain;

public class HttpResponse {
//TODO form
    private String statusCode;
    private String header;
    private String body;

    public HttpResponse(String statusCode, String header, String body) {
        this.statusCode = statusCode;
        this.header = header;
        this.body = body;
    }

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
}
