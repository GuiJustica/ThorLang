import java.text.CharacterIterator;


public class PalavraReservada extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        StringBuilder palavra = new StringBuilder();

        // Lê a palavra enquanto for uma letra ou dígito
        while (Character.isLetter(code.current())) {
            palavra.append(code.current());
            code.next();
        }

        // Verifica au uma palavra foi formada
        String palavraStr = palavra.toString();
        switch (palavraStr) {
            case "au":
                return new Token("if", "au");
            case "senao":
                return new Token("else", "senao");
            case "so":
                return new Token("sou", "so");
            case "auuuu":
                return new Token("while", "auuuu");
            case "auau":
                return new Token("for", "auau");
            case "returne":
                return new Token("return", "returne");
            case "passe":
                return new Token("pass", "passe");
            case "Texto":
                return new Token("string", "Texto");
            case "inteiro":
                return new Token("inteiro", "inteiro");
            case "flutua":
                return new Token("float", "Flutua");
            case "f":
                return new Token("void", "f");
            case "mostra":
                return new Token("sout", "mostra");
            case "latindo":
                return new Token("latindo", "latindo");
            case "E":
                return new Token("&", "E");
            case "OU":
                return new Token("OU", "OU");
            case "auauauau":
                return new Token("comentario", "auauauau");
            case "duvida?":
                return new Token("duvida?", "?");
            case "petisco":
                return new Token("petisco", "petisco");
            case "Thor":
                return new Token("Thor", "Thor");
            case "Gordoo":
                return new Token("Gordao", "Gordoo");
            case "'":
                return new Token("aspas", "'");
            case "carnecarne":
                  return new Token("increse", "carnecarne");
            case "limaolimao":
                return new Token("deincrese", "limaolimao");
            case ";":
                return new Token("separa", ";");
            case "fim codigo":
                return new Token("fim codigo", "fim codigo");


            default:
                return null; // Retorna null caso não seja uma palavra reservada
        }
    }
}
