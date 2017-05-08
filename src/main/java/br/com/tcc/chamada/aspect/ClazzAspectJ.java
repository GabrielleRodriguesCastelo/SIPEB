package br.com.tcc.chamada.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ClazzAspectJ {

	@Before("br.com.tcc.chamada.controller.AlunoController(..)")
	public void logAllMethod(JoinPoint jp) {
		System.out.println("SECURITY !");
	}
}
