package noble;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class TitleGenerator {

	public static void main(String[] args) throws Exception {

		PropertyReader carProperties = new PropertyReader("car");
		PropertyReader bikeProperties = new PropertyReader("bike");

		carProperties.getValues().stream().forEach(System.out::println);
		Collection<Object> allCars = carProperties.getValues();
		// Collection<Object> allBikes = bikeProperties.getValues();
		List<Object> allVehicles = new ArrayList<Object>();
		// allVehicles.addAll(allBikes);
		allVehicles.addAll(allCars);

		String title = "S Line Red 3D Metal Sports Sticker Emblem";

		createFile(allVehicles, title);
		System.out.println("Job Done for " + title);
	}

	private static void createFile(Collection<Object> allKeys, String title)
			throws Exception, FileNotFoundException {
		try (PrintWriter writer = new PrintWriter("E:\\" + title + ".txt");) {

			for (Object obj : allKeys) {

				String newTitle = (title + " " + obj);
				newTitle = newTitle.replaceAll("  ", " ");
				newTitle = newTitle.trim();
				// Split
				if (newTitle.length() < 79) {
					writer.println(newTitle);
				} else {
					System.out.println(" ERROR for " + newTitle);

					throw new Exception("Title has to be shorter" + newTitle);
				}
			}
		}
	}
}

/*
 * 
 * package noble;
 * 
 * import java.io.FileNotFoundException; import java.io.IOException; import
 * java.io.InputStream; import java.util.Collection; import
 * java.util.Properties; import java.util.Set;
 */

class PropertyReader {

	private Properties prop = null;

	public PropertyReader(String name) {

		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass()
					.getResourceAsStream("/" + name + ".properties");
			if (is != null)
				prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Set<Object> getAllKeys() {
		Set<Object> keys = prop.keySet();
		return keys;
	}

	public Collection<Object> getValues() {
		Collection<Object> values = prop.values();
		return values;
	}

	public String getPropertyValue(String key) {
		return this.prop.getProperty(key);
	}

	/*
	 * public static void main(String a[]){
	 * 
	 * PropertyReader mpc = new PropertyReader("car"); Set<Object> keys =
	 * mpc.getAllKeys(); for(Object k:keys){ String key = (String)k;
	 * //System.out.println(key+": "+mpc.getPropertyValue(key)); } }
	 */
}