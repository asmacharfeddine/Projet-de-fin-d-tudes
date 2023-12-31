package iss4u.ehr.backoffice.parameterization.human_resources.repositories;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffPasswordRepository extends JpaRepository<StaffPassword, Long> {
    List<StaffPassword> findByStaff(Staff staff);
}
