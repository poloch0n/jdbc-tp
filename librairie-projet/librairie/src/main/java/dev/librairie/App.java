package dev.librairie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;

import dev.librairie.module.Livre;


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

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bibliotheque-jpa");
            EntityManager em = entityManagerFactory.createEntityManager();
            
            Livre l = em.find(Livre.class,4);
            if(l != null) {
            	System.out.println("livre trouvé");
            	System.out.println(l.getAuteur());
            	System.out.println(l.getTitre());
            } else {
            	System.out.println("livre pas trouvé");
            }

			TypedQuery<Livre>query = em.createQuery("select l from Livre l where l.titre like :titre ",Livre.class);
			query.setParameter("titre", "Apprendre à parler aux animaux");

			try {
				Livre l2 = query.getSingleResult();
            	System.out.println("livre trouvé");
            	System.out.println(l2.getAuteur());
            	System.out.println(l2.getTitre());
            }catch(Exception e){
            	System.out.println("livre pas trouvé");
            }

        } catch(Exception e) {
        	
        }
    }
}
