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
public class Consumer implements Runnable {

    private final List<Integer> burritos;

    public Consumer(List<Integer> sharedQueue) {
        this.burritos = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (burritos) {
            while (burritos.isEmpty()) {
                System.out.println("El plato esta vacio " + Thread.currentThread().getName() + " está esperando. Actualmente hay: " + burritos.size()+" burritos.");
                burritos.wait();
            }
            Thread.sleep(1000);
            int i = (Integer) burritos.remove(0);
            System.out.println(Thread.currentThread().getName()+" ha consumido un burrito. Ahora hay: " +burritos.size());
            burritos.notifyAll();
        }
    }
}
