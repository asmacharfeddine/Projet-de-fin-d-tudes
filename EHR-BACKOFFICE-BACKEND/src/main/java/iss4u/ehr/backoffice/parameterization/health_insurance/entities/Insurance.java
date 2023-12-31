package iss4u.ehr.backoffice.parameterization.health_insurance.entities;



import lombok.*;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Insurance")
public class Insurance {
    @Id
    @Column(name="Insurance_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insKy;
    @Column(name="Name_insurance", nullable=false)
    private String insNm ;
    @Column(name="Number_insurance", nullable=false)
    private Integer insNumber ;
    @Column(name="Name_policy", nullable=false)
    private String policyNm;
    @Column(name="Number_policy", nullable=false)
    private Integer policyNum ;
    @Column(name="Website", nullable=false)
    private String Website ;
    @Column(name="Type_policy", nullable=false)
    private PolicyType policyType;
    @Column(name="Contact_person_name", nullable=false)
    private String ContactPersonName;
    @Column(name="Contact_person_email", nullable=false)
    private String ContactPersonEmail;
    @Column(name="Contact_person_phone_number", nullable=false)
    private Integer ContactPersonPhoneNumber;
    //
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Insurance_UnxTmCrt", nullable=false)
    private Date insuranceUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Insurance_UnxTmUpdt", nullable=false)
    private Date insuranceUnxTmUpdt;

    @Column(name="insurance_RcrdSts", nullable=false)
    private Integer insuranceRcrdSts ;


}