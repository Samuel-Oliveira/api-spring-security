package teste;

import br.com.swconsultoria.apispringsecurity.entity.*;
import br.com.swconsultoria.apispringsecurity.util.Util;
import ch.qos.logback.classic.Level;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Teste {

    private static final String URL_BASE = "http://localhost:9090/api/v1/";
    private static final String URL_VENDA = URL_BASE + "venda";
    private static final String TOKEN = "@pi123Yhdfg5ai6#";

    public static void main(String[] args) {

        try {
            ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
            logger.setLevel(Level.toLevel("warn"));
//            enviaVenda();
            buscaVendas();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void enviaVenda() throws Exception {
        HttpPost post = new HttpPost(URL_VENDA);
        Venda venda = criaVenda();
        String jsonEnvio = Util.objectToJson(venda);
        post.setEntity(new StringEntity(jsonEnvio));
        String jsonResposta = enviaRequest(post);
        Venda vendaSalva = Util.jsonToObject(jsonResposta, Venda.class);
        System.out.println(vendaSalva);
    }

    private static void buscaVendas() throws Exception {
        HttpGet get = new HttpGet(URL_VENDA + "/99999999999999");
        String resposta = enviaRequest(get);
        List<Venda> vendas = Arrays.asList(Util.jsonToObject(resposta, Venda[].class));
        vendas.forEach(System.out::println);
    }

    private static String enviaRequest(HttpRequestBase metodo) throws IOException, HttpException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        metodo.setHeader("Content-type", "application/json");

        metodo.setHeader(HttpHeaders.AUTHORIZATION, Util.criaTokenCriptorgrafado(TOKEN));
        CloseableHttpResponse response = httpClient.execute(metodo);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200 && statusCode != 201) {
            throw new HttpException("Erro ao enviar requisição: " + statusCode);
        }
        return EntityUtils.toString(response.getEntity());
    }

    private static Venda criaVenda() {
        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        venda.setValorTotal(BigDecimal.TEN);
        venda.setCnpj("99999999999999");

        Caixa caixa = new Caixa();
        caixa.setValorFechamento(BigDecimal.TEN);
        caixa.setValorAbertura(BigDecimal.TEN);
        caixa.setDataFechamento(LocalDate.now());
        caixa.setDataFechamento(LocalDate.now());
        caixa.setEstado(true);
        venda.setCaixa(caixa);

        VendaPagamento pagamento = new VendaPagamento();
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setNome("Dinheiro");
        pagamento.setPagamento(tipoPagamento);
        pagamento.setValor(BigDecimal.TEN);
        venda.setPagamentos(Collections.singletonList(pagamento));

        VendaProduto vendaProduto = new VendaProduto();
        vendaProduto.setQuantidade(BigDecimal.ONE);
        vendaProduto.setTotal(BigDecimal.TEN);

        Produto produto = new Produto();
        produto.setNome("Produto");
        produto.setValor(BigDecimal.TEN);
        vendaProduto.setProduto(produto);
        venda.setProdutos(Collections.singletonList(vendaProduto));

        return venda;
    }
}
