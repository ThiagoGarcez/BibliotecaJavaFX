package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entidade.Editora;
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
import persistencia.EditoraDAO;

public class PesquisaEditoraController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<Editora, String> colunaId;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private TableColumn<Editora, String> colunaNome;

    @FXML
    private TableView<Editora> tvEditora;
    
    private static Editora editora;

	public static Editora getEditora() {
		return editora;
	}

	public static void setEditora(Editora editora) {
		PesquisaEditoraController.editora = editora;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 0. Initialize the columns.
				colunaId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
				colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
				
				ObservableList<Editora> masterData = FXCollections.observableArrayList();

				masterData = EditoraDAO.converterArrayListToObservableList();

				FilteredList<Editora> filteredData = new FilteredList<>(masterData, p -> true);

				txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(editora -> {

						// Checa se tem algo digitado, caso nao estiver digitado mostra todo mundo
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}

						// Compara nome e login com todos os campos.
						String lowerCaseFilter = newValue.toLowerCase();

						if (editora.getNome().toLowerCase().contains(lowerCaseFilter)) {
							return true; // Filter matches first name.
						} 
						return false; // Does not match.
					});
				});

				// 3. Wrap the FilteredList in a SortedList.

				SortedList<Editora> sortedData = new SortedList<>(filteredData);

				// 4. Bind the SortedList comparator to the TableView comparator.
				sortedData.comparatorProperty().bind(tvEditora.comparatorProperty());

				// 5. Add sorted (and filtered) data to the table.
				tvEditora.setItems(sortedData);

				tvEditora.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
							PesquisaEditoraController.setEditora(tvEditora.getSelectionModel().getSelectedItem());
							tvEditora.getScene().getWindow().hide();
						}
					}
				});
		
	}

}
