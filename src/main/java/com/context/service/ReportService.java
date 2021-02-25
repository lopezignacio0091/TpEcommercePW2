package com.context.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.context.model.dto.ReportDTO;

@Service
public interface ReportService {

	 void postProcessedCarts();
	 List<ReportDTO> getReports();
	 List<ReportDTO> getReportsDate(Date desde , Date hasta);
}
