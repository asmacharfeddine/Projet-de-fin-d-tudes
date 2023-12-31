package iss4u.ehr.backoffice.parameterization.human_resources.services.implementation;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffPassword;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffPasswordRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffPasswordRepository staffPasswordRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, StaffPasswordRepository staffPasswordRepository) {
        this.staffRepository = staffRepository;
        this.staffPasswordRepository = staffPasswordRepository;
    }

    @Override
    public void create(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public StaffPassword createStaffPassword(Optional<Staff> staffOptional, StaffPassword password) {
        if (staffOptional.isEmpty()) {
            throw new IllegalArgumentException("Staff not found");
        }

        Staff staff = staffOptional.get();
        password.setStaff(staff);
        return staffPasswordRepository.save(password);
    }

    @Override
    public List<StaffPassword> getStaffPasswords(Optional<Staff> staffOptional) {
        if (staffOptional.isEmpty()) {
            throw new IllegalArgumentException("Staff not found");
        }

        Staff staff = staffOptional.get();
        return staffPasswordRepository.findByStaff(staff);
    }

    @Override
    public List<Staff> retrieveStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> getStaffByKy(Long staffKy) {
        return staffRepository.findById(staffKy);
    }

    @Override
    public void update(Long staffKy, Staff updatedStaff) {
        Staff existingStaff = staffRepository.findById(staffKy)
                .orElseThrow(() -> new EntityNotFoundException("Staff with id " + staffKy + " not found"));

        // Update the properties
        existingStaff.setStaffImage(updatedStaff.getStaffImage());
        existingStaff.setFirstName(updatedStaff.getFirstName());
        existingStaff.setLastName(updatedStaff.getLastName());
        existingStaff.setCvlStatus(updatedStaff.getCvlStatus());
        existingStaff.setMaidenName(updatedStaff.getMaidenName());
        existingStaff.setNationality(updatedStaff.getNationality());
        existingStaff.setGender(updatedStaff.getGender());
        existingStaff.setBirthDate(updatedStaff.getBirthDate());
        existingStaff.setStaffUnxTmUpdt(updatedStaff.getStaffUnxTmUpdt());

        staffRepository.save(existingStaff);

    }

    @Override
    public void delete(Long staffKy) {
        staffRepository.findById(staffKy);
    }
}
