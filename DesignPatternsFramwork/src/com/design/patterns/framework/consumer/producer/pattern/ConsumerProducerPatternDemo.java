package com.design.patterns.framework.consumer.producer.pattern;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  http://javarevisited.blogspot.co.il/2015/07/how-to-use-wait-notify-and-notifyall-in.html
 */
public class ConsumerProducerPatternDemo {

	public static void main(String[] args) {
		
		Queue<Integer> sharedQueue = new LinkedList<Integer>();
		Consumer taskConsumer = new Consumer(sharedQueue, 10);
		Producer taskProducer = new Producer(sharedQueue, 1000 , 10);
		
		Thread mConsumer = new Thread(taskConsumer , "ConsumerThread");
		Thread mProducer = new Thread(taskProducer , "ProducerThread");
		
		mConsumer.start();
		mProducer.start();
	}

}
