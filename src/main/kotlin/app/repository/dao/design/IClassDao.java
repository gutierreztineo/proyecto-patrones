package app.repository.dao.design;

import java.util.List;

import app.model.Class;

public interface IClassDao {

	List<Class> getClassByIdCourseModule(Long idCourseModule);
	void saveNewClass(Long idCourseModule, Class aClass);

}
