package dev.librairie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
            
        } catch(Exception e) {
        	
        }
    }
}
