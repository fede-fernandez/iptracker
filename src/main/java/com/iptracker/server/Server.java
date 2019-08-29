package com.iptracker.server;

import org.slf4j.LoggerFactory;

import spark.Spark;

public class Server {
	
	public static void main(String[] args) {
		new Initializer().initialize();
		int port = Configuration.getInstance().getIntegerPropertyValue("server.attending.port");
		Spark.port(port);
		Router.configure();
		LoggerFactory.getLogger(Server.class).info("SERVER RUNNING AT PORT: " + port);
	}
}