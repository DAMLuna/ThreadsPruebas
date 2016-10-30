/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebathreads;

import java.util.List;

/**
 *
 * @author Kerinvel
 */
public class Producer implements Runnable {

    private final List<Integer> burritos;
    private final int capacidadPlato;
    
    public Producer(List<Integer> sharedQueue, int size) {
        this.burritos = sharedQueue;
        this.capacidadPlato = size;
    }
    
    @Override
    public void run() {
        int counter = 1;
        while (true) {
            try {
                cocina(counter++);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void cocina(int i) throws InterruptedException {
        synchronized (burritos) {
            while (burritos.size() == capacidadPlato) {
                System.out.println("El plato está lleno " + Thread.currentThread().getName() + " está esperando. Actualmente hay: " + burritos.size()+" burritos.");
                burritos.wait();
            }

            Thread.sleep(2000);
            burritos.add(i);
            System.out.println(Thread.currentThread().getName() + " ha hecho un burrito más. Hay: " + burritos.size()+ " burritos.");
            burritos.notifyAll();
        }
    }

}
