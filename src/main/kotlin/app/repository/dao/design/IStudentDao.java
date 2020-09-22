package app.repository.dao.design;

import app.repository.dto.UserDataDto;

public interface IStudentDao {

	UserDataDto getDataStudent(Long idUser);

}
