package app.repository.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.Course;
import app.repository.dao.design.ICourseDao;
import app.repository.dao.design.ICourseModuleDao;
import app.repository.daoDataSource.CloseResources;
import app.repository.daoDataSource.DataSourceDao;

public class CourseDaoImpl implements ICourseDao {

	DataSourceDao dataSourceDao = DataSourceDao.getInstance();
	ICourseModuleDao courseModuleDao = new CourseModuleDaoImpl();

	public List<Course> getAllCourses() {
		List<Course> courseList = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select c.idCurso, c.titulo, c.descripcion, c.costo, c.inicio, c.fin, c.nombre_imagen, c.imagen  "
				+ "from cursos c";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			courseList = new ArrayList<Course>();
			while (resultSet.next()) {
				courseList.add(new Course(resultSet.getLong("idCurso"),
						resultSet.getString("titulo"),
						resultSet.getString("descripcion"),
						resultSet.getDouble("costo"),
						resultSet.getString("inicio"),
						resultSet.getString("fin"),
						resultSet.getString("nombre_imagen"),
						resultSet.getString("imagen"),
						courseModuleDao.getCourseModuleByIdCourse(resultSet.getLong("idCurso"))));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement, resultSet);
		}

		return courseList;
	}

	public Course getCourseByIdCourse(Long idCourse) {
		Course course = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select c.idCurso, c.titulo, c.descripcion, c.costo, c.inicio, c.fin, c.nombre_imagen, c.imagen "
				+ "from cursos c where c.idCurso = ?";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, idCourse);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course = new Course(resultSet.getLong("idCurso"),
						resultSet.getString("titulo"),
						resultSet.getString("descripcion"),
						resultSet.getDouble("costo"),
						resultSet.getString("inicio"),
						resultSet.getString("fin"),
						resultSet.getString("nombre_imagen"),
						resultSet.getString("imagen"),
						courseModuleDao.getCourseModuleByIdCourse(resultSet.getLong("idCurso")));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement, resultSet);
		}
		return course;
	}

	public List<Course> getCoursesByDni(String dni) {
		List<Course> courseList = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select c.idCurso, c.titulo, c.descripcion, c.costo, c.inicio, c.fin, c.nombre_imagen, c.imagen  "
				+ "from personas p, estudiantes e, suscripcion s, cursos c "
				+ "where p.DNI = ? "
				+ "and e.DNI2 = p.DNI "
				+ "and s.id_estudiante3 = e.id_estudiante "
				+ "and c.idCurso = s.idCurso4 ";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dni);
			resultSet = preparedStatement.executeQuery();
			courseList = new ArrayList<Course>();
			while (resultSet.next()) {
				courseList.add(new Course(resultSet.getLong("idCurso"),
						resultSet.getString("titulo"),
						resultSet.getString("descripcion"),
						resultSet.getDouble("costo"),
						resultSet.getString("inicio"),
						resultSet.getString("fin"),
						resultSet.getString("nombre_imagen"),
						resultSet.getString("imagen"),
						courseModuleDao.getCourseModuleByIdCourse(resultSet.getLong("idCurso"))));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement, resultSet);
		}

		return courseList;
	}

	@Override
	public void saveNewCourse(Long idTeacher, Course course) {
		Connection connection = dataSourceDao.connect();
		String sql = "insert into cursos (titulo, descripcion, costo, inicio, fin, nombre_imagen, imagen, id_docente1) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, course.getTitle());
			preparedStatement.setString(2, course.getDescription());
			preparedStatement.setDouble(3, course.getCost());
			preparedStatement.setString(4, course.getStart());
			preparedStatement.setString(5, course.getEnd());
			preparedStatement.setString(6, course.getImageName());
			preparedStatement.setString(7, course.getImage());
			preparedStatement.setLong(8, idTeacher);
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement);
		}

	}
}
