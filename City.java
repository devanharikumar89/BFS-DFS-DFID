/**
 * @author devan harikumar
 * dhariku@ncsu.edu
 * No external sources used
 * 
 *
 */
public class City {
	
	String name;
	City[] neighbors = null;
	boolean visited = false;
	public City() {

	}
	public City(String name, City[] neighbors) {
		this.name = name;
		this.neighbors = neighbors;
	}
	@Override
	public String toString() {
		return this.name;
	}

}
