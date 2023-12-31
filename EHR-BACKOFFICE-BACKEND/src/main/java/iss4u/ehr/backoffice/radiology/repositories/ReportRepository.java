package iss4u.ehr.backoffice.radiology.repositories;

import iss4u.ehr.backoffice.radiology.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByReportKy(Long reportKy);

}
