package com.golf.app.utils;

import com.golf.app.dto.CourseDto;
import com.golf.app.enums.TeeColour;
import com.golf.app.model.Hole;
import com.golf.app.model.Tee;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CourseUtils {

    private static final int HOLES_QUANTITY = 18;

    private CourseUtils() {
    }

    public static List<Hole> buildHoles(CourseDto courseDto) {
        return IntStream.range(0, HOLES_QUANTITY)
            .mapToObj(holeIndex -> {
                Hole hole = new Hole();
                hole.setHoleNumber(holeIndex+1);
                hole.setPar(courseDto.getParHoles().get(holeIndex));
                hole.setStrokeIndex(courseDto.getStrokeIndexHoles().get(holeIndex));

                List<Tee> tees = Arrays.asList(
                    new Tee(TeeColour.BLACK, courseDto.getBlackTeeLengths().get(holeIndex)),
                    new Tee(TeeColour.WHITE, courseDto.getWhiteTeeLengths().get(holeIndex)),
                    new Tee(TeeColour.YELLOW, courseDto.getYellowTeeLengths().get(holeIndex)),
                    new Tee(TeeColour.BLUE, courseDto.getBlueTeeLengths().get(holeIndex)),
                    new Tee(TeeColour.RED, courseDto.getRedTeeLengths().get(holeIndex))
                );
                hole.setTees(tees);
                return hole;
            })
            .collect(Collectors.toList());
    }
}
