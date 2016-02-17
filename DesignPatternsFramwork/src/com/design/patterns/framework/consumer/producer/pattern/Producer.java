package com.design.patterns.framework.consumer.producer.pattern;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable{
	private Queue<Integer> sharedQueue;
	private int numProduceItems;
	private final int QUEUE_SIZE;
	
	public Producer(Queue<Integer> sharedQueue,int numProduceItems,int queueSize){
		this.sharedQueue = sharedQueue;
		this.numProduceItems = numProduceItems;
		this.QUEUE_SIZE = queueSize;
	}

	@Override
	public void run() {
		while(numProduceItems>0){
			synchronized (sharedQueue) {
				while(sharedQueue.size() == QUEUE_SIZE){
					System.out .println("Queue is full, "  +  Thread.currentThread().getName() + " waiting for " + "consumer to take something from queue");
					try {
						sharedQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				Random random = new Random(); 
				int i = random.nextInt();
				System.out.println("Producing value : " + i);
				sharedQueue.add(i);
				numProduceItems--;
				sharedQueue.notifyAll();
			}
		}
	}
}
