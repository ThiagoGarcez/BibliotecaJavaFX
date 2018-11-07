package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidade.Editora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditoraDAO {

	public static int pegarUltimoId() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Editora> listaEditora = new ArrayList<>();

		String sqlSelect = "select * from tbeditora ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaEditora.add(new Editora(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Integer> ids = listaEditora.stream().map(x -> x.getId()).collect(Collectors.toList());

		return ids.get(ids.size() - 1) + 1;

	}

	public static void cadastrarEditora(String nome) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlInsert = "INSERT INTO tbeditora(nome) VALUES (?)";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlInsert);
			stm.setString(1, nome);
			stm.execute();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static List<Integer> mapearID() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Integer> listaID = new ArrayList<>();

		String sqlSelect = "select id from tbeditora ";
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

	public static void editarEditora(String pesquisa, String nome) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		
		String sqlUpdate = "update tbeditora set nome = ? where id like ? ";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlUpdate);
			stm.setString(1, nome);
			stm.setString(2, pesquisa);
			
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
	
	public static void apagarEditora(String pesquisa) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlDelete = "delete from tbeditora where id = ?";

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
	
	public static ObservableList<Editora> converterArrayListToObservableList() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Editora> listaEditora = new ArrayList<>();

		String sqlSelect = "select * from tbeditora ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaEditora.add(new Editora(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ObservableList<Editora> listaEditoraObservable = FXCollections.observableArrayList();

		for (int i = 0; listaEditora.size() > i; i++) {

			listaEditoraObservable.add(listaEditora.get(i));
		}

		return listaEditoraObservable;
	}
	
	public static Editora pesquisarEditgora(String pesquisa) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		List<Editora> listaEditora = new ArrayList<>();

		String sqlSelect = "select * from tbeditora where id like ? ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, "%" + pesquisa + "%");
			rs = stm.executeQuery();

			while (rs.next()) {
				listaEditora.add(
						new Editora(rs.getInt(1), rs.getString(2)));
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
		if (listaEditora.isEmpty()) {
			return null;
		} else {
			return listaEditora.get(0);
		}
	}
}
