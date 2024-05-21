package fr.umontpellier.iut.trains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import fr.umontpellier.iut.graphes.Graphe;
import fr.umontpellier.iut.graphes.Sommet;
import fr.umontpellier.iut.trains.plateau.Plateau;

@Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestPerso {
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
}