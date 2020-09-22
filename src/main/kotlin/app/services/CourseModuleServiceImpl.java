package app.services;

import java.util.List;

import app.model.CourseModule;
import app.repository.dao.design.ICourseModuleDao;
import app.repository.dao.implement.CourseModuleDaoImpl;

public class CourseModuleServiceImpl implements CourseModuleService {

	ICourseModuleDao courseModuleDao = new CourseModuleDaoImpl();

	public List<CourseModule> getCourseModulesByIdCourse(String idCourse) {
		return courseModuleDao.getCourseModuleByIdCourse(Long.parseLong(idCourse));
	}

	@Override
	public void saveNewCourseModule(String idCourse, CourseModule courseModule) {
		courseModuleDao.saveNewCourseModule(Long.parseLong(idCourse), courseModule);
	}
}
