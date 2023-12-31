package iss4u.ehr.backoffice.radiology.services.implementations;

import iss4u.ehr.backoffice.radiology.entities.Series;
import iss4u.ehr.backoffice.radiology.repositories.SeriesRepository;
import iss4u.ehr.backoffice.radiology.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public void create(Series series) {
        seriesRepository.save(series);
    }

    @Override
    public List<Series> retrieveSeries() {
        return seriesRepository.findAll();
    }

    @Override
    public Optional<Series> getSeriesByKy(Long seriesKy) {
        return seriesRepository.findById(seriesKy);
    }

    @Override
    public void update(Long seriesKy, Series updatedSeries) {
        Series existingSeries = seriesRepository.findById(seriesKy)
                .orElseThrow(() -> new EntityNotFoundException("Series with id " + seriesKy + " not found"));

        // Update the properties
        existingSeries.setSeriesDesc(updatedSeries.getSeriesDesc());
        existingSeries.setSeriesBodyPart(updatedSeries.getSeriesBodyPart());
        existingSeries.setSeriesDcmModality(updatedSeries.getSeriesDcmModality());
        existingSeries.setSeriesDlp(updatedSeries.getSeriesDlp());
        existingSeries.setSeriesUnxTmUpdt(updatedSeries.getSeriesUnxTmUpdt());

        seriesRepository.save(existingSeries);
    }

    @Override
    public void delete(Long seriesKy) {
        seriesRepository.deleteById(seriesKy);
    }
}
