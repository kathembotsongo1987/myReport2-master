package com.example.ReportSolution.Controller;

import com.example.ReportSolution.Service.ItemsService;
import com.example.ReportSolution.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class PdfExcelController {

    @Autowired
    private ItemsService itemsService;
    @Autowired private ServletContext context;
    @GetMapping(value = "/")
    public String allItems(Model model){
        List<Items> items = itemsService.getAllItems();
        model.addAttribute("items",items);
        return "view/items";
    }

    @GetMapping(value = "/createPdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response){
        List<Items> items = itemsService.getAllItems();
        boolean isFlag = itemsService.createPdf(items, context, request, response);
        if(isFlag){
            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"items"+".pdf");
            filedownload(fullPath, response, "items.pdf");
        }
    }

    @GetMapping(value = "/createExcel")
    public void createExcel(HttpServletRequest request, HttpServletResponse response){
        List<Items> items = itemsService.getAllItems();
        boolean isFlag = itemsService.createExcel(items, context, request, response);
        if(isFlag){
            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"items"+".xls");
            filedownload(fullPath, response, "items.xls");
        }
    }
    private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final  int BUFFER_SIZE = 4096;
        if(file.exists()){
            try{
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename="+fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer))!= -1){
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

