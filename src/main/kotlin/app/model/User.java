package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long idUser;
	private String username;
	private String password;
	private Short enabled;
	private Role roles;

}
