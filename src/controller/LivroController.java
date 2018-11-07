package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistencia.AutorDAO;
import persistencia.EditoraDAO;
import persistencia.LivroDAO;
import util.MascarasFX;

public class LivroController implements Initializable {

	@FXML
	private TextField txtSubTitulo;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtTag;
	
	@FXML
	private TextField txtNomeAutor;
	
	@FXML
	private TextField txtNomeEditora;

	@FXML
	private TextField txtEdicao;

	@FXML
	private TextField txtAnoPub;

	@FXML
	private Button btnCadastrar;
	
	@FXML
	private Button btnCadastrarAutor;
	
	@FXML
	private Button btnCadastrarEditora;

	@FXML
	private DatePicker txtDataAquisicao;

	@FXML
	private Button btnGravar;

	@FXML
	private TextArea txtDescricao;

	@FXML
	private Button btnDeletar;

	@FXML
	private Button btnPesquisar;

	@FXML
	private CheckBox chCatVerde;

	@FXML
	private CheckBox chCatAmarelo;

	@FXML
	private CheckBox chCatAzul;

	@FXML
	private TextField txtEditora;

	@FXML
	private TextField txtId;
	
    @FXML
    private Button btnPesquisarEditora;
    
    @FXML
    private Button btnPesquisarAutor;

	@FXML
	private CheckBox chCatVermelho;

	@FXML
	private TextField txtIsbn;

	@FXML
	private TextField txtTitulo;

	@FXML
	private Button btnNovo;

	@FXML
	private TextField txtPaginas;

	@FXML
	private TextField txtValor;

	private static int categoria;

	public static int getCategoria() {
		return categoria;
	}

	public static void setCategoria(int categoria) {
		LivroController.categoria = categoria;
	}

	@FXML
	void chCatVerdeOnAction(ActionEvent event) {
		LivroController.setCategoria(1);
		if (chCatVerde.isSelected()) {
			chCatVermelho.setDisable(true);
			chCatAmarelo.setDisable(true);
			chCatAzul.setDisable(true);
		} else {
			chCatVermelho.setDisable(false);
			chCatAmarelo.setDisable(false);
			chCatAzul.setDisable(false);
		}

	}

	@FXML
	void chCatVermelhoOnAction(ActionEvent event) {
		LivroController.setCategoria(2);
		if (chCatVermelho.isSelected()) {
			chCatVerde.setDisable(true);
			chCatAmarelo.setDisable(true);
			chCatAzul.setDisable(true);
		} else {
			chCatVerde.setDisable(false);
			chCatAmarelo.setDisable(false);
			chCatAzul.setDisable(false);
		}
	}
	
	@FXML
	void chCatAmareloOnAction(ActionEvent event) {
		LivroController.setCategoria(3);
		if (chCatAmarelo.isSelected()) {
			chCatVermelho.setDisable(true);
			chCatAzul.setDisable(true);
			chCatVerde.setDisable(true);
		} else {
			chCatVermelho.setDisable(false);
			chCatAzul.setDisable(false);
			chCatVerde.setDisable(false);
		}
	}
	
	@FXML
	void chCatAzulOnAction(ActionEvent event) {
		LivroController.setCategoria(4);
		if (chCatAzul.isSelected()) {
			chCatVermelho.setDisable(true);
			chCatAmarelo.setDisable(true);
			chCatVerde.setDisable(true);
		} else {
			chCatVermelho.setDisable(false);
			chCatAmarelo.setDisable(false);
			chCatVerde.setDisable(false);
		}
	}
	
	@FXML
	void btnNovoOnAction(ActionEvent event) {
		
		
		txtId.setText(Integer.toString(LivroDAO.pegarUltimoId()));
		txtTitulo.clear();
		txtIsbn.clear();
		txtSubTitulo.clear();
		txtDescricao.clear();
		txtTag.clear();
		txtAutor.clear();
		txtEdicao.clear();
		txtEditora.clear();
		txtAnoPub.clear();
		txtDataAquisicao.setValue(LocalDate.now());
		txtValor.clear();
		txtPaginas.clear();
		chCatVerde.setSelected(false);
		chCatVermelho.setSelected(false);
		chCatAmarelo.setSelected(false);
		chCatAzul.setSelected(false);
		txtNomeAutor.clear();
		txtNomeEditora.clear();
		

	}

	@FXML
	void btnCadastrarOnAction(ActionEvent event) {

		if ("".equals(txtTitulo.getText()) || "".equals(txtAutor.getText()) || "".equals(txtEditora.getText())) {

			Alert alert = new Alert(AlertType.INFORMATION, "Preencha os campos obrigatorios para cadastrar um livro",
					ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			String titulo = txtTitulo.getText();
			String subTitulo = txtSubTitulo.getText();
			String isbn = txtIsbn.getText();
			String descricao = txtDescricao.getText();
			String tag = txtTag.getText();
			int categoria = LivroController.getCategoria();
			int idAutor = Integer.parseInt(txtAutor.getText());
			int idEditora = Integer.parseInt(txtEditora.getText());
			String edicao = txtEdicao.getText();
			int anoPub = "".equals(txtAnoPub.getText()) ? 0 : Integer.parseInt(txtAnoPub.getText());
			String dataAquisicao = txtDataAquisicao.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String valor = txtValor.getText();
			int nPaginas = "".equals(txtPaginas.getText()) ? 0 : Integer.parseInt(txtPaginas.getText());

			LivroDAO.cadastrarLivro(titulo, subTitulo, isbn, descricao, tag, categoria, idAutor, idEditora, edicao,
					anoPub, dataAquisicao, valor, nPaginas);

			Alert alert = new Alert(AlertType.INFORMATION, "Livro cadastrado com sucesso.", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();

			txtId.setText(Integer.toString(LivroDAO.pegarUltimoId()));
			txtTitulo.clear();
			txtIsbn.clear();
			txtSubTitulo.clear();
			txtDescricao.clear();
			txtTag.clear();
			txtAutor.clear();
			txtEdicao.clear();
			txtEditora.clear();
			txtAnoPub.clear();
			txtDataAquisicao.setValue(LocalDate.now());
			txtValor.clear();
			txtPaginas.clear();
			chCatVerde.setSelected(false);chCatVerde.setDisable(false);
			chCatVermelho.setSelected(false);chCatVermelho.setDisable(false);
			chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(false);
			chCatAzul.setSelected(false);chCatAzul.setDisable(false);
			txtNomeAutor.clear();
			txtNomeEditora.clear();

		}
	}

	@FXML
	void btnGravarOnAction(ActionEvent event) {

		if ("".equals(txtId.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION, "Livro nao informado para ser alterado", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			if (LivroDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {

				if ("".equals(txtTitulo.getText()) || "".equals(txtAutor.getText())
						|| "".equals(txtEditora.getText())) {

					Alert alert = new Alert(AlertType.INFORMATION, "Os campos obrigatorios nao podem ficar em branco.",
							ButtonType.OK);
					alert.setTitle("Atenção");
					alert.setHeaderText("Informação");
					alert.show();
				} else {

					String pesquisa = txtId.getText();
					String titulo = txtTitulo.getText();
					String subTitulo = txtSubTitulo.getText();
					String isbn = txtIsbn.getText();
					String descricao = txtDescricao.getText();
					String tag = txtTag.getText();
					int categoria = LivroController.getCategoria();
					int idAutor = Integer.parseInt(txtAutor.getText());
					int idEditora = Integer.parseInt(txtEditora.getText());
					String edicao = txtEdicao.getText();
					int anoPub = "".equals(txtAnoPub.getText()) ? 0 : Integer.parseInt(txtAnoPub.getText());
					String dataAquisicao = txtDataAquisicao.getValue()
							.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					String valor = txtValor.getText();
					int nPaginas = Integer.parseInt(txtPaginas.getText());
					LivroDAO.editarLivro(pesquisa, titulo, subTitulo, isbn, descricao, tag, categoria, idAutor,
							idEditora, edicao, anoPub, dataAquisicao, valor, nPaginas);

					Alert alert = new Alert(AlertType.INFORMATION, "Livro Alterado com Sucesso.", ButtonType.OK);
					alert.setTitle("Atenção");
					alert.setHeaderText("Informação");
					alert.show();

					txtId.setText(Integer.toString(LivroDAO.pegarUltimoId()));
					txtTitulo.clear();
					txtIsbn.clear();
					txtSubTitulo.clear();
					txtDescricao.clear();
					txtTag.clear();
					txtAutor.clear();
					txtEdicao.clear();
					txtEditora.clear();
					txtAnoPub.clear();
					txtDataAquisicao.setValue(LocalDate.now());
					txtValor.clear();
					txtPaginas.clear();
					chCatVerde.setSelected(false);chCatVerde.setDisable(false);
					chCatVermelho.setSelected(false);chCatVermelho.setDisable(false);
					chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(false);
					chCatAzul.setSelected(false);chCatAzul.setDisable(false);
					txtNomeAutor.clear();
					txtNomeEditora.clear();
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar um livro valido para alterar",
						ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();
			}
		}

	}

	@FXML
	void btnDeletarOnAction(ActionEvent event) {

		if ("".equals(txtId.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION, "Livro nao informado para ser deletado", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			if (LivroDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {

				LivroDAO.apagarLivro(txtId.getText());
				Alert alert = new Alert(AlertType.INFORMATION, "Livro apagado com sucesso.", ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();

				txtId.setText(Integer.toString(LivroDAO.pegarUltimoId()));
				txtTitulo.clear();
				txtIsbn.clear();
				txtSubTitulo.clear();
				txtDescricao.clear();
				txtTag.clear();
				txtAutor.clear();
				txtEdicao.clear();
				txtEditora.clear();
				txtAnoPub.clear();
				txtDataAquisicao.setValue(LocalDate.now());
				txtValor.clear();
				txtPaginas.clear();
				chCatVerde.setSelected(false);chCatVerde.setDisable(false);
				chCatVermelho.setSelected(false);chCatVermelho.setDisable(false);
				chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(false);
				chCatAzul.setSelected(false);chCatAzul.setDisable(false);
				txtNomeAutor.clear();
				txtNomeEditora.clear();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar um livro valido para deletar",
						ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();
			}
		}
	}

	@FXML
	void btnPesquisarOnAction(ActionEvent event) {
		try {
			Stage stageLivro = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/view/vwPesquisaLivro.fxml"));
			Scene scene = new Scene(root1);
			stageLivro.setScene(scene);
			stageLivro.show();

			stageLivro.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							if (PesquisaLivroController.getLivro() != null) {
								txtId.setText(Integer.toString(PesquisaLivroController.getLivro().getId()));
								txtTitulo.setText(PesquisaLivroController.getLivro().getTitulo());
								txtIsbn.setText(PesquisaLivroController.getLivro().getIsbn());
								txtSubTitulo.setText(PesquisaLivroController.getLivro().getSubTitulo());
								txtDescricao.setText(PesquisaLivroController.getLivro().getDescricao());
								txtTag.setText(PesquisaLivroController.getLivro().getTag());
								txtAutor.setText(Integer.toString(PesquisaLivroController.getLivro().getIdAutor()));
								
								txtNomeAutor.setText(AutorDAO.pesquisarAutor(txtAutor.getText()).getNome());
								txtNomeEditora.setText(EditoraDAO.pesquisarEditgora(txtEditora.getText()).getNome());
								
								
								txtEditora.setText(Integer.toString(PesquisaLivroController.getLivro().getIdEditora()));
								txtValor.setText(PesquisaLivroController.getLivro().getValor());
								txtPaginas.setText(Integer.toString(PesquisaLivroController.getLivro().getnPaginas()));
								txtEdicao.setText(PesquisaLivroController.getLivro().getEdicao());
								txtAnoPub.setText(Integer.toString(PesquisaLivroController.getLivro().getAnoPub()));
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								LocalDate date = LocalDate.parse(PesquisaLivroController.getLivro().getDataaquisicao(),
										formatter);
								txtDataAquisicao.setValue(date);
								
								switch (PesquisaLivroController.getLivro().getCategoria()) {
								case 1:
									chCatVerde.setSelected(true);chCatVerde.setDisable(false);
									chCatVermelho.setSelected(false);chCatVermelho.setDisable(true);
									chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(true);
									chCatAzul.setSelected(false);chCatAzul.setDisable(true);
									break;
								case 2:
									chCatVerde.setSelected(false);chCatVerde.setDisable(true);
									chCatVermelho.setSelected(true);chCatVermelho.setDisable(false);
									chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(true);
									chCatAzul.setSelected(false);chCatAzul.setDisable(true);
									break;
								case 3:
									chCatVerde.setSelected(false);chCatVerde.setDisable(true);
									chCatVermelho.setSelected(false);chCatVermelho.setDisable(true);
									chCatAmarelo.setSelected(true);chCatAmarelo.setDisable(false);
									chCatAzul.setSelected(false);chCatAzul.setDisable(true);
									break;
								case 4:
									chCatVerde.setSelected(false);chCatVerde.setDisable(true);
									chCatVermelho.setSelected(false);chCatVermelho.setDisable(true);
									chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(true);
									chCatAzul.setSelected(true);chCatAzul.setDisable(false);
									break;
								default:
									chCatVerde.setSelected(false);chCatVerde.setDisable(false);
									chCatVermelho.setSelected(false);chCatVermelho.setDisable(false);
									chCatAmarelo.setSelected(false);chCatAmarelo.setDisable(false);
									chCatAzul.setSelected(false);chCatAzul.setDisable(false);
									break;
								}
							}
						}
					});
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
    @FXML
    void btnPesquisarAutorOnAction(ActionEvent event) {
    	try {
			Stage stageAutor = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/view/vwPesquisaAutor.fxml"));
			Scene scene = new Scene(root1);
			stageAutor.setScene(scene);
			stageAutor.show();

			stageAutor.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							if (PesquisaAutorController.getAutor() != null) {
								txtAutor.setText(Integer.toString(PesquisaAutorController.getAutor().getId()));
								txtNomeAutor.setText(PesquisaAutorController.getAutor().getNome());
							}
						}
					});
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnPesquisarEditoraOnAction(ActionEvent event) {
    	try {
			Stage stageEditora = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/view/vwPesquisaEditora.fxml"));
			Scene scene = new Scene(root1);
			stageEditora.setScene(scene);
			stageEditora.show();

			stageEditora.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							if (PesquisaEditoraController.getEditora() != null) {
								txtEditora.setText(Integer.toString(PesquisaEditoraController.getEditora().getId()));
								txtNomeEditora.setText(PesquisaEditoraController.getEditora().getNome());
							}
						}
					});
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtDataAquisicao.setValue(LocalDate.now());
		MascarasFX.mascaraNumeroInteiro(txtPaginas);
		MascarasFX.mascaraNumeroInteiro(txtAnoPub);
		MascarasFX.mascaraNumeroInteiro(txtAutor);
		MascarasFX.mascaraNumeroInteiro(txtEditora);
		MascarasFX.mascaraData(txtDataAquisicao);
		MascarasFX.monetaryField(txtValor);

	}
	
    @FXML
    void btnCadastrarEditoraOnAction(ActionEvent event) {
    	try {
			Stage s1 = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/vwEditora.fxml"));
			Scene scene = new Scene(root);
			s1.setScene(scene);
			s1.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnCadastrarAutorOnAction(ActionEvent event) {
    	try {
			Stage s1 = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/vwAutor.fxml"));
			Scene scene = new Scene(root);
			s1.setScene(scene);
			s1.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    


}
