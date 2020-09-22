package app.services;

import java.util.List;

import app.model.CourseModule;

public interface CourseModuleService {

	List<CourseModule> getCourseModulesByIdCourse(String idCourse);
	void saveNewCourseModule(String idCourse, CourseModule courseModule);

}