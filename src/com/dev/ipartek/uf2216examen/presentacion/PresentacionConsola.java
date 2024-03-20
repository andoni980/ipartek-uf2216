package com.dev.ipartek.uf2216examen.presentacion;

import static com.dev.ipartek.uf2216examen.libs.Consola.OBLIGATORIO;
import static com.dev.ipartek.uf2216examen.libs.Consola.leerInt;
import static com.dev.ipartek.uf2216examen.libs.Consola.leerLong;
import static com.dev.ipartek.uf2216examen.libs.Consola.leerString;

import com.dev.ipartek.uf2216examen.dal.DaoLibro;
import com.dev.ipartek.uf2216examen.fabrica.FabricaDaoImp;
import com.dev.ipartek.uf2216examen.pojos.Libro;

public class PresentacionConsola {

	private static final int SALIR = 0;
	
	private static final DaoLibro DAO = new FabricaDaoImp().getDaoLibro();

	
	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);
		} while (opcion != SALIR);
	}
	
	private static void mostrarMenu() {
	
	System.out.println("""
			MENU
			----

			1. LISTADO
			2. BUSCAR POR ID
			3. INSERTAR
			4. MODIFICAR
			5. BORRAR

			0. SALIR
			""");
	}
	
	private static int pedirOpcion() {
		return leerInt("Selecciona una opción", OBLIGATORIO);
	}
	
	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listado();
		case 2 -> buscar();
		case 3 -> insertar();
		case 4 -> modificar();
		case 5 -> borrar();
		case 0 -> salir();
		default -> System.out.println("Opción no reconocida");
		}
	}
	
	private static void listado() {
		for (var producto : DAO.obtenerTodos()) {
			mostrarFila(producto);
		}
	}
	
	private static void buscar() {
		Long id = leerLong("Dime el id", OBLIGATORIO);
		var producto = DAO.obtenerPorId(id);

		mostrarFicha(producto);
	}
	
	private static void insertar() {
		try {
			String titulo = leerString("Titulo", OBLIGATORIO);
			int longitudTitulo = titulo.length();
			if (longitudTitulo <= 3 || longitudTitulo >= 150) {
				System.out.println("La longitud del título no es válida(debe ser de más de 3 y menos de 150 letras)");
				System.out.println("Ingresa de nuevo el título");
				titulo = leerString("Titulo", OBLIGATORIO);
			}
			
			String isbn = leerString("isbn", OBLIGATORIO);
			int longitudISBN = isbn.length();
			if (longitudISBN != 10) {
				System.out.println("La longitud del ISBN no es válida(debe ser de 10 caracteres)");
				System.out.println("Ingresa de nuevo el ISBN");
				isbn = leerString("isbn", OBLIGATORIO);
			}
			
			Integer numPaginas = leerInt("Numero de Paginas");
			if (numPaginas <= 0) {
				System.out.println("El número de paginas debe ser positivo");
				System.out.println("Ingresa de nuevo el Número de Paginas");
				numPaginas = leerInt("Numero de Paginas");
			}
			
			boolean esCorrecto = false;
			Boolean formato = false;
			String formatoString= leerString("Formato");
			do {
				if("DIGITAL".equalsIgnoreCase(formatoString)) {
					formato = true;
					esCorrecto= true;
				}else if("PAPEL".equalsIgnoreCase(formatoString)) {
					formato = false;
					esCorrecto= true;
				}else {
					System.out.println("No es correcto, el formato debe ser o DIGITAL o PAPEL");
					System.out.println("Introduce de nuevo el Formato");
					formatoString = leerString("Formato");
				}
				
			}while(!esCorrecto);
			
			var libro = new Libro(null, titulo, isbn, numPaginas, formato);
			if(libro != null) {
				System.out.println("¿Quieres guardar el libro?");
				String resp = leerString("[S|N]");
				
				if("S".equalsIgnoreCase(resp)) {
					DAO.insertar(libro);
					System.out.println();
					System.out.println("RESUMEN DEL LIBRO INSERTADO");
					mostrarFila(libro);
					System.out.println();
				}else if("N".equalsIgnoreCase(resp)) {
					System.out.println("NO SE HA GUARDADO EL LIBRO");
					mostrarMenu();
				}else {
					insertar();
				}
			}
			
		}catch(RuntimeException e) {
			insertar();
		}
		
	}
	
	private static void modificar() {
		Long id = leerLong("Id", OBLIGATORIO);
		
		String titulo = leerString("Titulo", OBLIGATORIO);
		int longitudTitulo = titulo.length();
		if (longitudTitulo <= 3 || longitudTitulo >= 150) {
			System.out.println("La longitud del título no es válida(debe ser de más de 3 y menos de 150 letras)");
			System.out.println("Ingresa de nuevo el título");
			titulo = leerString("Titulo", OBLIGATORIO);
		}
		
		String isbn = leerString("isbn", OBLIGATORIO);
		int longitudISBN = isbn.length();
		if (longitudISBN != 10) {
			System.out.println("La longitud del ISBN no es válida(debe ser de 10 caracteres)");
			System.out.println("Ingresa de nuevo el ISBN");
			isbn = leerString("isbn", OBLIGATORIO);
		}
		
		Integer numPaginas = leerInt("Numero de Paginas");
		if (numPaginas <= 0) {
			System.out.println("El número de paginas debe ser positivo");
			System.out.println("Ingresa de nuevo el Número de Paginas");
			numPaginas = leerInt("Numero de Paginas");
		}
		
		boolean esCorrecto = false;
		Boolean formato = false;
		String formatoString= leerString("Formato");
		do {
			if("DIGITAL".equalsIgnoreCase(formatoString)) {
				formato = true;
				esCorrecto= true;
			}else if("PAPEL".equalsIgnoreCase(formatoString)) {
				formato = false;
				esCorrecto= true;
			}else {
				System.out.println("No es correcto, el formato debe ser o DIGITAL o PAPEL");
				System.out.println("Introduce de nuevo el Formato");
				formatoString = leerString("Formato");
			}
			
		}while(!esCorrecto);
		
		var libro = new Libro(id, titulo, isbn, numPaginas, formato);
		
		if(libro != null) {
			System.out.println("¿Quieres modificar el libro?");
			String resp = leerString("[S|N]");
			
			if("S".equalsIgnoreCase(resp)) {
				DAO.modificar(libro);
				System.out.println();
				System.out.println("RESUMEN DEL LIBRO MODIFICADO");
				mostrarFila(libro);
				System.out.println();
			}else if("N".equalsIgnoreCase(resp)) {
				System.out.println("NO SE HA MODIFICADO EL LIBRO");
				mostrarMenu();
			}else {
				modificar();
			}
		}
		
		DAO.modificar(libro);
	}
	
	private static void borrar() {
		Long id = leerLong("Dime el id que deseas borrar", OBLIGATORIO);
		
		DAO.borrar(id);
	}
	
	private static void salir() {
		System.out.print("Gracias por usar esta aplicación");
	}
	
	private static void mostrarFila(Libro libro) {
		System.out.printf("| %03d | %-30s | %10s | %4d | %13s |\n", libro.getId(), libro.getTitulo(), libro.getIsbn(),
				libro.getNumPaginas(), libro.getFormato() ? "DIGITAL" : "PAPEL");
	}
	
	private static void mostrarFicha(Libro libro) {
		System.out.println("------------------------------------------------------------------");
		System.out.printf("%10s: %d", "Id", libro.getId());
		System.out.printf("%10s: %s", "Titulo", libro.getTitulo());
		System.out.printf("%10s: %s", "ISBN", libro.getIsbn());
		System.out.printf("%17s: %d", "Numero de pags", libro.getNumPaginas());
		System.out.printf("%10s: %s", "Formato", libro.getFormato() ? "DIGITAL\n" : "PAPEL\n");
		System.out.println("------------------------------------------------------------------");
	}
}

