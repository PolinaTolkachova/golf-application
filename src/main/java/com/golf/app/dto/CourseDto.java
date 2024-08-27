package com.golf.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDto {

    @NotBlank(message = "Name should not be empty")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;
    @NotBlank(message = "Location should not be empty")
    @Size(min = 3, max = 50, message = "Location should be between 3 and 50 characters")
    private String location;
    private List<Integer> parHoles;
    private List<Integer> strokeIndexHoles;
    private List<Integer> blackTeeLengths;
    private List<Integer> whiteTeeLengths;
    private List<Integer> yellowTeeLengths;
    private List<Integer> blueTeeLengths;
    private List<Integer> redTeeLengths;
}
