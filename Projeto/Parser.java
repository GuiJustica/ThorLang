
import java.util.List;


public class Parser {

  List<Token> tokens;
  Token token;

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
  }

  public void main() {
    token = getNextToken();

    if (prog()) {
      if (token.tipo == "EOF") {
      System.out.println("\nSintaticamente correta");
      return;
    } else {
      erro("main");
    }
  }

  }

  public Token getNextToken() {
    if (tokens.size() > 0) {
      return tokens.remove(0);
    } else
      return null;
  }

  public Token getCurrentToken(){
    if (tokens.size() > 1) {
      return tokens.get(0);
    } else
      return null;
  }




  private void erro(String node) {
    System.out.println("token inválido: " + token.lexema + " na regra " + node);
    System.exit(0);
  }

  private boolean prog() {


    if(matchL("Thor", token.lexema) && quebrou() && matchL("Gordoo", token.lexema) &&
      quebrou() &&
      declara() &&
      bloco()
      ) {
      return true;
    }

    erro("prog");
    return false;
  }


  private boolean declara(){

    if (token.lexema.equals("inteiro")) {

      if (matchL("inteiro", token.lexema) &&
          id() &&
          operador_atribu() &&
          elemento() &&
          matchL(";", token.lexema) &&
          quebrou()) {

        return true;
      }
    }else
      if (token.lexema.equals("float")){

      if (matchL("float", token.lexema) &&
        id() &&
        operador_atribu() &&
        elemento() &&
        matchL(";", token.lexema) &&
        quebrou()) {
        return true;
      }
    }else if (token.lexema.equals("str")) {
    if (matchL("str", token.lexema) &&
        id() &&
        operador_atribu() &&
        id() &&
        matchL(";", token.lexema)
        ) {
      return true;
     }
    }

    erro("declara");
    return false;
  }


  private boolean bloco() {
    if (token.tipo.equals("eol")) {
      quebrou();
    } else if (!cmd()) {
      erro("bloco");
      return false;
    }

    // Verifica se deve chamar `bloco()` novamente ou terminar
    if (token.lexema.equals("au") || token.lexema.equals("leia") ||
        token.lexema.equals("latindo") || token.tipo.equals("id") ||
        token.lexema.equals("auuuu") || token.lexema.equals("auau") ||
        token.tipo.equals("eol")) {
      return bloco();
    }

    return true;
  }


  private boolean cmd(){

    if (token.lexema.equals("au")){
      if (if_()) {
        return true;
      }
    } else if (token.lexema.equals("leia")) {

      if (ler()) {
        return true;
      }
    } else if (token.lexema.equals("latindo")) {

      if (latindo()) {
        return true;
     }
    } else if (token.tipo.equals("id")) {
      if (expre_atrib()) {
        return true;
     }
    }else if (token.lexema.equals("auuuu")) {
      if (while_()) {
        return true;
     }
    }else if (token.lexema.equals("auau")) {
      if (for_()) {
        return true;
    }
  }
    erro("cmd");
    return false;
  }


  private boolean if_() {
    if (matchL("au", token.lexema)) {
      if (condicao()) {
        System.out.println("){");
        if (bloco() && matchL("petisco", token.lexema)) {
          return true;
        }
      }
    }
    erro("if_");
    return false;
  }


  private boolean while_() {
    if (matchL("auuuu", token.lexema)){
        if (condicao()) {
          traduz("){");
        }if (bloco() && matchL("petisco", token.lexema)) {
          return true;
        }
      }
    erro("while_");
    return false;
  }


  private boolean for_() {
    if (matchL("auau", token.lexema) &&
        id() &&
        operador_atribu() &&
        num() &&
        matchL(";", token.lexema) &&
        id() &&
        operador_rel() &&
        num() &&
        matchL(";", token.lexema) &&
        id()){
        if (token.lexema.equals("carnecarne")){
          incredesce();
          System.out.println(") {");
          quebrou();
        bloco();
        matchL("petisco", token.lexema);
        }
      return true;
    }
    erro("for_");
    return false;
  }


  private boolean incredesce() {
    if(token.lexema.equals("carnecarne")){
      if (matchL("carnecarne", token.lexema)){
        return true;
      }
    }else if (token.lexema.equals("limaolimao")){
      if (matchL("limaolimao", token.lexema)) {
        return true;
      }
    }
    erro("incredesce");
    return false;
  }



  private boolean operador_atribu() {
    if (matchL("=", token.lexema)) {
      return true;
    }
    erro("operador_atribu");
    return false;
  }





  private boolean ler() {

    if (matchL("leia", token.lexema) &&
        quebrou()){
        if (token.lexema.equals("str")) {
          declara();
      }
      return true;
    }
    erro("ler");
    return false;
  }



  private boolean latindo() {
    if (matchL("latindo", token.lexema) &&
        matchL("(", token.lexema) &&
        id() &&
        matchL(")", token.lexema) &&
        matchL(";", token.lexema) &&
        quebrou())
        {
      return true;
    }
    erro("latindo");
    return false;
  }


  private boolean texto() {
    if (matchT("`", token.lexema) &&
      id()&&
      matchT("`", token.lexema)
    ) {
      return true;
    }
    erro("texto");
    return false;
  }


  private boolean expre_atrib(){
    if (id() &&
        operador_atribu() &&
        expre_mate() &&
        quebrou()) {
      return true;
    }
    erro("expre_atrib");
    return false;

  }


  private boolean expre_mate() {
      if (fator()) {
        if (token.lexema.equals("some") || token.lexema.equals("subtraia") ) {
            if (operador_ad() &&
            fator() &&
            matchL(";", token.lexema) &&
            quebrou()){
            return true;
            }
        }
        return true;
      }
      erro("expre_mate");
      return false;
    }

  private boolean elemento() {

  if (token.tipo.equals("numero")) {
    if (num()){
      return true;
    }
  } else if (token.tipo.equals("id")) {
    if (id()) {
      return true;
    }
  } else if (token.lexema.equals("(")) {

    if (matchL("(", token.lexema) &&
      expre_mate() &&
      matchL(")", token.lexema))
    {
      return true;
    }
  }
    erro("elemento");
    return false;
  }




  private boolean fator() {

    if (elemento()) {
      if (token.lexema.equals("multi") || token.lexema.equals("divida")) {
        if (operador_mul() &&
            elemento() &&
            quebrou()) {
          return true;
        }
      }
      matchL(";", token.lexema);
      return true;
    }

      erro("fator");
      return false;
    }


  private boolean operador_mul() {

    if (token.lexema.equals("multi")) {
      if (matchL("multi", token.lexema)) {
        return true;
      }

    } else if (token.lexema.equals("divida")) {
      if (matchL("divida", token.lexema)) {
        return true;
      }

    }

    erro("operador_mul");
    return false;
  }

  private boolean operador_ad() {

    if (token.lexema.equals("some")) {
      if (matchL("some", token.lexema)) {
        return true;
      }

    } else if (token.lexema.equals("subtraia")) {

      if (matchL("subtraia", token.lexema)) {
        return true;
      }
    }

    erro("operador_ad");
    return false;
  }



  private boolean quebrou() {

    if (matchT("eol", token.lexema)){
      return true;
    }
    erro("quebrou");
    return false;
  }


  private boolean condicao() {
    if (id() && operador_rel() && num()) {
      return true;
    }
    erro("condicao");
    return false;
  }


  private boolean operador_rel() {

    if (token.lexema.equals(">")) {
      if (matchL(">", token.lexema)) {
        return true;
      }} else if (token.lexema.equals("<")){
      if (matchL("<", token.lexema)){
        return true;

      }}else if (token.lexema.equals("<=")){
      if (matchL("<=", token.lexema)){
        return true;
      }}else if (token.lexema.equals(">=")){
      if (matchL(">=", token.lexema)){
        return true;
      }}else if (token.lexema.equals("==")){
      if (matchL("==", token.lexema)){
        return true;
        }
      }

    erro("operador_rel");
    return false;
  }

  private boolean id() {
    if (matchT("id", token.lexema)) {
      return true;
    }
    erro("id");
    return false;
  }

  private boolean num() {
    if (matchT("numero", token.lexema)) {
      return true;
    }
    erro("num");
    return false;
  }


  private boolean matchL(String palavra, String newcode) {
    if (token.lexema.equals(palavra)) {
      traduz(newcode);
      token = getNextToken();
      return true;
    }

    erro(palavra);
    return false;
  }

  private boolean matchT(String palavra, String newcode) {
    if (token.tipo.equals(palavra)) {
      traduz(newcode);
      token = getNextToken();
      return true;
    }

    erro(palavra);
    return false;
  }

private void traduz(String code){
  switch (code) {
    case "inteiro":
      System.out.print("int ");
      break;
    case "float":
      System.out.print("float ");
      break;
    case "str":
      System.out.print("String ");
      break;
    case "au":
      System.out.print("if (");
      break;
    case "auuuu":
      System.out.print("while (");
      break;
    case "auau":
      System.out.print("for (");
      break;
    case "petisco":
      System.out.println("}"); // Para fechar blocos
      break;
    case "tab":
      System.out.println("\n"); // Para fechar blocos
      break;
    case "leia":
      System.out.print("Scanner input = new Scanner(System.in); ");
      break;
    case "=":
      System.out.print("= ");
      break;
    case "some":
      System.out.print("+ ");
      break;
    case "subtraia":
      System.out.print("- ");
      break;
    case "multi":
      System.out.print("* ");
      break;
    case "divida":
      System.out.print("/ ");
      break;
    case "carnecarne":
      System.out.print("++");
      break;
    case "limaolimao":
      System.out.print("--");
      break;
    case "latindo":
      System.out.print("System.out.println");
      break;
    case "Thor":
      System.out.print("public class Main { ");
      break;
    case "Gordo":
      System.out.print("public static void main (Strings args[]) { ");
      break;
    default:
      System.out.print(code + " ");
      break;
    }
  }
}
