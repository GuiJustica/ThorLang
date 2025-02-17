import java.text.CharacterIterator;

public class AtribPlus extends AFD{
    @Override
    public Token evaluate(CharacterIterator code){

    switch(code.current()){

        case '\n':
            code.next();
            return new Token("eol", "\n");
        case '(':
            code.next();
            return new Token("AbrePare","(" );
        case ')':
            code.next();
            return new Token("FechaPare",")");
        case '{':
            code.next();
            return new Token("AbreChave","{");
        case '}':
            code.next();
            return new Token("FechaChave","}");
        case '[':
            code.next();
            return new Token("AbreConc","[");
        case ']':
            code.next();
            return new Token("FechaCon","]");
        case ';':
            code.next();
            return new Token(";;", ";");
        case '=':
            code.next();
            if(code.current() == '='){
                code.next();
                return new Token("Comparar","==");
            }
            return new Token("operadorAtribuicao","=");
        case '>':
            code.next();
            if(code.current() == '='){
                code.next();
                return new Token("Maior",">=");
            }
            return new Token("Maior",">");
        case '<':
            code.next();
            if(code.current() == '='){
                code.next();
                return new Token("Menor","<=");
            }
            return new Token("Menor","<");

        default:
            return null;
        }
    }
}
