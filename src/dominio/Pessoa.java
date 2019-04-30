package dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	
	
	public Pessoa(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public Pessoa() {
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void listarPessoas(EntityManager em) {
		String jpql = "SELECT p FROM Pessoa p";
		List<Pessoa> pessoas = em.createQuery(jpql, Pessoa.class).getResultList();
		pessoas = em.createQuery(jpql, Pessoa.class).getResultList();
		System.out.println(pessoas);
	}
	public void buscarId(EntityManager em) {
		Scanner s = new Scanner(System.in);
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
	}
	public void cadastrarPessoa(EntityManager em) {
		Scanner s = new Scanner(System.in);
		System.out.println("Digite o nome: ");
		String nome = s.next();
		System.out.println("Digite o email: ");
		String email = s.next();
		em.getTransaction().begin();
		Pessoa pessoa = new Pessoa(null, nome, email);
		em.persist(pessoa);
		em.getTransaction().commit();
		System.out.println("Pessoa cadastrada!");
	}
	public void atualizarPessoa(EntityManager em) {
		Scanner s = new Scanner(System.in);
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
	}
	public void removerPessoa(EntityManager em) {
		Scanner s = new Scanner(System.in);
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
	}
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
}
