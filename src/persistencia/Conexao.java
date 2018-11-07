package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection connection;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String SERVIDOR = "localhost";
	private static final String PORTA = "3307";
	private static final String BANCO = "AULA";
	private static final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO
			+ "?useTimezone=true&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String SENHA = "123456";

	public Conexao() {
		}

	public Connection obtemConexao() {
		if (connection == null) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			} catch (ClassNotFoundException e) {
				System.out.println("Erro ao carregar o driver de conexão\\n");
			} catch (SQLException e) {
				System.out.println("Não foi possível estabalecer conexão com o banco de dados\\n");
			}
		}
		return connection;
	}

}
