package com.dev.ipartek.uf2216examen.dal;

import java.util.TreeMap;

import com.dev.ipartek.uf2216examen.pojos.Libro;

public class DaoLibroMemoria implements DaoLibro {

	protected TreeMap<Long, Libro> libros = new TreeMap<>();
	
	
	protected DaoLibroMemoria() {
		libros.put(1L, new Libro(1L, "Primer Libro", "SADASERF13", 100, false));
		libros.put(2L, new Libro(2L, "Segundo Libro", "GFD4RF67HU", 150, false));
		libros.put(3L, new Libro(3L, "Tercer Libro", "QWERTYRT54", 200, true));
		libros.put(4L, new Libro(4L, "Cuarto Libro", "FDDFDFFDT5", 250, true));
	}
	
	private static final DaoLibroMemoria INSTANCIA = new DaoLibroMemoria();

	public static DaoLibroMemoria getInstancia() {
		return INSTANCIA;
	}
	
	
	@Override
	public Iterable<Libro> obtenerTodos() {
		return libros.values();
	}

	@Override
	public Libro obtenerPorId(Long id) {
		return libros.get(id);
	}

	@Override
	public Libro insertar(Libro libro) {
		Long id = libros.size() > 0 ? libros.lastKey() + 1L : 1L;
		libro.setId(id);
		libros.put(id, libro);
		return libro;
	}

	@Override
	public Libro modificar(Libro libro) {
		libros.put(libro.getId(), libro);
		return libro;
		
	}

	@Override
	public void borrar(Long id) {
		libros.remove(id);
	}

}
