package app.repository.daoDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CloseResources {

	private CloseResources() {

	}

	public static void closeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			}
			catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			}
			catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}

	public static void closeResources(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			}
			catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}

}
