    package iss4u.ehr.backoffice.parameterization.prescription.entities;
    
    import com.fasterxml.jackson.annotation.*;
    import lombok.*;
    
    import javax.persistence.*;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.time.LocalTime;
    import java.util.Date;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;
    
    @AllArgsConstructor
    @NoArgsConstructor
    //@Data
    @Getter
    @Setter
    @Entity
    @Table(name = "prescription")
    public class Prescription {
        @Id
        @Column(name="prescription_Key", nullable=false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer prescriptionKey;

        @Column (name = "status")
        @Enumerated(EnumType.STRING)
        private Status status;
        @Column(name = "treatmentDuration")
        private String treatmentDuration;
        @Column (name ="dosage")
        private String dosage;
        @Column(name = "specialInstructions")
        private String specialInstructions;
        @Column (name = "prescriptionDate")
        private Date prescriptionDate;
        @Column (name = "prescriptionTime")
        private LocalTime prescriptionTime;
    
       /* @ManyToOne(fetch = FetchType.EAGER, targetEntity = Medication.class)
        @JsonIgnoreProperties("medications")
        @JoinColumn(name = "medicationId")
        private Medication medication;*/
    
        // A prescription can have
    
        /* this part is ignored in the new work
        @ManyToOne
        @JoinColumn(name = "medication_id")
        @JsonIgnoreProperties("prescriptions")
        private Medication medication;*/
    
        // many prescritions can be assigned to one patient
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "patientId") // Name of the foreign key column in Prescription table
        //@JsonIgnoreProperties("prescriptions")
        @JsonBackReference
        private Patient patient;

        // one prescription can have many medicationParts
       /* @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "medicationPart_Key")
        private List<MedicationPart> medicationParts ;*/

        @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<MedicationPart> medicationParts;





    }
