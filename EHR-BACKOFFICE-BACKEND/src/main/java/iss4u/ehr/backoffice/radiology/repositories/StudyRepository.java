package iss4u.ehr.backoffice.radiology.repositories;

import iss4u.ehr.backoffice.radiology.entities.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Study findByStudyKy(Long studyKy);
}
