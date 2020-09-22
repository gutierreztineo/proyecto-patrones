package app.services;

import java.util.List;

import app.model.Class;
import app.repository.dao.design.IClassDao;
import app.repository.dao.implement.ClassDaoImpl;

public class ClassServiceImpl implements ClassService {

	IClassDao classDao = new ClassDaoImpl();

	public List<Class> getClassesByIdCourseModule(String idCourseModule) {
		return classDao.getClassByIdCourseModule(Long.parseLong(idCourseModule));
	}

	@Override
	public void saveNewClass(String idCourseModule, Class aClass) {
		classDao.saveNewClass(Long.parseLong(idCourseModule), aClass);
	}

}
