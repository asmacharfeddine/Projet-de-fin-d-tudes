package iss4u.ehr.backoffice.parameterization.medical_record.entities;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.AlgSymptoms;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Allergy")
public class Allergy {

    @Id
    @Column(name="allergy_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer allergy_Key;

    @Column(name = "AllergyName", nullable = false)
    private String algNm;

    @Column(name = "AllergySymptoms", nullable = false)
    private AlgSymptoms algSym;

    @Column(name = "AllergyGravity", nullable = false)
    private String algGrv;

    @Column(name = "AllergyDescription", nullable = false)
    private String algDesc;

    @Column(name = "AllergyType", nullable = false)
    private String algTyp;

    @Column(name="AllergyRcrdSts", nullable=false)
    private Integer allergyRcrdSts ;

}
