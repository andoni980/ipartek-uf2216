package com.dev.ipartek.uf2216examen.fabrica;

import java.util.Properties;

import com.dev.ipartek.uf2216examen.dal.DalException;
import com.dev.ipartek.uf2216examen.dal.DaoLibro;
import com.dev.ipartek.uf2216examen.dal.DaoLibroMemoria;

public class FabricaDaoImp implements FabricaDao {

	private static final String FABRICA_PROPERTIES = "fabrica.properties";
	
	private final DaoLibro daoLibro;

	public FabricaDaoImp() {
		this(FABRICA_PROPERTIES);
	}
	
	public FabricaDaoImp(String configuracion) {
		try {
			Properties props = new Properties();
			props.load(FabricaDaoImp.class.getClassLoader().getResourceAsStream(configuracion));

			String tipo = props.getProperty("tipo");

			daoLibro = switch (tipo) {
			case "memoria" -> DaoLibroMemoria.getInstancia();
			default -> throw new DalException("NO se reconoce el tipo " + tipo);
			};
		} catch (Exception e) {
			throw new DalException("Error al leer la configuración", e);
		} finally {
			
		}

	}

	@Override
	public DaoLibro getDaoLibro() {
		return daoLibro;
	}

}
