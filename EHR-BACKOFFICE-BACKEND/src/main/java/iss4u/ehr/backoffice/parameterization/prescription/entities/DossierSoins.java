package iss4u.ehr.backoffice.parameterization.prescription.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "DossierSoins")
public class DossierSoins {
    @Id
    @Column(name="DossierSoins_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dossierSoins_Key;
}
