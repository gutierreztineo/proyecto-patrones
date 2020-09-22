package app.repository.dao.design;

import java.util.List;

import app.model.Teacher;
import app.repository.dto.UserDataDto;

public interface ITeacherDao {

	List<Teacher> getTeachers();
	UserDataDto getDataTeacher(Long idUser);
}
