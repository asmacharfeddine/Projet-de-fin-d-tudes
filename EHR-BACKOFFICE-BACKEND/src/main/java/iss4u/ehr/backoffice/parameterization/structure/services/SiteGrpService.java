package iss4u.ehr.backoffice.parameterization.structure.services;

import iss4u.ehr.backoffice.parameterization.structure.entities.Site;
import iss4u.ehr.backoffice.parameterization.structure.entities.SiteGrp;

import java.util.List;
import java.util.Optional;

public interface SiteGrpService {

    void create(SiteGrp siteGrp);

    List<SiteGrp> retrieveSiteGrps();

    Optional<SiteGrp> getSiteGrpByKy(Long siteGrpKy);

    SiteGrp getSiteGrpByNm(String siteGrpNm);

    String getSiteGroupNameByKey(Long siteGrpKy);

    void update(Long siteGrpKy, SiteGrp updatedSiteGrp);

    void delete(Long siteGrpKy);

    List<Site> getChildElements(SiteGrp siteGrp);

    void addChildElement(SiteGrp siteGrp, Site site);

}
