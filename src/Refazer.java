import java.util.Stack;

public class Refazer {
    private static Stack<Paragrafo> avancar;
    public static void push(Paragrafo paragrafo){
        if (avancar==null)
            avancar=new Stack();
        avancar.push(paragrafo);
    }
    public static Paragrafo pop(){
        if (avancar==null)
            return null;
        return avancar.pop();
    }
}
