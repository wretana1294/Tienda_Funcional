/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.commons;

import com.tienda.enums.TipoReporteEnum;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlitos
 */
@Component
public class JasperReportManager {
    private static final String REPORT_FOLDER="reports";
    private static final String JASPER= ".jasper";
    
    public ByteArrayOutputStream export(String fileName, String tipoReporte, Map<String, Object> params,
            Connection conn) throws JRException, IOException{
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ClassPathResource resource = new ClassPathResource(REPORT_FOLDER + File.separator + fileName + JASPER);
        
        InputStream inputStream = resource.getInputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, conn);
        
        if(tipoReporte.equalsIgnoreCase(TipoReporteEnum.EXCEL.toString())){
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(Boolean.TRUE);
            configuration.setCollapseRowSpan(Boolean.TRUE);
            exporter.setConfiguration(configuration);
            exporter.exportReport();            
        } else {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        }
        return stream;
    }
}
