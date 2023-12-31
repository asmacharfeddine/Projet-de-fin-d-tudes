package iss4u.ehr.backoffice.parameterization.medical_record.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Bio_Analyses")
public class BioAnalyses {
    @Id
    @Column(name="analyse_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer analyse_Key;

    @Column(name = "AnalyseName", nullable = false)
    private String ansNm;

    @Column(name = "Unit_Mesure", nullable = false)
    private String unitMesure;

    @Column(name = "Min_ref", nullable = false)
    private String minRef;

    @Column(name = "Max_ref", nullable = false)
    private String maxRef;

    @Column(name = "BioAnalyse_type", nullable = false)
    private String bioAnsTyp;

    @Column(name="AnalyseRcrdSts", nullable=false)
    private Integer analyseRcrdSts ;

}


