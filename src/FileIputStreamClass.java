import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class FileIputStreamClass implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public void ReadObjectsFromFile() throws IOException {
		
		FileInputStream fileStream = new FileInputStream("C:\\Users\\dell\\Documents\\Orders.html");
		ObjectInputStream ois = new ObjectInputStream(fileStream);
		File file = new File("OrdersSaved.html");
		FileWriter fw = new FileWriter(file, true);
		
		while(fileStream.available() != 0) {
			Object obj = null;
			try {
				obj = ois.readObject();
				if(obj != null) {
					fw.write("Order date: " + ((Order) obj).getOrderDate());
					fw.write("Collection date: " + ((Order) obj).getCollectionDate());
				}else {
					fw.close();
					ois.close();
					fileStream.close();
				}
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}		
	}
	
}