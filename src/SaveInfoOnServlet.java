import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveInfoOnServlet")
public class SaveInfoOnServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
	ArrayList<Order> myList = new ArrayList<Order>();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("Hello World");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		System.out.println(request.getParameter("SubmitQuery"));
		System.out.println(request.getParameter("resetData"));
		
		if(request.getParameter("SubmitQuery") != null) {
	
			System.out.println("Order date: ");
			
			String orderDate = request.getParameter("date1");
			String collectionDate = request.getParameter("date2");
			String name = request.getParameter("name");
			String surname = request.getParameter("lname");
			String donuts = request.getParameter("donuts");
			int doughnuts = 0;
				
			System.out.println("Order date: " + orderDate);
			System.out.println("Collection date: " + collectionDate);
			System.out.println("Name: " + name);
			System.out.println("Surname: " + surname);
			System.out.println("Doughnuts: " + donuts + "\n");
			
			RequestDispatcher req = request.getRequestDispatcher("index.html");
			req.include(request, response);
			
			try{
				doughnuts = Integer.parseInt(donuts);
			}catch(NumberFormatException e) {
				out.println("There was an error while parsing your donuts' quantity to an integer");
			}
			String results = "";
				
			Topping top = new Topping();
			results = top.calculateTopping(doughnuts);
					
			try {
			
				Date date1 = new SimpleDateFormat("dd/MM/yy").parse(orderDate);
				Date date2 = new SimpleDateFormat("dd/MM/yy").parse(collectionDate);
				Date date = new Date();
					
				if(date1.compareTo(date2) > 0) {
					out.println("<script>alert('Order date is supposed to occur before the future.');</script>");
					return;
				}else if(date1.compareTo(date) < 0) {
					out.println("<script>alert('Order date is supposed to be today going forth, not in the past.');</script>");
					return;
				} else if(date2.compareTo(date) < 0) {
					out.println("<script>alert('Your collection date should be in the future, not in the past.');</script>");
					return;
				}
				
				int var1 = date1.compareTo(date);
				int var2 = date2.compareTo(date);
				int var3 = date2.compareTo(date1);
				
				if(var1 < 0) {
					out.println("<script>alert('Your order date should be today or in the future, not in the past.');</script>");
					return;
				}else if(var2 < 0) {
					out.println("<script>alert('Your collection date should be in the future, not in the past.');</script>");
					return;
				}else if(var3 < 0) {
					out.println("<script>alert('Your order date occurs after your collection date.');</script>");
					return;
				}
				
				ServletFormValidation sfv = new ServletFormValidation();
				
				if(name == null ||  name == " ") {
					out.println("<script>alert('Username is required.');</script>");
					return;
				}else if(name.length() <= 3) {
					out.println("<script>alert('Username is too short.');</script>");
					return;
				}else if(!sfv.hasSpecialCharacters(name.trim())) {
					out.println("<script>alert('There's a special character in your name string.');</script>");
					return;
				}
					
				if(surname == null || surname == " ") {
					out.println("<script>alert('User's lastname is required.');</script>");
					return;
				}else if(surname.length() <= 3) {
					out.println("<h4>alert('User's lastname is too short.');</script>");
					return;
				}else if(!sfv.hasSpecialCharacters(surname.trim())) {
					out.println("<script>alert('There's a special character in your surname string.');</script>");
					return;
				}
				
				if(doughnuts == 0) {
					out.println("<script>alert('You didn't mention the number of doughnuts needed.');</script>");
					return;
				}else if(doughnuts < 0) {
					out.println("<script>alert('Please order more than 1 doughnut.');</script>");
					return;
				}
					
				Order order = new Order(date1, date2, name, surname, doughnuts, results);
				
				myList.add(order);
									
				}catch(ParseException e) {
					out.println("<script>alert('There was an error while parsing your String to a Date');</script>");
				}catch(NumberFormatException fnfe){
					out.println("<script>alert('The number of doughnuts entered isn't a number!');</script>");
				}catch(Exception e) {
					out.println("<script>alert('An error occurred: " + e + "');</script>");
				}
			}
			
			else if(request.getParameter("ViewOrders") != null) {
												
				RequestDispatcher req = request.getRequestDispatcher("index.html");
				req.include(request, response);
				
				List<Order> mySortedList = myList.stream().sorted(Comparator.comparing(Order::getOrderDate)).collect(Collectors.toList());
				mySortedList = myList.stream().sorted(Comparator.comparing(Order::getCollectionDate)).collect(Collectors.toList());
				mySortedList = myList.stream().sorted(Comparator.comparing(Order::getSurname)).collect(Collectors.toList());
				
				out.println("<table align='center' border='3px' style='text-align: center; padding: 5px; font-family: Georgia; font-size: 16px; color: Black'><tr><td>");
				out.println("Order Date</td><td>Collection Date</td><td>First Name</td><td>Last Name</td><td>Doughnuts Ordered</td><td>Topping Type</td></tr>");
				for(Order values : mySortedList) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
					
					String date1 = sdf.format(values.getOrderDate());
					String date2 = sdf.format(values.getCollectionDate());
					
					out.println("<tr><td>" + date1 + "</td><td>" + date2 + "</td><td>" + values.getName() + 
							"</td><td>" + values.getSurname() + "</td><td>" + values.getDonutOrdered() 
							+ "</td><td>" + values.getResults() + "</td></tr>");
				}
				out.println("</table>");
			}else {
				System.out.println("Submit Query is null");
			}
	}
}