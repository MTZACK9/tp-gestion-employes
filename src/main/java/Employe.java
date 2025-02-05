public class Employe {
    private int id;
    private String nom;
    private String poste;
    private Double salaire;

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Employe(){}

    public Employe(int id, String nom, String poste, Double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

    public static Employe compareParSalaire(Employe employe1, Employe employe2){
        return employe1.salaire  > employe2.salaire ? employe1 : employe2;
    }

    @Override
    public String toString() {
        return "Id : "+ this.id + "\nNom : " + this.nom + "\nPoste : " + this.poste + "\nSalaire : " + this.salaire;
    }
}
