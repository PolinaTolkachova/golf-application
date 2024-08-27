package com.golf.app.mapper;

import com.golf.app.dto.CompetitionDto;
import com.golf.app.model.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    Competition competition(CompetitionDto competitionDto);
    CompetitionDto competitionDto(Competition competition);
}
