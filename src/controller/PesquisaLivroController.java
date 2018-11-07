package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entidade.Livro;
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
import persistencia.LivroDAO;

public class PesquisaLivroController implements Initializable {

	@FXML
	private TableColumn<Livro, String> colunaTitulo;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TableColumn<Livro, String> colunaId;

	@FXML
	private TableColumn<Livro, String> colunaSubTitulo;

	@FXML
	private TextField txtPesquisa;

	@FXML
	private TableView<Livro> tvLivro;

	private static Livro livro;

	public static Livro getLivro() {
		return livro;
	}

	public static void setLivro(Livro livro) {
		PesquisaLivroController.livro = livro;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 0. Initialize the columns.
		colunaTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
		colunaSubTitulo.setCellValueFactory(cellData -> cellData.getValue().subTituloProperty());
		colunaId.setCellValueFactory(cellData -> cellData.getValue().idProperty());

		ObservableList<Livro> masterData = FXCollections.observableArrayList();

		masterData = LivroDAO.converterArrayListToObservableList();

		FilteredList<Livro> filteredData = new FilteredList<>(masterData, p -> true);

		txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livro -> {

				// Checa se tem algo digitado, caso nao estiver digitado mostra todo mundo
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compara nome e login com todos os campos.
				String lowerCaseFilter = newValue.toLowerCase();

				if (livro.getTitulo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (livro.getSubTitulo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.

		SortedList<Livro> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvLivro.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tvLivro.setItems(sortedData);

		tvLivro.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					PesquisaLivroController.setLivro(tvLivro.getSelectionModel().getSelectedItem());
					tvLivro.getScene().getWindow().hide();
				}
			}
		});

	}

}
