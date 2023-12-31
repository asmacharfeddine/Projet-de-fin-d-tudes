package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.DayTakes;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.DayTakesServiceImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.MedicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/DayTakes")
public class DayTakesController {
    @Autowired
    private DayTakesServiceImpl dayTakesService;
    /* @GetMapping("/{medicationPartKey}/AllDayTakes")
    public List<DayTakes> getDayTakesByMedicationPartKey(@PathVariable Integer medicationPartKey) {
        return dayTakesService.getDayTakesByMedicationPartKey(medicationPartKey);
    }*/
}
