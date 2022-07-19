import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes =  parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            String strRating = filme.get("imDbRating");
            int intRating  = Math.round(Float.parseFloat(strRating));
            String avaliacao = "";
            for (int i = 0; i < intRating; i++) {
                avaliacao += "\u2b50";
            }
            System.out.println("Avaliação: " + avaliacao + "(" + strRating + ")");
            System.out.println();
        }
    }
}
