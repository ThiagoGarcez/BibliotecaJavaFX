package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidade.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDAO {

	public static void cadastrarUsuario(String nome, String email, String login, String senha) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlInsert = "INSERT INTO tbusuario(nome, email, login, senha) VALUES ( ?, ?, ?, ?)";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlInsert);
			stm.setString(1, nome);
			stm.setString(2, email);
			stm.setString(3, login);
			stm.setString(4, senha);
			stm.execute();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static int pegarUltimoId() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Usuario> listaUsuario = new ArrayList<>();

		String sqlSelect = "select * from tbusuario ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaUsuario.add(
						new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Integer> ids = listaUsuario.stream().map(x -> x.getId()).collect(Collectors.toList());

		return ids.get(ids.size() - 1) + 1;

	}

	public static Usuario pesquisarUsuario(String pesquisa) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		List<Usuario> listaUsuario = new ArrayList<>();

		String sqlSelect = "select * from tbusuario where nome like ? ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, "%" + pesquisa + "%");
			rs = stm.executeQuery();

			while (rs.next()) {
				listaUsuario.add(
						new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (listaUsuario.isEmpty()) {
			return null;
		} else {
			return listaUsuario.get(0);
		}
	}

	public static void editarUsuario(String pesquisa, String nome, String email, String login, String senha) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlUpdate = "update tbusuario set nome = ?, email = ? , login = ?, senha = ? where id like ? ";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlUpdate);
			stm.setString(1, nome);
			stm.setString(2, email);
			stm.setString(3, login);
			stm.setString(4, senha);
			stm.setString(5, pesquisa);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stm.close();

			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	public static void apagarUsuario(String pesquisa) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlDelete = "delete from tbusuario where id = ?";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlDelete);
			stm.setString(1, pesquisa);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static ObservableList<Usuario> converterArrayListToObservableList() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Usuario> listaUsuario = new ArrayList<>();

		String sqlSelect = "select * from tbusuario ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaUsuario.add(
						new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ObservableList<Usuario> listaUsuarioObservable = FXCollections.observableArrayList();

		for (int i = 0; listaUsuario.size() > i; i++) {

			listaUsuarioObservable.add(listaUsuario.get(i));
		}

		return listaUsuarioObservable;
	}

	public static List<Usuario> mapearUsuario() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Usuario> listaUsuario = new ArrayList<>();

		String sqlSelect = "select * from tbusuario ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaUsuario.add(
						new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuario;

	}

	public static List<Integer> mapearID() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Integer> listaID = new ArrayList<>();

		String sqlSelect = "select id from tbusuario ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaID.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaID;

	}

}
