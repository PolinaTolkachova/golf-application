package com.golf.app.mapper;

import com.golf.app.dto.CourseDto;
import com.golf.app.dto.RoundScoreDto;
import com.golf.app.model.Course;
import com.golf.app.model.RoundScore;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoundScoreMapper {

    RoundScore roundScore (RoundScoreDto roundScoreDto);
    RoundScoreDto roundScoreDto(RoundScore roundScore);
}
