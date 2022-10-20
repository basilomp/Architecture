package org.example;

import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;


public class RxWebServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        Observable.<Socket>create(emitter -> {
            try {

                while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection established");
                emitter.onNext(socket);
                }
            } catch (IOException e) {
                emitter.onError(e);
            }
        }).map(socket -> {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready());

            Deque<String> request = new ArrayDeque<>();
            while (input.ready()) {
                String line = input.readLine();
//                logger.info(line);
                request.add(line);
            }
            return request;
        }).subscribe(request -> {
            request.forEach(System.out::println);
        });

    }
}
