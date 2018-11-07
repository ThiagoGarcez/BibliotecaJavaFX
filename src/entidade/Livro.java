package entidade;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Livro {

	private int id;
	private String titulo;
	private String subTitulo;
	private String isbn;
	private String descricao;
	private String tag;
	private int categoria;
	private int idAutor;
	private int idEditora;
	private String edicao;
	private int anoPub;
	private String dataaquisicao;
	private String valor;
	private int nPaginas;

	
	public Livro() {
	}

	
	public Livro(int id, String titulo, String subTitulo, String isbn, String descricao, String tag, int categoria,
			int idAutor, int idEditora, String edicao, int anoPub, String dataaquisicao, String valor, int nPaginas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.isbn = isbn;
		this.descricao = descricao;
		this.tag = tag;
		this.categoria = categoria;
		this.idAutor = idAutor;
		this.idEditora = idEditora;
		this.edicao = edicao;
		this.anoPub = anoPub;
		this.dataaquisicao = dataaquisicao;
		this.valor = valor;
		this.nPaginas = nPaginas;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public int getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getAnoPub() {
		return anoPub;
	}

	public void setAnoPub(int anoPub) {
		this.anoPub = anoPub;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}


	public String getDataaquisicao() {
		return dataaquisicao;
	}


	public void setDataaquisicao(String dataaquisicao) {
		this.dataaquisicao = dataaquisicao;
	}


	public int getnPaginas() {
		return nPaginas;
	}


	public void setnPaginas(int nPaginas) {
		this.nPaginas = nPaginas;
	}


	public StringProperty tituloProperty() {
		return new SimpleStringProperty(titulo);
	}
	
	public StringProperty subTituloProperty() {
		return new SimpleStringProperty(subTitulo);
	}
	
	public StringProperty idProperty() {
		return new SimpleStringProperty(Integer.toString(id));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoPub;
		result = prime * result + categoria;
		result = prime * result + ((dataaquisicao == null) ? 0 : dataaquisicao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((edicao == null) ? 0 : edicao.hashCode());
		result = prime * result + id;
		result = prime * result + idAutor;
		result = prime * result + idEditora;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + nPaginas;
		result = prime * result + ((subTitulo == null) ? 0 : subTitulo.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (anoPub != other.anoPub)
			return false;
		if (categoria != other.categoria)
			return false;
		if (dataaquisicao == null) {
			if (other.dataaquisicao != null)
				return false;
		} else if (!dataaquisicao.equals(other.dataaquisicao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (edicao == null) {
			if (other.edicao != null)
				return false;
		} else if (!edicao.equals(other.edicao))
			return false;
		if (id != other.id)
			return false;
		if (idAutor != other.idAutor)
			return false;
		if (idEditora != other.idEditora)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (nPaginas != other.nPaginas)
			return false;
		if (subTitulo == null) {
			if (other.subTitulo != null)
				return false;
		} else if (!subTitulo.equals(other.subTitulo))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	
}
