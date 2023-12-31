package iss4u.ehr.backoffice.radiology.services.implementations;

import iss4u.ehr.backoffice.radiology.entities.Study;
import iss4u.ehr.backoffice.radiology.repositories.StudyRepository;
import iss4u.ehr.backoffice.radiology.services.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;

    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public void create(Study study) {
        studyRepository.save(study);
    }

    @Override
    public List<Study> retrieveStudies() {
        return studyRepository.findAll();
    }

    @Override
    public Optional<Study> getStudyByKy(Long studyKy) {
        return studyRepository.findById(studyKy);
    }

    @Override
    public void update(Long studyKy, Study updatedStudy) {
        Study existingStudy = studyRepository.findById(studyKy)
                .orElseThrow(() -> new EntityNotFoundException("Study with id " + studyKy + " not found"));

        // Update the properties
        existingStudy.setStudyLabel(updatedStudy.getStudyLabel());
        existingStudy.setStudyDesc(updatedStudy.getStudyDesc());
        existingStudy.setStudyComment(updatedStudy.getStudyComment());
        existingStudy.setStudyRfrntphyscn(updatedStudy.getStudyRfrntphyscn());
        existingStudy.setStudyPrfrmngphyscn(updatedStudy.getStudyPrfrmngphyscn());
        existingStudy.setStudyAetitle(updatedStudy.getStudyAetitle());
        existingStudy.setStudyType(updatedStudy.getStudyType());
        existingStudy.setStudyStatus(updatedStudy.getStudyStatus());
        existingStudy.setStudyPriority(updatedStudy.getStudyPriority());
        existingStudy.setStudyUnxTmUpdt(updatedStudy.getStudyUnxTmUpdt());

        studyRepository.save(existingStudy);
    }

    @Override
    public void delete(Long studyKy) {
        studyRepository.deleteById(studyKy);
    }
}
