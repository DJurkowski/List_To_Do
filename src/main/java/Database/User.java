package Database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

	@GeneratedValue
	@Id
	private String name;
	private String nick;
	private String password;
	private String email;

	@OneToMany
	@JoinColumn(name = "User_id")
	private List<Task> tasksList;

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

	public List<Task> getTaskList() {
		return tasksList;
	}

	public void setTaskList(List<Task> tasksList) {
		this.tasksList = tasksList;
	}

}
