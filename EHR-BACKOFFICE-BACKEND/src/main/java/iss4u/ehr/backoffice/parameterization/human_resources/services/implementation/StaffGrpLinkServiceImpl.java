package iss4u.ehr.backoffice.parameterization.human_resources.services.implementation;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrp;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrpLink;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffGrpLinkRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffGrpRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.services.StaffGrpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffGrpLinkServiceImpl implements StaffGrpLinkService {
    @Autowired
    private StaffGrpLinkRepository staffGroupLinkRepository;
    private final StaffRepository staffRepository;
    private final StaffGrpLinkRepository staffGrpLinkRepository;
    private final StaffGrpRepository staffGrpRepository;

    public StaffGrpLinkServiceImpl(StaffGrpLinkRepository staffGroupLinkRepository, StaffRepository staffRepository, StaffGrpLinkRepository staffGrpLinkRepository, StaffGrpRepository staffGrpRepository) {
        this.staffGroupLinkRepository = staffGroupLinkRepository;
        this.staffRepository = staffRepository;
        this.staffGrpLinkRepository = staffGrpLinkRepository;
        this.staffGrpRepository = staffGrpRepository;
    }

    @Override
    public List<StaffGrpLink> retrieveStaffGrpLinks() {
        return staffGrpLinkRepository.findAll();
    }
    @Override
    public void assignStaffToGroup(Long staffKy, Long staffGrpKy) {
        Optional<Staff> staffOptional = staffRepository.findById(staffKy);
        Optional<StaffGrp> staffGrpOptional = staffGrpRepository.findById(staffGrpKy);

        if (staffOptional.isPresent() && staffGrpOptional.isPresent()) {
            Staff staff = staffOptional.get();
            StaffGrp staffGrp = staffGrpOptional.get();

            StaffGrpLink staffGrpLink = new StaffGrpLink();
            staffGrpLink.setStaff(staff);
            staffGrpLink.setStaffGrp(staffGrp);

            staff.getStaffGrpLinks().add(staffGrpLink);
            staffGrp.getStaffGrpLinks().add(staffGrpLink);

            staffRepository.save(staff);
            staffGrpRepository.save(staffGrp);

            staffGrpLinkRepository.save(staffGrpLink);
        } else {
            throw new IllegalArgumentException("Staff or StaffGroup not found. Please provide valid IDs.");
        }
    }

    @Override
    public void unassignStaffFromGroup(Long staffKy, Long staffGrpKy) {
        Optional<Staff> staffOptional = staffRepository.findById(staffKy);
        Optional<StaffGrp> staffGrpOptional = staffGrpRepository.findById(staffGrpKy);

        if (staffOptional.isPresent() && staffGrpOptional.isPresent()) {
            Staff staff = staffOptional.get();
            StaffGrp staffGrp = staffGrpOptional.get();

            StaffGrpLink staffGrpLinkToRemove = null;
            for (StaffGrpLink staffGrpLink : staff.getStaffGrpLinks()) {
                if (staffGrpLink.getStaffGrp().equals(staffGrp)) {
                    staffGrpLinkToRemove = staffGrpLink;
                    break;
                }
            }

            if (staffGrpLinkToRemove != null) {
                staff.getStaffGrpLinks().remove(staffGrpLinkToRemove);
                staffGrp.getStaffGrpLinks().remove(staffGrpLinkToRemove);

                staffRepository.save(staff);
                staffGrpRepository.save(staffGrp);

                staffGrpLinkRepository.delete(staffGrpLinkToRemove);
            }
        } else {
            throw new IllegalArgumentException("Staff or StaffGroup not found. Please provide valid IDs.");
        }
    }
}
