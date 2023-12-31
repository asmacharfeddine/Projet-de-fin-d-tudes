package iss4u.ehr.backoffice.config;

import iss4u.ehr.backoffice.radiology.entities.Series;
import iss4u.ehr.backoffice.radiology.entities.Study;
import iss4u.ehr.backoffice.radiology.repositories.SeriesRepository;
import iss4u.ehr.backoffice.radiology.repositories.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrp;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrpLink;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffGrpRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffRepository;
import iss4u.ehr.backoffice.parameterization.human_resources.repositories.StaffGrpLinkRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final StaffGrpRepository staffGrpRepository;
    private final StaffRepository staffRepository;
    private final StaffGrpLinkRepository staffGrpLinkRepository;
    private final StudyRepository studyRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public DataInitializer(
            StaffGrpRepository staffGrpRepository,
            StaffRepository staffRepository,
            StaffGrpLinkRepository staffGrpLinkRepository,
            StudyRepository studyRepository, SeriesRepository seriesRepository) {
        this.staffGrpRepository = staffGrpRepository;
        this.staffRepository = staffRepository;
        this.staffGrpLinkRepository = staffGrpLinkRepository;
        this.studyRepository = studyRepository;
        this.seriesRepository = seriesRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Faker faker = new Faker();

//        // Generate fake Staff Groups
//        List<StaffGrp> fakeStaffGroups = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            StaffGrp staffGrp = new StaffGrp();
//            staffGrp.setStaffGrpName(faker.company().name());
//            staffGrp.setStaffGrpType("Type " + (i + 1));
//            staffGrp.setStaffGrpRcrdSts(faker.number().numberBetween(0, 2));
//            staffGrp.setStaffGrpPrvlgd(faker.number().numberBetween(0, 2));
//            staffGrp.setStaffGrpUnxTmCrt(new Date());
//            staffGrp.setStaffGrpUnxTmUpdt(new Date());
//            // Populate other staffGrp properties...
//
//            fakeStaffGroups.add(staffGrp);
//        }
//        staffGrpRepository.saveAll(fakeStaffGroups);
//
//        // Generate fake Staff Members
//        List<Staff> fakeStaffMembers = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Staff staff = new Staff();
//            staff.setStaffImage("image_" + i + ".jpg");
//            staff.setLastName(faker.name().lastName());
//            staff.setFirstName(faker.name().firstName());
//            staff.setStaffId("ID" + i);
//            staff.setMaidenName(faker.name().lastName());
//            staff.setBirthDate(faker.date().birthday());
//            staff.setGender(faker.options().option("Male", "Female"));
//            staff.setCvlStatus(faker.options().option("Single", "Married", "Divorced"));
//            staff.setNationality(faker.address().country());
//            staff.setStaffUnxTmCrt(new Date());
//            staff.setStaffUnxTmUpdt(new Date());
//            staff.setStaffRcrdSts(faker.number().numberBetween(0, 3));
//
//            // Populate other staff properties...
//
//            fakeStaffMembers.add(staff);
//        }
//        staffRepository.saveAll(fakeStaffMembers);
//
//        // Generate and associate StaffGrpLinks
//        List<StaffGrpLink> staffGrpLinks = new ArrayList<>();
//        for (Staff staff : fakeStaffMembers) {
//            StaffGrp randomStaffGrp = fakeStaffGroups.get(faker.number().numberBetween(0, fakeStaffGroups.size()));
//            StaffGrpLink staffGrpLink = new StaffGrpLink(randomStaffGrp, staff);
//            staffGrpLinks.add(staffGrpLink);
//        }
//        staffGrpLinkRepository.saveAll(staffGrpLinks);

        List<Study> fakeStudies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Study study = new Study();
            study.setStudyLabel("Study " + (i + 1));
            study.setStudyDesc(faker.lorem().sentence());
            study.setStudyComment(faker.lorem().paragraph());
            study.setStudyRfrntphyscn(faker.name().fullName());
            study.setStudyPrfrmngphyscn(faker.name().fullName());
            study.setStudyAetitle("AETitle" + i);
            study.setStudyType(faker.options().option("Type1", "Type2", "Type3"));
            study.setStudyStatus(faker.options().option("Status1", "Status2", "Status3"));
            study.setStudyPriority(faker.options().option("High", "Medium", "Low"));
            study.setStudyUnxTmCrt(new Date());
            study.setStudyUnxTmUpdt(new Date());
            study.setStudyRcrdSts(faker.number().numberBetween(0, 3));

            fakeStudies.add(study);
        }
        studyRepository.saveAll(fakeStudies);

        List<Series> fakeSeries = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Series series = new Series();
            series.setSeriesDesc(faker.lorem().sentence());
            series.setSeriesBodyPart(faker.animal().name());
            series.setSeriesDcmModality(faker.options().option("CT", "MRI", "X-ray"));
            series.setSeriesDlp(faker.number().numberBetween(100, 1000) + " mGy/cm");
            series.setSeriesUnxTmCrt(new Date());
            series.setSeriesUnxTmUpdt(new Date());
            series.setSeriesRcrdSts(faker.number().numberBetween(0, 3));

            Study randomStudy = fakeStudies.get(faker.number().numberBetween(0, fakeStudies.size()));
            series.setStudy(randomStudy);

            fakeSeries.add(series);
        }
        seriesRepository.saveAll(fakeSeries);

    }
}
