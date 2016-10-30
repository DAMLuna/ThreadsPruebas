/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebathreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kerinvel
 */
public class Pruebathreads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> burritos = new ArrayList<Integer>();
        int capacidadPlato = 10;
        System.out.println("Introduce el número de Cocineros que trabajan:");
        int nproducers = leerValorEntero();
        for (int i = 0; i < nproducers; i++) {
            Thread tProducer= new Thread(new Producer(burritos, capacidadPlato), "Cocinero"+(i+1)); 
            tProducer.start();
        }
        Thread tConsumer = new Thread(new Consumer(burritos), "Cliente");
        tConsumer.start();
        
    }
    public static int leerValorEntero() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}

