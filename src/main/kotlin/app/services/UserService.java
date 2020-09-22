package app.services;

import app.repository.dto.UserDataDto;

public interface UserService {

	UserDataDto authentication(String username, String password);

}
