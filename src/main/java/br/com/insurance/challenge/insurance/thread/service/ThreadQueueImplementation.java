package br.com.insurance.challenge.insurance.thread.service;

import br.com.insurance.challenge.insurance.thread.domain.ThreadQeue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadQueueImplementation extends Thread {

    private static final ConcurrentLinkedQueue<ThreadQeue> stack_queue = new ConcurrentLinkedQueue<>();

    public static void add(ThreadQeue threadQeue) {
        stack_queue.add(threadQeue);
    }

    @Override
    public void run() {

        System.out.println("Fila rodando");

        while (true) {

            synchronized (stack_queue) { // bloquear o acesso a essa lista por outros processos(isola a Thread)

                Iterator<ThreadQeue> iterator = stack_queue.iterator();

                while (iterator.hasNext()) { // Enquanto conter dados na lista ira processar

                    ThreadQeue process = iterator.next();//pega o Objeto atual e processa

                    // TODO Gerar uma lista de pdf de apolice do seguro
                    // TODO Gerar envio em massa de emails com arquivos grandes de contratos de seguro

                    System.out.println("----------------------------");

                    System.out.println(process.getName());
                    System.out.println(process.getEmail());

                    iterator.remove();

                    try {
                        Thread.sleep(1000);// para dar um tempo de descarga de memoria para rotinas pesadas
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000); // Processou toda a lista dá um tempo para limpeza de memoria
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
