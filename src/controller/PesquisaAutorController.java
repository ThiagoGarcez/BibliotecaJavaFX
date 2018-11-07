package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entidade.Autor;
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
import persistencia.AutorDAO;

public class PesquisaAutorController implements Initializable {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TableColumn<Autor, String> colunaId;

	@FXML
	private TextField txtPesquisa;

	@FXML
	private TableView<Autor> tvAutor;

	@FXML
	private TableColumn<Autor, String> colunaNome;

	private static Autor autor;

	public static Autor getAutor() {
		return autor;
	}

	public static void setAutor(Autor autor) {
		PesquisaAutorController.autor = autor;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 0. Initialize the columns.
		colunaId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		
		ObservableList<Autor> masterData = FXCollections.observableArrayList();

		masterData = AutorDAO.converterArrayListToObservableList();

		FilteredList<Autor> filteredData = new FilteredList<>(masterData, p -> true);

		txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(autor -> {

				// Checa se tem algo digitado, caso nao estiver digitado mostra todo mundo
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compara nome e login com todos os campos.
				String lowerCaseFilter = newValue.toLowerCase();

				if (autor.getNome().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.

		SortedList<Autor> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvAutor.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tvAutor.setItems(sortedData);

		tvAutor.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					PesquisaAutorController.setAutor(tvAutor.getSelectionModel().getSelectedItem());
					tvAutor.getScene().getWindow().hide();
				}
			}
		});

	}

}
