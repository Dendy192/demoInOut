package com.dendy.countinout.service;

import com.dendy.countinout.vo.GenerateVo;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface ReportService {
    byte[] generateReportPdf(GenerateVo vo) throws JRException;

    byte[] generateXLSXReport(GenerateVo vo) throws IOException;
}
