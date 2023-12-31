package iss4u.ehr.backoffice.parameterization.prescription.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
@Entity
@Table(name = "treamtment")
public class Treatment {
    @Id
    @Column(name="treatment_Key", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer treatment_Key;

    @NotEmpty
    @Column(name = "type", nullable=false)
    private String type;
    @NotEmpty
    @Column(name = "frequency")
    private String frequency;
    @NotEmpty
    @Column(name = "duration")
    private String duration;
    @NotEmpty
    @Column(name = "administrationDate")
    private Date administrationDate;
    @NotEmpty
    @Column(name = "administrationTime")
    private Date administrationTime;

    @Column(name = "treatmentActions")
    private String treatmentActions;


}
