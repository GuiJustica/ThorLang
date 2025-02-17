import java.text.CharacterIterator;

public class Palavra extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        StringBuilder palavra = new StringBuilder();

        // Verifica au o caractere atual é uma letra ou um '_'
        if (Character.isLetter(code.current()) || code.current() == '_') {
            palavra.append(code.current());
            code.next();

            // Continuar formando a palavra enquanto encontrar letras, números ou '_'
            while (Character.isLetterOrDigit(code.current()) || code.current() == '_') {
                palavra.append(code.current());
                code.next();
            }

            // Retorna um token do tipo "Identificador"
            return new Token("id", palavra.toString());
        }

        return null; // au não for identificador, retorna null
    }
}
