package app.repository.dao.design;

import java.util.List;

import app.model.Course;

public interface ICourseDao {

	List<Course> getAllCourses();
	Course getCourseByIdCourse(Long idCourse);
	List<Course> getCoursesByDni(String dni);
	void saveNewCourse(Long idTeacher, Course course);

}
