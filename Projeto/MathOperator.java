import java.text.CharacterIterator;

public class MathOperator extends AFD{
    @Override
    public Token evaluate(CharacterIterator code){
        StringBuilder palavra = new StringBuilder();
        while (Character.isLetterOrDigit(code.current())) {
            palavra.append(code.current());
            code.next();
        }

        switch (palavra.toString()) {
            case "some":
                code.next();
                return new Token("Mais","some");
            case "subtraia":
                code.next();
                return new Token("Menos","subtraia");
            case "multi":
                code.next();
                return new Token("Multi","multi");
            case "div":
                code.next();
                return new Token("Div","divida");

            default:
                return null;
            }
    }
}
