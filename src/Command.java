import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {								//this class holds Command list's member
	private String commandAll;
	private String deptAirportAlias;
	private String arrAirportAlias;
	private String command;
	private int dept;
	private int arr;
	private String startDate;
	private String extra;
	
	
	
	public Command(String commandAll, String command) {				//constructor just has command
		super();
		this.commandAll = commandAll;
		this.command = command;
	}



	public Command(String commandAll, String deptAirportAlias, String arrAirportAlias, String command, int dept,
			int arr, String startDate) {
		super();
		this.commandAll = commandAll;
		this.deptAirportAlias = deptAirportAlias;					//constructor has not excluding extra command
		this.arrAirportAlias = arrAirportAlias;
		this.command = command;
		this.dept = dept;
		this.arr = arr;
		this.startDate = startDate;
	}



	public Command(String commandAll, String deptAirportAlias, String arrAirportAlias, String command, int dept,
			int arr, String startDate, String extra) {
		super();
		this.commandAll = commandAll;								//constructor has excluding extra command
		this.deptAirportAlias = deptAirportAlias;
		this.arrAirportAlias = arrAirportAlias;
		this.command = command;
		this.dept = dept;
		this.arr = arr;
		this.startDate = startDate;
		this.extra = extra;
	}



	public String getDeptAirportAlias() {						//Getter Setter
		return deptAirportAlias;
	}
	public void setDeptAirportAlias(String deptAirportAlias) {
		this.deptAirportAlias = deptAirportAlias;
	}
	public String getArrAirportAlias() {
		return arrAirportAlias;
	}
	public void setArrAirportAlias(String arrAirportAlias) {
		this.arrAirportAlias = arrAirportAlias;
	}
	public String getCommandAll() {
		return commandAll;
	}

	public void setCommandAll(String commandAll) {
		this.commandAll = commandAll;
	}

	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public static void readCommand(Scanner path, ArrayList<Command> array) throws FileNotFoundException {
		
		ArrayList<Airport> arrayAirport1 = new ArrayList<>();
		File f = new File("airportList.txt");							//read command.txt
		Scanner airportList = new Scanner(f);
		Airport.readAirport(airportList,arrayAirport1);
		while(path.hasNextLine()==true) {
			String l1 = path.nextLine();
			String[] arr=l1.split("	");
			String command="command : "+l1;
			String deptAirportAlias="";
			String arrAirportAlias="";
			if(arr.length==3) {
				String[] arr2=arr[1].split("->");
				String[] arr3=arr[1].split("->");
				for(Airport emk:arrayAirport1) {
					if(arr2[0].equals(emk.getCity())) {
						arr2[0]=Integer.toString(emk.getId());
					}
					else if(arr2[1].equals(emk.getCity())) {
						arr2[1]=Integer.toString(emk.getId());
					}
				}
				int counter=0;
				int counter2=0;
				for(Airport emk:arrayAirport1) {
					if(arr3[0].equals(emk.getCity())) {
						if(counter==0) {
							deptAirportAlias+=Integer.toString(emk.getAliasId());
							counter++;
						}
						else {
							deptAirportAlias+=","+Integer.toString(emk.getAliasId());
						}
					}
					else if(arr3[1].equals(emk.getCity())) {
						if(counter2==0) {
							arrAirportAlias+=Integer.toString(emk.getAliasId());
							counter2++;
						}
						else {
							arrAirportAlias+=","+Integer.toString(emk.getAliasId());
						}
						
					}
				}
				array.add(new Command(command,deptAirportAlias,arrAirportAlias,arr[0],Integer.parseInt(arr2[0]),Integer.parseInt(arr2[1]),arr[2]));
			}
			else if(arr.length==4){
				String[] arr2=arr[1].split("->");
				for(Airport emk:arrayAirport1) {
					if(arr2[0].equals(emk.getCity())) {
						arr2[0]=Integer.toString(emk.getId());
					}
					else if(arr2[1].equals(emk.getCity())) {
						arr2[1]=Integer.toString(emk.getId());
					}
				}
				array.add(new Command(command,deptAirportAlias,arrAirportAlias,arr[0],Integer.parseInt(arr2[0]),Integer.parseInt(arr2[1]),arr[2],arr[3]));
			}
			else {
				array.add(new Command(command,arr[0]));
			}
				
		}
	}
	
	
	
}
