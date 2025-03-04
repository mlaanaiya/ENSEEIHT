package allumettes;

/**
 * Exception qui indique que l'opération est interdite si un joueur tricheur
 * essaye de changer le jeu.
 *
 * @author Xavier Crégut
 * @version 1.4
 */
public class OperationInterditeException extends RuntimeException {

    /**
     * Initaliser une OperationInterditeException avec le message précisé.
     *
     * @param message le message explicatif
     */
    public OperationInterditeException(String message) {
        super(message);
    }

}
