package com.beginner.pdfGeneratorwithTable.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beginner.pdfGeneratorwithTable.entity.Employee;
import com.beginner.pdfGeneratorwithTable.service.PdfGeneratorService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PdfGeneratorController {
    @Autowired
    private PdfGeneratorService pdfGeneratorservice;
    
    @GetMapping("/get")
    public List<Employee> getAllEmployees() {
        return pdfGeneratorservice.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return pdfGeneratorservice.getEmployeeById(id);
    }

    @PostMapping("/save")
    public void addEmployee(@RequestBody Employee employee) {
    	pdfGeneratorservice.saveEmployee(employee);
    }
    
    @GetMapping("/pdfGenerate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        pdfGeneratorservice.export(response);
    }
}

//attachment inline