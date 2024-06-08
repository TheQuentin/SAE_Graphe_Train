package fr.umontpellier.iut.trains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.umontpellier.iut.trains.plateau.Plateau;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import fr.umontpellier.iut.graphes.Graphe;
import fr.umontpellier.iut.graphes.Sommet;

@Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestPerso {


    @Test
    public void test_contructeurGrapheAvecOrdre(){
        Graphe g = new Graphe(5);
        assertEquals(5, g.getNbSommets());
    }

    @Test
    public void test_ajouter_sommet() {
        Graphe g = new Graphe();
        g.ajouterSommet(1);
        g.ajouterSommet(2);
        g.ajouterSommet(3);
        g.ajouterSommet(4);
        g.ajouterSommet(5);
        g.ajouterSommet(6);

        assertEquals(6, g.getNbSommets());
    }

    @Test
    public void test_getNbSommets() {
        Graphe g = new Graphe();
        g.ajouterSommet(1);
        g.ajouterSommet(2);
        g.ajouterSommet(3);


        assertEquals(3, g.getNbSommets());
    }

    @Test
    public void test_ajout_arrete(){
        Graphe g = new Graphe();
        g.ajouterSommet(1);
        g.ajouterSommet(2);
        g.ajouterSommet(3);
        g.ajouterSommet(4);
        g.ajouterSommet(5);
        g.ajouterSommet(6);

        g.getSommet(1).ajouterVoisin(g.getSommet(2));
        g.getSommet(2).ajouterVoisin(g.getSommet(3));
        g.getSommet(3).ajouterVoisin(g.getSommet(4));
        g.getSommet(4).ajouterVoisin(g.getSommet(5));
        g.getSommet(5).ajouterVoisin(g.getSommet(6));
        g.getSommet(6).ajouterVoisin(g.getSommet(1));   
        g.getSommet(1).ajouterVoisin(g.getSommet(3));
        g.getSommet(2).ajouterVoisin(g.getSommet(4));
        g.getSommet(3).ajouterVoisin(g.getSommet(5));
        g.getSommet(4).ajouterVoisin(g.getSommet(6));
        g.getSommet(5).ajouterVoisin(g.getSommet(1));
        g.getSommet(6).ajouterVoisin(g.getSommet(2));

        assertEquals(12, g.getNbAretes());
    }

    @Test
    public void test_supprimer_Arete(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);

        g.supprimerArete(s1, s2);

        assertEquals(3, g.getNbAretes());
    }

    @Test
    public void test_getnbAretes(){
        Graphe g = new Graphe();
        g.ajouterSommet(1);
        g.ajouterSommet(2);
        g.ajouterSommet(3);
        g.ajouterSommet(4);
        g.ajouterSommet(5);
        g.ajouterSommet(6);

        g.getSommet(1).ajouterVoisin(g.getSommet(2));
        g.getSommet(2).ajouterVoisin(g.getSommet(3));
        g.getSommet(3).ajouterVoisin(g.getSommet(4));
        g.getSommet(4).ajouterVoisin(g.getSommet(5));
        g.getSommet(5).ajouterVoisin(g.getSommet(6));
        g.getSommet(6).ajouterVoisin(g.getSommet(1));   
        g.getSommet(1).ajouterVoisin(g.getSommet(3));
        g.getSommet(2).ajouterVoisin(g.getSommet(4));
        

        assertEquals(8, g.getNbAretes());
    }



    @Test
    public void test_get_arrete() {

        Graphe g = new Graphe();
        g.ajouterSommet(1);
        g.ajouterSommet(2);
        g.ajouterSommet(3);
        g.ajouterSommet(4);
        g.ajouterSommet(5);
        g.ajouterSommet(6);
    
        g.getSommet(1).ajouterVoisin(g.getSommet(2));
        g.getSommet(2).ajouterVoisin(g.getSommet(3));
        g.getSommet(3).ajouterVoisin(g.getSommet(4));
        g.getSommet(4).ajouterVoisin(g.getSommet(5));
        g.getSommet(5).ajouterVoisin(g.getSommet(6));
        g.getSommet(6).ajouterVoisin(g.getSommet(1));   
        g.getSommet(1).ajouterVoisin(g.getSommet(3));
        g.getSommet(2).ajouterVoisin(g.getSommet(4));
        g.getSommet(3).ajouterVoisin(g.getSommet(5));
        g.getSommet(4).ajouterVoisin(g.getSommet(6));
        g.getSommet(5).ajouterVoisin(g.getSommet(1));
        g.getSommet(6).ajouterVoisin(g.getSommet(2));

    
        System.out.println(g.getAretes());
        System.out.println(g.getAretes().size());
    
        assertEquals(12, g.getAretes().size());
    }

    @Test
    public void test_get_arrete_fail(){
        Graphe g = new Graphe();
        g.ajouterSommet(1);
        g.ajouterSommet(2);
        g.ajouterSommet(3);
        g.ajouterSommet(4);
    
        g.getSommet(1).ajouterVoisin(g.getSommet(2));
        g.getSommet(2).ajouterVoisin(g.getSommet(3));
        g.getSommet(3).ajouterVoisin(g.getSommet(4)); 
        g.getSommet(1).ajouterVoisin(g.getSommet(3));    

    
        assertEquals(4 , g.getAretes().size());
    }

    @Test
    public void test_sequenceEstGraphe_passe(){
        List<Integer> sequence = List.of(1,2,3,4,5,6,1,3,5,2,4,6);
        Graphe g = new Graphe();

        assertEquals(true, g.sequenceEstGraphe(sequence));
    }

    @Test 
    public void test_sequenceEstGraphe_passe2(){
        List<Integer> sequence = List.of(1,1,1,2,3);
        Graphe g = new Graphe();

        assertEquals(true, g.sequenceEstGraphe(sequence));
    }

    @Test
    public void test_sequenceEstGraphe_passe3(){
        List<Integer> sequence = List.of(1,1);
        Graphe g = new Graphe();

        assertEquals(true, g.sequenceEstGraphe(sequence));
    }

    @Test
    public void test_sequenceEstGraphe_fail(){
        List<Integer> sequence = List.of(1,2,3,4,5,6,1,3,5,2,4,6,7);
        Graphe g = new Graphe();

        assertEquals(false, g.sequenceEstGraphe(sequence));
        
    }

    @Test
    public void test_sequenceEstGraphe_fail2(){
        List<Integer> sequence = List.of(1,2);
        Graphe g = new Graphe();

        assertEquals(false, g.sequenceEstGraphe(sequence));
        
    }
        
    @Test   
    public void test_estComplet_passe(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s2, s3);

        assertEquals(true, g.estComplet());
    }

    @Test
    public void test_estComplet_passe2(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s4);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s4);
        g.ajouterArete(s3, s4);

        assertEquals(true, g.estComplet());
    }

    @Test
    public void test_estComplet_fail(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);

        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);

        assertEquals(false, g.estComplet());
    }

    @Test
    public void test_estComplet_fail2(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s4);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s4);

        assertEquals(false, g.estComplet());
    }

    @Test
    public void test_degreMax_passe(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s4);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s4);

        assertEquals(3, g.degreMax());
    }

    @Test
    public void test_degreMax_passe2(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s2, s3);

        assertEquals(2, g.degreMax());
    }

    @Test
    public void test_possede_un_cycle_passe(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);

        assertEquals(true, g.possedeUnCycle());
    }

    @Test
    public void test_possede_un_cycle_passe2(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);
        g.ajouterArete(s1, s3);

        assertEquals(true, g.possedeUnCycle());
    }

    @Test
    public void test_possede_un_cycle_fail(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);

        assertEquals(false, g.possedeUnCycle());
    }

    @Test
    public void test_possede_un_cycle_fail2(){
        Graphe g = new Graphe();

        assertEquals(false, g.possedeUnCycle());
    }

    @Test
    public void test_possede_un_isthme_fail(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);
        g.ajouterArete(s1, s3);

        assertEquals(false, g.possedeUnIsthme());
    }

    @Test
    public void test_possede_un_isthme_passe(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        

        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);
        g.ajouterArete(s1, s3);
        

        assertEquals(true, g.possedeUnIsthme());
    }

    @Test
    public void test_possede_un_isthme_passe2(){
        Graphe g = new Graphe();

        List<Sommet> sommets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sommets.add(new Sommet(i));
            g.ajouterSommet(sommets.get(i));
        }

        g.ajouterArete(sommets.get(0), sommets.get(1));
        g.ajouterArete(sommets.get(1), sommets.get(2)); 
        g.ajouterArete(sommets.get(2), sommets.get(3));
        g.ajouterArete(sommets.get(3), sommets.get(4));
        g.ajouterArete(sommets.get(4), sommets.get(5));
        g.ajouterArete(sommets.get(5), sommets.get(6));
        g.ajouterArete(sommets.get(6), sommets.get(7));
        g.ajouterArete(sommets.get(7), sommets.get(8));
        g.ajouterArete(sommets.get(8), sommets.get(9));
        g.ajouterArete(sommets.get(2), sommets.get(4));
        

        assertEquals(true, g.possedeUnIsthme());
    }

    @Test
    public void test_possede_un_isthme_passe3(){
        Graphe g = new Graphe();

        List<Sommet> sommets = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            sommets.add(new Sommet(i));
            g.ajouterSommet(sommets.get(i));
        }

        g.ajouterArete(sommets.get(0), sommets.get(1));
        g.ajouterArete(sommets.get(1), sommets.get(2));
        g.ajouterArete(sommets.get(2), sommets.get(3));
        g.ajouterArete(sommets.get(3), sommets.get(0));
        g.ajouterArete(sommets.get(2), sommets.get(4)); // c'est un isthme
        g.ajouterArete(sommets.get(4), sommets.get(5));
        g.ajouterArete(sommets.get(5), sommets.get(6));
        g.ajouterArete(sommets.get(6), sommets.get(7));
        g.ajouterArete(sommets.get(7), sommets.get(4));


        assertEquals(true, g.possedeUnIsthme());

    }

    @Test
    public void test_possede_un_isthme_fail1(){
        Graphe g = new Graphe();        

        assertEquals(false, g.possedeUnIsthme());
    }

    @Test
    public void test_possede_un_isthme_fail2(){
        Graphe g = new Graphe();    

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);

        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s2, s3);
        

        assertEquals(false, g.possedeUnIsthme());
    }   

    @Test
    public void test_estConnexe_fail(){
        Graphe g = new Graphe();

        assertEquals(false, g.estConnexe());
    }

    @Test
    public void test_estConnexe_passe(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);

        assertEquals(true, g.estConnexe());
    }	

    @Test
    public void test_estConnexe_passe2(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s1);

        assertEquals(true, g.estConnexe());
    }

    @Test
    public void test_est_cycle_passe(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s1);

        assertEquals(true, g.estCycle());

    }

    @Test
    public void test_est_cycle_fail(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);  
        Sommet s6 = new Sommet(6);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        g.ajouterSommet(s5);
        g.ajouterSommet(s6);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s1);

        g.ajouterArete(s4, s5);
        g.ajouterArete(s5, s6);
        g.ajouterArete(s6, s4);


        assertEquals(false, g.estCycle());
    }

    @Test
    public void test_possede_sous_graphe_complete_passe(){
        Graphe g = new Graphe();

        List<Sommet> sommets = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            sommets.add(new Sommet(i));
            g.ajouterSommet(sommets.get(i));
        }

        g.ajouterArete(sommets.get(0), sommets.get(1));
        g.ajouterArete(sommets.get(1), sommets.get(2));
        g.ajouterArete(sommets.get(2), sommets.get(3));
        g.ajouterArete(sommets.get(3), sommets.get(0));

        //Le sous graphe complet
        g.ajouterArete(sommets.get(4), sommets.get(5));
        g.ajouterArete(sommets.get(5), sommets.get(6));
        g.ajouterArete(sommets.get(6), sommets.get(7));
        g.ajouterArete(sommets.get(7), sommets.get(4));
        g.ajouterArete(sommets.get(4), sommets.get(6));
        g.ajouterArete(sommets.get(5), sommets.get(7));


        assertEquals(true, g.possedeSousGrapheComplet(4));
    }

    @Test
    public void test_possede_sous_graphe_complete_fail(){
        Graphe g = new Graphe();

        List<Sommet> sommets = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            sommets.add(new Sommet(i));
            g.ajouterSommet(sommets.get(i));
        }

        g.ajouterArete(sommets.get(0), sommets.get(1));
        g.ajouterArete(sommets.get(1), sommets.get(2));
        g.ajouterArete(sommets.get(2), sommets.get(3));
        g.ajouterArete(sommets.get(3), sommets.get(0));

        //Le sous graphe complet
        g.ajouterArete(sommets.get(4), sommets.get(5));
        g.ajouterArete(sommets.get(5), sommets.get(6));
        g.ajouterArete(sommets.get(6), sommets.get(7));
        g.ajouterArete(sommets.get(7), sommets.get(4));
        g.ajouterArete(sommets.get(4), sommets.get(6));
        g.ajouterArete(sommets.get(5), sommets.get(7));


        assertEquals(false, g.possedeSousGrapheComplet(5));
    }

    @Test
    public void possedeSousGrapheCompletPasse(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);  
        Sommet s6 = new Sommet(6);

        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        g.ajouterSommet(s4);
        g.ajouterSommet(s5);
        g.ajouterSommet(s6);

        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s4);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s4);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s6, s5);


        assertEquals(true, g.possedeSousGrapheComplet(4));
    }

    @Test
    public void possedeSousGrapheCompletPasse2(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        
        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s2, s3);
  
        assertEquals(true, g.possedeSousGrapheComplet(3));
    }

    @Test
    public void possedeSousGrapheCompletFail(){
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        
        g.ajouterSommet(s3);
        g.ajouterSommet(s2);
        g.ajouterSommet(s1);
        
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s2, s3);
  
        assertEquals(false, g.possedeSousGrapheComplet(4));
    }

    @Test
    public void possedeSousGrapheCompletPasse3() {
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);
        Sommet s6 = new Sommet(6);
        Sommet s7 = new Sommet(7);
        Sommet s8 = new Sommet(8);

        g.ajouterSommet(s1);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);
        g.ajouterSommet(s5);
        g.ajouterSommet(s6);
        g.ajouterSommet(s7);
        g.ajouterSommet(s8);

        g.ajouterArete(s1, s2);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s4);
        g.ajouterArete(s2, s5);
        g.ajouterArete(s2, s6);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s3, s5);
        g.ajouterArete(s3, s6);
        g.ajouterArete(s4, s5);
        g.ajouterArete(s4, s6);
        g.ajouterArete(s5, s6);
        g.ajouterArete(s7, s1);
        g.ajouterArete(s7, s2);
        g.ajouterArete(s7, s4);
        g.ajouterArete(s7, s6);
        g.ajouterArete(s8, s1);
        g.ajouterArete(s8, s3);
        g.ajouterArete(s8, s5);

        assertEquals(false, g.possedeSousGrapheComplet(8));
        assertEquals(false, g.possedeSousGrapheComplet(7));
        assertEquals(false, g.possedeSousGrapheComplet(6));
        assertEquals(true, g.possedeSousGrapheComplet(5));
        assertEquals(true, g.possedeSousGrapheComplet(4));
        assertEquals(true, g.possedeSousGrapheComplet(3));
        assertEquals(true, g.possedeSousGrapheComplet(2));
        assertEquals(true, g.possedeSousGrapheComplet(1));
    }

    @Test
    public void possedeSousGrapheCompletPasse4() {
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        

        g.ajouterSommet(s1);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);


        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s4);
        g.ajouterArete(s2, s3);

        assertEquals(true, g.possedeSousGrapheComplet(3));
    }

    @Test
    public void possedeSousGrapheCompletPasse5() {
        Graphe g = new Graphe();

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);
        

        g.ajouterSommet(s1);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);
        g.ajouterSommet(s5);


        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s4);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s5);
        g.ajouterArete(s3, s5);
        g.ajouterArete(s4, s5);

        assertEquals(true, g.possedeSousGrapheComplet(3));
        assertEquals(false, g.possedeSousGrapheComplet(4));
        assertEquals(true, g.possedeSousGrapheComplet(2));
    }

    @Test
    public void test_fusion_vrai(){
        Sommet.SommetBuilder s1b = new Sommet.SommetBuilder();
        Set<Integer> joueurs1 = new HashSet<>(); joueurs1.add(1); joueurs1.add(3);
        s1b.setIndice(1); s1b.setSurcout(2); s1b.setNbPointsVictoire(3); s1b.setJoueurs(joueurs1);
        Sommet s1 = s1b.createSommet();

        Sommet.SommetBuilder s2b = new Sommet.SommetBuilder();
        Set<Integer> joueurs2 = new HashSet<>(); joueurs2.add(4); joueurs2.add(6);
        s2b.setIndice(2); s2b.setSurcout(2); s2b.setNbPointsVictoire(1); s2b.setJoueurs(joueurs2);
        Sommet s2 = s2b.createSommet();

        Sommet.SommetBuilder s3b = new Sommet.SommetBuilder();
        Set<Integer> joueurs3 = new HashSet<>(); joueurs3.add(1); joueurs3.add(2);
        s3b.setIndice(3); s3b.setSurcout(3); s3b.setNbPointsVictoire(1); s3b.setJoueurs(joueurs3);
        Sommet s3 = s3b.createSommet();

        Sommet.SommetBuilder s4b = new Sommet.SommetBuilder();
        Set<Integer> joueurs4 = new HashSet<>(); joueurs4.add(3); joueurs4.add(6);
        s4b.setIndice(4); s4b.setSurcout(2); s4b.setNbPointsVictoire(3); s4b.setJoueurs(joueurs4);
        Sommet s4 = s4b.createSommet();

        Sommet.SommetBuilder s5b = new Sommet.SommetBuilder();
        Set<Integer> joueurs5 = new HashSet<>(); joueurs5.add(2); joueurs5.add(4);
        s5b.setIndice(5); s5b.setSurcout(2); s5b.setNbPointsVictoire(4); s5b.setJoueurs(joueurs5);
        Sommet s5 = s5b.createSommet();


        Graphe g = new Graphe();

        g.ajouterSommet(s1);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);
        g.ajouterSommet(s5);

        g.ajouterArete(s1, s3);
        g.ajouterArete(s1, s5);
        g.ajouterArete(s2,s3);
        g.ajouterArete(s4,s1);
        g.ajouterArete(s4,s5);


        Set<Sommet> ensemble = new HashSet<>();
        ensemble.add(s1);
        ensemble.add(s3);


        Graphe fusionne = new Graphe();
        Sommet.SommetBuilder s1bf = new Sommet.SommetBuilder();
        Set<Integer> joueurs1f = new HashSet<>(); joueurs1f.add(1); joueurs1f.add(3); joueurs1f.add(2);
        s1bf.setIndice(1); s1bf.setSurcout(5); s1bf.setNbPointsVictoire(4); s1bf.setJoueurs(joueurs1f);
        Sommet s1f = s1bf.createSommet();

        Sommet.SommetBuilder s2bf = new Sommet.SommetBuilder();
        Set<Integer> joueurs2f = new HashSet<>(); joueurs2f.add(4); joueurs2f.add(6);
        s2bf.setIndice(2); s2bf.setSurcout(2); s2bf.setNbPointsVictoire(1); s2bf.setJoueurs(joueurs2f);
        Sommet s2f = s2bf.createSommet();

        Sommet.SommetBuilder s4bf = new Sommet.SommetBuilder();
        Set<Integer> joueurs4f = new HashSet<>(); joueurs4f.add(3); joueurs4f.add(6);
        s4bf.setIndice(4); s4bf.setSurcout(2); s4bf.setNbPointsVictoire(3); s4bf.setJoueurs(joueurs4f);
        Sommet s4f = s4bf.createSommet();

        Sommet.SommetBuilder s5bf = new Sommet.SommetBuilder();
        Set<Integer> joueurs5f = new HashSet<>(); joueurs5f.add(2); joueurs5f.add(4);
        s5bf.setIndice(5); s5bf.setSurcout(2); s5bf.setNbPointsVictoire(4); s5bf.setJoueurs(joueurs5f);
        Sommet s5f = s5bf.createSommet();

        fusionne.ajouterSommet(s1f);
        fusionne.ajouterSommet(s2f);
        fusionne.ajouterSommet(s4f);
        fusionne.ajouterSommet(s5f);

        fusionne.ajouterArete(s1f, s2f);
        fusionne.ajouterArete(s1f, s4f);
        fusionne.ajouterArete(s1f, s5f);
        fusionne.ajouterArete(s4f, s5f);

        assertEquals(fusionne.getNbSommets(), g.fusionnerEnsembleSommets(g, ensemble).getNbSommets());
        assertEquals(fusionne.getNbAretes(), g.fusionnerEnsembleSommets(g, ensemble).getNbAretes());
        for(Sommet s : fusionne.getSommets()){
            assertEquals(s.getIndice(), g.fusionnerEnsembleSommets(g, ensemble).getSommet(s.getIndice()).getIndice());
            assertEquals(s.getNbPointsVictoire(), g.fusionnerEnsembleSommets(g, ensemble).getSommet(s.getIndice()).getNbPointsVictoire());
            assertEquals(s.getSurcout(), g.fusionnerEnsembleSommets(g, ensemble).getSommet(s.getIndice()).getSurcout());
            assertEquals(s.getVoisins(), g.fusionnerEnsembleSommets(g, ensemble).getSommet(s.getIndice()).getVoisins());
            assertEquals(s.getJoueurs(), g.fusionnerEnsembleSommets(g, ensemble).getSommet(s.getIndice()).getJoueurs());
        }
    }


    @Test
    public void test_getColorationGloutonne() {
        // Création d'un graphe
        Graphe g = new Graphe();

        // Ajout des sommets
        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);

        g.ajouterSommet(s1);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);
        g.ajouterSommet(s5);

        // Ajout des arêtes
        g.ajouterArete(s1, s2);
        g.ajouterArete(s1, s3);
        g.ajouterArete(s2, s3);
        g.ajouterArete(s2, s4);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s5);

        // Appel de la méthode à tester
        Map<Integer, Set<Sommet>> coloration = g.getColorationGloutonne();

        // Vérification du résultat
        // Par exemple, on peut vérifier que chaque sommet est bien coloré
        assertNotNull(coloration);
        
        // Vérifier que le nombre de couleurs utilisées ne dépasse pas le nombre de sommets (cas extrême)
        assertTrue(coloration.size() <= g.getNbSommets());

        // Vérification des sommets colorés
        Set<Sommet> tousLesSommetsColorés = new HashSet<>();
        for (Set<Sommet> sommetsDeMemeCouleur : coloration.values()) {
            tousLesSommetsColorés.addAll(sommetsDeMemeCouleur);
        }
        assertEquals(g.getNbSommets(), tousLesSommetsColorés.size());

        // Vérifier que deux voisins n'ont pas la même couleur
        for (Map.Entry<Integer, Set<Sommet>> entry : coloration.entrySet()) {
            int couleur = entry.getKey();
            Set<Sommet> sommets = entry.getValue();
            for (Sommet sommet : sommets) {
                for (Sommet voisin : sommet.getVoisins()) {
                    // Si le voisin est dans le même ensemble de couleur, il y a un problème
                    assertEquals(false, coloration.get(couleur).contains(voisin), 
                        "Le sommet " + sommet.getIndice() + " et son voisin " + voisin.getIndice() + " ont la même couleur " + couleur);
                }
            }
        }
    }

    // @Test //Doit pouvoir passer !!
    // public void test_possede_un_isthme_largeGraph() {
    //     Graphe g = new Graphe();

    //     for (int i = 0; i < 100000; i++) {
    //         g.ajouterSommet(i);
    //     }

    //     for (int i = 0; i < 9999; i++) {
    //         g.ajouterArete(g.getSommet(i), g.getSommet(i + 1));
    //     }

    //     assertEquals(true, g.possedeUnIsthme());
    // }

    @Test
    public void testGloutonerieSimple(){
        Graphe graphe = new Graphe(7);
        graphe.ajouterArete(graphe.getSommet(0), graphe.getSommet(1));
        graphe.ajouterArete(graphe.getSommet(1), graphe.getSommet(2));
        graphe.ajouterArete(graphe.getSommet(2), graphe.getSommet(3));
        graphe.ajouterArete(graphe.getSommet(4), graphe.getSommet(0));
        graphe.ajouterArete(graphe.getSommet(4), graphe.getSommet(2));
        graphe.ajouterArete(graphe.getSommet(0), graphe.getSommet(5));
        graphe.ajouterArete(graphe.getSommet(6), graphe.getSommet(1));

        Map<Integer, Set<Sommet>> glouton = graphe.getColorationGloutonne();
        assertTrue(glouton.size()==2);

        Set<Sommet> coloration1 = new HashSet<>();
        coloration1.add(graphe.getSommet(0));
        coloration1.add(graphe.getSommet(2));
        coloration1.add(graphe.getSommet(6));


        Set<Sommet> coloration2 = new HashSet<>();
        coloration2.add(graphe.getSommet(1));
        coloration2.add(graphe.getSommet(3));
        coloration2.add(graphe.getSommet(4));
        coloration2.add(graphe.getSommet(5));

        assertTrue(sontEgaux(glouton.get(0),coloration1));
        assertTrue(sontEgaux(glouton.get(1),coloration2));


    }

    public boolean sontEgaux(Collection<Sommet> c1,Collection<Sommet> c2){
        if(c1.size()!= c2.size())return false;
        for (Sommet som : c1){
            if (!c2.contains(som)) return false;
        }
        return true;
    }

    @Test
    public void colorationPropreOptimalPasse1 (){
        Graphe g = new Graphe();

        List<Sommet> sommets = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            sommets.add(new Sommet(i));
            g.ajouterSommet(sommets.get(i));
        }

        g.ajouterArete(sommets.get(0), sommets.get(1));
        g.ajouterArete(sommets.get(1), sommets.get(2));
        g.ajouterArete(sommets.get(2), sommets.get(3));
        g.ajouterArete(sommets.get(3), sommets.get(4));
        g.ajouterArete(sommets.get(4), sommets.get(5));
        g.ajouterArete(sommets.get(5), sommets.get(6));
        g.ajouterArete(sommets.get(6), sommets.get(7));
        g.ajouterArete(sommets.get(7), sommets.get(0));
        g.ajouterArete(sommets.get(0), sommets.get(3));
        g.ajouterArete(sommets.get(0), sommets.get(4));
        g.ajouterArete(sommets.get(0), sommets.get(5));
        g.ajouterArete(sommets.get(7), sommets.get(5));
        g.ajouterArete(sommets.get(1), sommets.get(3));


        Map<Integer, Set<Sommet>> coloration = g.getColorationGloutonne();
        assertEquals(3, coloration.size());
    }

    @Test
    public void colorationPropreOptimalPasse2 () {
        Jeu jeu = new Jeu(new String[]{"Rick", "Morty"}, new String[]{}, Plateau.TOKYO);
        Graphe graphe = jeu.getGraphe();

        Map<Integer, Set<Sommet>> coloration = graphe.getColorationGloutonne();
        assertEquals(4, coloration.size());
    }
}


