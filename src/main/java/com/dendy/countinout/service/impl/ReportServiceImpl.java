package com.dendy.countinout.service.impl;

import com.dendy.countinout.service.ReportService;
import com.dendy.countinout.vo.GenerateDetailVo;
import com.dendy.countinout.vo.GenerateVo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public byte[] generateReportPdf( GenerateVo vo) throws JRException {
        InputStream mainReportStream = getClass().getResourceAsStream("/jasper/gate.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(mainReportStream);
        InputStream subReportStream = getClass().getResourceAsStream("/jasper/detail.jasper");
        JasperReport subJasperReport = (JasperReport) JRLoader.loadObject(subReportStream);
        Map parameters = new HashMap<>();
        parameters.put("tanggal", vo.getTanggal());
        parameters.put("pintu", vo.getGate());
        parameters.put("subreport", subJasperReport);

        List<Map<String, Object>> subReportData = mappingData(vo.getData());
        JRBeanCollectionDataSource subDataSource = new JRBeanCollectionDataSource(subReportData);
        Map test = new HashMap();
        test.put("datasource", subDataSource);
        parameters.put("datasource_param",test);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public byte[] generateXLSXReport(GenerateVo vo) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(vo.getGate());
        int rowId = 0;
        Row row = sheet.createRow(rowId++);
        row.createCell(1).setCellValue("Gate :");
        row.createCell(2).setCellValue(vo.getGate());

        row = sheet.createRow(rowId++);
        row.createCell(1).setCellValue("Tanggal :");
        row.createCell(2).setCellValue(vo.getTanggal());
        rowId++;
        row = sheet.createRow(rowId++);
        String[] headers = {"No", "Waktu", "No Kartu", "Nama", "Perusahaan","Jabatan","Zona", "Akess"};
        for (int col = 0; col < headers.length; col++) {
            row.createCell(col+1).setCellValue(headers[col]);
        }
        int no = 1;
        for (GenerateDetailVo detail : vo.getData()) {
            row = sheet.createRow(rowId++);
            row.createCell(1).setCellValue(no);
            row.createCell(2).setCellValue(detail.getWaktu());
            row.createCell(3).setCellValue(detail.getNoKartu());
            row.createCell(4).setCellValue(detail.getNama());
            row.createCell(5).setCellValue(detail.getPerusahaan());
            row.createCell(6).setCellValue(detail.getJabatan());
            row.createCell(7).setCellValue(detail.getZona());
            row.createCell(8).setCellValue(detail.getAkess());
            no++;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    private List<Map<String, Object>> mappingData(List<GenerateDetailVo> vo) {
        List map = new ArrayList();
        if (vo.isEmpty()) {
            Map mapData = new HashMap();
            map.add(mapData);
        } else {
            for (GenerateDetailVo v : vo) {
                Map<String, Object> mapData = new HashMap();
                mapData.put("waktu", v.getWaktu());
                mapData.put("noKartu", v.getNoKartu());
                mapData.put("nama", v.getNama());
                mapData.put("perusahaan", v.getPerusahaan());
                mapData.put("jabatan", v.getJabatan());
                mapData.put("zona", v.getZona());
                mapData.put("akses", v.getAkess());
                map.add(mapData);
            }
        }
        return map;
    }

}
