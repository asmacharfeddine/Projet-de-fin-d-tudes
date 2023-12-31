package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Active_Ingredients")
public class ActiveIngredient {

    @Id
    @Column(name = "Active_Ingredients_Key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activeIngredients_Key;

    //@Column(name="selected")
  // private boolean selected;

    @NotEmpty
    @Column(name = "valueName", nullable = false)  // Removed extra spaces
    private String valueName;


    @ManyToMany(mappedBy = "activeIngredients", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("Active_Ingredients")
    private Set<Medication> medications = new HashSet<>();
}
