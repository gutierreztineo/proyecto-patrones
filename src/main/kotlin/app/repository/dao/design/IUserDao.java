package app.repository.dao.design;

import app.repository.dto.UserDataDto;
import app.repository.dto.UserLoginDto;

public interface IUserDao {

	UserLoginDto authentication(String username, String password);
	UserDataDto getDataAdmin(Long idUser);

}
