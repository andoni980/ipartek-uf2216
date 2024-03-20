package com.dev.ipartek.uf2216examen.pojos;

import java.util.Objects;

public class Libro {

	private Long id;
	private String titulo;
	private String isbn;
	private Integer numPaginas;
	private Boolean formato;

	public Libro() {
	}

	public Libro(Long id, String titulo, String isbn, Integer numPaginas, Boolean formato) {
		setId(id);
		setTitulo(titulo);
		setIsbn(isbn);
		setNumPaginas(numPaginas);
		setFormato(formato);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id < 0) {
			throw new RuntimeException("No se admiten valores de id menores que 0");
		}
		this.id = id;
	}

	public void setId(String id) {
		if (id == null || id.isBlank()) {
			this.id = null;
			return;
		}

		this.id = Long.valueOf(id);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.isBlank()) {
			throw new RuntimeException("El titulo del libro es obligatorio");
		}

		this.titulo = titulo.trim();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		if (titulo == null || titulo.isBlank()) {
			throw new RuntimeException("El ISBN del libro es obligatorio");
		}
		this.isbn = isbn;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Integer numPaginas) {
		if (numPaginas != null && numPaginas <= 0) {
			throw new RuntimeException("Un libro no puede tener 0 o menos páginas");
		}
		this.numPaginas = numPaginas;
	}

	public Boolean getFormato() {
		return formato;
	}

	public void setFormato(Boolean formato) {
		if (formato == null) {
			throw new RuntimeException("Es obligatorio definir cual es el formato");
		}
		this.formato = formato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(formato, id, isbn, numPaginas, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(formato, other.formato) && Objects.equals(id, other.id)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(numPaginas, other.numPaginas)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + "" + ", titulo=" + titulo + "" + ", isbn=" + isbn + "" + ", numPaginas=" + numPaginas
				+ "" + ", formato=" + formato + "]";
	}

}
