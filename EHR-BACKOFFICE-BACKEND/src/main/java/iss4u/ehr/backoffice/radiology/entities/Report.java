package iss4u.ehr.backoffice.radiology.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Report_Ky", nullable=false)
    private Long reportKy ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Report_UnxTmCrt", nullable=false)
    private Date reportUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Report_UnxTmUpdt", nullable=false)
    private Date reportUnxTmUpdt;

    @Column(name="Report_RcrdSts", nullable=false)
    private Integer reportRcrdSts ;
}
