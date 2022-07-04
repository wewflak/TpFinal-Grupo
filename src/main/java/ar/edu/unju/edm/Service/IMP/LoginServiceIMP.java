
package ar.edu.unju.edm.Service.IMP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Model.Cliente;
import ar.edu.unju.edm.Repository.ClienteRepository;

@Service
public class LoginServiceIMP implements UserDetailsService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException{
		// TODO Auto-generated method stub
		
		//busqueda del usuario
		Cliente clienteEncontrado = clienteRepository.findById(Long.parseLong(dni)).orElseThrow(()->new UsernameNotFoundException("Login Invalido"));
		
		//definir autorizaciones
		List<GrantedAuthority> tipos = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(clienteEncontrado.getTipo());
		tipos.add(grantedAuthority);
		
		//definir el usuario en sesion
		UserDetails usuarioEnSesion = new User(dni,clienteEncontrado.getContrasena(),tipos);
		
		return usuarioEnSesion;
	}

}