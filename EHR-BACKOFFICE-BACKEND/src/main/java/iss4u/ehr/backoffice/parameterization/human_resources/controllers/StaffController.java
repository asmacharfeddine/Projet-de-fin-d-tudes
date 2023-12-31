package iss4u.ehr.backoffice.parameterization.human_resources.controllers;


import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffPassword;
import iss4u.ehr.backoffice.parameterization.human_resources.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parameterization/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        staffService.create(staff);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

    @PostMapping("/{staffId}/password")
    public ResponseEntity<StaffPassword> createStaffPassword(
            @PathVariable Long staffId, @RequestBody StaffPassword password) {
        Optional<Staff> staff = staffService.getStaffByKy(staffId);
        if (staff.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StaffPassword savedPassword = staffService.createStaffPassword(staff, password);
        return ResponseEntity.ok(savedPassword);
    }

    @GetMapping("/{staffId}/passwords")
    public ResponseEntity<List<StaffPassword>> getStaffPasswords(@PathVariable Long staffId) {
        Optional<Staff> staff = staffService.getStaffByKy(staffId);
        List<StaffPassword> passwords = staffService.getStaffPasswords(staff);
        return ResponseEntity.ok(passwords);
    }


    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaffs() {
        List<Staff> staffs = staffService.retrieveStaffs();
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable("id") Long staffId) {
        Optional<Staff> staff = staffService.getStaffByKy(staffId);
        return staff.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{staffKy}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long staffKy, @RequestBody Staff updatedStaff) {
        try {
            staffService.update(staffKy, updatedStaff);
            return ResponseEntity.ok().build(); // Return 200 (OK) without a body
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStaff(@PathVariable("id") Long staffId) {
        Optional<Staff> existingStaff = staffService.getStaffByKy(staffId);
        if (existingStaff.isPresent()) {
            staffService.delete(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
