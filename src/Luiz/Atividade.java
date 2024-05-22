package Luiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Atividade {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(2);
        numeros.add(9);
        numeros.add(7);
        numeros.add(1);

        System.out.println("Elementos do array numeros:");
        for (int num : numeros) {
            System.out.println(num);
        }

        Collections.sort(numeros);
        System.out.println("Elementos do array numeros ordenados:");
        for (int num : numeros) {
            System.out.println(num);
        }

        int index = Collections.binarySearch(numeros, 9);
        System.out.println("Resultado da busca pelo número 9: " + index);


        ArrayList<Integer> maiores = new ArrayList<>();

        List<Integer> subList = numeros.subList(numeros.size() - 3, numeros.size());
        maiores.addAll(subList);
        System.out.println("Elementos do array maiores:");
        for (int num : maiores) {
            System.out.println(num);
        }

        boolean iguais = numeros.equals(maiores);

        if(iguais == true){
            System.out.println("Os arrays numeros e maiores são iguais!");

        }else{
            System.out.println("Os arrays numeros e maiores são diferentes!");
        }
        Collections.sort(numeros, Collections.reverseOrder());
        System.out.println("Elementos do array numeros em ordem decrescente:");
        for (int num : numeros) {
            System.out.println(num);
        }


    }
}
