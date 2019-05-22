package dev.banque;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.hibernate.Query;

import dev.banque.modele.AssuranceVie;
import dev.banque.modele.Client;
import dev.banque.modele.Compte;
import dev.banque.modele.LivretA;
import dev.banque.modele.Operation;
import dev.banque.modele.Virement;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        try {			
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque-tp-jpa");
            EntityManager em = entityManagerFactory.createEntityManager();
        	//insérer 2 clients
            // insérer un compte appartenant a 2 clients
            Client client1 = new Client();
            client1.setNom("nom client1");
            client1.setPrenom("prenom client1");
            client1.setLocalDate(Date.valueOf(LocalDate.now()));
            
            Client client2 = new Client();
            client2.setNom("nom client2");
            client2.setPrenom("prenom client2");
            client2.setLocalDate( Date.valueOf(LocalDate.now()));
            
            AssuranceVie compte1 = new AssuranceVie();
            compte1.addClient(client1);
            compte1.addClient(client2);
            compte1.setNumero(123456789);
            compte1.setSolde(52.22);
            compte1.setDateFin(Date.valueOf(LocalDate.now()));
            compte1.setTaux(0.88);

            LivretA compte2 = new LivretA();
            compte2.setNumero(123456787);
            compte2.setSolde(45);
            compte2.addClient(client1);
            compte2.setTaux(0.88);
            
            Operation operation1 = new Operation();
            operation1.setCompte(compte1);
            operation1.setDate(LocalDateTime.now());
            operation1.setMontant(8585.2);
            operation1.setMotif("Inshalaah");
            

            Virement operation2 = new Virement();
            operation2.setCompte(compte1);
            operation2.setDate(LocalDateTime.now());
            operation2.setMontant(6445654.2);
            operation2.setMotif("Inshalaah2");
            operation2.setBeneficiaire("alllah");

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(client1);
            em.persist(client2);
            em.persist(compte1);
            em.persist(compte2);
            em.persist(operation1);
            em.persist(operation2);
            
            transaction.commit();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
