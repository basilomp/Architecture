package main.java.serializers;

import main.java.domain.HttpResponse;

public interface ResponseSerializer {
    String serialize(HttpResponse httpResponse);
}
