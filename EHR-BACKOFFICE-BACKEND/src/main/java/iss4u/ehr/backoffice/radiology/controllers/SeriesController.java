package iss4u.ehr.backoffice.radiology.controllers;

import iss4u.ehr.backoffice.radiology.entities.Series;
import iss4u.ehr.backoffice.radiology.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/radiography/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @PostMapping
    public ResponseEntity<Series> createSeries(@RequestBody Series series) {
        seriesService.create(series);
        return new ResponseEntity<>(series, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Series>> getAllSeries() {
        List<Series> seriesList = seriesService.retrieveSeries();
        return new ResponseEntity<>(seriesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> getSeriesById(@PathVariable("id") Long seriesId) {
        Optional<Series> Series = seriesService.getSeriesByKy(seriesId);
        return Series.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{SeriesKy}")
    public ResponseEntity<Series> updateSeries(@PathVariable Long seriesKy, @RequestBody Series updatedSeries) {
        try {
            seriesService.update(seriesKy, updatedSeries);
            return ResponseEntity.ok().build(); // Return 200 (OK) without a body
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSeries(@PathVariable("id") Long seriesId) {
        Optional<Series> existingSeries = seriesService.getSeriesByKy(seriesId);
        if (existingSeries.isPresent()) {
            seriesService.delete(seriesId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
