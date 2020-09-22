package app.repository.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.repository.dao.design.IUserDao;
import app.repository.daoDataSource.CloseResources;
import app.repository.daoDataSource.DataSourceDao;
import app.repository.dto.UserDataDto;
import app.repository.dto.UserLoginDto;

public class UserDaoImpl implements IUserDao {

	DataSourceDao dataSourceDao = DataSourceDao.getInstance();

	public UserLoginDto authentication(String username, String password) {
		UserLoginDto userLoginDto = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select u.id_usuario, r.id_rol from proyecto_patrones.usuarios u, proyecto_patrones.rol r "
				+ "where u.username = ? and u.pasword = ? and u.habilitado = 1 and r.id_rol = u.id_rol1";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				userLoginDto = new UserLoginDto();
				userLoginDto.setIdUser(resultSet.getLong("id_usuario"));
				userLoginDto.setIdRole(resultSet.getLong("id_rol"));
			}

			resultSet.close();
			preparedStatement.close();
			dataSourceDao.disconnect();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			CloseResources.closeResources(preparedStatement, resultSet);
		}
		return userLoginDto;
	}

	public UserDataDto getDataAdmin(Long idUser) {
		UserDataDto userDataDto = null;
		Connection connection = dataSourceDao.connect();
		String sql = "select p.DNI, p.nombre, p.apellido_paterno, p.apellido_materno, p.numero_celular, p.correo, u.username, r.descripcion "
				+ "from usuarios u, rol r, personas p "
				+ "where u.id_usuario = ? "
				+ "and r.id_rol = u.id_rol1 "
				+ "and p.id_usuario1 = u.id_usuario";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, idUser);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userDataDto = new UserDataDto(null ,
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
