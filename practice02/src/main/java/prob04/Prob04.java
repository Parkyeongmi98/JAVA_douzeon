package prob04;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Prob04 {

   public static void main(String[] args) {
      char[] c1 = reverse( "Hello World" );
      printCharArray( c1 );
      
      char[] c2 = reverse( "Java Programming!" );
      printCharArray( c2 );
   }
   
   public static char[] reverse(String str) {
      /* 코드를 완성합니다 */
      StringBuffer sb = new StringBuffer(str);
      return sb.reverse().toString().toCharArray();
   }

   public static void printCharArray(char[] array){
      /* 코드를 완성합니다 */
      System.out.println(new String(array, 0, array.length).replaceAll(" ", ","));
   }
}