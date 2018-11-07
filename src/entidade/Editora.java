package entidade;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Editora {
	
	private int id;
	private String nome;
	
	public Editora() {
	}
	
	public Editora(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Editora(String nome) {
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

	@Override
	public String toString() {
		return "Editora: \n" + "ID: " + id + " Nome: " + nome;
	}
	
	
}
