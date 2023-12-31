package iss4u.ehr.backoffice.parameterization.prescription.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
@Entity
@Table(name = "Patient")
public class Patient extends Userr {

    /*@Id
    @Column(name="patientId", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;*/


    @Column (name= "gender")
    private String gender;
    @Column(name = "surgeryDate")
    private String surgeryDate;
    @Column(name = "patientBirthDate")
    private Date patientBirthDate;
    @Column(name = "bloodGroup")
    private String bloodGroup;
    @Column(name = "phoneNumber")
    private Long phoneNumber;
    @Column (name = "address")
    private String address;
    @Column(name = "surgeryType")
    @Enumerated(EnumType.STRING)
    private surgeryType surgeryType;

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    @JsonIgnoreProperties("patient")
    @JsonManagedReference
    private Set<Prescription> prescriptions = new HashSet<>();


    // Custom method to get all prescriptions for the patient
    public Set<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    /*@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Prescription> prescriptions = new HashSet<>();
*/

    /*public List<DayTakes> getAllDayTakes() {
        List<DayTakes> allDayTakes = new ArrayList<>();

        for (Prescription prescription : prescriptions) {
            for (MedicationPart medicationPart : prescription.getMedicationParts()) {
                allDayTakes.addAll(medicationPart.getDayTakes());
            }
        }

        return allDayTakes;
    }*/


    // with doctor
    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    //with nurses
    @JsonIgnore
    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private Set<Nurse> nurses = new HashSet<>();
    /*@ManyToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("Active_Ingredients")
    private Set<Doctor> doctors = new HashSet<>();*/

    //with nurse

    /*@ManyToMany(mappedBy = "activeIngredients", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("Active_Ingredients")
    private Set<Medication> medications = new HashSet<>();*/
}
