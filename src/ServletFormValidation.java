public class ServletFormValidation {
	
	public boolean hasSpecialCharacters(String str) {
		
		for(int i = 0, j = str.length(); i < j; i++) {
			if(str.charAt(i) > 0 && str.charAt(i) < 65) {
				return false;
			}else if(str.charAt(i) > 90 && str.charAt(i) < 96) {
				return false;
			}
		}		
		return true;
	}	
}