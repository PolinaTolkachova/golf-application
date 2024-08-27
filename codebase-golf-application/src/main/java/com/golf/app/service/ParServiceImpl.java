package com.golf.app.service;

import com.golf.app.model.Par;
import com.golf.app.repo.ParRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParServiceImpl implements ParService {

    private final ParRepository parRepository;

    @Autowired
    public ParServiceImpl(ParRepository parRepository) {
        this.parRepository = parRepository;
    }

    @Override
    public Optional<Par> getParById(Long id) {
        return parRepository.findById(id);
	}
}
