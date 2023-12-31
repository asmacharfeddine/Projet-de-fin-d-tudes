package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// to specify a class as a jp entity
@Entity
@Table(name = "PhysicalTreatmentCategory")
public class PhysicalTreatmentCategory {
    @Id
    // Annotation for auto_increment the key (id)
    @Column(name="PhysicalTreatmentCategory_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer physicalTreatmentCategory_Key;
   @NotEmpty
    @Column(name ="categoryName")
    private String categoryName;
    @Column(name ="categoryDescription")
    private String categoryDescription;

    @Override
    public String toString() {
        return "PhysicalTreatmentCategory{" +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "physicalTreatmentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("physicalTreatmentCategory")
    private Set<PhysicalTreatment> physicalTreatments;

}
