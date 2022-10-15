package main.java;

import main.java.domain.HttpResponse;

public class ResponseSerializerImpl implements ResponseSerializer{
    StringBuilder response = new StringBuilder();

    @Override
    public String serialize(HttpResponse httpResponse) {
        response.append(httpResponse.getStatusCode()).append(System.lineSeparator());
        response.append(httpResponse.getHeader()).append(System.lineSeparator());
        System.out.println(response);
        return String.valueOf(response);
    }

}
