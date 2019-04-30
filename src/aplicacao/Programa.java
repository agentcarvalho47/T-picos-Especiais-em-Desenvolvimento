package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		Scanner s = new Scanner(System.in);
		Pessoa p = new Pessoa();
		int e = 0;

		do {
			System.out.println("1 – Listar Pessoas cadastradas");
			System.out.println("2 – Buscar uma Pessoa pelo id");
			System.out.println("3 – Cadastrar Pessoa");
			System.out.println("4 – Atualizar Pessoa");
			System.out.println("5 – Remover uma Pessoa");
			System.out.println("0 – Sair");
			e = s.nextInt();
			switch (e) {
			case 1:
				p.listarPessoas(em);
				break;
			case 2:			
				p.buscarId(em);
				break;
			case 3:
				p.cadastrarPessoa(em);
				break;
			case 4:
				p.atualizarPessoa(em);
				break;
			case 5:
				p.removerPessoa(em);
				break;
			case 0:
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (e != 0);
		
		em.close();
		emf.close();
	}

}
