package iss4u.ehr.backoffice.validate_credential;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffPassword;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffPasswordRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialValidatorService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffPasswordRepository staffPasswordRepository;

    public boolean validateCredentials(String staffId, String password) {
        Staff staff = staffRepository.findByStaffId(staffId);

        if (staff == null) {
            return false; // User not found
        }

        List<StaffPassword> staffPasswords = staffPasswordRepository.findByStaff(staff);
        for (StaffPassword staffPassword : staffPasswords) {
            if (staffPassword.getStaffPwdTxt().equals(password)) {
                return true; // Successful validation
            }
        }

        return false; // Incorrect password

    }
}
