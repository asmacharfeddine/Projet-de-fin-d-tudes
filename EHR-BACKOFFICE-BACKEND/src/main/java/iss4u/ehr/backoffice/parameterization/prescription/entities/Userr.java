package iss4u.ehr.backoffice.parameterization.prescription.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
//@Entity
@Table(name = "Userr")
@MappedSuperclass
public class Userr {

    @Id
    @Column(name="userKey", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userKey;

    @Column(name = "userFirstName")
    private String userFirstName;
    @Column(name = "userLastName")
    private String userLastName;


    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile")
    private String mobile;

    @Override
    public String toString() {
        return "User{" +
                "userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }
}
