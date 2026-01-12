
public class Voiture implements Auto {

    private String couleur="black";
    private String marque;
    private boolean roule;

    public Voiture() {
    }

     public Voiture(String couleur, String marque, boolean  roule) {
        this.couleur = couleur;
        this.marque = marque;
        this.roule =roule;
    }
    public String getCouleur()
    {
        return this.couleur;
    }
    public String getMarque(){
        return this.marque;
    }

    public boolean roule() {
        return this.roule;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
