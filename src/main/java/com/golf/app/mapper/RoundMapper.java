package com.golf.app.mapper;

import com.golf.app.dto.RoundDto;
import com.golf.app.model.Round;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoundMapper {

    Round round(RoundDto roundDto);
    RoundDto roundDto(Round round);
}
