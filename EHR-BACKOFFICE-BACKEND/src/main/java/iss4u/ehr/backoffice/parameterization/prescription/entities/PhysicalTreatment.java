package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// to specify a class as a jp entity
@Entity
// @Table annotation to specify the table details
@Table(name = "PhysicalTreatment")
public class PhysicalTreatment {
    @Id
    @Column(name="PhysicalTreatment_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer physicalTreatment_Key;

    @NotEmpty // le nom doit etre unique
    @Column(name = "treatmentName")
    private String treatmentName;

    @Column(name ="TreatmentDescription" )
    private String treatmentDescription;
    // la durée prévu pour ce traitement
    @Column(name = "duration")
    private String duration;

    @Column(name = "notes")
    private String notes;

    @Override
    public String toString() {
        return "PhysicalTreatment{" +
                "PhysicalTreatment_Key=" + physicalTreatment_Key +
                ", treatmentName='" + treatmentName + '\'' +
                ", treatmentDescription='" + treatmentDescription + '\'' +
                ", duration='" + duration + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PhysicalTreatmentCategory.class)
    @JsonIgnoreProperties("physicalTreatments")
    @JoinColumn(name = "categoryId")
    private PhysicalTreatmentCategory physicalTreatmentCategory;

}
