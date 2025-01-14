import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nome) throws IOException {

        // leitura da imagem
        //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filmeMaior.jpg"));
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurarFonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);


        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nome));
    }

    // public static void main(String[] args) throws IOException {
    //     GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();

    //     geradoraDeFigurinhas.cria();
    // }
}