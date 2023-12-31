package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "nurses")
public class Nurse extends Userr{
   /* @ManyToMany(fetch = FetchType.LAZY, cascade ={ CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("medications")
    @JoinTable(name = "MEDICATION_ACTIVE_INGREDIENT_LINK",
            joinColumns = {
                    @JoinColumn(name = "medication_id", referencedColumnName = "Medication_Key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "activeIngredient_id", referencedColumnName = "Active_Ingredients_Key")
            })
    private Set<ActiveIngredient> activeIngredients = new HashSet<>();*/


    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("nurses")
    @JoinTable(name = "NURSE_PATIENTS_LINK",
            joinColumns = @JoinColumn(name = "nurseId", referencedColumnName = "userKey"),
            inverseJoinColumns = @JoinColumn(name = "patientId", referencedColumnName = "userKey"))
    private Set<Patient> patients = new HashSet<>();

}
