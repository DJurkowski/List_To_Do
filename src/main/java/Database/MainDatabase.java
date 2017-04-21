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
	
	public static void begin(){
		entityManager.getTransaction().begin();
	}
	
	public static void commit(){
		entityManager.getTransaction().commit();
	}
	
	public static void add(Object t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	public static void remove(Object t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	public static void close() {
		entityManager.clear();
		entityManagerFactory.close();
	}

	public static boolean find(String nick, String password) {
		boolean result = false;

		TypedQuery<User> query = entityManager.createQuery("Select u from User u", User.class);
		List<User> users = query.getResultList();
		
		for (User user : users) {
			if (user.getNick().equals(nick) && user.getPassword().equals(password)) {
				result = true;
			}
		}
		return result;
	}

	public static User findUser(String nick, String password) {
		User result = null;

		TypedQuery<User> query = entityManager.createQuery("Select u from User u", User.class);
		List<User> users = query.getResultList();

		for (User user : users) {
			if (user.getNick().equals(nick) && user.getPassword().equals(password)) {
				result = user;
			}
		}
		return result;
	}

	public static List<Task> loadTask(User user) {

		TypedQuery<Task> query = entityManager
				.createQuery("Select t.name from Task t where t.userId = :id", Task.class);
		query.setParameter("id", user.getId());
		List<Task> tasks = query.getResultList();
		return tasks;
		// Dokonczyc!!!!
	}

}
