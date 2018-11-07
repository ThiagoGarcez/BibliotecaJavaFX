package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entidade.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import persistencia.UsuarioDAO;

public class PesquisaUsuarioController implements Initializable {

	@FXML
	private TableColumn<Usuario, String> ColunaLogin;

	@FXML
	private TableColumn<Usuario, String> ColunaId;

	@FXML
	private TextField txtPesquisa;

	@FXML
	private TableView<Usuario> tvUsuario;

	@FXML
	private TableColumn<Usuario, String> ColunaNome;
	
	@FXML
	private AnchorPane anchorPane;


	private static Usuario usuario;

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		PesquisaUsuarioController.usuario = usuario;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 0. Initialize the columns.
		ColunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		ColunaLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
		ColunaId.setCellValueFactory(cellData -> cellData.getValue().idProperty());

		ObservableList<Usuario> masterData = FXCollections.observableArrayList();

		masterData = UsuarioDAO.converterArrayListToObservableList();

		FilteredList<Usuario> filteredData = new FilteredList<>(masterData, p -> true);

		txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(usuario -> {

				// Checa se tem algo digitado, caso nao estiver digitado mostra todo mundo
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compara nome e login com todos os campos.
				String lowerCaseFilter = newValue.toLowerCase();

				if (usuario.getNome().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (usuario.getLogin().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.

		SortedList<Usuario> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvUsuario.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tvUsuario.setItems(sortedData);

		tvUsuario.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					PesquisaUsuarioController.setUsuario(tvUsuario.getSelectionModel().getSelectedItem());
					tvUsuario.getScene().getWindow().hide();
				}
			}
		});

	}

	

}
