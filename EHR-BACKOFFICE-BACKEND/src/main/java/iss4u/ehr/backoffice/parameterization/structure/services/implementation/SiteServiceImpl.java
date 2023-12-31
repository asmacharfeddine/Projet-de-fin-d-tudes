package iss4u.ehr.backoffice.parameterization.structure.services.implementation;

import iss4u.ehr.backoffice.parameterization.structure.entities.Service;
import iss4u.ehr.backoffice.parameterization.structure.entities.Site;
import iss4u.ehr.backoffice.parameterization.structure.repositories.SiteRepository;
import iss4u.ehr.backoffice.parameterization.structure.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;

    @Autowired
    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public void create(Site site) {
        siteRepository.save(site);
    }

    @Override
    public List<Site> retrieveSites() {
        return siteRepository.findAll();
    }

    @Override
    public Optional<Site> getSiteByKy(Long siteKy) {
        return siteRepository.findById(siteKy);
    }

    @Override
    public Site getSiteByNm(String siteNm) {
        return siteRepository.findBySiteNm(siteNm);
    }

    @Override
    public String getSiteNameByKey(Long siteKy) {
        Optional<Site> siteOptional = siteRepository.findById(siteKy);
        return siteOptional.isPresent() ? siteOptional.get().getSiteNm() : "";
    }

    @Override
    public void update(Long siteKy, Site updatedSite) {
        Site existingSite = siteRepository.findById(siteKy)
                .orElseThrow(() -> new EntityNotFoundException("Site with id " + siteKy + " not found"));

        // Update the properties of the existing site with the values from the updated site
        existingSite.setSiteNm(updatedSite.getSiteNm());
        existingSite.setSiteCountry(updatedSite.getSiteCountry());
        existingSite.setSiteUnxTmUpdt(updatedSite.getSiteUnxTmUpdt());

        siteRepository.save(existingSite);

    }

    @Override
    public void delete(Long siteKy) {
        siteRepository.deleteById(siteKy);
    }

    @Override
    public List<Service> getChildElements(Site site) {
       return site.getServices();
    }

    @Override
    public void addChildElement(Site site, Service service) {
        List<Service> services = site.getServices();
        services.add(service);
        service.setSite(site);
        siteRepository.save(site);
    }
}
