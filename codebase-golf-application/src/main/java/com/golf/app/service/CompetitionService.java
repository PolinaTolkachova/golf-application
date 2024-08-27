package com.golf.app.service;

import com.golf.app.model.Competition;
import java.util.Optional;

public interface CompetitionService {

    Iterable<Competition> getAllCompetition();
    Optional<Competition> getCompetitionById(Long id);
    Competition saveCompetition(Competition competition);
    void deleteCompetition(Long id);
    Optional<Competition> getCompetitionByName(String name);
}
