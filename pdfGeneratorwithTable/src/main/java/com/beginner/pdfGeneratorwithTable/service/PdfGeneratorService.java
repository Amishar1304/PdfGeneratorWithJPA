package com.beginner.pdfGeneratorwithTable.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beginner.pdfGeneratorwithTable.entity.Employee;
import com.beginner.pdfGeneratorwithTable.repository.EmployeeRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
@Service
public class PdfGeneratorService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
    
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
	
    public PdfPTable export(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeRepository.findAll();
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
        HeaderFooter footer = new HeaderFooter(true, new Phrase(" page"));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);
        
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(20);
        
        Paragraph paragraph = new Paragraph("Employee Information", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Department");
        
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        
        for (Employee employee : employees) {
            table.addCell(String.valueOf(employee.getId()));
            table.addCell(employee.getName());
            table.addCell(employee.getDepartment());
        }
        
        document.add(table);
        
        document.close();
        
        return table; 
        }
}
