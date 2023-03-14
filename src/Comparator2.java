import java.util.Date;

public class Comparator2 {
	
	static class SortOrderByDate extends Comparator2{
		public int compare(Order a, Order b) {
			return a.getOrderDate().compareTo(b.getOrderDate());
		}
	}
	
	static class SortByCollectionDate extends Comparator2{
		public int compare(Order a, Order b) {
			return a.getCollectionDate().compareTo(b.getCollectionDate());
		}
	}
	
	static class SortBySurname extends Comparator2{
		public int compare(Order a, Order b) {
			return a.getSurname().compareTo(b.getSurname());
		}
	}	
}