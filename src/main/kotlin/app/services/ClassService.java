package app.services;

import java.util.List;

import app.model.Class;

public interface ClassService {

	List<Class> getClassesByIdCourseModule(String idCourseModule);
	void saveNewClass(String idCourseModule, Class aClass);

}
