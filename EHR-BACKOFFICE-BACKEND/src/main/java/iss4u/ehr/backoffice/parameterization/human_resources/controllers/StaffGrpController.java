package iss4u.ehr.backoffice.parameterization.human_resources.controllers;


import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrp;
import iss4u.ehr.backoffice.parameterization.human_resources.services.StaffGrpService;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parameterization/staffGrp")
public class StaffGrpController {
    @Autowired
    private StaffGrpService staffGrpService;

    @PostMapping
    public ResponseEntity<StaffGrp> createStaffGrp(@RequestBody StaffGrp staffGrp) {
        staffGrpService.create(staffGrp);
        return new ResponseEntity<>(staffGrp, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StaffGrp>> getAllStaffGrps() {
        List<StaffGrp> staffGrps = staffGrpService.retrieveStaffGrps();
        return new ResponseEntity<>(staffGrps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffGrp> getStaffGrpById(@PathVariable("id") Long staffGrpId) {
        Optional<StaffGrp> staffGrp = staffGrpService.getStaffGrpByKy(staffGrpId);
        return staffGrp.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{staffGrpKy}")
    public ResponseEntity<StaffGrp> updateStaffGrp(@PathVariable Long staffGrpKy, @RequestBody StaffGrp updatedStaffGrp) {
        try {
            staffGrpService.update(staffGrpKy, updatedStaffGrp);
            return ResponseEntity.ok().build(); // Return 200 (OK) without a body
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStaffGrp(@PathVariable("id") Long staffGrpId) {
        Optional<StaffGrp> existingStaffGrp = staffGrpService.getStaffGrpByKy(staffGrpId);
        if (existingStaffGrp.isPresent()) {
            staffGrpService.delete(staffGrpId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/child-elements")
    public ResponseEntity<List<Staff>> getChildElements(@PathVariable("id") Long staffGrpId) {
        Optional<StaffGrp> staffGrp = staffGrpService.getStaffGrpByKy(staffGrpId);
        if (staffGrp.isPresent()) {
            List<Staff> childElements = staffGrpService.getChildElements(staffGrp.get());
            return new ResponseEntity<>(childElements, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{staffGrpId}/addStaff")
    public ResponseEntity<String> addChildElement(@PathVariable Long staffGrpId, @RequestBody Staff staff) {
        Optional<StaffGrp> staffGrp = staffGrpService.getStaffGrpByKy(staffGrpId);
        if (staffGrp.isPresent()) {
            staffGrpService.addChildElement(staffGrp, staff);
            return ResponseEntity.ok("Staff added to StaffGrp successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
