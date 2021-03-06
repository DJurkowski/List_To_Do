package Database;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
*
* @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
*
*/

@Entity
public class Task {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;
	private LocalDate date;
	
	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
