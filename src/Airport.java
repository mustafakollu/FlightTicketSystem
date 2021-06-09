import java.util.ArrayList;
import java.util.Scanner;

public class Airport {						//this class holds Airport list's member

	private String city;
	private String airportAlias;					
	private int id;
	private int aliasId;
	
	public Airport(String city, String airportAlias, int id, int aliasId) {
		super();
		this.city = city;								//constructor
		this.airportAlias = airportAlias;
		this.id = id;
		this.aliasId=aliasId;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {					//Getter Setter
		this.city = city;
	}
	public String getAirportAlias() {
		return airportAlias;
	}
	public void setAirportAlias(String airportAlias) {
		this.airportAlias = airportAlias;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAliasId() {
		return aliasId;
	}

	public void setAliasId(int aliasId) {
		this.aliasId = aliasId;
	}

	public static void readAirport(Scanner path, ArrayList<Airport> array) {		//read airportList.txt
		int counter=0;
		int counter2=0;
		String temp="";
		while(path.hasNextLine()==true) {
			String l1 = path.nextLine();
			String[] arr=l1.split("	");
			for(int i=1;i<arr.length;i++) {
				if(temp.equals(arr[0])) {
					array.add(new Airport(arr[0],arr[i],--counter,counter2));
					counter++;
					counter2++;
				}
				else {
					temp=arr[0];
					array.add(new Airport(arr[0],arr[i],counter,counter2));
					counter++;
					counter2++;
				}
			}
		}
	}
	
}
