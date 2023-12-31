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
@Table(name = "doctors")
public class Doctor extends Userr{
    /*@Id
    @Column(name="doctorId", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;*/

   /* @ManyToMany(fetch = FetchType.LAZY, cascade ={ CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("medications")
    @JoinTable(name = "DOCTOR_PATIENTS_LINK",
            joinColumns = {
                    @JoinColumn(name = "doctorId", referencedColumnName = "userKey")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "patientId", referencedColumnName = "userKey")
            })
    private Set<Patient> Patients = new HashSet<>();*/


    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("doctors")
    @JoinTable(name = "DOCTOR_PATIENTS_LINK",
            joinColumns = @JoinColumn(name = "doctorId", referencedColumnName = "userKey"),
            inverseJoinColumns = @JoinColumn(name = "patientId", referencedColumnName = "userKey"))
    private Set<Patient> patients = new HashSet<>();


}
