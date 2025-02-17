import java.text.CharacterIterator;

public class Numero extends AFD{
    @Override
    public Token evaluate(CharacterIterator code){
        if (Character.isDigit(code.current())){
            String numero = readNumber(code);

            if(endNumber(code)){
                return new Token("numero", numero);
            }
            
        }
        return null;
    }


private String readNumber(CharacterIterator code){
    String numero="";
    while(Character.isDigit(code.current())){
        numero += code.current();
        code.next();

    }
    return numero;

}

private boolean endNumber(CharacterIterator code){
    return code.current() == ' ' ||
     code.current() == '+' ||
     code.current() == '\n' ||
     code.current() == CharacterIterator.DONE;
        
    }
}

