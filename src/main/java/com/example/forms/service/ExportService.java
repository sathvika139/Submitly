package com.example.forms.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.forms.model.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExportService {

    public void generatePdf(List<User> users, HttpServletResponse response) throws Exception {
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Student Project Submissions").setBold().setFontSize(18));

        // Define 7 columns
        float[] columnWidth = {100, 150, 50, 80, 100, 100, 100};
        Table table = new Table(columnWidth);

        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Sec");
        table.addCell("Branch");
        table.addCell("Frontend");
        table.addCell("Backend");
        table.addCell("GitHub");

        for (User user : users) {
            table.addCell(user.getName() != null ? user.getName() : "");
            table.addCell(user.getEmail() != null ? user.getEmail() : "");
            table.addCell(user.getSection() != null ? user.getSection() : "");
            table.addCell(user.getBranch() != null ? user.getBranch() : "");
            table.addCell(user.getFrontendLink() != null ? user.getFrontendLink() : "");
            table.addCell(user.getBackendLink() != null ? user.getBackendLink() : "");
            table.addCell(user.getGithubLink() != null ? user.getGithubLink() : "");
        }

        document.add(table);
        document.close();
    }

    public void generateExcel(List<User> users, HttpServletResponse response) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        Row header = sheet.createRow(0);
        String[] headers = {"Name", "Email", "Section", "Branch", "Frontend", "Backend", "GitHub"};
        
        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        int rowCount = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(user.getName());
            row.createCell(1).setCellValue(user.getEmail());
            row.createCell(2).setCellValue(user.getSection());
            row.createCell(3).setCellValue(user.getBranch());
            row.createCell(4).setCellValue(user.getFrontendLink());
            row.createCell(5).setCellValue(user.getBackendLink());
            row.createCell(6).setCellValue(user.getGithubLink());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}