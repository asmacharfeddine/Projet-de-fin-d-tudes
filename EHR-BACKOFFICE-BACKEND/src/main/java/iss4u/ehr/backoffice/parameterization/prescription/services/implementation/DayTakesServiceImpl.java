package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.DayTakes;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.DayTakesRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.MedicationPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iss4u.ehr.backoffice.parameterization.prescription.entities.MedicationPart;

import java.util.ArrayList;
import java.util.List;
@Service
public class DayTakesServiceImpl {
    private final DayTakesRepository dayTakesRepository;

    @Autowired
    public DayTakesServiceImpl(DayTakesRepository dayTakesRepository) {
        this.dayTakesRepository = dayTakesRepository;
    }

    /*public List<DayTakes> getDayTakesByMedicationPartKey(Integer medicationPartKey) {
        return dayTakesRepository.findByMedicationPart_medicationPartKey(medicationPartKey);
    }*/
}
