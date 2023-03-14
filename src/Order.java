import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Order implements Serializable {
			
	private static final long serialVersionUID = 1L;
	
	private Date orderDate;
	private Date collectionDate;
	private String userFirstName;
	private String userLastName;
	private int donutOrdered;
	private String results;
	
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
	
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	
	public Order(Date orderDate, Date collectionDate, String userFirstName, String userLastName, int donutOrdered, String results) {
		
		this.orderDate = orderDate;
		this.collectionDate = collectionDate;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.donutOrdered = donutOrdered;
		this.results = results;
		
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = sdf.format(collectionDate);
		String date2 = sdf.format(orderDate);
		return "Order Date: " + date2 + "<br> " + "CollectionDate: " + date1 
				+ "<br>" + "Full Names: " + userFirstName + " " + userLastName 
				+ "<br>" + "Doughnuts Ordered: " + donutOrdered + "<br>Topping Types: " + results;
	}
	
}