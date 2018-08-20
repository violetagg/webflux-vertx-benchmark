package com.example.reactornetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.ipc.netty.http.server.HttpServer;
import reactor.ipc.netty.tcp.BlockingNettyContext;

@SpringBootApplication
public class ReactornettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactornettyApplication.class, args);

		HttpServer server = HttpServer.create(8080);
		BlockingNettyContext ctx = server.startRouter(r -> r.post("/text",
						(req, res) -> res.addHeader("Content-Type", "text/plain")
								.sendString(req.receive()
										.aggregate()
										.asString())));
		ctx.getContext().onClose().block();
	}
}
