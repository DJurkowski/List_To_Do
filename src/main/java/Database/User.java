package Database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
*
* @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
*
*/

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String nick;
	private String password;
	private String email;

	@OneToMany
	@JoinColumn(name = "userId")
	private List<Task> tasksList;
	
	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Task> tasksList) {
		this.tasksList = tasksList;
	}

}
