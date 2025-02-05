import java.util.Scanner;

public class GestionEmploye {
    private static final int MAX_EMPLOYES = 50;
    private static Employe[] employes = new Employe[MAX_EMPLOYES];
    private static int nbrEmployes = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    ajouterEmploye(sc);
                    break;
                case 2:
                    modifierEmploye(sc);
                    break;
                case 3:
                    supprimerEmploye(sc);
                    break;
                case 4:
                    afficherEmployes();
                    break;
                case 5:
                    rechercherEmploye(sc);
                    break;
                case 6:
                    System.out.println("La masse salariale : " + calculerMasseSalariale());
                    break;
                case 7:
                    trierEmployesParSalaire(sc);
                    break;
            }
        } while (choix != 0);

        sc.close();
    }
    // Affiche les options du menu principal du système de gestion des employés
    private static void printMenu() {
        System.out.println("Gestion Des Employés");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }


    // Ajoute un nouvel employé au système en collectant son ID, nom, poste et salaire.
    // Vérifie si la limite maximale d'employés est atteinte et si l'ID existe déjà
    private static void ajouterEmploye(Scanner scanner) {
        if (nbrEmployes >= MAX_EMPLOYES) {
            System.out.println("La liste est plein (50 employés maximum)");
            return;
        }
        System.out.print("Entrez l'id d'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[i].getId() == id) {
                System.out.println("L'id existe déja.");
                return;
            }
        }
        System.out.print("Entrer le nom d'employé: ");
        String nom = scanner.nextLine();

        System.out.print("Entrer le poste d'employé: ");
        String poste = scanner.nextLine();

        System.out.print("Entrer le salaire d'employé: ");
        double salaire = scanner.nextDouble();

        employes[nbrEmployes] = new Employe(id, nom, poste, salaire);
        nbrEmployes++;
        System.out.println("Employé ajouté avec succès!");
    }
    // Modifie les informations d'un employé existant (nom, poste, salaire) en
    // recherchant son ID. Affiche une erreur si l'employé n'est pas trouvé
    private static void modifierEmploye(Scanner scanner) {
        System.out.print("Entrer l'id d'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[i].getId() == id) {

                System.out.print("Entrer le nom d'employé: ");
                String nom = scanner.nextLine();
                System.out.print("Entrer le poste d'employé: ");
                String poste = scanner.nextLine();
                System.out.print("Entrer le salaire d'employé: ");
                double salaire = scanner.nextDouble();

                employes[i].setNom(nom);
                employes[i].setPoste(poste);
                employes[i].setSalaire(salaire);
                System.out.println("Employé modifié avec succès!");
                return;
            }
        }
        System.out.println("Aucun employé avec ce id n'a été trouvé.");
    }

    // Supprime un employé du système en fonction de son ID. Décale les employés
    // restants pour combler le vide et affiche une erreur si l'employé n'est pas trouvé
    private static void supprimerEmploye(Scanner scanner) {
        System.out.print("Entrer l'id d'employé : ");
        int id = scanner.nextInt();
        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[i].getId() == id) {
                for (int j = i; j < nbrEmployes - 1; j++) {
                    employes[j] = employes[j + 1];
                }
                employes[nbrEmployes - 1] = null;
                nbrEmployes--;
                System.out.println("Employé supprimé avec succès!");
                return;
            }
        }

        System.out.println("Aucun employé avec ce id n'a été trouvé.");
    }

    // Affiche la liste de tous les employés actuellement dans le système.
    // Montre un message si la liste est vide
    private static void afficherEmployes() {
        if (nbrEmployes == 0) {
            System.out.println("Aucun employé dans la liste.");
            return;
        }
        System.out.println("-----Liste des employés-----");
        for (int i = 0; i < nbrEmployes; i++) {
            System.out.println(employes[i]);
        }
    }
    // Recherche un employé par son nom ou son poste et affiche
    // ses informations s'il est trouvé
    public static void rechercherEmploye(Scanner scanner) {
        System.out.println("Entrer le nom ou le poste d'employé à rechercher : ");
        String critere = scanner.nextLine();
        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[i].getNom().equals(critere) || employes[i].getPoste().equals(critere)) {
                System.out.println("Employé trouvé : " + employes[i]);
                return;
            }
        }
    }
    // Calcule et renvoie la somme totale des salaires de tous les employés du système
    public static double calculerMasseSalariale() {
        double masseSalariale = 0.0;
        for (int i = 0; i < nbrEmployes; i++) {
            masseSalariale += employes[i].getSalaire();
        }
        return masseSalariale;
    }


    // Trie et affiche la liste des employés par salaire dans l'ordre croissant ou
    // décroissant selon le choix de l'utilisateur. Utilise l'algorithme de tri à bulles
    public static void trierEmployesParSalaire(Scanner scanner) {
        if (nbrEmployes == 0) {
            System.out.println("Aucun employé dans la liste.");
            return;
        }

        System.out.print("Trier par ordre croissant (true) ou décroissant (false) ? ");
        boolean ordreCroissant = scanner.nextBoolean();


        for (int i = 0; i < nbrEmployes - 1; i++) {
            for (int j = i + 1; j < nbrEmployes; j++) {
                Employe employe1 = employes[i];
                Employe employe2 = employes[j];
                Employe employeMax = Employe.compareParSalaire(employe1, employe2);

                if ((ordreCroissant && employeMax == employe1) || (!ordreCroissant && employeMax == employe2)) {
                    employes[i] = employe2;
                    employes[j] = employe1;
                }
            }
        }

        System.out.println("-----Liste des employés triés par salaire-----");
        for (int i = 0; i < nbrEmployes; i++) {
            System.out.println(employes[i]);
        }
    }
}
