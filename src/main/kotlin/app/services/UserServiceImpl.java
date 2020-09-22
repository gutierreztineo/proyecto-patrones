package app.services;

import app.repository.dao.design.IStudentDao;
import app.repository.dao.design.ITeacherDao;
import app.repository.dao.design.IUserDao;
import app.repository.dao.implement.StudentDaoImpl;
import app.repository.dao.implement.TeacherDaoImpl;
import app.repository.dao.implement.UserDaoImpl;
import app.repository.dto.UserDataDto;
import app.repository.dto.UserLoginDto;

public class UserServiceImpl implements UserService {

	private IUserDao userDao = new UserDaoImpl();
	private IStudentDao studentDao = new StudentDaoImpl();
	private ITeacherDao teacherDao = new TeacherDaoImpl();

	public UserDataDto authentication(String username, String password) {
		UserLoginDto userLoginDto = userDao.authentication(username, password);
		if(userLoginDto == null) return null;
		if(userLoginDto.getIdRole() == 1L) return studentDao.getDataStudent(userLoginDto.getIdUser());
		if(userLoginDto.getIdRole() == 2L) return teacherDao.getDataTeacher(userLoginDto.getIdUser());
		if(userLoginDto.getIdRole() == 3L) return userDao.getDataAdmin(userLoginDto.getIdUser());
		return null;
	}
}
