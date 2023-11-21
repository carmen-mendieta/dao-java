package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public interface UsuarioDAO {
	
 Usuario autenticar(String usuario,String clave);
 Usuario getRolesByUsuario(int id);
 
	 

}
