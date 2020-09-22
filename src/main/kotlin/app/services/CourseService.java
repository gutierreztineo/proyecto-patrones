package app.services;

import java.util.List;

import app.model.Course;

public interface CourseService {

	List<Course> getAllCourses();
	Course getCourseByIdCourse(String idCourse);
	List<Course> getCoursesByDni(String dni);
	void saveNewCourse(String idTeacher, Course course);

}
