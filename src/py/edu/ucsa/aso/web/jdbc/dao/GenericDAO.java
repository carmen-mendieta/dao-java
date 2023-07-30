package py.edu.ucsa.aso.web.jdbc.dao;

import java.util.List;

public interface GenericDAO<T> {
	List<T> listar();
	T getById(int id);
	T insertar(T objeto);
	T modificar (T objeto);
	void eliminar(int id);

}
