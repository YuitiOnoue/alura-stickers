import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        
        FileReader reader = new FileReader("properties/common.properties");
        
        Properties properties = new Properties();
        properties.load(reader);

        // fazer uma conexão HTTP e buscar os top 250 filmes
        //String url = "https://api.mocki.io/v2/549a5d8b";
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_188yo8wc";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-18&end_date=2022-07-20";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        //System.out.println(json);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3 && i < conteudos.size(); i++) {
            
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getImagemUrl()).openStream();
            String nomeArquivo = properties.getProperty("figurinhas.pasta.saida") + "/" + conteudo.getTitulo() 
                                    + properties.getProperty("figurinhas.imagem.formato");

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

        // for (Map<String,String> filme : listaDeFilmes) {
        //     System.out.println("Título: " + filme.get("title"));
        //     System.out.println("Poster: " + filme.get("image"));
        //     String strRating = filme.get("imDbRating");
        //     int intRating  = Math.round(Float.parseFloat(strRating));
        //     String avaliacao = "";
        //     for (int i = 0; i < intRating; i++) {
        //         avaliacao += "\u2b50";
        //     }
        //     System.out.println("Avaliação: " + avaliacao + "(" + strRating + ")");
        //     System.out.println();
        // }
    }
}
