public class Topping {
    int total;

    public String calculateTopping(int donut){
        int sprinkle = 0, cream = 0, jam = 0, cinnamon = 0, secretTopping = 0;

        for(int i = 1; i <= donut; i++){
            total++;
            if(total % 100 == 0){
                secretTopping++;
            }else if(total % 15 == 0){
                sprinkle++;
            }else if(total % 5 == 0){
                cream++;
            }else if(total % 3 == 0){
                jam++;
            }else{
                cinnamon++;
            }
        }
        return cinnamon  + ", " + jam + ", " + cream + ", " + sprinkle;
    }
}