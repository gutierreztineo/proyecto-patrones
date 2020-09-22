package app.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

	private Integer idTeacher;
	private Integer code;
	private List<Course> courses = new ArrayList<Course>();

}
