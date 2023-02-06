package prob5;

public class Prob5 {

   public static void main(String[] args) {
      for(int i=1;i<100;i++) {
         int temp = Integer.toString(i).replaceAll("[^369]","").length();
         if(temp>0) {
            System.out.println(i+" "+"짝".repeat(temp));
         }
      }      
   }
}