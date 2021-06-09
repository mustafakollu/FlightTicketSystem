import java.util.Date;

public class listAll {
	private String all;
	private Date firstDate;
	private Date lastDate;						//this class holds list All's member and prober list's member
	private String cityId;
	private String flightId;
	private int duration;
	private int price;
	
	public listAll(String all, Date firstDate, Date lastDate, String cityId, String flightId, int duration, int price) {
		super();
		this.all = all;
		this.firstDate = firstDate;
		this.lastDate = lastDate;				//constructor
		this.cityId = cityId;
		this.flightId = flightId;
		this.duration = duration;
		this.price = price;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public Date getFirstDate() {
		return firstDate;								//Getter Setter
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
