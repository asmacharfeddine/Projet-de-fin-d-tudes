package iss4u.ehr.backoffice.radiology.services;


import iss4u.ehr.backoffice.radiology.entities.Series;

import java.util.List;
import java.util.Optional;

public interface SeriesService {

    void create(Series series);

    List<Series> retrieveSeries();

    Optional<Series> getSeriesByKy(Long seriesKy);

    void update(Long seriesKy, Series updatedSeries);

    void delete(Long seriesKy);
}
