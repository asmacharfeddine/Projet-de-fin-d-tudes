package iss4u.ehr.backoffice.parameterization.req_physicians.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Requesting_Physician")
public class RequestingPhysician {
    @Id

    @Column(name="RequestPhys_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestPhysKy;

    @Column(name="RequestPhys_Image", nullable=false)
    private String requestPhysImage;

    @Column(name="RequestPhys_Last_Name", nullable=false)
    private String requestPhysLastName;

    @Column(name="RequestPhys_First_Name", nullable=false)
    private String requestPhysFirstName;

    @Column(name="RequestPhys_Maiden_Name", nullable=false)
    private String requestPhysMaidenName;

    @Column(name="RequestPhys_Birth_Date", nullable=false)
    private Date requestPhysBirthDate;

    @Column(name="RequestPhys_Gender", nullable=false)
    private String requestPhysGender;

    @Column(name="RequestPhys_Marital_Status", nullable=false)
    private String requestPhysMaritalStatus;

    @Column(name="RequestPhys_Pro_Title", nullable=false)
    private String requestPhysProTitle;

    @Column(name="RequestPhys_Med_Id_Number", nullable=false, unique = true)
    private String requestPhysMedIdNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RequestPhys_UnxTmCrt", nullable=false)
    private Date requestPhysUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RequestPhys_UnxTmUpdt", nullable=false)
    private Date requestPhysUnxTmUpdt;

    @Column(name="RequestPhys_RcrdSts", nullable=false)
    private Integer requestPhysRcrdSts ;

    @Column(name="RequestPhys_Speciality", nullable=false)
    private String requestPhysSpeciality;
}
