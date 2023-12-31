package iss4u.ehr.backoffice.radiology.services;


import iss4u.ehr.backoffice.radiology.entities.Study;

import java.util.List;
import java.util.Optional;

public interface StudyService {

    void create(Study study);

    List<Study> retrieveStudies();

    Optional<Study> getStudyByKy(Long studyKy);

    void update(Long studyKy, Study updatedStudy);

    void delete(Long studyKy);
}
