package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entidade.Usuario;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistencia.UsuarioDAO;
import util.MascarasFX;

public class UsuarioController implements Initializable {

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtLogin;

	@FXML
	private Button btnPesquisarUsuario;

	@FXML
	private TextField txtSenha;

	@FXML
	private Button btnGravarAlteracoes;

	@FXML
	private Button btnCadastrar;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnDeletar;

	@FXML
	private Button btnNovo;


	@FXML
	void btnNovoOnAction(ActionEvent event) {
		txtEmail.clear();
		txtLogin.clear();
		txtNome.clear();
		txtSenha.clear();
		txtId.setText(Integer.toString(UsuarioDAO.pegarUltimoId()));
	}

	@FXML
	void btnCadastrarOnAction(ActionEvent event) {
		if (txtNome.getText().trim().equals("") || txtEmail.getText().trim().equals("")
				|| txtLogin.getText().trim().equals("") || txtSenha.getText().trim().equals("")) {

			Alert alert = new Alert(AlertType.INFORMATION, "É necessario todos que todos os dados estejam preenchidos.",
					ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();

		} else {

			UsuarioDAO.cadastrarUsuario(txtNome.getText(), txtEmail.getText(), txtLogin.getText(), txtSenha.getText());
			Alert alert = new Alert(AlertType.INFORMATION, "Usuario cadastrado com sucesso.", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
			txtEmail.clear();
			txtLogin.clear();
			txtNome.clear();
			txtSenha.clear();
			txtId.clear();

		}
	}

	@FXML
	void btnGravarAlteracoesOnAction(ActionEvent event) {

		if ("".equals(txtId.getText().trim()) || txtNome.getText().trim().equals("")
				|| txtEmail.getText().trim().equals("") || txtLogin.getText().trim().equals("")
				|| txtSenha.getText().trim().equals("")) {

			Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar um usuario valido para alterar",
					ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();

		} else {

			if (UsuarioDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {
				UsuarioDAO.editarUsuario(txtId.getText(), txtNome.getText(), txtEmail.getText(), txtLogin.getText(),
						txtSenha.getText());
				Alert alert = new Alert(AlertType.INFORMATION, "Usuario Alterado com Sucesso.", ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();
				txtEmail.clear();
				txtLogin.clear();
				txtNome.clear();
				txtSenha.clear();
				txtId.clear();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar um usuario valido para alterar",
						ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();
			}

		}
	}

	@FXML
	void btnDeletarOnAction(ActionEvent event) {

		Usuario usuario = null;

		if (txtNome.getText().trim().equals("") || txtEmail.getText().trim().equals("")
				|| txtLogin.getText().trim().equals("") || txtSenha.getText().trim().equals("")) {

			usuario = new Usuario(1, "invalido", "invalido", "invalido", "invalido");

		} else {

			usuario = new Usuario(txtId.getText().equals("") ? 1 : Integer.parseInt(txtId.getText()), txtNome.getText(),
					txtEmail.getText(), txtLogin.getText(), txtSenha.getText());
		}
		if (UsuarioDAO.mapearUsuario().contains(usuario)) {
			UsuarioDAO.apagarUsuario(txtId.getText());
			Alert alert = new Alert(AlertType.INFORMATION, "Usuario apagado com sucesso.", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
			txtEmail.clear();
			txtLogin.clear();
			txtNome.clear();
			txtSenha.clear();
			txtId.clear();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar um usuario valido para deletar",
					ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		}
	}

	@FXML
	void btnPesquisarUsuarioOnAction(ActionEvent event) {

		try {

			Stage s1 = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/vwPesquisaUsuario.fxml"));
			Scene scene = new Scene(root);
			s1.setScene(scene);
			s1.show();

			s1.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							if (PesquisaUsuarioController.getUsuario() != null) {
								txtId.setText(Integer.toString(PesquisaUsuarioController.getUsuario().getId()));
								txtNome.setText(PesquisaUsuarioController.getUsuario().getNome());
								txtEmail.setText(PesquisaUsuarioController.getUsuario().getEmail());
								txtLogin.setText(PesquisaUsuarioController.getUsuario().getLogin());
								txtSenha.setText(PesquisaUsuarioController.getUsuario().getSenha());
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
		MascarasFX.mascaraEmail(txtEmail);

	}

}
