import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class execicio01 {
    public static void main(String[] args) {
        ArrayList<Integer>numeros = new ArrayList<Integer>();

        numeros.add(5);
        numeros.add(2);
        numeros.add(9);
        numeros.add(7);
        numeros.add(1);

        for(int num : numeros){
            System.out.println(num);            
        }

        System.out.println("Lista ordenada");
        //numeros.sort();
        Collections.sort(numeros);
        System.out.println(numeros);  

       
    List<Integer>maiores = numeros.subList(numeros.size() - 3, numeros.size());
    System.out.println(maiores);
    
    boolean iguais = numeros.equals(maiores);
    System.out.println(iguais);

    Collections.sort(numeros, Collections.reverseOrder());
    System.out.println(numeros);
   }  
    
}
