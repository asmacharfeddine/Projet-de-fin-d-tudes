package iss4u.ehr.backoffice.parameterization.human_resources.controllers;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrpLink;
import iss4u.ehr.backoffice.parameterization.human_resources.services.StaffGrpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parameterization/staffGrpLink")
public class StaffGrpLinkController {

    @Autowired
    private StaffGrpLinkService staffGrpLinkService;

    @GetMapping
    public ResponseEntity<List<StaffGrpLink>> getAllStaffGrpLinks() {
        List<StaffGrpLink> staffGrpLinks = staffGrpLinkService.retrieveStaffGrpLinks();
        return new ResponseEntity<>(staffGrpLinks, HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignStaffToGroup(
            @RequestParam Long staffKy,
            @RequestParam Long staffGrpKy) {
        staffGrpLinkService.assignStaffToGroup(staffKy, staffGrpKy);
        return ResponseEntity.ok("Staff assigned to group successfully.");
    }

    @PostMapping("/unassign")
    public ResponseEntity<String> unassignStaffFromGroup(
            @RequestParam Long staffKy,
            @RequestParam Long staffGrpKy) {
        staffGrpLinkService.unassignStaffFromGroup(staffKy, staffGrpKy);
        return ResponseEntity.ok("Staff unassigned from group successfully.");
    }

}
