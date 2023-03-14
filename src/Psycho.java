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

@WebServlet("/Psycho")
public class Psycho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ArrayList<Order> myList = new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 		 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String buttonCheck = request.getParameter("formSubmit");
		String buttonCheck1 = request.getParameter("formVew");
		
		if("Submit Query".equalsIgnoreCase(buttonCheck)) {
			
			String date1 = request.getParameter("future");
			String date2 = request.getParameter("partyNextDoor");
			String name = request.getParameter("firstName");
			String surname = request.getParameter("lastName");
			int doughnuts = 0;
						
			Date orderDate = null;
			Date collectionDate = null;
			Date date = new Date();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String todayDate = sdf.format(date);
			try {
				doughnuts = Integer.parseInt(request.getParameter("donuts"));
				orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
				collectionDate = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
			}catch(NumberFormatException e) {
				out.println("There was an error parsing String to Int");
			}catch(ParseException e) {
				out.println("There was an error parsing String to Date");
			}
			
			if(date1.compareTo(date2) > 0 || date1.compareTo(todayDate) < 0) {
				out.println("<h4>Order date is supposed to be today going forth, not in the past.</h4>");
//				return;
			}else if(date2.compareTo(todayDate) < 0) {
				out.println("<h4>Your collection date should be in the future, not in the past.</h4>");
//				return;
			}
			
			
			if(name == null ||  name == " ") {
				out.println("<h4>Username is required.</h4>");
//				return;
			}else if(name.length() <= 3) {
				out.println("<h4>Username is too short.</h4>");
//				return;
			}
				
			if(surname == null || name == " ") {
				out.println("<h4>User's lastname is required.</h4>");
//				return;
			}else if(surname.length() <= 5) {
				out.println("<h4>User's lastname is too short.</h4>");
//				return;
			}
			
			if(doughnuts == 0) {
				out.println("<h4>You didn't mention the number of doughnuts needed.</h4>");
//				return;
			}else if(doughnuts < 0) {
				out.println("<h4>Please order more than 1 doughnut.</h4>");
//				return;
			}
			
			Topping top = new Topping();
			String results = top.calculateTopping(doughnuts);
						
			Order order = new Order(orderDate, collectionDate, name, surname, doughnuts, results);
			
			myList.add(order);
			
			RequestDispatcher session = request.getRequestDispatcher("index.html");
			session.include(request, response);
			
		}else if("View Orders".equalsIgnoreCase(buttonCheck1)) {
			
			RequestDispatcher session = request.getRequestDispatcher("index.html");
			session.include(request, response);
			
			List<Order> mySortedList = myList.stream().sorted(Comparator.comparing(Order::getOrderDate)).collect(Collectors.toList());
			mySortedList = myList.stream().sorted(Comparator.comparing(Order::getCollectionDate)).collect(Collectors.toList());
			mySortedList = myList.stream().sorted(Comparator.comparing(Order::getSurname)).collect(Collectors.toList());
			
			out.println("<table align='center' border='5px' style='padding: 5px; font-family: Courier; font-size: 110%; color: Steelblue'><tr><td>");
			out.println("Order Date</td><td>Collection Date</td><td>First Name</td><td>Last Name</td><td>Doughnuts Ordered</td><td>Topping Type</td></tr><tr><td>");
			for(Order values : mySortedList) {
				
				out.println(values.getOrderDate()+ "</td><td>" + values.getCollectionDate() + "</td><td>" + values.getName() + 
						"</td><td>" + values.getSurname() + "</td><td>" + values.getDonutOrdered() 
						+ "</td></tr>");
			}
			out.println("</table>");
		}
	}
}