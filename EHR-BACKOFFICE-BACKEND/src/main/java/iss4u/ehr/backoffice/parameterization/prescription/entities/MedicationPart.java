package iss4u.ehr.backoffice.parameterization.prescription.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static iss4u.ehr.backoffice.parameterization.prescription.entities.Status.inProgress;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
@Entity
@Table(name = "medicationPart")
public class MedicationPart {
    @Id
    @Column(name="medicationPartKey", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicationPartKey;


    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    // 25 jours par exemple
   @Column(name = "period")
    private Integer period;
    // dosage = nb de fois
    @Column (name ="takes")
    private Integer takes;

    @Column (name ="quantity")
    private Integer quantity;

    @Column (name="notes")
    private String notes;

    @Column (name="startDate")
    private Date startDate;

    @Column (name="endDate")
    private Date endDate;

    @Column(name = "takesCount")
    private Integer takesCount = 0;

    @Column(name = "daysCount")
    private Integer daysCount = 0;

    @Column(name = "totalTakesCount")
    private Integer totalTakesCount = 0;

    @Column(name = "totalCount")
    private Integer totalCount = 0;

    // every medicaionPart can have only one medication
    @ManyToOne
    @JoinColumn(name = "medication_id")
    @JsonIgnoreProperties("medicationParts")
    private Medication medication;
    // every medicationPart is assigned to one prescription
   /* @ManyToOne
    @JoinColumn(name = "prescription_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Prescription prescription;*/

    @ManyToOne
    @JoinColumn(name = "prescriptionKey")
    @JsonBackReference
    private Prescription prescription;


    @OneToMany(mappedBy = "medicationPart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DayTakes> dayTakes;

    public void incrementTotalCount() {
        this.totalTakesCount++;
        this.totalCount++;
    }

    public void decrementTotalCount() {
        this.totalTakesCount--;
        this.totalCount--;
    }

    public Integer getPrescriptionKeyForMedicationPart() {
        if (prescription != null) {
            return prescription.getPrescriptionKey();
        }
        return null; // Handle the case when the MedicationPart has no associated prescription
    }
}
