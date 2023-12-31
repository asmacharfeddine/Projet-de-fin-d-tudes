package iss4u.ehr.backoffice.parameterization.medical_record.entities;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.SideEff;
import lombok.*;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Vaccination")
public class Vaccination {
    @Id
    @Column(name="vaccination_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccination_Key;
    @Column(name = "Vaccination_name", nullable = false)
    private String VacNm;
    @Column(name = "Vac_drug", nullable = false)
    private String VacDrg;
    @Column(name = "Manufacturer", nullable = false)
    private String Mnf;
    @Column(name = "Side_Effects", nullable = false)
    private SideEff Side_eff;
    @Column(name = "Vaccination_type", nullable = false)
    private String VacTyp;

    @Column(name="Vaccination_RcrdSts", nullable=false)
    private Integer VaccinationRcrdSts ;

}

