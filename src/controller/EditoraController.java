package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistencia.EditoraDAO;

public class EditoraController {

	@FXML
	private TextField txtId;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnAlterar;

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnPesquisa;

	@FXML
	private Button btnDeletar;

	@FXML
	private Button btnNovo;

	@FXML
	void btnPesquisaOnAction(ActionEvent event) {
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
								txtId.setText(Integer.toString(PesquisaEditoraController.getEditora().getId()));
								txtNome.setText(PesquisaEditoraController.getEditora().getNome());
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
	void btnNovoOnAction(ActionEvent event) {
		txtNome.clear();
		txtId.setText(Integer.toString(EditoraDAO.pegarUltimoId()));
	}

	@FXML
	void btnCadastrarOnAction(ActionEvent event) {

		if ("".equals(txtNome.getText())) {

			Alert alert = new Alert(AlertType.INFORMATION, "Preencha o nome para cadastrar uma Editora", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			String nome = txtNome.getText();

			EditoraDAO.cadastrarEditora(nome);

			Alert alert = new Alert(AlertType.INFORMATION, "Editora cadastrada com sucesso.", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();

			txtId.setText(Integer.toString(EditoraDAO.pegarUltimoId()));
			txtNome.clear();

		}
	}

	@FXML
	void btnAlterarOnAction(ActionEvent event) {
		
		if ("".equals(txtId.getText())) {

			Alert alert = new Alert(AlertType.INFORMATION, "Editora nao informada para ser alterada", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			if (EditoraDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {

				if ("".equals(txtNome.getText())) {

					Alert alert = new Alert(AlertType.INFORMATION, "É preciso preencher o nome da Editora",
							ButtonType.OK);
					alert.setTitle("Atenção");
					alert.setHeaderText("Informação");
					alert.show();
				} else {

					String pesquisa = txtId.getText();
					String nome = txtNome.getText();

					EditoraDAO.editarEditora(pesquisa, nome);

					Alert alert = new Alert(AlertType.INFORMATION, "Editora Alterada com Sucesso.", ButtonType.OK);
					alert.setTitle("Atenção");
					alert.setHeaderText("Informação");
					alert.show();

					txtId.setText(Integer.toString(EditoraDAO.pegarUltimoId()));
					txtNome.clear();
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar uma Editora valida para alterar",
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
			Alert alert = new Alert(AlertType.INFORMATION, "Editora nao informada para ser deletada", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			if (EditoraDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {

				EditoraDAO.apagarEditora(txtId.getText());
				Alert alert = new Alert(AlertType.INFORMATION, "Editora apagada com sucesso.", ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();

				txtId.setText(Integer.toString(EditoraDAO.pegarUltimoId()));
				txtNome.clear();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar uma editora valida para deletar",
						ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();
			}
		}
	}

}
