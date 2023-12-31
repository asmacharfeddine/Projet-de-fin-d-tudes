package iss4u.ehr.backoffice.parameterization.structure.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Service")
public class Service {

    //--- SERVICE PRIMARY KEYS
    @Id

    @Column(name="Service_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceKy;

    //--- SERVICE DATA FIELDS
    @Column(name="Service_Nm", nullable=false, unique = true)
    private String serviceNm ;

    //--- SERVICE TEMPORAL DATA
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Service_UnxTmCrt", nullable=false)
    private Date serviceUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Service_UnxTmUpdt", nullable=false)
    private Date serviceUnxTmUpdt;

    @Column(name="Service_RcrdSts", nullable=false)
    private Integer serviceRcrdSts ;

    //--- SERVICE LINKS ( RELATIONSHIP )
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Site_Ref")
    private Site site;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<ServiceArea> serviceAreas ;
}
