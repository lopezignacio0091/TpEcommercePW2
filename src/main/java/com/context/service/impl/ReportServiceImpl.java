package com.context.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import com.context.model.Report;
import com.context.model.dto.ReportDTO;
import com.context.repository.ReportRepository;
import com.context.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	
	private final ReportRepository repoReport;

	public ReportServiceImpl(ReportRepository repoReport) {
		super();
		this.repoReport = repoReport;
	}
	
	
	@Override
	public void getProcessedCarts() {

		List<Report> report = repoReport.findAll();
		List<ReportDTO> dtos = new ArrayList<>(report.size());

		for (int i = 0; i < report.size(); i++) {
			ReportDTO reportDTO = new ReportDTO();
			BeanUtils.copyProperties(report.get(i), reportDTO);
			dtos.add(reportDTO);
		}

		
	}

}
