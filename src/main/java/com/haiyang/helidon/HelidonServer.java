package com.haiyang.helidon;

import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class HelidonServer {
    //https://helidon.io/docs/latest/#/about/01_overview
    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "false");

        WebServer webServer = buildServer();
        String url = "http://localhost:" + webServer.port();

        Desktop.getDesktop().browse(new URI(url));
    }

    private static WebServer buildServer() throws Exception {
        return WebServer
                .create(Routing.builder()
                        .any((req, res) -> res.send("It works!"))
                        .build())
                .start()
                .toCompletableFuture()
                .get(10, TimeUnit.SECONDS);
    }
}
