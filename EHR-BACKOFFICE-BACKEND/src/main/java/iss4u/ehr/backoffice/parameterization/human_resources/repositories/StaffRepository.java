package iss4u.ehr.backoffice.parameterization.human_resources.repositories;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByStaffId(String staffId);
}
