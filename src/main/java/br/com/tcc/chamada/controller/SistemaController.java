package br.com.tcc.chamada.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.chamada.modelo.Aluno;
import br.com.tcc.chamada.modelo.BuscaAluno;
import br.com.tcc.chamada.modelo.Login;
import br.com.tcc.chamada.modelo.Professor;
import br.com.tcc.chamada.security.UserDetailsServiceImpl;

@Controller
@RequestMapping("/sistema")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)

public class SistemaController {
	
	
	@RequestMapping(value = "/home",method = RequestMethod.GET, name = "montarHome")
	public ModelAndView montarHome() {
		ModelAndView mav = new ModelAndView("sistema/home");
		
		return mav;
	}
		
		
		@RequestMapping(value = "/sobre",method = RequestMethod.GET, name = "montarSobre")
		public ModelAndView montarSobre() {
			ModelAndView mav = new ModelAndView("sistema/sobre");
			
			return mav;
	
	}
		
		@RequestMapping(value = "/tipoAcesso",method = RequestMethod.GET, name = "montarTipoAcesso")
		public ModelAndView montarTipoAcesso() {
			ModelAndView mav = new ModelAndView("sistema/tipoAcesso");
			
			return mav;
	
	}
		
		
		@RequestMapping(value = "/paginaAdm",method = RequestMethod.GET, name = "montarPaginaAdm")
		public ModelAndView montarPaginaAdm() {
			ModelAndView mav = new ModelAndView("sistema/paginaAdm");
			
			return mav;
	
	}
		
		@RequestMapping(value = "/paginaProfessor",method = RequestMethod.GET, name = "montarPaginaProfessor")
		public ModelAndView montarPaginaProfessor() {
			ModelAndView mav = new ModelAndView("sistema/paginaProfessor");
			
			return mav;
	}
		@RequestMapping(value = "/acessoAdm",method = RequestMethod.GET, name = "montarAcessoAdm")
		public ModelAndView montarAcessoAdm() {
			ModelAndView mav = new ModelAndView("sistema/acessoAdm");
			
			return mav;
	
	}
		
		
		@RequestMapping(value = "/acessoProfessor",method = RequestMethod.GET, name = "montarAcessoProfessor")
		public ModelAndView montarAcessoProfessor() {
			ModelAndView mav = new ModelAndView("sistema/acessoProfessor");
			
			return mav;
	
	}
		
		
		
   @RequestMapping(value = "/acessoAdm",method = RequestMethod.POST, name = "montarLogin")
    public ModelAndView montarLogin(Login login) {
			ModelAndView mav  = new ModelAndView("sistema/acessoAdm");;
			
			if(login.getUsername().equals("admin") &&
					login.getPassword().equals("123")){
				mav = new ModelAndView("sistema/paginaAdm");
			}else{
				UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();
				Professor professor = null;
				try{
					professor = (Professor)userDetailsServiceImpl
							.loadUserByUsername(login.getUsername());
				
				}catch(UsernameNotFoundException user){
					mav = new ModelAndView("sistema/acessoProfessor");
				}
				
				if(professor.getPassword().equals(login.getPassword())){
					mav = new ModelAndView("sistema/paginaProfessor");
				}else{
					mav = new ModelAndView("sistema/acessoProfessor");
				}
				
			}
			mav.addObject("login",login);
			return mav;
	
	}
	


	

	
		
	
	

}


