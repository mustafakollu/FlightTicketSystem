import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Program {          //all function works here
	
	public static void work(String argument,String argument2,String argument3) throws FileNotFoundException, ParseException{
		ArrayList<Airport> arrayAirport = new ArrayList<>();
		File f = new File(argument);							//read airportList.txt
		Scanner airportList = new Scanner(f);
		Airport.readAirport(airportList,arrayAirport);
		
		ArrayList<Command> arrayCommand = new ArrayList<>();
		File g = new File(argument3);							//read commandList.txt
		Scanner commandList = new Scanner(g);
		Command.readCommand(commandList,arrayCommand);
		
		ArrayList<Flight> arrayFlight = new ArrayList<>();
		File h = new File(argument2);							//read flightList.txt
		Scanner flightList = new Scanner(h);
		Flight.readFlight(flightList,arrayFlight);
		
		ArrayList<String> arrayPath = new ArrayList<>();
		ArrayList<listAll> arrayListAll = new ArrayList<>();		//some arrayList that keeps ListAll and ListProper
		ArrayList<listAll> arrayProper = new ArrayList<>();
		ArrayList<listAll> arrayDiameter = new ArrayList<>();
		
		PrintStream outputX = new PrintStream("output.txt");		//object of what function helps to write Sout on output.txt
		PrintStream pathTxt = new PrintStream("Path.txt");			//object of what function helps to write paths on path.txt
		System.setOut(outputX);
		
		int vect =0;
		for(Airport emk:arrayAirport) {								//gives number to adjacency list's vertices
			vect=emk.getAliasId()+1;
		}
		for(Command emk:arrayCommand) {								//command function runs here
			if(emk.getCommand().equals("listAll")) {				
				Flight gra = new Flight(vect);
				for(Flight emf: arrayFlight) {
					gra.addEdge(emf.getDept(), emf.getArr());
				}															//give vertices to graph
				
				System.setOut(pathTxt);
				String[] source = emk.getDeptAirportAlias().split(",");
				String[] destination = emk.getArrAirportAlias().split(",");
				for(int y=0;y<source.length;y++) {
					for(int r=0;r<destination.length;r++) {								//create new file that holdings path
						gra.printAllPaths(Integer.parseInt(source[y]), Integer.parseInt(destination[r]));
					}
				}
				
				File j = new File("Path.txt");							//read path.txt
				Scanner pathList = new Scanner(j);
				Flight.readPath(pathList,arrayPath);
				System.setOut(outputX);
				System.out.println(emk.getCommandAll());
				for(String emp: arrayPath) {
					String[] arr=emp.split(",");
					int price=0;
					int duration=0;
					String temp="";
					String temp2="";
					String temp3="";
					Date date1 = null;
					Date date2 = null;
					Date date3 = null;
					boolean bool = true;
					for(int i=0;i<arr.length;i++) {							//prepare list All requirement
						if(i==0) {
							for(Flight ems: arrayFlight) {
								if(ems.getDept()==Integer.parseInt(arr[i])&&ems.getArr()==Integer.parseInt(arr[i+1])) {
									temp2+=ems.getFlightId();
									temp3+=Integer.toString(ems.getCityId());
									temp3+=","+Integer.toString(ems.getCityId2());
									price+=ems.getPrice();
									date1 = ems.getDate();
									date2 = new Date(date1.getTime() + (1000*60*ems.getMinutes()));
									temp+=ems.getFlightName()+"	"+ems.getRealDept()+"->"+ems.getRealArr();
								}
							}
						}
						else if(arr.length>i+1){
							for(Flight ems: arrayFlight) {
								if(ems.getDept()==Integer.parseInt(arr[i])&&ems.getArr()==Integer.parseInt(arr[i+1])) {
									temp2+=","+ems.getFlightId();
									temp3+=","+Integer.toString(ems.getCityId2());
									temp+="||";
									date3= ems.getDate();
									if(date2.after(date3)) {
										bool=false;
									}
									date2 = new Date(date3.getTime() + (1000*60*ems.getMinutes()));
									price+=ems.getPrice();
									temp+=ems.getFlightName()+"	"+ems.getRealDept()+"->"+ems.getRealArr();
									break;
									
								}
							}
						}
						else {
							duration=(int) (date2.getTime()-date1.getTime())/60000;
							String hours="";
							String minute="";
							if(duration/60<10) {
								hours = "0"+Integer.toString(duration/60);
							}
							else {
								hours = Integer.toString(duration/60);
							}
							if(duration%60<10) {
								minute = "0"+Integer.toString(duration%60);
							}
							else {
								minute = Integer.toString(duration%60);
							}
							String time = hours+":"+minute;
							temp+="	"+time+"/"+price;	
						}
						
					}
					if(bool) {					//finally create list All
						arrayListAll.add(new listAll(temp, date1, date2, temp3, temp2, duration, price));
						
					}
				}
				for(listAll emg:arrayListAll) {
					boolean bool1=false;
					boolean bool2=true;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					String[] gecici = emg.getCityId().split(",");
					for(int k=0;k<gecici.length;k++) {
						for(int l=0;l<gecici.length;l++) {
							if(gecici[k].equals(gecici[l])&&k!=l) {
								bool2=false;
							}											//check list all what flight possible
						}
					}
					if(bool1&&bool2) {
						System.out.println(emg.getAll());				//print possible flight
					}
				}
				System.out.println();
				System.out.println();
			}
			
			else if(emk.getCommand().equals("listProper")) {			//prepare prober list
				System.out.println(emk.getCommandAll());
				
				int price = 1000000;
				int duration = 1000000;
				for(listAll emg:arrayListAll) {
					price=emg.getPrice();
					duration=emg.getDuration();
					break;
				}
				for(listAll emg:arrayListAll) {
					for(listAll emh:arrayListAll) {
						if(emg.getDuration()<emh.getDuration()&&emg.getPrice()<emh.getPrice()&&emg.getDuration()<duration&&emg.getPrice()<price) {
							price=emg.getPrice();
							duration=emg.getDuration();
						}
					}
				}
				for(listAll emg:arrayListAll) {
					boolean bool1=false;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					if((emg.getDuration()<=duration||emg.getPrice()<price)&&(emg.getDuration()<duration||emg.getPrice()<=price&&bool1)) {
						System.out.println(emg.getAll());
						arrayProper.add(new listAll(emg.getAll(), emg.getFirstDate(), emg.getLastDate(), emg.getCityId(), emg.getFlightId(), emg.getDuration(), emg.getPrice()));
					}						//create prober list and print
				}
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("listCheapest")) {
				System.out.println(emk.getCommandAll());
				int temp=1000000;
				for(listAll emg:arrayListAll) {
					for(listAll emh:arrayListAll) {
						if(emg.getPrice()<emh.getPrice()&&emg.getPrice()<temp) {
							temp=emg.getPrice();
						}
					}
				}															//print most cheapest flight(s) 
				for(listAll emg:arrayListAll) {
					boolean bool1=false;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					if(emg.getPrice()==temp&&bool1) {
						System.out.println(emg.getAll());
					}
				}
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("listQuickest")) {
				System.out.println(emk.getCommandAll());
				int temp=10000000;
				for(listAll emg:arrayListAll) {
					for(listAll emh:arrayListAll) {
						if(emg.getDuration()<emh.getDuration()&&emg.getDuration()<temp) {
							temp=emg.getDuration();
						}
					}
				}
				for(listAll emg:arrayListAll) {							//print most quickest flight(s) 
					boolean bool1=false;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					if(emg.getDuration()==temp&&bool1) {
						System.out.println(emg.getAll());
					}
				}
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("listCheaper")) {
				System.out.println(emk.getCommandAll());
				int counter=0;
				for(listAll emg:arrayProper) {			//The function that finds flights cheaper than the given price
					boolean bool1=false;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					if(emg.getPrice()<Integer.parseInt(emk.getExtra())&&bool1) {
						counter++;
						System.out.println(emg.getAll());
					}
					else if(counter==0){
						System.out.println("No suitable flight plan is found");
					}
				}
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("listQuicker")) {
				System.out.println(emk.getCommandAll());		//The function that finds flights quicker than the given price
				for(listAll emg:arrayProper) {
					boolean bool1=false;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate2 = dateFormat2.parse(emk.getExtra());
					if(emg.getLastDate().before(inputDate2)&&bool1) {
						System.out.println(emg.getAll());
					}
					else {
						System.out.println("No suitable flight plan is found");
					}
				}
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("listExcluding")) {
				System.out.println(emk.getCommandAll());		//The function that finds flights do not excluding the given company
				int counter =0;
				for(listAll emg:arrayProper) {
					boolean bool1=false;
					boolean bool2=true;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					String[] gecici = emg.getFlightId().split(",");
					for(int k=0;k<gecici.length;k++) {
						if(gecici[k].equals(emk.getExtra())) {
							bool2=false;
						}
						
					}
					if(bool1&&bool2) {
						System.out.println(emg.getAll());
						counter++;
					}
					else if(counter==0){
						System.out.println("No suitable flight plan is found");
					}
				}
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("listOnlyFrom")) {
				System.out.println(emk.getCommandAll());
				int counter =0;
				for(listAll emg:arrayProper) {		//The function that finds flights what only excluding the given company
					boolean bool1=false;
					boolean bool2=true;
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date inputDate = dateFormat1.parse(emk.getStartDate()+" "+"00:00");
					if(emg.getFirstDate().after(inputDate)) {
						bool1=true;
					}
					String[] gecici = emg.getFlightId().split(",");
					for(int k=0;k<gecici.length;k++) {
						if(!gecici[k].equals(emk.getExtra())) {
							bool2=false;
						}
					}
					if(bool1&&bool2) {
						System.out.println(emg.getAll());
						counter++;
					}
					else if(counter==0){
						System.out.println("No suitable flight plan is found");
					}
				}
				System.out.println();
				System.out.println();
			}
			
			else if(emk.getCommand().equals("diameterOfGraph")) {
				System.out.println(emk.getCommandAll());
				System.out.println("Not implemented");
				System.out.println();
				System.out.println();
			}
			else if(emk.getCommand().equals("pageRankOfNodes")) {
				System.out.println(emk.getCommandAll());
				System.out.println("Not implemented");
				System.out.println();
				System.out.println();
			}
		}
	}
}
