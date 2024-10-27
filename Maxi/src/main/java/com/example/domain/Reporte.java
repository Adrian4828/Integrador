package com.example.domain;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Reporte {

    public void crearReporteCat(List<Categoria> categoria) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Datos");

        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("ID");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Nombre");
        Cell cell3 = row.createCell(2);
        cell3.setCellValue("Descripcion");

        int rowIndex = 1;
        for (Categoria categorias : categoria) {
            Row row2 = sheet.createRow(rowIndex);
            Cell cell4 = row2.createCell(0);
            cell4.setCellValue(categorias.getIdCategoria());
            Cell cell5 = row2.createCell(1);
            cell5.setCellValue(categorias.getNombre());
            Cell cell6 = row2.createCell(2);
            cell6.setCellValue(categorias.getDescripcion());
            rowIndex++;
        }

        File archivo = new File("ReporteCategoria.xlsx");
        try (FileOutputStream fileOut = FileUtils.openOutputStream(archivo)) {
            workbook.write(fileOut);
            System.out.println("Archivo creado");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}





