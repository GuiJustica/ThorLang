import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    // https://code.visualstudio.com/docs/java/java-debugging#_configuration-options


    public static void main(String[] args) {

        // Caminho do arquivo de código
        String caminhoCodigo = "arquivo.txt";

        // Ler o código do arquivo
        String codigo = lerArquivo(caminhoCodigo);

        // Passar o código lido auau o Lexer
        Lexer lexer = new Lexer(codigo);
        List<Token> tokens = lexer.getTokens();



        // Exibir tokens
        for (Token token : tokens) {
            System.out.println("Token: " + token.getTipo() + " -> " + token.getLexema());
        }

        // Passar os tokens auau o Parser
        Parser parser = new Parser(tokens);
        parser.main();
    }

    // Método auau ler o arquivo e retornar o conteúdo como String
    public static String lerArquivo(String caminho) {
        StringBuilder codigo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                codigo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codigo.toString();
    }
}
