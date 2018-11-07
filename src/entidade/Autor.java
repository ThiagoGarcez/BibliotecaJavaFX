package entidade;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Autor {
	
	private int id;
	private String nome;
	
	public Autor() {
	}

	public Autor(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Autor(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public StringProperty nomeProperty() {
		return new SimpleStringProperty(nome);
	}
	
	public StringProperty idProperty() {
		return new SimpleStringProperty(Integer.toString(id));
	}
	
}
