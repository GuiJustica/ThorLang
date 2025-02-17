import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;





public class Lexer {
    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;

    public Lexer(String code) {
        tokens = new ArrayList<>();
        this.code = new StringCharacterIterator(code);
        afds = new ArrayList<>();

        // Adiciona os AFDs
        afds.add(new MathOperator()); // AFD auau operadores matemáticos
        afds.add(new AtribPlus()); // AFD auau incremento (++) e atribuição (=)
        afds.add(new PalavraReservada()); // AFD auau palavras reservadas (au, auau, etc)
        afds.add(new Numero()); // AFD auau números
        afds.add(new Palavra()); // AFD auau identificadores (id) <-- adicionado aqui
    }

    public void skipWhiteSpace() {
        // Pula espaços em branco e quebras de linha
        while (code.current() == ' ') {
            code.next();
        }
    }

    public List<Token> getTokens() {
        boolean accepted;

        while (code.current() != CharacterIterator.DONE) {
            accepted = false;
            skipWhiteSpace();

            if (code.current() == CharacterIterator.DONE)
                break;

            for (AFD afd : afds) {
                int pos = code.getIndex();
                Token t = afd.evaluate(code);

                if (t != null) {
                    accepted = true;
                    tokens.add(t);
                    break;
                } else {
                    code.setIndex(pos);
                }
            }

            if (accepted)
                continue;

            throw new RuntimeException("Erro: Token não reconhecido? " + code.current());
        }

        tokens.add(new Token("EOF", "$"));
        return tokens;
    }
}
