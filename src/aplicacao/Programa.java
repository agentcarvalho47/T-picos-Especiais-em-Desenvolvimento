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
		String jpql = "SELECT p FROM Pessoa p";
		List<Pessoa> pessoas = em.createQuery(jpql, Pessoa.class).getResultList();
		Scanner s = new Scanner(System.in);
		
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
				pessoas = em.createQuery(jpql, Pessoa.class).getResultList();
				System.out.println(pessoas);
				break;
			case 2:
				System.out.print("Digite o id: ");
				int id = s.nextInt();				
				Pessoa p = em.find(Pessoa.class, id);
				if(p == null) {
					System.out.println("id não cadastrado!");
				}
				else {
					System.out.println("Pessoa encontrada!");
					System.out.println(p);					
				}
				break;
			case 3:
				System.out.println("Digite o nome: ");
				String nome = s.next();
				System.out.println("Digite o email: ");
				String email = s.next();
				em.getTransaction().begin();
				Pessoa pessoa = new Pessoa(null, nome, email);
				em.persist(pessoa);
				em.getTransaction().commit();
				System.out.println("Pessoa cadastrada!");
				break;
			case 4:
				System.out.println("Digite o id: ");
				int id1 = s.nextInt();
				Pessoa pFound = em.find(Pessoa.class, id1);
				if(pFound == null) {
					System.out.println("id não cadastrado!");
				}
				else {
					System.out.println("Escolha o campo para atualizar: 1 - Nome, 2 - Email");
					int e1 = s.nextInt();
					switch (e1) {
					case 1:
						String nomeNovo = s.next();
						pFound.setNome(nomeNovo);
						em.getTransaction().begin();
						em.persist(pFound);
						em.getTransaction().commit();
						break;
					case 2:
						String emailNovo = s.next();
						pFound.setEmail(emailNovo);
						em.getTransaction().begin();
						em.persist(pFound);
						em.getTransaction().commit();
						break;
					default:
						System.out.println("Opção Inválida!");
						break;
					}
					System.out.println("Atualizado!");
				}
				break;
			case 5:
				System.out.println("Digite o id: ");
				int id2 = s.nextInt();
				Pessoa pfound = em.find(Pessoa.class, id2);
				if(pfound == null) {
					System.out.println("id não cadastrado!");
				}
				else {
					em.getTransaction().begin();
					em.remove(pfound);
					em.getTransaction().commit();
					System.out.println("Removido!");					
				}
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
