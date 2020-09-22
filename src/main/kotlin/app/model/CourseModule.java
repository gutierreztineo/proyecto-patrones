package app.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModule {

	private Long idCourseModule;
	private String title;
	List<Class> classList = new ArrayList<Class>();

}
