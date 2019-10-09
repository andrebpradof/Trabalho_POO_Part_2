import java.util.Stack;

public class Desfazer {
    private static Stack<Paragrafo> texto;
    public static void push(Paragrafo paragrafo){
        if (texto==null)
            texto=new Stack();
        texto.push(paragrafo);
    }
    public static Paragrafo pop(){
        if (texto==null)
            return null;
        return texto.pop();
    }

}
