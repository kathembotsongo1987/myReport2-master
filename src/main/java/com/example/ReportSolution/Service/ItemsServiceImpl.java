package com.example.ReportSolution.Service;

import com.example.ReportSolution.Repository.ItemsRepository;
import com.example.ReportSolution.model.Items;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {
    @Autowired private ItemsRepository itemsRepository;
    @Override
    public List<Items> getAllItems() {
        return (List<Items>) itemsRepository.findAll();
    }

    @Override
    public boolean createPdf(List<Items> items, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
       Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try{
            String filePath = context.getRealPath("/resources/reports");
            File file = new File(filePath);
            boolean exists = new File(filePath).exists();
            if(!exists){
                new File(filePath).mkdirs();
            }
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"items"+".pdf"));
            document.open();

            Font mainFont = FontFactory.getFont("Arial",10, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("All Items", mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingAfter(10f);
            table.setSpacingAfter(10);

            Font tableHeader = FontFactory.getFont("Arial",10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial",9, BaseColor.BLACK);

            float[] columWidths = {2f, 2f, 2f, 2f, 2f};
            table.setWidths(columWidths);

            PdfPCell item = new PdfPCell(new Paragraph("Item", tableHeader));
            item.setBorderColor(BaseColor.BLACK);
            item.setPadding(10);
            item.setHorizontalAlignment(Element.ALIGN_CENTER);
            item.setVerticalAlignment(Element.ALIGN_CENTER);
            item.setBackgroundColor(BaseColor.GRAY);
            item.setExtraParagraphSpace(5f);
            table.addCell(item);

            PdfPCell quantity = new PdfPCell(new Paragraph("Quantity", tableHeader));
            quantity.setBorderColor(BaseColor.BLACK);
            quantity.setPadding(10);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setVerticalAlignment(Element.ALIGN_CENTER);
            quantity.setBackgroundColor(BaseColor.GRAY);
            quantity.setExtraParagraphSpace(5f);
            table.addCell(quantity);

            PdfPCell price = new PdfPCell(new Paragraph("Price", tableHeader));
            price.setBorderColor(BaseColor.BLACK);
            price.setPadding(10);
            price.setHorizontalAlignment(Element.ALIGN_CENTER);
            price.setVerticalAlignment(Element.ALIGN_CENTER);
            price.setBackgroundColor(BaseColor.GRAY);
            price.setExtraParagraphSpace(5f);
            table.addCell(price);

            PdfPCell created_at = new PdfPCell(new Paragraph("Create at", tableHeader));
            created_at.setBorderColor(BaseColor.BLACK);
            created_at.setPadding(10);
            created_at.setHorizontalAlignment(Element.ALIGN_CENTER);
            created_at.setVerticalAlignment(Element.ALIGN_CENTER);
            created_at.setBackgroundColor(BaseColor.GRAY);
            created_at.setExtraParagraphSpace(5f);
            table.addCell(created_at);

            PdfPCell updated_at = new PdfPCell(new Paragraph("Update at", tableHeader));
            updated_at.setBorderColor(BaseColor.BLACK);
            updated_at.setPadding(10);
            updated_at.setHorizontalAlignment(Element.ALIGN_CENTER);
            updated_at.setVerticalAlignment(Element.ALIGN_CENTER);
            updated_at.setBackgroundColor(BaseColor.GRAY);
            updated_at.setExtraParagraphSpace(5f);
            table.addCell(updated_at);

            for(Items product : items) {
                PdfPCell itemValue = new PdfPCell(new Paragraph(product.getItems(), tableBody));
                itemValue.setBorderColor(BaseColor.BLACK);
                itemValue.setPadding(10);
                itemValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                itemValue.setVerticalAlignment(Element.ALIGN_CENTER);
                itemValue.setBackgroundColor(BaseColor.WHITE);
                itemValue.setExtraParagraphSpace(5f);
                table.addCell(itemValue);

                PdfPCell quantityValue = new PdfPCell(new Paragraph(product.getQuantity(), tableBody));
                quantityValue.setBorderColor(BaseColor.BLACK);
                quantityValue.setPadding(10);
                quantityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityValue.setVerticalAlignment(Element.ALIGN_CENTER);
                quantityValue.setBackgroundColor(BaseColor.WHITE);
                quantityValue.setExtraParagraphSpace(5f);
                table.addCell(quantityValue);

                PdfPCell priceValue = new PdfPCell(new Paragraph(String.valueOf(product.getPrice()), tableBody));
                priceValue.setBorderColor(BaseColor.BLACK);
                priceValue.setPadding(10);
                priceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                priceValue.setBackgroundColor(BaseColor.WHITE);
                priceValue.setExtraParagraphSpace(5f);
                table.addCell(priceValue);

                PdfPCell created_at_Value = new PdfPCell(new Paragraph(String.valueOf(product.getCreated_at()), tableBody));
                created_at_Value.setBorderColor(BaseColor.BLACK);
                created_at_Value.setPadding(10);
                created_at_Value.setHorizontalAlignment(Element.ALIGN_CENTER);
                created_at_Value.setVerticalAlignment(Element.ALIGN_CENTER);
                created_at_Value.setBackgroundColor(BaseColor.WHITE);
                created_at_Value.setExtraParagraphSpace(5f);
                table.addCell(created_at_Value);

                PdfPCell updated_at_Value = new PdfPCell(new Paragraph(String.valueOf(product.getUpdated_at()), tableBody));
                updated_at_Value.setBorderColor(BaseColor.BLACK);
                updated_at_Value.setPadding(10);
                updated_at_Value.setHorizontalAlignment(Element.ALIGN_CENTER);
                updated_at_Value.setVerticalAlignment(Element.ALIGN_CENTER);
                updated_at_Value.setBackgroundColor(BaseColor.WHITE);
                updated_at_Value.setExtraParagraphSpace(5f);
                table.addCell(updated_at_Value);

            }
            document.add(table);
            document.close();
            writer.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
        @Override
    public boolean createExcel(List<Items> items, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
            String filePath = context.getRealPath("/resources/reports");
            File file = new File(filePath);
            boolean exists = new File(filePath).exists();
            if (!exists) {
                new File(filePath).mkdirs();
            }
            try {
                FileOutputStream outputStream = new FileOutputStream(file + "/" + "items" + ".xls");
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet("Items");
                worksheet.setDefaultColumnWidth(30);

                HSSFCellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
                headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                HSSFRow headerRow = worksheet.createRow(0);

                HSSFCell item = headerRow.createCell(0);
                item.setCellValue("Items");
                item.setCellStyle(headerCellStyle);

                HSSFCell quantity = headerRow.createCell(1);
                quantity.setCellValue("Quantity");
                quantity.setCellStyle(headerCellStyle);

                HSSFCell price = headerRow.createCell(2);
                price.setCellValue("Price");
                price.setCellStyle(headerCellStyle);

                HSSFCell created_at = headerRow.createCell(3);
                created_at.setCellValue("Created at");
                created_at.setCellStyle(headerCellStyle);

                HSSFCell updated_at = headerRow.createCell(4);
                updated_at.setCellValue("Updated at");
                updated_at.setCellStyle(headerCellStyle);

                int i = 1;
                for (Items product : items) {
                    HSSFRow bodyRow = worksheet.createRow(i);

                    HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
                    bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

                    HSSFCell itemValue = bodyRow.createCell(0);
                    itemValue.setCellValue(product.getItems());
                    itemValue.setCellStyle(bodyCellStyle);

                    HSSFCell quantityValue = bodyRow.createCell(1);
                    quantityValue.setCellValue(product.getQuantity());
                    quantityValue.setCellStyle(bodyCellStyle);

                    HSSFCell priceValue = bodyRow.createCell(2);
                    priceValue.setCellValue(product.getPrice());
                    priceValue.setCellStyle(bodyCellStyle);

                    HSSFCell created_at_Value = bodyRow.createCell(3);
                    created_at_Value.setCellValue(product.getCreated_at());
                    created_at_Value.setCellStyle(bodyCellStyle);

                    HSSFCell updated_at_Value = bodyRow.createCell(4);
                    updated_at_Value.setCellValue(product.getCreated_at());
                    updated_at_Value.setCellStyle(bodyCellStyle);

                    i++;
                }
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
}
