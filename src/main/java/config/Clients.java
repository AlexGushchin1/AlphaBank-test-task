package config;
import javax.persistence.*;
@Entity
@Table(name = "CLIENTS")
public class Clients {
	@Id @GeneratedValue
	   @Column(name = "IDCLIENT")
	   private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
