package iss4u.ehr.backoffice.radiology.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Study")
public class Study {
    @Id

    @Column(name="Study_Ky", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyKy;

    @Column(name="Study_Label", nullable=false)
    private String studyLabel;

    @Column(name="Study_Desc", nullable=false)
    private String studyDesc ;

    @Column(name="Study_Comment", nullable=false)
    private String studyComment ;

    @Column(name="Study_RfrntPhyscn", nullable=false)
    private String studyRfrntphyscn ;//radiologue

    @Column(name="Study_PrfrmngPhyscn", nullable=false)
    private String studyPrfrmngphyscn ;//technicien

    @Column(name="Study_AETitle", nullable=false)
    private String studyAetitle ;

    @Column(name="Study_Type", nullable=false)
    private String studyType;

    @Column(name="Study_Status", nullable=false)
    private String studyStatus;

    @Column(name="Study_Priority", nullable=false)
    private String studyPriority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Study_UnxTmCrt", nullable=false)
    private Date studyUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Study_UnxTmUpdt", nullable=false)
    private Date studyUnxTmUpdt;

    @Column(name="Study_RcrdSts", nullable=false)
    private Integer studyRcrdSts ;

    // STUDY RELATIONSHIP

    @OneToMany(mappedBy="study")
    private List<Series> listOfSeries ;
}
