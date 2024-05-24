import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeckendAtv {
    public static void main(String[] args) {
        ArrayList<Integer> numeros =new ArrayList<Integer>();
        numeros.addAll(Arrays.asList(5,2,9,7,1));
        System.out.print("Mostrando os números no laço de repetição FOR: ");
        for (int i = 0; i < numeros.size(); i++){
            System.out.print(numeros.get(i) + " ");
        }
        System.out.println();

        Collections.sort(numeros);
        System.out.println("Números ordenados: " + numeros);
        System.out.println("Indice do número 9: " + Collections.binarySearch(numeros,9));

        ArrayList<Integer> maiores = new ArrayList<Integer>();
        List<Integer> submaiores = numeros.subList(numeros.size()- 3,numeros.size());
        maiores.addAll(submaiores);
        System.out.println("Três maiores números: " + maiores);
        boolean iguais = numeros.equals(maiores);
        System.out.println("As Arrays são iguais? = " + iguais);
        Collections.sort(numeros, Collections.reverseOrder());
        System.out.println("Números em ordem decrescente: " + numeros);
    }
}
