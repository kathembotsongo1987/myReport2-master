package com.example.ReportSolution.Service;

import com.example.ReportSolution.model.Items;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ItemsService {

    List<Items> getAllItems();

    boolean createPdf(List<Items> items, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean createExcel(List<Items> items, ServletContext context, HttpServletRequest request, HttpServletResponse response);

}
