import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try {
			ArrayList<Order> myList = new ArrayList<Order>();
			Order order = null;
			String text = "";
			Scanner in = new Scanner(new File("C:\\Please Work\\Orders.txt"));
			
			FileWriter fw = new FileWriter("SavedOrders.html", true);
			int doCount = 0;
			while(in.hasNext()) {
				text = in.nextLine();
				
				doCount++;
				
				String[] breakString = text.split(",");
				
				String date1 = breakString[0];
				String date2 = breakString[1];
				int donuts = Integer.parseInt(breakString[3]);
				String[] fullNamesArray = breakString[2].split(" ");
				String name = fullNamesArray[0];
				String surname = fullNamesArray[1];
				
				Topping top = new Topping();
				String results = top.calculateTopping(donuts);
				
				Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(date1);
				Date date4 = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
								
				order = new Order(date3, date4, name, surname, donuts, results);
				
				myList.add(order);
				
				fw.write(doCount + ". Order Date: " + date1 + "<br>Collection Date: " + date2 
						+ "<br>First Name: " + name + "<br>Last Name: " + surname + 
						"<br>Doughnuts' Quantity: " + donuts + "<br>Topping Type: " + results + "<br><br>");
			}
			
			String location = "SavedOrders.html";
			response.sendRedirect(location);
			fw.close();
			in.close();
			
		}catch(FileNotFoundException e) {
			out.println("The file mentioned wasn't found: " + e);
		}catch(IOException e) {
			out.println("An error occurred: " + e);			
		}catch(Exception e) {
			out.println("An error occurred: " + e);
		}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}