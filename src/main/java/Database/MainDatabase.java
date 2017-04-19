package Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
 *
 */
public class MainDatabase {

	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;

	public MainDatabase() {
		entityManagerFactory = Persistence.createEntityManagerFactory("listtodo");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static <T> void add(T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	public static <T> void remove(T t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	public static void close() {
		entityManager.clear();
		entityManagerFactory.close();
	}
	
	

}
