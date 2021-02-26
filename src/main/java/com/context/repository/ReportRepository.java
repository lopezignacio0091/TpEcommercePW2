package com.context.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.context.model.Report;
@Repository
public interface ReportRepository extends JpaRepository<Report,Long>{
	
	//List<Report> findCreationDate(Date to ,Date from);
}
