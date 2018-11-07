package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private Button btnLivro;

    @FXML
    private Button btnUsuario;

    @FXML
    void btnUsuarioOnAction(ActionEvent event) {

		try {
			Stage s1 = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/vwUsuario.fxml"));
			Scene scene = new Scene(root);
			s1.setScene(scene);
			s1.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void btnLivroOnAction(ActionEvent event) {
		try {
			Stage s1 = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/vwLivro.fxml"));
			Scene scene = new Scene(root);
			s1.setScene(scene);
			s1.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void btnAutorOnAction(ActionEvent event) {
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

    @FXML
    void btnEditoraOnAction(ActionEvent event) {
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
    void btnRelatoriosOnAction(ActionEvent event) {

    }

}
