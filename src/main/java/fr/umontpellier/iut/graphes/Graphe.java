package fr.umontpellier.iut.graphes;

import java.util.HashSet;
import java.util.*;


/**
 * Graphe simple non-orienté pondéré représentant le plateau du jeu.
 * Pour simplifier, on supposera que le graphe sans sommets est le graphe vide.
 * Le poids de chaque sommet correspond au coût de pose d'un rail sur la tuile correspondante.
 * Les sommets sont indexés par des entiers (pas nécessairement consécutifs).
 */

public class Graphe {
    private final Set<Sommet> sommets;

    public Graphe(Set<Sommet> sommets) {
        this.sommets = sommets;
    }

    /**
     * Construit un graphe à n sommets 0..n-1 sans arêtes
     */
    public Graphe(int n) { //fait mais pas testé
        this.sommets = new HashSet<>();
        ajouterSommet(n);        
    }

    /**
     * Construit un graphe vide
     */
    public Graphe() { //fait mais pas testé
        this.sommets = new HashSet<>();
    }

    /**
     * Construit un sous-graphe induit par un ensemble de sommets
     * sans modifier le graphe donné
     *
     * @param g le graphe à partir duquel on construit le sous-graphe
     * @param X les sommets à considérer (on peut supposer que X est inclus dans l'ensemble des sommets de g,
     *          même si en principe ce n'est pas obligatoire)
     */
    public Graphe(Graphe g, Set<Sommet> X) { //fait mais pas testé
        Graphe sousGraphe = new Graphe();
        sommets = X;
        for (Sommet s : X) {
            sousGraphe.ajouterSommet(s);
        }
        for (Sommet s : X) {
            for (Sommet t : s.getVoisins()) {
                if (X.contains(t)) {
                    sousGraphe.ajouterArete(s, t);
                }
            }
        }
    }

    /**
     * @return true si et seulement si la séquence d'entiers passée en paramètre
     * correspond à un graphe simple valide dont les degrés correspondent aux éléments de la liste.
     * Pré-requis : on peut supposer que la séquence est triée dans l'ordre croissant.
     */
    public static boolean sequenceEstGraphe(List<Integer> sequence) { //fait mais pas testé
        List<Integer> sequenceOrdonnee = new ArrayList<>(sequence);
        while (true) {
            Collections.sort(sequenceOrdonnee, Collections.reverseOrder());
            if (sequenceOrdonnee.get(0) == 0) {
                return true;
            }
            int degMax = sequenceOrdonnee.remove(0); 

            if (degMax >= sequenceOrdonnee.size()) {
                return false;
            }

            for (int i = 0; i < degMax; i++) {
                sequenceOrdonnee.set(i, sequenceOrdonnee.get(i) - 1);
                if (sequenceOrdonnee.get(i) < 0) {
                    return false;
                }
                //Collections.sort(sequenceOrdonnee, Collections.reverseOrder());  PEUT ETRE JSP
            }
        }
    }


    
    /**
     * @param g        le graphe source, qui ne doit pas être modifié
     * @param ensemble un ensemble de sommets
     *                 pré-requis : l'ensemble donné est inclus dans l'ensemble des sommets de {@code g}
     * @return un nouveau graph obtenu en fusionnant les sommets de l'ensemble donné.
     * On remplacera l'ensemble de sommets par un seul sommet qui aura comme indice
     * le minimum des indices des sommets de l'ensemble. Le surcout du nouveau sommet sera
     * la somme des surcouts des sommets fusionnés. Le nombre de points de victoire du nouveau sommet
     * sera la somme des nombres de points de victoire des sommets fusionnés.
     * L'ensemble de joueurs du nouveau sommet sera l'union des ensembles de joueurs des sommets fusionnés.
     */
    public static Graphe fusionnerEnsembleSommets(Graphe g, Set<Sommet> ensemble) {
        throw new RuntimeException("Méthode à implémenter");
    }

    /**
     * @param i un entier
     * @return le sommet d'indice {@code i} dans le graphe ou null si le sommet d'indice {@code i} n'existe pas dans this
     */
    public Sommet getSommet(int i) { //fait mais pas testé
        for (Sommet s : sommets) {
            if (s.getIndice() == i) {
                return s;
            }
        }
        return null;
    }

    /**
     * @return l'ensemble des sommets du graphe
     */
    public Set<Sommet> getSommets() { //fait mais pas testé
        return sommets;
    }

    /**
     * @return l'ordre du graphe, c'est-à-dire le nombre de sommets
     */
    public int getNbSommets() {//fait mais pas testé
        return sommets.size();
    }

    /**
     * @return l'ensemble d'arêtes du graphe sous forme d'ensemble de paires de sommets
     */
    public Set<Set<Sommet>> getAretes() { //fait mais pas testé
        Set<Set<Sommet>> aretes = new HashSet<>();

        for (Sommet s : sommets) {
            for (Sommet t : s.getVoisins()) {
                Set<Sommet> arete = new HashSet<>();
                arete.add(s);
                arete.add(t);
                aretes.add(arete);
            }
        }
        return aretes;
    }

    /**
     * @return le nombre d'arêtes du graphe
     */
    public int getNbAretes() { //fait mais pas testé
        return getAretes().size();
    }

    /**
     * Ajoute un sommet d'indice i au graphe s'il n'est pas déjà présent
     *
     * @param i l'entier correspondant à l'indice du sommet à ajouter dans le graphe
     */
    public boolean ajouterSommet(int i) { //fait mais pas testé
        if(getSommet(i) != null){
            return false;
        }
        Sommet sommetAdd = new Sommet(i);
        return sommets.add(sommetAdd);
    }

    /**
     * Ajoute un sommet au graphe s'il n'est pas déjà présent
     *
     * @param s le sommet à ajouter
     * @return true si le sommet a été ajouté, false sinon
     */
    public boolean ajouterSommet(Sommet s) { //fait mais pas testé
        return sommets.add(s);
    }

    /**
     * @param s le sommet dont on veut connaître le degré
     *          pré-requis : {@code s} est un sommet de this
     * @return le degré du sommet {@code s}
     */
    public int degre(Sommet s) { //fait mais pas testé
        return s.getVoisins().size();
    }

    /**
     * @return true si et seulement si this est complet.
     */
    public boolean estComplet() { //fait mais pas testé
        return getNbAretes() == getNbSommets() * (getNbSommets() - 1) / 2; // si le graphe est complet, il a n(n-1)/2 arêtes
    }

    /**
     * @return true si et seulement si this est une chaîne. On considère que le graphe vide est une chaîne.
     */
    public boolean estChaine() { //fait mais pas testé
        // il faut qu'il possède 0 (c'est un cylce) ou deux 2 sommets de degré impair(les deux en bout de chaine.
        int nbSommetsImpairs = 0;
        for (Sommet s : sommets) {
            if (degre(s) % 2 == 1) {
                nbSommetsImpairs++;
            }
        }
        return nbSommetsImpairs == 0 || nbSommetsImpairs == 2;

    }

    /**
     * @return true si et seulement si this est un cycle. On considère que le graphe vide n'est pas un cycle.
     */
    public boolean estCycle() { //fait mais pas testé
        if(sommets.isEmpty()){
            return false;
        }
        if(getNbAretes() != getNbSommets()){
            return false;
        }
        for (Sommet s : sommets) {
            if (degre(s) != 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true si et seulement si this est une forêt. On considère qu'un arbre est une forêt
     * et que le graphe vide est un arbre.
     */
    public boolean estForet() { //fait mais pas testé
        if (sommets.isEmpty()) {
            return true;
        }
        return (!possedeUnCycle());
    }

    /**
     * @return true si et seulement si this a au moins un cycle. On considère que le graphe vide n'est pas un cycle.
     */
    public boolean possedeUnCycle() { //fait mais pas testé
        if (sommets.isEmpty()) {
            return false;
        }
        return getNbAretes() > getNbSommets()-1;
    }

    /**
     * @return true si et seulement si this a un isthme
     */
    public boolean possedeUnIsthme() { //pas fini et pas testé
        if (sommets.isEmpty()) {
            return false;
        }
        if (estCycle()) {
            return false;
        }
        if (estChaine()) {
            return true;
        }
        for (Sommet s : sommets) {
            if (degre(s) == 1) {
                return true;
            }
        }
        return false;
    }

    public void ajouterArete(Sommet s, Sommet t) { //fait mais pas testé
        if (s.equals(t)) {
            throw new IllegalArgumentException("Un sommet ne peut pas être relié à lui-même");
        }
        if(!s.estVoisin(t)) {
            s.ajouterVoisin(t);
            t.ajouterVoisin(s);
        }
    }

    public void supprimerArete(Sommet s, Sommet t) { //fait mais pas testé
        if (s.equals(t)) {
            throw new IllegalArgumentException("Un sommet ne peut pas être relié à lui-même");
        }
        if(s.estVoisin(t)){
            s.getVoisins().remove(t);
            t.getVoisins().remove(s);
        }
    }

    /**
     * @return une coloration gloutonne du graphe sous forme d'une Map d'ensemble indépendants de sommets.
     * L'ordre de coloration des sommets est suivant l'ordre décroissant des degrés des sommets
     * (si deux sommets ont le même degré, alors on les ordonne par indice croissant).
     */
    public Map<Integer, Set<Sommet>> getColorationGloutonne() {
        throw new RuntimeException("Méthode à implémenter");
    }

    /**
     * @param depart  - ensemble non-vide de sommets
     * @param arrivee
     * @return le surcout total minimal du parcours entre l'ensemble de depart et le sommet d'arrivée
     * pré-requis : l'ensemble de départ et le sommet d'arrivée sont inclus dans l'ensemble des sommets de this
     */
    public int getDistance(Set<Sommet> depart, Sommet arrivee) { // plusieurs départ et un arrivée (dijsktra)
        //utilise getDistance(Sommet depart, Sommet arrivee)
        int distanceMin = Integer.MAX_VALUE;
        for (Sommet s : depart) {
            int distance = getDistance(s, arrivee);
            if (distance < distanceMin) {
                distanceMin = distance;
            }
        }
        return distanceMin;
    }

    /**
     * @return le surcout total minimal du parcours entre le sommet de depart et le sommet d'arrivée
     */
    public int getDistance(Sommet depart, Sommet arrivee) { //ne fonctionne pas
        Set<Sommet> dejaVus = new HashSet<>();
        Set<Sommet> aTraiter = new HashSet<>();
        Map<Sommet, Integer> distanceParSommet = new HashMap<>();

        for (Sommet s : sommets) {
            distanceParSommet.put(s, Integer.MAX_VALUE);
        }
        distanceParSommet.put(depart, 0);
        aTraiter.add(depart);

        while (!aTraiter.isEmpty()) {
            Sommet s = aTraiter.iterator().next();
            aTraiter.remove(s);
            dejaVus.add(s);
            for (Sommet voisin : s.getVoisins()) {
                if (!dejaVus.contains(voisin)) {
                    int distanceVoisin = distanceParSommet.get(s) + 1;
                    if (distanceVoisin < distanceParSommet.get(voisin)) {
                        distanceParSommet.put(voisin, distanceVoisin);
                        aTraiter.add(voisin);
                    }
                }
            }
        }

        return distanceParSommet.get(arrivee);
    }

    /**
     * @return l'ensemble des classes de connexité du graphe sous forme d'un ensemble d'ensembles de sommets.
     */
    public Set<Set<Sommet>> getEnsembleClassesConnexite() {
        Set<Set<Sommet>> ensembleClassesConnexite = new HashSet<>();
        if (sommets.isEmpty())
            return ensembleClassesConnexite;
        Set<Sommet> sommets = new HashSet<>(this.sommets);
        while (!sommets.isEmpty()) {
            Sommet v = sommets.iterator().next();
            Set<Sommet> classe = getClasseConnexite(v);
            sommets.removeAll(classe);
            ensembleClassesConnexite.add(classe);
        }
        return ensembleClassesConnexite;
    }

    /**
     * @param v un sommet du graphe this
     * @return la classe de connexité du sommet {@code v} sous forme d'un ensemble de sommets.
     */
    public Set<Sommet> getClasseConnexite(Sommet v) {
        if (!sommets.contains(v))
            return new HashSet<>();
        Set<Sommet> classe = new HashSet<>();
        calculerClasseConnexite(v, classe);
        return classe;
    }

    private void calculerClasseConnexite(Sommet v, Set<Sommet> dejaVus) {
        dejaVus.add(v);
        Set<Sommet> voisins = v.getVoisins();

        for (Sommet voisin : voisins) {
            if (dejaVus.add(voisin))
                calculerClasseConnexite(voisin, dejaVus);
        }
    }

    /**
     * @return true si et seulement si this est connexe.
     */
    public boolean estConnexe() { //fait mais pas testé
        return getEnsembleClassesConnexite().size() == 1;
    }

    /**
     * @return le degré maximum des sommets du graphe
     */
    public int degreMax() { // fait mais pas testé
        int degreMax = 0;
        for (Sommet s : sommets) {
            if (degre(s) > degreMax) {
                degreMax = degre(s);
            }
        }
        return degreMax;
    }

    /**
     * @return une coloration propre optimale du graphe sous forme d'une Map d'ensemble indépendants de sommets.
     * Chaque classe de couleur est représentée par un entier (la clé de la Map).
     * Pré-requis : le graphe est issu du plateau du jeu Train (entre autres, il est planaire).
     */
    public Map<Integer, Set<Sommet>> getColorationPropreOptimale() {
        throw new RuntimeException("Méthode à implémenter");
    }

    /**
     * @return true si et seulement si this possède un sous-graphe complet d'ordre {@code k}
     */
    public boolean possedeSousGrapheComplet(int k) {
        throw new RuntimeException("Méthode à implémenter");
    }

    /**
     * @param g un graphe
     * @return true si et seulement si this possède un sous-graphe isomorphe à {@code g}
     */
    public boolean possedeSousGrapheIsomorphe(Graphe g) {
        throw new RuntimeException("Méthode à implémenter");
    }

    /**
     * @param s
     * @param t
     * @return un ensemble de sommets qui forme un ensemble critique de plus petite taille entre {@code s} et {@code t}
     */
    public Set<Sommet> getEnsembleCritique(Sommet s, Sommet t){
        throw new RuntimeException("Méthode à implémenter");
    }
}
