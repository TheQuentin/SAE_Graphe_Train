package fr.umontpellier.iut.graphes;

import java.util.*;

import fr.umontpellier.iut.graphes.Sommet.SommetBuilder;


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
        for (int i = 0; i < n; i++) {
            sommets.add(new Sommet(i));
        }
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
    public static boolean sequenceEstGraphe(List<Integer> sequence) { //fait testé
        List<Integer> sequenceOrdonnee = new ArrayList<>(sequence);
        while (true) {
            Collections.sort(sequenceOrdonnee, Collections.reverseOrder());
            if (sequenceOrdonnee.get(0) == 0) {
                return true;
            }
            int degMax = sequenceOrdonnee.remove(0); 

            if (degMax > sequenceOrdonnee.size()) {
                return false;
            }

            for (int i = 0; i < degMax; i++) {
                sequenceOrdonnee.set(i, sequenceOrdonnee.get(i) - 1);
                if (sequenceOrdonnee.get(i) < 0) {
                    return false;
                }
                Collections.sort(sequenceOrdonnee, Collections.reverseOrder()); 
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
        Graphe grapheFusionne = new Graphe();
        int indice = Integer.MAX_VALUE;
        int surcout = 0;
        int nbPointsVictoire = 0;
        Set<Integer> joueurs = new HashSet<>();

        //creation du sommet
        for(Sommet sommetE : ensemble){
            if(indice>sommetE.getIndice()){
                indice = sommetE.getIndice();
            }
            surcout += sommetE.getSurcout();
            nbPointsVictoire += sommetE.getNbPointsVictoire();
            joueurs.addAll(sommetE.getJoueurs());
        }

        Sommet.SommetBuilder sommetFusion = new Sommet.SommetBuilder();
        sommetFusion.setIndice(indice);
        sommetFusion.setSurcout(surcout);
        sommetFusion.setNbPointsVictoire(nbPointsVictoire);
        sommetFusion.setJoueurs(joueurs);
        Sommet sommetFusionne = sommetFusion.createSommet();


        //insertion des sommets
        grapheFusionne.ajouterSommet(sommetFusionne);
        for(Sommet sommetG : g.getSommets()){
            if(!ensemble.contains(sommetG)){
                Sommet.SommetBuilder sommetBuilder = new Sommet.SommetBuilder();
                sommetBuilder.setIndice(sommetG.getIndice());
                sommetBuilder.setSurcout(sommetG.getSurcout());
                sommetBuilder.setJoueurs(sommetG.getJoueurs());
                sommetBuilder.setNbPointsVictoire(sommetG.getNbPointsVictoire());
                Sommet copie = sommetBuilder.createSommet();
                grapheFusionne.ajouterSommet(copie);
            }
        }

        //ajout des aretes
        for(Sommet sommet: g.getSommets()){
            if(!ensemble.contains(sommet)){
                for(Sommet sV: sommet.getVoisins()){
                    if (!ensemble.contains(sV)) {
                        grapheFusionne.ajouterArete(grapheFusionne.getSommet(sommet.getIndice()), grapheFusionne.getSommet(sV.getIndice()));

                    }
                }
            }
        }
        for(Sommet sommet2 : ensemble){
            for(Sommet sommetV : sommet2.getVoisins()){
                if (!ensemble.contains(sommetV)) {
                    grapheFusionne.ajouterArete(grapheFusionne.getSommet(sommetFusionne.getIndice()), grapheFusionne.getSommet(sommetV.getIndice()));

                }
            }
        }

        return grapheFusionne;
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
    public int getNbSommets() {//fait testé
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
    public int getNbAretes() { //fait testé
        return getAretes().size();
    }

    /**
     * Ajoute un sommet d'indice i au graphe s'il n'est pas déjà présent
     *
     * @param i l'entier correspondant à l'indice du sommet à ajouter dans le graphe
     */
    public boolean ajouterSommet(int i) { //fait testé
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
    public boolean ajouterSommet(Sommet s) { //fait testé
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
    public boolean estComplet() { //fait testé
        return getNbAretes() == getNbSommets() * (getNbSommets() - 1) / 2; // si le graphe est complet, il a n(n-1)/2 arêtes
    }

    /**
     * @return true si et seulement si this est une chaîne. On considère que le graphe vide est une chaîne.
     */
    public boolean estChaine() { //fait mais pas testé
        if (sommets.isEmpty()) return true;
        
        if (estCycle()) return true;
        
        else if (possedeUnCycle()) return false;
        
         // il faut qu'il possède 0 (c'est un cylce) ou deux 2 sommets de degré impair(les deux en bout de chaine.)
        int nbSommetsImpairs = 0;
        for (Sommet s : sommets) {
            if (degre(s) % 2 == 1) {
                nbSommetsImpairs++;
            }
        }
        return nbSommetsImpairs == 0 || nbSommetsImpairs == 2; //A REVOIR !!!

    }

    /**
     * @return true si et seulement si this est un cycle. On considère que le graphe vide n'est pas un cycle.
     */
    public boolean estCycle() { //fait mais pas testé
        if (sommets.isEmpty()) {
            return false;
        }
        if (getNbAretes() != getNbSommets()) {
            return false;
        }
        if (!estConnexe()) {
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
        return (!estCycle() && estConnexe());
    }

    /**
     * @return true si et seulement si this a au moins un cycle. On considère que le graphe vide n'est pas un cycle.
     */
    public boolean possedeUnCycle() { //fait testé
        if (sommets.isEmpty()) {
            return false;
        }
        return getNbAretes() > getNbSommets()-1;
    }

    /**
     * @return true si et seulement si this a un isthme
     */
    public boolean possedeUnIsthme() { //fait testé  A OPTI 
        if (sommets.isEmpty()) {
            return false;
        }
        if (estCycle()) {
            return false;
        }
        if (estChaine()) {
            return true;
        }

        Set<Set<Sommet>> aretes = getAretes(); // on récupère les arêtes du graphe
        int nbClasseConnexite = getEnsembleClassesConnexite().size();

        if (nbClasseConnexite > 1) return false; // si le graphe est connexe, il n'y a pas d'isthme (un isthme sépare un graphe en deux parties connexes

        for(Set<Sommet> e : aretes){ // on parcourt toutes les arêtes
            ArrayList<Sommet> sommet = new ArrayList<>(e);
             
            if (sommet.get(0).getVoisins().size() == 1 || sommet.get(1).getVoisins().size() == 1) return true; // si un des sommets de l'arête est une feuille, c'est un isthme
            supprimerArete(sommet.get(0),sommet.get(1));
            int classeConnexiteNew = getEnsembleClassesConnexite().size();
            if(classeConnexiteNew > nbClasseConnexite) return true; // si le nombre de classe de connexité augmente, c'est un isthme

            ajouterArete(sommet.get(0),sommet.get(1));
        }
        return false;
    }

    public void ajouterArete(Sommet s, Sommet t) { //fait testé
        if (s.equals(t)) {
            throw new IllegalArgumentException("Un sommet ne peut pas être relié à lui-même");
        }
        if(!s.estVoisin(t)) {
            s.ajouterVoisin(t);
            t.ajouterVoisin(s);
        }
    }

    public void supprimerArete(Sommet s, Sommet t) { //fait testé
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
        Map<Sommet, Integer> coloration = new HashMap<>();
        Map<Integer, Set<Sommet>> gloutonne = new HashMap<>();
    
        List<Sommet> sommetsTries = new ArrayList<>();
        List<Sommet> sommetsNonVus = new ArrayList<>(sommets);
        int i = 0;
        while (!sommetsNonVus.isEmpty()) {
            int maxDeg = -1;
            Sommet sommetMaxDeg = null;
            for (Sommet s : sommetsNonVus) {
                int deg = degre(s);
                if (deg > maxDeg) {
                    maxDeg = deg;
                    sommetMaxDeg = s;
                } else if (deg == maxDeg) {
                    if (sommetMaxDeg == null || s.getIndice() < sommetMaxDeg.getIndice()) {
                        sommetMaxDeg = s;
                        maxDeg = degre(s);
                    }
                }
            }
            if (sommetMaxDeg != null) {
                sommetsTries.add(i, sommetMaxDeg);
                i++;
                sommetsNonVus.remove(sommetMaxDeg);
            }
        }

    
        for (Sommet sommet : sommetsTries) {
            Set<Integer> couleursVoisines = new HashSet<>();
            for (Sommet voisin : sommet.getVoisins()) {
                if (coloration.containsKey(voisin)) {
                    couleursVoisines.add(coloration.get(voisin));
                }
            }
    
            int couleur = 0;
            while (couleursVoisines.contains(couleur)) {
                couleur++;
            }
    
            coloration.put(sommet, couleur);
    
            if (!gloutonne.containsKey(couleur)) {
                gloutonne.put(couleur, new HashSet<>());
            }
            gloutonne.get(couleur).add(sommet);
        }
    
        return gloutonne;
    }
    

    /**
     * @param depart  - ensemble non-vide de sommets
     * @param arrivee
     * @return le surcout total minimal du parcours entre l'ensemble de depart et le sommet d'arrivée
     * pré-requis : l'ensemble de départ et le sommet d'arrivée sont inclus dans l'ensemble des sommets de this
     */
    public int getDistance(Set<Sommet> depart, Sommet arrivee) { // plusieurs départ et un arrivée (dijsktra)
        Map<Sommet, Integer> distances = new HashMap<>(); // un sommet avec sa distance associée par rapport au sommet de départ

        for (Sommet s : sommets) {
            distances.put(s, Integer.MAX_VALUE); // on met les distances à l'infini (comme dijkstra)
        }

        PriorityQueue<Sommet> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get)); //listes de sommets triés par distance
        for (Sommet s : depart) {
            distances.put(s, 0); // on met la distance à 0 pour les sommets de départ
            queue.add(s);
        }

        while (!queue.isEmpty()) {
            Sommet actuel = queue.poll(); // queue.poll() c'est pour récupérer le sommet avec la distance la plus petite

            if (actuel.equals(arrivee)) {
                return distances.get(actuel);
            }

            for (Sommet voisin : actuel.getVoisins()) {
                int nouvelleDist = distances.get(actuel) + voisin.getSurcout(); // calcul de la nouvelle distance
                if (nouvelleDist < distances.get(voisin)) { // si la nouvelle distance est plus petite que l'ancienne
                    distances.put(voisin, nouvelleDist); // on met à jour la distance
                    queue.add(voisin);
                }
            }
        }

        return distances.get(arrivee);
    }

    /**
     * @return le surcout total minimal du parcours entre le sommet de depart et le sommet d'arrivée
     */
    public int getDistance(Sommet depart, Sommet arrivee) { // un seul départ et une arrivée (dijsktra)
        Map<Sommet, Integer> distances = new HashMap<>(); // un sommet avec sa distance associée par rapport au sommet de départ

        for (Sommet s : sommets) {
            distances.put(s, Integer.MAX_VALUE); // on met les distances à la valeur infini
        }
        distances.put(depart, 0);

        PriorityQueue<Sommet> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get)); //listes de sommets triés par distance
        queue.add(depart); // on ajoute le sommet de départ à la queue

        while (!queue.isEmpty()) {
            Sommet actuelle = queue.poll(); // on récupère le sommet avec la distance la plus petite

            if (actuelle.equals(arrivee)) {
                return distances.get(actuelle);
            }

            for (Sommet voisins : actuelle.getVoisins()) {
                int newDist = distances.get(actuelle) + voisins.getSurcout(); // calcul de la nouvelle distance
                if (newDist < distances.get(voisins)) { // si la nouvelle distance est plus petite que l'ancienne
                    distances.put(voisins, newDist); // on met à jour la distance
                    queue.add(voisins);
                }
            }
        }

        return distances.get(arrivee);
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
    public boolean estConnexe() { //fait testé
        return getEnsembleClassesConnexite().size() == 1;
    }

    /**
     * @return le degré maximum des sommets du graphe
     */
    public int degreMax() { // fait testé
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
            Map<Integer, Set<Sommet>> coulEtSommets = new HashMap<>();

            for(int i = 0; i<4; i++){
                coulEtSommets.put(i, new HashSet<>());
            }

            List<Sommet> pasColories = new ArrayList<>(sommets);

            for(Sommet sommet : pasColories){
                boolean[] possible = new boolean[4];

                for(Sommet voisin : sommet.getVoisins()){
                    for(int i=0; i<4; i++){
                        if(coulEtSommets.get(i).contains(voisin)) possible[i] = false; // si un voisin est de la couleur i, on ne peut donc pas colorier s en i
                    }
                }
                for(int i=0; i<4; i++){
                    if(possible[i]){ // si on peut colorier le sommet en i
                        coulEtSommets.get(i).add(sommet); // on ajoute le sommet à la couleur i
                    }
                }
            }
            return coulEtSommets;
    }

    /**
     * @return true si et seulement si this possède un sous-graphe complet d'ordre {@code k}
     */
    public boolean possedeSousGrapheComplet(int k) {
        int n = getNbSommets();
        if (k > n) return false;
        if (k == n) return estComplet();
        
        ArrayList<Sommet> sommets = new ArrayList<>(this.sommets);
        for (Sommet s : sommets) {
            if (s.getVoisins().size() >= k - 1) {
                Set<Sommet> sousGraphe = new HashSet<>();
                sousGraphe.add(s);
                ArrayList<Sommet> voisins = new ArrayList<>(s.getVoisins());
                for (int i = 0; i < voisins.size(); i++) {
                    if (sousGraphe.size() >= k) break;
                    sousGraphe.add(voisins.get(i));
                }
                if (estSousGrapheComplet(sousGraphe)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean estSousGrapheComplet(Set<Sommet> sousGraphe) {
        List<Sommet> sommets = new ArrayList<>(sousGraphe);
        int n = sommets.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!sommets.get(i).estVoisin(sommets.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param g un graphe
     * @return true si et seulement si this possède un sous-graphe isomorphe à {@code g}
     */
    public boolean possedeSousGrapheIsomorphe(Graphe g) {
        if (g.sommets.size() > this.sommets.size()) {
            return false;
        }

        List<Sommet> sommetsG = new ArrayList<>(this.sommets);
        List<Sommet> sommetsH = new ArrayList<>(g.sommets);

        return backtrack(sommetsG, sommetsH, new HashMap<>(), new HashSet<>());
    }

    private boolean backtrack(List<Sommet> sommetsG, List<Sommet> sommetsH, Map<Sommet, Sommet> mapping, Set<Sommet> used) {
        if (mapping.size() == sommetsH.size()) {
            return verifierIsomorphe(mapping);
        }

        Sommet nextH = sommetsH.get(mapping.size());

        for (Sommet g : sommetsG) {
            if (!used.contains(g)) {
                mapping.put(nextH, g);
                used.add(g);

                if (backtrack(sommetsG, sommetsH, mapping, used)) {
                    return true;
                }

                mapping.remove(nextH);
                used.remove(g);
            }
        }

        return false;
    }

    private boolean verifierIsomorphe(Map<Sommet, Sommet> mapping) {
        for (Sommet h1 : mapping.keySet()) {
            Sommet g1 = mapping.get(h1);

            for (Sommet h2 : h1.getVoisins()) {
                Sommet g2 = mapping.get(h2);
                if (g2 == null || !g1.getVoisins().contains(g2)) {
                    return false;
                }
            }
        }
        return true;
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
