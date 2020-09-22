package app.repository.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.repository.dao.design.IStudentDao;
import app.repository.daoDataSource.CloseResources;
import app.repository.daoDataSource.DataSourceDao;
import app.repository.dto.UserDataDto;

public class StudentDaoImpl implements IStudentDao {

	DataSourceDao dataSourceDao = DataSourceDao.getInstance();

	public UserDataDto getDataStudent(Long idUser) {
		UserDataDto userDataDto = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select e.codigo, p.DNI, p.nombre, p.apellido_paterno, p.apellido_materno, p.numero_celular, p.correo, u.username, r.descripcion "
				+ "from usuarios u, rol r, personas p, estudiantes e "
				+ "where u.id_usuario = ? "
				+ "and r.id_rol = u.id_rol1 "
				+ "and p.id_usuario1 = u.id_usuario "
				+ "and e.DNI2 = p.DNI";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, idUser);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userDataDto = new UserDataDto(Long.toString(resultSet.getLong("codigo")) ,
						resultSet.getString("DNI"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido_paterno"),
						resultSet.getString("apellido_materno"),
						Long.toString(resultSet.getLong("numero_celular")),
						resultSet.getString("correo"),
						resultSet.getString("username"),
						resultSet.getString("descripcion"));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			dataSourceDao.disconnect();
			CloseResources.closeResources(preparedStatement, resultSet);
		}
		return userDataDto;
	}

}
