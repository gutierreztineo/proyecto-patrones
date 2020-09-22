package app.repository.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.Class;
import app.repository.dao.design.IClassDao;
import app.repository.daoDataSource.CloseResources;
import app.repository.daoDataSource.DataSourceDao;

public class ClassDaoImpl implements IClassDao {

	DataSourceDao dataSourceDao = DataSourceDao.getInstance();

	public List<Class> getClassByIdCourseModule(Long idCourseModule) {
		List<Class> classList = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select c.id_clase, c.titulo, c.descripcion, c.url_link "
				+ "from clase c "
				+ "where c.id_modulo1 = ?";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, idCourseModule);
			resultSet = preparedStatement.executeQuery();
			classList = new ArrayList<Class>();
			while (resultSet.next()) {
				classList.add(new Class(resultSet.getLong("id_clase"),
						resultSet.getString("titulo"),
						resultSet.getString("descripcion"),
						resultSet.getString("url_link")));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement, resultSet);
		}

		return classList;
	}

	@Override
	public void saveNewClass(Long idCourseModule, Class aClass) {
		Connection connection = dataSourceDao.connect();
		String sql = "insert into clase (titulo, descripcion, url_link, id_modulo1) "
				+ "values (?, ?, ?, ?)";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, aClass.getTitle());
			preparedStatement.setString(2, aClass.getDescription());
			preparedStatement.setString(3, aClass.getUrlLink());
			preparedStatement.setLong(4, idCourseModule);
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement);
		}

	}

}
