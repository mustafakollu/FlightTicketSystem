import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Flight {							//this class holds Flight list's member
	private String flightId;
	private String flightName;
	private int cityId;
	private int cityId2;
	private String realDept;
	private int dept;
	private String realArr;
	private int arr;
	private Date date;
	private String realDate;
	private String duration;
	private int minutes;
	private int price;
	
	public Flight(String flightId, String flightName, int cityId,int cityId2, String realDept, int dept, String realArr, int arr, Date date,
			String realDate, String duration, int minutes, int price) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.cityId = cityId;
		this.cityId2 = cityId2;
		this.realDept = realDept;							// Constructor
		this.dept = dept;
		this.realArr = realArr;
		this.arr = arr;
		this.date = date;
		this.realDate = realDate;
		this.duration = duration;
		this.minutes = minutes;
		this.price = price;
	}
	
	public int getCityId2() {
		return cityId2;
	}

	public void setCityId2(int cityId2) {
		this.cityId2 = cityId2;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {							//Getter Setter
		this.cityId = cityId;
	}

	public String getRealDept() {
		return realDept;
	}

	public void setRealDept(String realDept) {
		this.realDept = realDept;
	}

	public String getRealArr() {
		return realArr;
	}

	public void setRealArr(String realArr) {
		this.realArr = realArr;
	}

	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public int getArr() {
		return arr;
	}
	public void setArr(int arr) {
		this.arr = arr;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRealDate() {
		return realDate;
	}
	public void setRealDate(String realDate) {
		this.realDate = realDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public static void readFlight(Scanner path, ArrayList<Flight> array) throws FileNotFoundException, ParseException {
		
		ArrayList<Airport> arrayAirport2 = new ArrayList<>();
		File f = new File("airportList.txt");							//read airportList.txt
		Scanner airportList = new Scanner(f);
		Airport.readAirport(airportList,arrayAirport2);
		
		while(path.hasNextLine()==true) {
			String l1 = path.nextLine();
			String[] arr=l1.split("	");
			String[] arr1=arr[0].split("");
			String[] arr2=arr[1].split("->");
			String temp=arr1[0]+arr1[1];
			String[] arr3=arr[3].split(":");
			String[] arr4= arr[2].split(" ");
			String date2 = arr4[0]+" "+arr4[1];
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date inputDate = dateFormat.parse(date2);
			int time= Integer.parseInt(arr3[0])*60+Integer.parseInt(arr3[1]);
			String realArr=arr2[1];
			String realDept=arr2[0];
			int city=0;
			int city2=0;
			for(Airport emk:arrayAirport2) {
				if(arr2[0].equals(emk.getAirportAlias())) {
					city=emk.getId();
					arr2[0]=Integer.toString(emk.getAliasId());
				}
				else if(arr2[1].equals(emk.getAirportAlias())) {
					city2=emk.getId();
					arr2[1]=Integer.toString(emk.getAliasId());
				}
			}
			
			array.add(new Flight(temp,arr[0],city,city2,realDept,Integer.parseInt(arr2[0]),realArr,Integer.parseInt(arr2[1]),inputDate,arr[2],arr[3],time,Integer.parseInt(arr[4])));
		}
	}
	private int v;												//vertices

	private static ArrayList<Integer>[] adjList;				//adjacency list

	public Flight(int vertices){
		this.v = vertices;										//constructor	
		initAdjList();
	}
	
	@SuppressWarnings("unchecked")
	private void initAdjList(){									//adjacency list
		adjList = new ArrayList[v];
		for (int i = 0; i < v; i++) {
			adjList[i] = new ArrayList<>();
		}
	}
	public void addEdge(int u, int v){							//add edge function
		adjList[u].add(v);
	}

	public void printAllPaths(int s, int d){					//create path list and call print function
		boolean[] isVisited = new boolean[v];
		ArrayList<Integer> pathList = new ArrayList<>();
		pathList.add(s);
		printAllPathsUtil(s, d, isVisited, pathList);
	}
	
	private static void printAllPathsUtil(Integer u, Integer d,boolean[] isVisited,List<Integer> localPathList){
		if (u.equals(d)) {
			System.out.println(localPathList);
			return;															//print possible path that departure to arrival
		}
		isVisited[u] = true;

		for (Integer i : adjList[u]) {
			if (!isVisited[i]) {
				localPathList.add(i);
				printAllPathsUtil(i, d, isVisited, localPathList);
				localPathList.remove(i);
			}
		}
		isVisited[u] = false;
	}
	public static void readPath(Scanner path, ArrayList<String> array) {				//read path.txt function
		while(path.hasNextLine()==true) {
			String l1 = path.nextLine();
			String[] arr=l1.split("");
			String temp ="";
			for(int i=0;i<arr.length;i++) {
				if(arr[i].equals("[")||arr[i].equals("]")||arr[i].equals(" ")) {
				}
				else {
					temp+=arr[i];
				}
			}
			array.add(temp);
		}
	}
	
}
