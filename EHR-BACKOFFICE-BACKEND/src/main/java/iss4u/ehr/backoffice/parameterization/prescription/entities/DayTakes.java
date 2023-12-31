package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// to specify a class as a jp entity
@Entity
// @Table annotation to specify the table details
@Table(name = "dayTakes")
public class DayTakes {
    @Id
    // Annotation for auto_increment the key (id)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dayTakesKey", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayTakesKey;

    @Column(name = "takeDate")
    private Date takeDate;

    @Column(name = "takeTime")
    private LocalTime takeTime;

    @NotEmpty
    @Column(name = "medicationName")
    private String medicationName;

    @Column(name = "takeNotes")
    private String takeNotes;


    @ManyToOne
    @JoinColumn(name = "medicationPartKey")
    @JsonBackReference
    private MedicationPart medicationPart;
}
