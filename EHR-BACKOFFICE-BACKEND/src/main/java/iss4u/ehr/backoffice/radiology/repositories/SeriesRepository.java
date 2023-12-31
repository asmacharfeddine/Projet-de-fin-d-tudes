package iss4u.ehr.backoffice.radiology.repositories;

import iss4u.ehr.backoffice.radiology.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Series findBySeriesKy(Long seriesKy);
}
