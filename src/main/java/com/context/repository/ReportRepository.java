package com.context.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.context.model.Report;

public interface ReportRepository extends JpaRepository<Report,Long>{

}
