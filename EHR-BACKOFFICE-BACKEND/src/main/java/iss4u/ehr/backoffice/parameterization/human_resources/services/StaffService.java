package iss4u.ehr.backoffice.parameterization.human_resources.services;


import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffPassword;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    void create(Staff staff);

    List<Staff> retrieveStaffs();

    Optional<Staff> getStaffByKy(Long staffKy);

    void update(Long staffKy, Staff updatedStaff);

    void delete(Long staffKy);

    StaffPassword createStaffPassword(Optional<Staff> staff, StaffPassword password);

    List<StaffPassword> getStaffPasswords(Optional<Staff> staff);
}
