package iss4u.ehr.backoffice.parameterization.human_resources.entities;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Staff_Grp")
public class StaffGrp {
    @Id

    @Column(name="StaffGrp_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffGrpKy;

    @Column(name="StaffGrp_Name", nullable=false, unique = true)
    private String staffGrpName;

    @Column(name="StaffGrp_Type", nullable=false)
    private String staffGrpType ;

    @Column(name="StaffGrp_Prvlgd", nullable=false)
    private Integer staffGrpPrvlgd ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="StaffGrp_UnxTmCrt", nullable=false)
    private Date staffGrpUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="StaffGrp_UnxTmUpdt", nullable=false)
    private Date staffGrpUnxTmUpdt;

    @Column(name="StaffGrp_RcrdSts", nullable=false)
    private Integer staffGrpRcrdSts ;

    //( RELATIONSHIP )

    @OneToMany(mappedBy = "staffGrp")
    private List<StaffGrpLink> staffGrpLinks = new ArrayList<>();

    public List<Staff> getStaffs() {
        return staffGrpLinks.stream()
                .map(StaffGrpLink::getStaff)
                .collect(Collectors.toList());
    }

}
