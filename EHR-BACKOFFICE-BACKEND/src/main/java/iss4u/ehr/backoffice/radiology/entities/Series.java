package iss4u.ehr.backoffice.radiology.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Series")
public class Series {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Series_Ky", nullable=false)
    private Long seriesKy ;

    @Column(name="Series_Desc", nullable=false)
    private String seriesDesc ;

    @Column(name="Series_BodyPart", nullable=false)
    private String seriesBodyPart ;

    @Column(name="Series_DcmModality", nullable=false)
    private String seriesDcmModality ;

    @Column(name="Series_DLP", nullable=false)
    private String seriesDlp ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Series_UnxTmCrt", nullable=false)
    private Date seriesUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Series_UnxTmUpdt", nullable=false)
    private Date seriesUnxTmUpdt;

    @Column(name="Series_RcrdSts", nullable=false)
    private Integer seriesRcrdSts ;

    // ( RELATIONSHIP )
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Study_Ky")
    private Study study;
}
