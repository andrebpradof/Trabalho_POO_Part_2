package Editor;

import java.io.Serializable;

/**
 * Classe modelo para o usuario
 */

public class Usuario implements Serializable {
    private int id;

    public Usuario(int user){
        this.id = user;
    }
}
