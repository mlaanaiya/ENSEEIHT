package allumettes;

import java.util.Scanner;


/**
 * La classe Arbitre fait respecter les règles du jeu aux deux joueurs.
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class Arbitre {
	// Nombre d'options.
	static final int NOMBRE_OPTIONS = 7;

    // Le premier joueur.
    private Joueur j1;

    // La deuxième joueur.
    private Joueur j2;

    /**
     * Constructeur de la classe Arbitre.
     *
     * @param j1 : Le premier joueur
     * @param j2 : le deuxième joueur
     */
    public Arbitre(Joueur j1, Joueur j2) {
        assert (j1 != null && j2 != null);
        this.j1 = j1;
        this.j2 = j2;
    }

    /**
     * Obtenir le premier joueur
     *
     * @return le premier joueur
     */
    public Joueur getJ1() {
        return j1;
    }

    /**
     * Obtenir le deuxième joueur
     *
     * @return le deuxième joueur
     */
    public Joueur getJ2() {
        return j2;
    }

    /**
     * Changer le premier joueur
     *
     * @param j1 le nouveau joueur
     */
    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    /**
     * Changer le deuxième joueur
     *
     * @param j2 le nouveau joueur
     */
    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    /**
     * Obtenir le joueur courant
     *
     * @param tour : le tour courant
     * @return le joueur courant
     */
    public Joueur getJoueurCourant(int tour) {
        assert (tour > 0);

        if (tour % 2 == 1) {
            return this.j1;
        } else {
            return this.j2;
        }
    }

    /**
     * Vérifier s'il y a un gagnant.
     *
     * @param tour : le tour courant
     * @param jeu  : le jeu courant
     * @return vrai si la partie est finie, sinon faux
     */
    public boolean estFinie(int tour, Jeu jeu) {
        assert (tour > 0 && jeu != null);

        if (jeu.getNombreAllumettes() > 0) {
            return false;
        } else {
            System.out.println(getJoueurCourant(tour + 1).getNom() + " a perdu !");
            System.out.println(getJoueurCourant(tour).getNom() + " a gagné !");
            return true;
        }

    }

    /**
     * Ajuster l'affichage
     *
     * @param n : le nombre d'allumettes
     * @return allumettes si n est supérieur à 2, sinon allumette
     */
    public String getString(int n) {
        if (n >= 2) {
            return " allumettes.";
        } else {
            return " allumette.";
        }
    }

    /**
     * Affiche le menu des stratègies
     */
    public void strategieMenu() {
        System.out.println("\nQuelle stratègie souhaitez vous avoir ?");
        System.out.println("1.Stratègie experte");
        System.out.println("2.Stratègie humaine");
        System.out.println("3.Stratègie lente");
        System.out.println("4.Stratègie rapide");
        System.out.println("5.Stratègie naive");
        System.out.println("6.Stratègie tricheuse");
        System.out.println("7.Desactiver l'option de changement dynamique");
        System.out.println("Entrez un entier de 1 à 7.\n");
    }

    /**
     * Lire un entier entre 1 et 7
     *
     * @return l'entier lu au clavier
     */
    public int inputInteger() {
        int choice = 0;
        Scanner scan = new Scanner(System.in);

        do {
            try {
                System.out.print("Entrez votre choix : ");
                choice = Integer.parseInt(scan.nextLine());
                if (choice < 1 || choice > this.NOMBRE_OPTIONS) {
                    System.out.print("\nErreur : ");
				    System.out.print("Votre choix doit etre "
						+ "compris entre 1 et 7.");
					System.out.println("\nRecommencez!\n");
                }
            } catch (NumberFormatException e) {
                System.out.print("\nErreur : Vous devez donner un entier.");
				System.out.println("\nRecommencez!\n");
                return inputInteger();
            }
        } while (choice < 1 || choice > this.NOMBRE_OPTIONS);
        return choice;
    }

    /**
     * Changer la stratégie pendant la partie
     *
     * @param choice le choix de la stratègie selon le menu
     * @param tour   courant
     * @param chgStr le flag du changement dynamique
     * @return le flag du changement dynamique
     */
    public boolean changeStrategie(int choice, int tour, boolean chgStr) {
        if (choice == 1) {
            getJoueurCourant(tour).setStrategie(new StrategieExperte());
            return true;
        } else if (choice == 2) {
            getJoueurCourant(tour).setStrategie(new StrategieHumaine());
            return true;
        } else if (choice == 3) {
            getJoueurCourant(tour).setStrategie(new StrategieLente());
            return true;
        } else if (choice == 4) {
            getJoueurCourant(tour).setStrategie(new StrategieRapide());
            return true;
        } else if (choice == 5) {
            getJoueurCourant(tour).setStrategie(new StrategieNaive());
            return true;
        } else if (choice == 6) {
            getJoueurCourant(tour).setStrategie(new StrategieTricheur());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Arbitrer une partie entre deux joueurs.
     *
     * @param jeu      : un jeu donné
     * @param proxyJeu : le proxy du jeu
     * @param chgStr   : un flag servant à savoir si le joueur a activer le
	 *				 changement dynamique de la straégie lors d'une partie.
     */
    public void arbitrer(Jeu jeu, Jeu proxyJeu, boolean chgStr) {
        assert (jeu != null && proxyJeu != null);

        int tour = 1;
        int tmp;
        while (!(estFinie(tour, jeu))) {
            System.out.println("\nNombre d'allumettes restantes : "
				+ jeu.getNombreAllumettes());
            System.out.println("Au tour de " + getJoueurCourant(tour).getNom() + ".");
            try {
                if (chgStr) {
                    strategieMenu();
                    chgStr = changeStrategie(inputInteger(), tour, chgStr);
                }
                tmp = getJoueurCourant(tour).getPrise(proxyJeu);
                System.out.println(getJoueurCourant(tour).getNom() + " prend " + tmp
					+ getString(tmp));
                jeu.retirer(tmp);
            } catch (CoupInvalideException e) {
                System.out.println("Erreur ! " + e.getMessage());
                System.out.println("Recommencez !");
                arbitrer(jeu, proxyJeu, chgStr);
				break;
            } catch (OperationInterditeException e) {
                System.out.println("Partie abandonnée car "
				+ getJoueurCourant(tour).getNom() + " a triché !");
                break;
            }
            tour += 1;
        }
    }

    /**
     * Verfifier si deux arbitres sont égaux
     *
     * @param obj passé en paramètre
     * @return true s'il y'a égalité, sinon faux
     */
    public boolean isEquals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Arbitre)) {
            return false;
        }
        Arbitre a = (Arbitre) obj;
        if (this.j1 != a.getJ1() || this.j2 != a.getJ2()) {
            return false;
        }
        return true;
    }

}
