import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author devan harikumar
 * dhariku@ncsu.edu
 * No external sources used
 * 
 *
 */
public class SearchRomania {

	static Map<String, City> cityMap;

	public static void main(String[] args) {

		String searchType = "BFS";
		String srcCity = "bucharest";
		String destCity = "arad";

		if (args != null && args.length == 3) {
			searchType = args[0];
			srcCity = args[1];
			destCity = args[2];
		} else {
			System.out.println("WRONG NUMBER OF ARGUMENTS\n");
			System.exit(0);
		}
		populateCity();
		if (searchType.equalsIgnoreCase("BFS")) {
			searchBFS(srcCity.toLowerCase(), destCity.toLowerCase());
		} else if (searchType.equalsIgnoreCase("DFS")) {
			searchDFS(srcCity.toLowerCase(), destCity.toLowerCase());
		} else {
			System.out.println("CHECK THE SEARCH METHOD ARGUMENT\n");
		}
	}

	private static void searchDFS(String srcCity, String destCity) {
		if (!(cityMap.containsKey(srcCity) && cityMap.containsKey(destCity))) {
			System.out.println("CHECK THE CITY NAME\n");
			System.exit(0);
		}
		System.out.println("\nDEPTH FIRST SEARCH\n\n");
		City source = cityMap.get(srcCity);
		City destination = cityMap.get(destCity);

		Stack<List<City>> stack = new Stack<List<City>>();
		List<City> visited = new ArrayList<City>();
		List<City> expanded = new ArrayList<City>();

		source.visited = true;
		visited.add(source);
		System.out.println("VISITED : " + visited.toString());
		stack.push(new ArrayList<City>(Arrays.asList(source)));
		List<City>path=null;
		boolean found = false;
		while (!stack.isEmpty() && !found) {
			List<City> currentList = stack.pop();
			City current = currentList.get(currentList.size()-1);
			expanded.add(current);
			System.out.println("EXPANDED : " + expanded.toString());
			if(current.equals(destination)){
				found = true;
				path = currentList;
			}
			if(!found)
			for (City city : current.neighbors) {
				
				path = new ArrayList<City>(currentList);
				
				if (!city.visited) {
					city.visited = true;
					path.add(city);
					stack.push(path);
					visited.add(city);
					System.out.println("VISITED : " + visited.toString());
				}
			}
		}
		System.out.println("Found path from " + source.name + " to "
				+ destination.name);
		System.out.println("Cities visited : " + visited.size());
		System.out.println("Cities expanded : " + expanded.size());
		System.out.println("Path from " + source.name + " to "
				+ destination.name +" : "+path.toString());

	}

	private static void searchBFS(String srcCity, String destCity) {
		if (!(cityMap.containsKey(srcCity) && cityMap.containsKey(destCity))) {
			System.out.println("CHECK THE CITY NAME\n");
			System.exit(0);
		}
		System.out.println("\nBREADTH FIRST SEARCH\n\n");
		
		City source = cityMap.get(srcCity);
		City destination = cityMap.get(destCity);

		List<List<City>> queue = new ArrayList<List<City>>();
		List<City> visited = new ArrayList<City>();
		List<City> expanded = new ArrayList<City>();

		source.visited = true;
		visited.add(source);
		System.out.println("VISITED : " + visited.toString());
		queue.add(new ArrayList<City>(Arrays.asList(source)));
		List<City>path=null;
		boolean found = false;
		while (!queue.isEmpty() && !found) {
			List<City> currentList = queue.get(0);
			City current = currentList.get(currentList.size()-1);
			expanded.add(current);
			System.out.println("EXPANDED : " + expanded.toString());
			if(current.equals(destination)){
				found = true;
				path=currentList;
			}
			if(!found)
			for (int i = current.neighbors.length - 1; i >= 0; i--) {
				
				City city = current.neighbors[i];
				path = new ArrayList<City>(currentList);
				
				if (!city.visited) {
					city.visited = true;
					path.add(city);
					queue.add(path);
					visited.add(city);
					System.out.println("VISITED : " + visited.toString());
				}
			}
			queue.remove(0);
		}
		System.out.println("Found path from " + source.name + " to "
				+ destination.name);
		System.out.println("Cities visited : " + visited.size());
		System.out.println("Cities expanded : " + expanded.size());
		System.out.println("Path from " + source.name + " to "
				+ destination.name +" : "+path.toString());

	}

	private static void populateCity() {

		cityMap = new HashMap<String, City>();

		City arad = new City("arad", null);
		City bucharest = new City("bucharest", null);
		City craiova = new City("craiova", null);
		City dobreta = new City("dobreta", null);
		City eforie = new City("eforie", null);
		City fagaras = new City("fagaras", null);
		City giurgiu = new City("giurgiu", null);
		City hirsova = new City("hirsova", null);
		City iasi = new City("iasi", null);
		City lugoj = new City("lugoj", null);
		City mehadia = new City("mehadia", null);
		City neamt = new City("neamt", null);
		City oradea = new City("oradea", null);
		City pitesti = new City("pitesti", null);
		City rimnicu_vilcea = new City("rimnicu_vilcea", null);
		City sibiu = new City("sibiu", null);
		City timisoara = new City("timisoara", null);
		City urziceni = new City("urziceni", null);
		City vaslui = new City("vaslui", null);
		City zerind = new City("zerind", null);

		arad.neighbors = new City[] { zerind, timisoara, sibiu };
		bucharest.neighbors = new City[] { urziceni, pitesti, giurgiu, fagaras };
		craiova.neighbors = new City[] { rimnicu_vilcea, pitesti, dobreta };
		dobreta.neighbors = new City[] { mehadia, craiova };
		eforie.neighbors = new City[] { hirsova };
		fagaras.neighbors = new City[] { sibiu, bucharest };
		giurgiu.neighbors = new City[] { bucharest };
		hirsova.neighbors = new City[] { urziceni, eforie };
		iasi.neighbors = new City[] { vaslui, neamt };
		lugoj.neighbors = new City[] { timisoara, mehadia };
		mehadia.neighbors = new City[] { lugoj, dobreta };
		neamt.neighbors = new City[] { iasi };
		oradea.neighbors = new City[] { zerind, sibiu };
		pitesti.neighbors = new City[] { rimnicu_vilcea, craiova, bucharest };
		rimnicu_vilcea.neighbors = new City[] { sibiu, pitesti, craiova };
		sibiu.neighbors = new City[] { rimnicu_vilcea, oradea, fagaras, arad };
		timisoara.neighbors = new City[] { lugoj, arad };
		urziceni.neighbors = new City[] { vaslui, hirsova, bucharest };
		vaslui.neighbors = new City[] { urziceni, iasi };
		zerind.neighbors = new City[] { oradea, arad };

		cityMap.put("arad", arad);
		cityMap.put("bucharest", bucharest);
		cityMap.put("craiova", craiova);
		cityMap.put("dobreta", dobreta);
		cityMap.put("eforie", eforie);
		cityMap.put("fagaras", fagaras);
		cityMap.put("giurgiu", giurgiu);
		cityMap.put("hirsova", hirsova);
		cityMap.put("iasi", iasi);
		cityMap.put("lugoj", lugoj);
		cityMap.put("mehadia", mehadia);
		cityMap.put("neamt", neamt);
		cityMap.put("oradea", oradea);
		cityMap.put("pitesti", pitesti);
		cityMap.put("rimnicu_vilcea", rimnicu_vilcea);
		cityMap.put("sibiu", sibiu);
		cityMap.put("timisoara", timisoara);
		cityMap.put("urziceni", urziceni);
		cityMap.put("vaslui", vaslui);
		cityMap.put("zerind", zerind);

	}
}
