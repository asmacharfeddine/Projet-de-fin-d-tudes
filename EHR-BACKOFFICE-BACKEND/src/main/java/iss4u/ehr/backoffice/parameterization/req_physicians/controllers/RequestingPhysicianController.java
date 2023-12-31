package iss4u.ehr.backoffice.parameterization.req_physicians.controllers;

import iss4u.ehr.backoffice.parameterization.req_physicians.entities.RequestingPhysician;
import iss4u.ehr.backoffice.parameterization.req_physicians.services.RequestingPhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parameterization/requesting-physicians")
public class RequestingPhysicianController {
    private final RequestingPhysicianService requestingPhysicianService;

    @Autowired
    public RequestingPhysicianController(RequestingPhysicianService requestingPhysicianService) {
        this.requestingPhysicianService = requestingPhysicianService;
    }

    @GetMapping
    public List<RequestingPhysician> getAllRequestingPhysicians() {
        return requestingPhysicianService.getAllRequestingPhysicians();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestingPhysician> getRequestingPhysicianById(@PathVariable Long id) {
        return requestingPhysicianService.getRequestingPhysicianById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RequestingPhysician> createRequestingPhysician(@RequestBody RequestingPhysician requestingPhysician) {
        RequestingPhysician savedPhysician = requestingPhysicianService.saveRequestingPhysician(requestingPhysician);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPhysician);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRequestingPhysician(@PathVariable Long id, @RequestBody RequestingPhysician requestingPhysician) {
        requestingPhysicianService.updateRequestingPhysician(id, requestingPhysician);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequestingPhysician(@PathVariable Long id) {
        requestingPhysicianService.deleteRequestingPhysician(id);
        return ResponseEntity.noContent().build();
    }
}
