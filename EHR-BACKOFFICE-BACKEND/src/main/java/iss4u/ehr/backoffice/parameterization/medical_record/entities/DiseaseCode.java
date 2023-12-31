package iss4u.ehr.backoffice.parameterization.medical_record.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Disease_Code")
public class DiseaseCode {
    @Id
    @Column(name="Disease_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Disease_Key;

    @Column(name = "Chapter", nullable = false)
    private String chapter;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "Sub_Category", nullable = false)
    private String subCategory;

    @Column(name = "Bloc", nullable = false)
    private String bloc;

    @Column(name="DiseaseCodeRcrdSts", nullable=false)
    private Integer diseaseCodeRcrdSts ;

}


