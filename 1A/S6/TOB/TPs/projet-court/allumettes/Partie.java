package allumettes;

/**
 * Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 *
 * @author Xavier Crégut
 * @version $Revision: 1.5 $
 */
public class Partie {

    /**
     * Obtenir la stratègie passée en entrée
     *
     * @param str est la stratègie passée en ligne de commande
     * @return la stratègie passée en ligne de commande
     * @throws ConfigurationException si la commande passée n'est pas reconnu
     */
    public static Strategie obtenirStr(String str) throws ConfigurationException {
        if (str.equals("tricheur")) {
            return new StrategieTricheur();
        } else if (str.equals("humain")) {
            return new StrategieHumaine();
        } else if (str.equals("lent")) {
            return new StrategieLente();
        } else if (str.equals("rapide")) {
            return new StrategieRapide();
        } else if (str.equals("naif")) {
            return new StrategieNaive();
        } else if (str.equals("expert")) {
            return new StrategieExperte();
        } else {
            throw new ConfigurationException("Stratégie inconnue");
        }
    }

	/** Split la ligne de commande
	 * @param args la description des deux joueurs
	 * @return args sous forme de string */
	public static String splitArgs(String[] args) {
		String str = "";
			for (int i = args.length - 1; i >= 0; i--) {
				if (i > 0) {
					str += args[i] + "@";
				} else {
					str += args[i];
				}
			}
            return str;
	}

    /**
     * Lancer une partie. En argument sont donnés les deux joueurs sous
     * la forme nom@stratégie.
     *
     * @param args la description des deux joueurs
     */
    public static void main(String[] args) {
        try {
            verifierNombreArguments(args);
            Jeu game = new Game(13);
            Jeu proxy = new Proxy(game);
			String str = splitArgs(args);
			String[] tmp = str.split("@");
            Joueur j1 = new Joueur(tmp[2], obtenirStr(tmp[3]));
            Joueur j2 = new Joueur(tmp[0], obtenirStr(tmp[1]));
            Arbitre arb = new Arbitre(j1, j2);
			if (args.length == 2) {
                arb.arbitrer(game, proxy, false);
            } else {
                if (!args[0].equals("-confiant") && !args[0].equals("-chgStr")) {
                    System.out.println("Unknown option");
                    System.out.println("Usage : java allumettes.Partie -confiant "
						+ "player1@strategie player2@strategie");
                    System.out.println("Usage : java allumettes.Partie -chgStr "
						+ "player1@strategie player2@strategie");
                } else {
                    if (args[0].equals("-confiant")) {
                        arb.arbitrer(game, game, false);
                    } else {
                        arb.arbitrer(game, proxy, true);
                    }
                }
            }
        } catch (ConfigurationException e) {
            System.out.println();
            System.out.println("Erreur : " + e.getMessage());
            afficherUsage();
        }
    }

    private static void verifierNombreArguments(String[] args) {
        final int nbJoueurs = 2;
        if (args.length < nbJoueurs) {
            throw new ConfigurationException("Trop peu d'arguments : "
                    + args.length);
        }
        if (args.length > nbJoueurs + 1) {
            throw new ConfigurationException("Trop d'arguments : "
                    + args.length);
        }
    }

    /**
     * Afficher des indications sur la manière d'exécuter cette classe.
     */
    public static void afficherUsage() {
        System.out.println("\n" + "Usage :"
                + "\n\t" + "java allumettes.Partie joueur1 joueur2"
                + "\n\t\t" + "joueur est de la forme nom@stratégie"
                + "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
                + "\n"
                + "\n\t" + "Exemple :"
                + "\n\t" + "	java allumettes.Partie Xavier@humain "
                + "Ordinateur@naif"
                + "\n"
        );
    }

}
