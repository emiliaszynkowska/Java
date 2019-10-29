package comp1206.sushi.common;
import java.net.URL;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Postcode extends Model implements Serializable {

	private String name;
	private Double lat;
	private Double lon;
	private Map<String,Double> latLong;
	private Number distance;

	public Postcode(String code) {
		this.name = code;
		calculateLatLong();
	}

	public Postcode(String code, Postcode postcode) {
		this.name = code;
		calculateLatLong();
		calculateDistance(postcode);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Number getDistance() {
		return this.distance;
	}

	public Map<String,Double> getLatLong() {
		return this.latLong;
	}

	public void calculateDistance(Postcode destination) {
		destination.calculateLatLong();
		this.calculateLatLong();
		this.distance = distance(this.lat, destination.lat, this.lon, destination.lon);
	}

	//https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
	public static Double distance(Double lat1, Double lat2, Double lon1, Double lon2) {
		final int R = 6371;
		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		Double distance = R * c * 1000;
		return distance;
	}

	protected void calculateLatLong() {
		this.latLong = new HashMap<String,Double>();
		try {
			String name = this.getName();
			name = name.replace(" ", "");
			URL url = new URL("https://www.southampton.ac.uk/~ob1a12/postcode/postcode.php?postcode=" + name);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				inputLine = inputLine.replace("\"", "");
				inputLine = inputLine.replace(":", "");
				inputLine = inputLine.replace("{", "");
				inputLine = inputLine.replace("}", "");
				inputLine = inputLine.replace(",", "");

				String[] inputArray = inputLine.split("long");
				String[] latitudeArray = inputArray[0].split("lat");

				this.lat = Double.parseDouble(latitudeArray[1]);
				this.lon = Double.parseDouble(inputArray[1]);
				latLong.put("lat", lat);
				latLong.put("lon", lon);
			}
			reader.close();
		} catch (Exception e) {}

	}

}
