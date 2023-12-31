package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// to specify a class as a jp entity
@Entity
// @Table annotation to specify the table details
@Table(name = "medications")
public class Medication {
    // Primary key annotation
    @Id
    // Annotation for auto_increment the key (id)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Medication_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_Key;

    @NotEmpty
    @Column(name = "Medication_code", nullable=false)
    private String code;
    @NotEmpty
    @Column(name = "Medication_name")
    private String name;
    @NotEmpty
    @Column(name = "Medication_dosageForm")
    private String dosageForm;
    @NotEmpty
    @Column(name = "Medication_type")
    private String type;
    @NotEmpty
    @Column(name = "Medication_force")
    private Float force;


    public Medication(String code, String name, String dosageForm, String type, Float force) {
        this.code = code;
        this.name = name;
        this.dosageForm = dosageForm;
        this.type = type;
        this.force=force;

    }

    @Override
    public String toString() {
        return "Medication{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", type='" + type + '\'' +
                ", force=" + force +
                '}';
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade ={ CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("medications")
    @JoinTable(name = "MEDICATION_ACTIVE_INGREDIENT_LINK",
            joinColumns = {
                    @JoinColumn(name = "medication_id", referencedColumnName = "Medication_Key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "activeIngredient_id", referencedColumnName = "Active_Ingredients_Key")
            })
    private Set<ActiveIngredient> activeIngredients = new HashSet<>();

    public List<Integer> getActiveIngredientIds() {
        return activeIngredients.stream()
                .map(activeIngredient -> activeIngredient.getActiveIngredients_Key())
                .collect(Collectors.toList());
    }



   /* this part is ignored in the new work
   @OneToMany(mappedBy = "medication")
    @JsonIgnore
    private List<Prescription> prescriptions;*/


    // every medication can be assigned to many medicationParts
    @OneToMany(mappedBy = "medication")
    @JsonIgnore
    private List<MedicationPart> medicationParts;


}




