package iss4u.ehr.backoffice.parameterization.human_resources.entities;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Staff")
public class Staff {
    @Id

    @Column(name="Staff_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffKy;

    @Column(name="Staff_Image", nullable=false)
    private String staffImage;

    @Column(name="Last_Name", nullable=false)
    private String lastName;

    @Column(name="First_Name", nullable=false)
    private String firstName;

    @Column(name="Staff_Id", nullable=false, unique = true)
    private String staffId ;

    @Column(name="Maiden_Name", nullable=false)
    private String maidenName;

    @Column(name="Birth_Date", nullable=false)
    private Date birthDate;

    @Column(name="Gender", nullable=false)
    private String gender;

    @Column(name="civil_status", nullable=false)
    private String cvlStatus;

    @Column(name="nationality", nullable=false)
    private String nationality;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Staff_UnxTmCrt", nullable=false)
    private Date staffUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="staff_UnxTmUpdt", nullable=false)
    private Date staffUnxTmUpdt;

    @Column(name="staff_RcrdSts", nullable=false)
    private Integer staffRcrdSts ;

    // ( RELATIONSHIP )

    @OneToMany(mappedBy = "staff")
    private List<StaffGrpLink> staffGrpLinks = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<StaffPassword> staffPasswords = new ArrayList<>();

}
