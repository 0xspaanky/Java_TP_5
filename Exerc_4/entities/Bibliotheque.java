package entities;

public class Bibliotheque {
    private Document[] documents;
    private int nbDoc;

    public Bibliotheque(int capacite) {
        documents = new Document[capacite];
        nbDoc = 0;
    }

    public boolean ajouter(Document doc) {
        if (nbDoc < documents.length) {
            documents[nbDoc++] = doc;
            return true;
        }
        return false;
    }

    public boolean supprimer(Document doc) {
        for (int i = 0; i < nbDoc; i++) {
            if (documents[i].equals(doc)) {
                for (int j = i; j < nbDoc - 1; j++) {
                    documents[j] = documents[j + 1];
                }
                documents[--nbDoc] = null;
                return true;
            }
        }
        return false;
    }

    public void afficherDocuments() {
        for (int i = 0; i < nbDoc; i++) {
            System.out.println(documents[i]);
        }
    }

    public Document document(int numEnrg) {
        for (int i = 0; i < nbDoc; i++) {
            if (documents[i].getNumEnreg() == numEnrg)
                return documents[i];
        }
        return null;
    }

    public void afficherAuteurs() {
        for (int i = 0; i < nbDoc; i++) {
            if (documents[i] instanceof Livre) {
                Livre livre = (Livre) documents[i];
                System.out.println(livre.getAuteur());
            }
        }
    }
}
