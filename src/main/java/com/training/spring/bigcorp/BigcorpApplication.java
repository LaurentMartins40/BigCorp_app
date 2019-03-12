package com.training.spring.bigcorp;

import com.training.spring.bigcorp.config.BigCorpApplicationConfig;
import com.training.spring.bigcorp.model.ApplicationInfo;
import com.training.spring.bigcorp.service.SiteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BigcorpApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(BigcorpApplication.class);

		ApplicationInfo applicationInfo = context.getBean(ApplicationInfo.class);
		System.out.println("==========================================================");
		System.out.println("Application [" + applicationInfo.getName() + "] - version : "
				+ applicationInfo.getVersion());
		System.out.println("plus d'informations sur " + applicationInfo.getWebSiteUrl());
		System.out.println("==========================================================");
		SiteService site = context.getBean(SiteService.class);
		site.findById("siteA");

	}}




