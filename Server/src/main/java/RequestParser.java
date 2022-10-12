package main.java;

import main.java.domain.HttpRequest;

import java.util.List;

public interface RequestParser {
    HttpRequest parse(List<String> rawRequest);
}
