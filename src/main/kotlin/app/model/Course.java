package app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	private Long idCourse;
	private String title;
	private String description;
	private Double cost;
	private String start;
	private String end;
	private String imageName;
	private String image;
	private List<CourseModule> courseModuleList;

}
