package br.com.roni;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.roni.enums.TipoCliente;
import br.com.roni.model.Categoria;
import br.com.roni.model.Cidade;
import br.com.roni.model.Cliente;
import br.com.roni.model.Endereco;
import br.com.roni.model.Estado;
import br.com.roni.model.Produto;
import br.com.roni.repository.CategoriaRepository;
import br.com.roni.repository.CidadeRepository;
import br.com.roni.repository.ClienteRepository;
import br.com.roni.repository.EnderecoRepository;
import br.com.roni.repository.EstadoRepository;
import br.com.roni.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetocursoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetocursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.);
		Produto p2 = new Produto(null, "Impressora", 2000.);
		Produto p3 = new Produto(null, "Mouse", 2000.);

		// cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		// cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		System.out.println("teste1");

		// est1.getCidades().addAll(Arrays.asList(c1));
		// est2.getCidades().addAll(Arrays.asList(c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		// System.out.println("teste2");

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "333433434", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("222237277", "98988979"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 104", "Jardim", "25600865", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Imperador", "10", "Apto 100", "Centro", "25600654", cli1, c2);

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

	}

}
