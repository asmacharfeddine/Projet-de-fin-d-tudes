package iss4u.ehr.backoffice.parameterization.human_resources.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Staff_Grp_Link")
public class StaffGrpLink {
    @Id

    @Column(name="StaffGrpLink_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffGrpLinkKy;

    // ( RELATIONSHIP )

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "StaffGrp_Ky")
    private StaffGrp staffGrp;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Staff_Ky")
    private Staff staff;

    public StaffGrpLink(StaffGrp randomStaffGrp, Staff staff) {
        this.staffGrp = randomStaffGrp;
        this.staff = staff;
    }

    // additional attributes
}
