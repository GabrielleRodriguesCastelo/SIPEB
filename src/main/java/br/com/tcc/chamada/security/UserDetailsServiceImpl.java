package br.com.tcc.chamada.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.tcc.chamada.dao.AlunoDAO;
import br.com.tcc.chamada.modelo.Aluno;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AlunoDAO alunoDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Aluno user = alunoDAO.findByEmail(email);

		if (user == null)
			throw new UsernameNotFoundException("Error, usuário não existe");

		return user;
	}

}
