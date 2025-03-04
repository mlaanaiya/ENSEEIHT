package allumettes;

/**
 * La classe StrategieRapide définit un joueur qui prend tout une prise maximale.
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class StrategieRapide implements Strategie {

    @Override
    public String getNom() {
        return "Rapide";
    }

    @Override
    public int getPrise(Jeu jeu) {
        return Math.min(jeu.getNombreAllumettes(), Jeu.PRISE_MAX);
    }

}

