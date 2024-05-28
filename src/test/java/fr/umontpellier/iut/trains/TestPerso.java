package fr.umontpellier.iut.trains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import fr.umontpellier.iut.graphes.Graphe;
import fr.umontpellier.iut.graphes.Sommet;
import fr.umontpellier.iut.trains.plateau.Plateau;

@Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestPerso {

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
    public void test_GetDistance_passe1(){
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
        g.ajouterArete(s2, s3);
        g.ajouterArete(s3, s4);
        g.ajouterArete(s4, s5);
        g.ajouterArete(s5, s1);

        assertEquals(4, g.getDistance(s1, s4));
    }
}