package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	private String dni;
	private String name;
	private String paternalSurname;
	private String maternalSurname;
	private String cellPhoneNumber;
	private String email;

}
