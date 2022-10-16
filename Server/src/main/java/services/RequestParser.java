package main.java.services;

import main.java.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {
    HttpRequest parseRequest(Deque<String> rawRequest);
}
