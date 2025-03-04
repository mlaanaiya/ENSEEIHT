package allumettes;

/**
 * La classe StrategieLente définit un joueur qui prend toujours une prise minimale.
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class StrategieLente implements Strategie {

    @Override
    public String getNom() {
        return "Lente";
    }

    @Override
    public int getPrise(Jeu jeu) {
        return 1;
    }

}
