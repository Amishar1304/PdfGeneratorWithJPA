package com.beginner.pdfGeneratorwithTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.beginner.pdfGeneratorwithTable.service.PdfGeneratorService;

@SpringBootApplication
public class PdfGeneratorwithTableApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(PdfGeneratorwithTableApplication.class, args);
	}
	}
