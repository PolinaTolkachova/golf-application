package com.golf.app.service;

import com.golf.app.model.Competition;
import com.golf.app.repo.CompetitionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Iterable<Competition> getAllCompetition() {
        Sort sort = Sort.by(Sort.Direction.DESC, "startDate");
        return competitionRepository.findAll(sort);
    }

    @Override
    public Optional<Competition> getCompetitionById(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Competition saveCompetition(Competition competition) {
        if (competition.getStartDate().isAfter(competition.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
        return competitionRepository.save(competition);
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }

    @Override
    public Optional<Competition> getCompetitionByName(String name) {
        return competitionRepository.findByName(name);
    }
}
