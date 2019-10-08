package br.curso.internetBanking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class COAF implements MovimentacaoObserver{
	private COAF() {};
	private static COAF INSTANCE = new COAF();
	
	public static COAF getInstance() {
		return INSTANCE;
	}
	
	public void notificarMovimentacao(Movimentacao movimentacao) {
		enviarMovimentacao(movimentacao);
	}
	
	private void enviarMovimentacao(Movimentacao movimentacao) {
		HttpResponse<String> response;
		
		StringBuilder sb = new StringBuilder();
		sb.append("{ ")
			.append("\"conta\": ")
			.append("\"")
			.append(movimentacao.getConta().getId())
			.append("\",")
			.append("\"cliente\": ")
			.append("\"")
			.append(movimentacao.getConta().getCliente().getNome())
			.append("\", ")
			.append("\"valor\": ")
			.append("\"")
			.append(movimentacao.getValor())
			.append("\", ")
			.append("\"tipoMovimentacao\": ")
			.append("\"")
			.append(movimentacao.getTipoMovimentacao().toString())
			.append("\", ")
			.append("\"data\": ")
			.append("\"")
			.append(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date.from(movimentacao.getDataMovimentacao().atZone(ZoneId.systemDefault()).toInstant())))
			.append("\" }");
		//Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(sb.toString()))
                .uri(URI.create("http://localhost:8086/movimentacaos")).build();

        CompletableFuture<HttpResponse<String>> future = HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString());
		future.thenAccept(futureResponse -> {
			System.out.println("Resposta do processamento: ");
			System.out.println(String.format("Código de resposta: %s", futureResponse.statusCode()));
			System.out.println(String.format("Retorno da requisição: %s", futureResponse.body()));
		});
		
		while(!future.isDone()) {
			System.out.println("Aguardando resposta da requisição!");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
