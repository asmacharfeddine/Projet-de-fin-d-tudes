package iss4u.ehr.backoffice.parameterization.structure.services.implementation;

import iss4u.ehr.backoffice.parameterization.structure.entities.Site;
import iss4u.ehr.backoffice.parameterization.structure.entities.SiteGrp;
import iss4u.ehr.backoffice.parameterization.structure.repositories.SiteGrpRepository;
import iss4u.ehr.backoffice.parameterization.structure.services.SiteGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SiteGrpServiceImpl implements SiteGrpService {

    private final SiteGrpRepository siteGrpRepository;

    @Autowired
    public SiteGrpServiceImpl(SiteGrpRepository siteGrpRepository) {
        this.siteGrpRepository = siteGrpRepository;
    }

    @Override
    public void create(SiteGrp siteGrp) {
        siteGrpRepository.save(siteGrp);
    }

    @Override
    public List<SiteGrp> retrieveSiteGrps() {
        return siteGrpRepository.findAll();
    }

    @Override
    public Optional<SiteGrp> getSiteGrpByKy(Long siteGrpKy) {
        return siteGrpRepository.findById(siteGrpKy);
    }

    @Override
    public SiteGrp getSiteGrpByNm(String siteGrpNm) {
        return siteGrpRepository.findBySiteGrpNm(siteGrpNm);
    }

    public String getSiteGroupNameByKey(Long siteGroupKey) {
        Optional<SiteGrp> siteGroupOptional = siteGrpRepository.findById(siteGroupKey);
        return siteGroupOptional.isPresent() ? siteGroupOptional.get().getSiteGrpNm() : "";
    }

    @Override
    public void update(Long siteGrpKy, SiteGrp updatedSiteGrp) {
        SiteGrp existingSiteGrp = siteGrpRepository.findById(siteGrpKy)
                .orElseThrow(() -> new EntityNotFoundException("Site group with id " + siteGrpKy + " not found"));

        // Update the properties
        existingSiteGrp.setSiteGrpNm(updatedSiteGrp.getSiteGrpNm());
        existingSiteGrp.setSiteGrpUnxTmUpdt(updatedSiteGrp.getSiteGrpUnxTmUpdt());

        siteGrpRepository.save(existingSiteGrp);
    }

    @Override
    public void delete(Long siteGrpKy) {
        siteGrpRepository.deleteById(siteGrpKy);
    }

    @Override
    public List<Site> getChildElements(SiteGrp siteGrp) {
        return siteGrp.getSites();
    }

    @Override
    public void addChildElement(SiteGrp siteGrp, Site site) {
        List<Site> sites = siteGrp.getSites();
        sites.add(site);
        site.setSiteGrp(siteGrp);
        siteGrpRepository.save(siteGrp);
    }
}
