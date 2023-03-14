import java.util.Date;
import java.text.SimpleDateFormat;

public class Order {
	
	private Date orderDate;
	private Date collectionDate;
	private String userFirstName;
	private String userLastName;
	private int donutOrdered;
	
	public Order(java.util.Date orderDate, java.util.Date collectionDate, String userFirstName, String userLastName, int donutOrdered) {
		
		this.orderDate = orderDate;
		this.collectionDate = collectionDate;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.donutOrdered = donutOrdered;
		
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getCollectionDate() {
		return orderDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	} 
	
	public String getName() {
		return userFirstName;
	}
	public void setName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	public String getSurname() {
		return userLastName;
	}
	public void setSurname(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public int getDonutOrdered() {
		return donutOrdered;
	}
	public void setDonutOrdered(int donutOrdered) {
		this.donutOrdered = donutOrdered;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = sdf.format(collectionDate);
		return date1 + "," + userFirstName + " " + userLastName + "," + donutOrdered;
	}
	
}