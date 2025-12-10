package entities;

public abstract class Document {
    private static int compteur = 0;
    protected int numEnreg;
    protected String titre;

    public Document(String titre) {
        this.numEnreg = ++compteur;
        this.titre = titre;
    }

    public int getNumEnreg() {
        return numEnreg;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Document #" + numEnreg + " : " + titre;
    }
}
