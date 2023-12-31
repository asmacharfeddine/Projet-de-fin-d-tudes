package iss4u.ehr.backoffice.parameterization.medical_record.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Cpt_Code")
public class CptCode {
    @Id

    @Column(name="cptCode_surgical_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer surgical_Key;

    @Column(name = "cptCode_text", nullable = false)
    private String txt;

    @Column(name = "cptCode_Phase", nullable = false)
    private String phase;

    @Column(name = "cptCode_Sector_Tarif", nullable = false)
    private String secTarif;

    @Column(name = "cptCode_NonSector_Tarif", nullable = false)
    private String nnSecTarif;

    @Column(name = "cptCode_prior_agreement", nullable = false)
    private String priorAgreement;

    @Column(name = "cptCode_Exo_TM", nullable = false)
    private String exoTM;

    @Column(name="cptCode_Rembt_ss_Cditions", nullable=false)
    private String rembt_ss_Cditions ;

    @Column(name="cptCode_grouping", nullable=false)
    private String grouping ;

    @Column(name="cptCode_RcrdSts", nullable=false)
    private Integer CptCodeRcrdSts ;

}


