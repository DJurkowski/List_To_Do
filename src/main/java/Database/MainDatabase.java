package Database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

	public static User findUser(String nick, String password) {
		User result = null;

		TypedQuery<User> query = entityManager.createQuery("Select * from User", User.class);
		List<User> users = query.getResultList();

		for (User user : users) {
			if (user.getNick().equals(nick) && user.getPassword().equals(password)) {
				result = user;
			}
		}
		return result;
	}

}
