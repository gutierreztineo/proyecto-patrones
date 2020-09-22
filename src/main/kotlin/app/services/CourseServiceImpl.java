package app.services;

import java.util.List;

import app.model.Course;
import app.repository.dao.design.ICourseDao;
import app.repository.dao.implement.CourseDaoImpl;

public class CourseServiceImpl implements CourseService {

	ICourseDao courseDao = new CourseDaoImpl();

	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	public Course getCourseByIdCourse(String idCourse) {
		return courseDao.getCourseByIdCourse(Long.parseLong(idCourse));
	}

	public List<Course> getCoursesByDni(String dni) {
		return courseDao.getCoursesByDni(dni);
	}

	@Override
	public void saveNewCourse(String idTeacher, Course course) {
		courseDao.saveNewCourse(Long.parseLong(idTeacher), course);
	}

}
