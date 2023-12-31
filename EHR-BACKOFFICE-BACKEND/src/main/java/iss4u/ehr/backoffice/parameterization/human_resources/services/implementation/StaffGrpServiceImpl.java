package iss4u.ehr.backoffice.parameterization.human_resources.services.implementation;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrp;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrpLink;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffGrpLinkRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffGrpRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.services.StaffGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StaffGrpServiceImpl implements StaffGrpService {
    private final StaffGrpRepository staffGrpRepository;
    private final StaffGrpLinkRepository staffGrpLinkRepository;

    @Autowired
    public StaffGrpServiceImpl(StaffGrpRepository staffGrpRepository, StaffGrpLinkRepository staffGrpLinkRepository) {
        this.staffGrpRepository = staffGrpRepository;
        this.staffGrpLinkRepository = staffGrpLinkRepository;
    }

    @Override
    public void create(StaffGrp staffGrp) {
        staffGrpRepository.save(staffGrp);
    }

    @Override
    public List<StaffGrp> retrieveStaffGrps() {
        return staffGrpRepository.findAll();
    }

    @Override
    public Optional<StaffGrp> getStaffGrpByKy(Long staffGrpKy) {
        return staffGrpRepository.findById(staffGrpKy);
    }

    @Override
    public void update(Long staffGrpKy, StaffGrp updatedStaffGrp) {
        StaffGrp existingStaffGrp = staffGrpRepository.findById(staffGrpKy)
                .orElseThrow(() -> new EntityNotFoundException("Staff Group with id " + staffGrpKy + " not found"));

        // Update the properties
        existingStaffGrp.setStaffGrpName(updatedStaffGrp.getStaffGrpName());
        existingStaffGrp.setStaffGrpType(updatedStaffGrp.getStaffGrpType());
        existingStaffGrp.setStaffGrpPrvlgd(updatedStaffGrp.getStaffGrpPrvlgd());
        existingStaffGrp.setStaffGrpUnxTmUpdt(updatedStaffGrp.getStaffGrpUnxTmUpdt());

        staffGrpRepository.save(existingStaffGrp);
    }

    @Override
    public void delete(Long staffGrpKy) {
        staffGrpRepository.deleteById(staffGrpKy);
    }

    @Override
    public List<Staff> getChildElements(StaffGrp staffGrp) {
        Optional<StaffGrp> staffGrpOptional = staffGrpRepository.findById(staffGrp.getStaffGrpKy());

        if(staffGrpOptional.isPresent()) {
            return staffGrpOptional.get().getStaffs();
        }

        return new ArrayList<>(); // StaffGrp Not found
    }


    @Override
    public void addChildElement(Optional<StaffGrp> staffGrpOptional, Staff staff) {
        if (staffGrpOptional.isPresent()) {
            StaffGrp staffGrp = staffGrpOptional.get();
            StaffGrpLink staffGrpLink = new StaffGrpLink();

            staffGrpLink.setStaffGrp(staffGrp);
            staffGrpLink.setStaff(staff);
            staffGrpLinkRepository.save(staffGrpLink);
            staffGrp.getStaffGrpLinks().add(staffGrpLink);

            staffGrpRepository.save(staffGrp);
        } else {
            throw new EntityNotFoundException("Staff Group not found");
        }
    }


}
