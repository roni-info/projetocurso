package br.com.roni;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.roni.enums.EstadoPagamento;
import br.com.roni.enums.TipoCliente;
import br.com.roni.model.Categoria;
import br.com.roni.model.Cidade;
import br.com.roni.model.Cliente;
import br.com.roni.model.Endereco;
import br.com.roni.model.Estado;
import br.com.roni.model.ItemPedido;
import br.com.roni.model.Pagamento;
import br.com.roni.model.PagamentoComBoleto;
import br.com.roni.model.PagamentoComCartao;
import br.com.roni.model.Pedido;
import br.com.roni.model.Produto;
import br.com.roni.repository.CategoriaRepository;
import br.com.roni.repository.CidadeRepository;
import br.com.roni.repository.ClienteRepository;
import br.com.roni.repository.EnderecoRepository;
import br.com.roni.repository.EstadoRepository;
import br.com.roni.repository.ItemPedidoRepository;
import br.com.roni.repository.PagamentoRepository;
import br.com.roni.repository.PedidoRepository;
import br.com.roni.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetocursoApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetocursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
