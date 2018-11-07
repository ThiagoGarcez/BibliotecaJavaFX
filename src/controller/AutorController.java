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
import persistencia.AutorDAO;

public class AutorController {

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
								txtId.setText(Integer.toString(PesquisaAutorController.getAutor().getId()));
								txtNome.setText(PesquisaAutorController.getAutor().getNome());
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
    	txtId.setText(Integer.toString(AutorDAO.pegarUltimoId()));
    }

    @FXML
    void btnCadastrarOnAction(ActionEvent event) {

    	if ("".equals(txtNome.getText())) {

			Alert alert = new Alert(AlertType.INFORMATION, "Preencha o nome para cadastrar um autor",
					ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			String nome = txtNome.getText();

			AutorDAO.cadastrarAutor(nome);

			Alert alert = new Alert(AlertType.INFORMATION, "Autor cadastrado com sucesso.", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();

			txtId.setText(Integer.toString(AutorDAO.pegarUltimoId()));
			txtNome.clear();

		}
    }

    @FXML
    void btnAlterarOnAction(ActionEvent event) {
    	
    	if ("".equals(txtId.getText())) {
    		
			Alert alert = new Alert(AlertType.INFORMATION, "Autor nao informado para ser alterado", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			if (AutorDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {

				if ("".equals(txtNome.getText())) {

					Alert alert = new Alert(AlertType.INFORMATION, "É preciso preencher o nome do Autor",
							ButtonType.OK);
					alert.setTitle("Atenção");
					alert.setHeaderText("Informação");
					alert.show();
				} else {

					String pesquisa = txtId.getText();
					String nome = txtNome.getText();
					
					AutorDAO.editarAutor(pesquisa, nome);

					Alert alert = new Alert(AlertType.INFORMATION, "Autor Alterado com Sucesso.", ButtonType.OK);
					alert.setTitle("Atenção");
					alert.setHeaderText("Informação");
					alert.show();

					txtId.setText(Integer.toString(AutorDAO.pegarUltimoId()));
					txtNome.clear();
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
			Alert alert = new Alert(AlertType.INFORMATION, "Autor nao informado para ser deletado", ButtonType.OK);
			alert.setTitle("Atenção");
			alert.setHeaderText("Informação");
			alert.show();
		} else {

			if (AutorDAO.mapearID().contains(Integer.parseInt(txtId.getText()))) {

				AutorDAO.apagarAutor(txtId.getText());
				Alert alert = new Alert(AlertType.INFORMATION, "Autor apagado com sucesso.", ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();

				txtId.setText(Integer.toString(AutorDAO.pegarUltimoId()));
				txtNome.clear();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "É necessario selecionar um autor valido para deletar",
						ButtonType.OK);
				alert.setTitle("Atenção");
				alert.setHeaderText("Informação");
				alert.show();
			}
		}
    }

}
