package com.golf.app.service;

import com.golf.app.model.Par;
import java.util.Optional;

public interface ParService {

    Optional<Par> getParById(Long id);
}
