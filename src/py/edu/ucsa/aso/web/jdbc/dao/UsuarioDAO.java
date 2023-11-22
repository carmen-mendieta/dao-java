package py.edu.ucsa.aso.web.jdbc.dao;

import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dto.Rol;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public interface UsuarioDAO {
	
 Usuario autenticar(String usuario,String clave);
 List<Rol> getRolesByUsuario(int idUsuario);
  
 
	 

}
