package serializers;

import domain.HttpResponse;

class ResponseSerializerImpl implements ResponseSerializer {

    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 " + httpResponse.getStatus().getStatus() + " " + httpResponse.getStatus().getName() + "\n");
        httpResponse.getHeader().forEach((header, value) -> {
            response.append(header + ": " + value + "\n");
        });
        response.append("\n");
        response.append(httpResponse.getBody());
        return response.toString();
    }

}
