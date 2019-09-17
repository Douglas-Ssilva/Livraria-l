package br.com.caelum.teste;

import br.com.caelum.livraria.dao.JPAUtil;

public class Teste {
	
	public static void main(String[] args) {
		JPAUtil jpaUtil= new JPAUtil();
		jpaUtil.getEntityManager();
	}

}
