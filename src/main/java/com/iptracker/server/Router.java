package com.iptracker.server;

import com.iptracker.controller.CountryController;

import freemarker.template.Configuration;

import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

public class Router {
	public static void configure() {
		Spark.staticFiles.location("/pages");
		
		Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_26);
		freemarkerConfiguration.setClassForTemplateLoading(Router.class, "/pages");
		
		CountryController countryController = new CountryController();
		Spark.post("/traceip", countryController::traceIP, new FreeMarkerEngine(freemarkerConfiguration));
		Spark.get("/reports", countryController::report, new FreeMarkerEngine(freemarkerConfiguration));
	}
}