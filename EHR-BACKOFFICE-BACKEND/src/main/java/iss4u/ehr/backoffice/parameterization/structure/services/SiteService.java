package iss4u.ehr.backoffice.parameterization.structure.services;

import iss4u.ehr.backoffice.parameterization.structure.entities.Service;
import iss4u.ehr.backoffice.parameterization.structure.entities.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {

    void create(Site site);

    List<Site> retrieveSites();

    Optional<Site> getSiteByKy(Long siteKy);

    Site getSiteByNm(String siteNm);

    String getSiteNameByKey(Long siteKy);

    void update(Long siteKy, Site updatedSite);

    void delete(Long siteKy);

    List<Service> getChildElements(Site site);

    void addChildElement(Site site, Service service);
}
