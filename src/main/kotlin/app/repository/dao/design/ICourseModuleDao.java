package app.repository.dao.design;

import java.util.List;

import app.model.CourseModule;

public interface ICourseModuleDao {

	List<CourseModule> getCourseModuleByIdCourse(Long idCourse);
	void saveNewCourseModule(Long idCourse, CourseModule courseModule);

}
