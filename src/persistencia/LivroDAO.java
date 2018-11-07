package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidade.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LivroDAO {

	public static void cadastrarLivro(String titulo, String subTitulo, String isbn, String descricao, String tag,
			int categoria, int idAutor, int idEditora, String edicao, int anoPub, String dataAquisicao, String valor,
			int nPaginas) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlInsert = "INSERT INTO tblivro(titulo, subTitulo, isbn, descricao, tag, categoria, idAutor, idEditora, edicao, anoPub, dataaquisicao, valor, npaginas ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlInsert);
			stm.setString(1, titulo);
			stm.setString(2, subTitulo);
			stm.setString(3, isbn);
			stm.setString(4, descricao);
			stm.setString(5, tag);
			stm.setInt(6, categoria);
			stm.setInt(7, idAutor);
			stm.setInt(8, idEditora);
			stm.setString(9, edicao);
			stm.setInt(10, anoPub);
			stm.setString(11, dataAquisicao);
			stm.setString(12, valor);
			stm.setInt(13, nPaginas);

			stm.execute();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static int pegarUltimoId() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Livro> listaLivro = new ArrayList<>();

		String sqlSelect = "select * from tblivro ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaLivro.add(new Livro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Integer> ids = listaLivro.stream().map(x -> x.getId()).collect(Collectors.toList());

		return ids.get(ids.size() - 1) + 1;

	}
	
	public static void editarLivro(String pesquisa, String titulo, String subTitulo, String isbn, String descricao, String tag,
			int categoria, int idAutor, int idEditora, String edicao, int anoPub, String dataAquisicao, String valor,
			int nPaginas) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		
		String sqlUpdate = "update tblivro set titulo = ?, subTitulo = ?, isbn = ?, descricao = ?, tag = ?, categoria = ?, idAutor = ?, idEditora = ?, edicao = ?, anoPub = ?, dataaquisicao = ?, valor = ?, npaginas = ? where idlivro like ? ";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sqlUpdate);
			stm.setString(1, titulo);
			stm.setString(2, subTitulo);
			stm.setString(3, isbn);
			stm.setString(4, descricao);
			stm.setString(5, tag);
			stm.setInt(6, categoria);
			stm.setInt(7, idAutor);
			stm.setInt(8, idEditora);
			stm.setString(9, edicao);
			stm.setInt(10, anoPub);
			stm.setString(11, dataAquisicao);
			stm.setString(12, valor);
			stm.setInt(13, nPaginas);
			stm.setString(14, pesquisa);
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


	public static ObservableList<Livro> converterArrayListToObservableList() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Livro> listaLivro = new ArrayList<>();

		String sqlSelect = "select * from tblivro ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaLivro.add(new Livro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ObservableList<Livro> listaLivroObservable = FXCollections.observableArrayList();

		for (int i = 0; listaLivro.size() > i; i++) {

			listaLivroObservable.add(listaLivro.get(i));
		}

		return listaLivroObservable;
	}

	public static List<Livro> mapearLivro() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Livro> listaLivro = new ArrayList<>();

		String sqlSelect = "select * from tblivro ";
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaLivro.add(new Livro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaLivro;

	}
	
	public static void apagarLivro(String pesquisa) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();

		String sqlDelete = "delete from tblivro where idlivro = ?";

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

	public static List<Integer> mapearID() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.obtemConexao();
		List<Integer> listaID = new ArrayList<>();

		String sqlSelect = "select idlivro from tblivro ";
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
