package app.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {

	private String code;
	private String dni;
	private String name;
	private String paternalSurname;
	private String maternalSurname;
	private String cellPhoneNumber;
	private String email;
	private String username;
	private String role;

}
