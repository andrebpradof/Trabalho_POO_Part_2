import java.io.Serializable;
import java.util.LinkedList;

public class Caracteres implements Serializable {
    private char caracter;
    private Usuario usuario;

    public Caracteres(char caracter, int user){
        this.caracter = caracter;
        this.usuario = new Usuario(user);
    }

    public char getCaracter() {
        return caracter;
    }
}
