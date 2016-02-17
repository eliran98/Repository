package com.design.patterns.framework.consumer.producer.pattern;

import java.util.Queue;

public class Consumer implements Runnable{

	private Queue<Integer> sharedQueue;
	private final int QUEUE_SIZE;
	
	public Consumer(Queue<Integer> sharedQueue,int queueSize){
		this.sharedQueue = sharedQueue;
		this.QUEUE_SIZE = queueSize;
	}
	
	@Override
	public void run() { 
		while (true) {
			synchronized (sharedQueue) {
				while(sharedQueue.isEmpty()){
					System.out.println("Queue is empty," + Thread.currentThread().getName() + " is waiting" + " for producer thread to put something in queue"); 
					try {
						sharedQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Consuming value : " + sharedQueue.remove());
				sharedQueue.notifyAll();
			}
		}
	}
}
