package com.algorithms.framework.questions;

import com.algorithms.framework.logic.IQueue;

public class Queue {
  
	
	public static class KQueues implements IQueue{
		
		private final int QUEUE_SIZE;
		private final int QUEUE_DETAILS_SIZE;
		private final int BUFFER_SIZE;
		private final int NUM_QUEUES;
		private int[] kQueues;
		private QueueDetails[] kQueuesDetails;
		
		public KQueues(int k,int n){
			QUEUE_SIZE = n/k;
			BUFFER_SIZE = n;
			QUEUE_DETAILS_SIZE = k*2;
			NUM_QUEUES = k;
			kQueues = new int[BUFFER_SIZE];
			kQueuesDetails = new QueueDetails[QUEUE_DETAILS_SIZE]; 
			initkQueuesDetails();
		}
		
		private void initkQueuesDetails(){
			for (int i = 0; i < NUM_QUEUES; i++) {
				kQueuesDetails[i] = new QueueDetails(i*QUEUE_SIZE,i*QUEUE_SIZE);
			}
		}
		
		private QueueDetails getQueueDetails(int qn){
			if(qn>=0 && qn<NUM_QUEUES){
				return kQueuesDetails[qn];
			}
			return null;
		}
		
		@Override
		public boolean enqueue(int x, int qn) throws Exception {
			
				QueueDetails queueDetails = getQueueDetails(qn);
				
				if(isKQueueFull(qn)){
					System.out.println("queue " + qn + " is Full!");
					return false;
				}
				
				if(queueDetails != null){
					int kQueueEndIndex = qn * QUEUE_SIZE + QUEUE_SIZE;
					kQueues[queueDetails.queueTail] = x;
					if((queueDetails.queueTail + 1) == kQueueEndIndex){
						queueDetails.queueTail = qn * QUEUE_SIZE;
					}else{
						queueDetails.queueTail++;
					}
					queueDetails.count++;
				}
				
			
			return false;
		}

		@Override
		public boolean dequeue(int qn) throws Exception {
			
			QueueDetails queueDetails = getQueueDetails(qn);
			if(isKQueueEmpty(qn)){
				System.out.println("queue " + qn + " is Empty!");
				return false;
			}
			
			if(queueDetails != null){
				int kQueueEndIndex = qn * QUEUE_SIZE + QUEUE_SIZE;
				kQueues[queueDetails.queueHead] = -1;
				if((queueDetails.queueHead + 1) == kQueueEndIndex){
					queueDetails.queueHead = qn * QUEUE_SIZE;
				}else{
					queueDetails.queueHead++;
				}
				queueDetails.count--;
			}
			
			return false;
		}

		@Override
		public void printQueue(int qn) throws Exception {
			int startKQueueIndex = qn * QUEUE_SIZE;
			int endKQueueIndex = qn * QUEUE_SIZE + QUEUE_SIZE - 1;
			while(startKQueueIndex<=endKQueueIndex){
				System.out.print(kQueues[startKQueueIndex] + " ");
				startKQueueIndex++;
			}
			System.out.println("");
		}
		
		@Override
		public boolean isKQueueEmpty(int qn) throws Exception {
	        QueueDetails queueDetails = getQueueDetails(qn);
			
			if(queueDetails != null){
				return queueDetails.count == 0;
			}
			
			return false;
			
		}

		@Override
		public boolean isKQueueFull(int qn) throws Exception {
			QueueDetails queueDetails = getQueueDetails(qn);
			
			if(queueDetails != null){
				return queueDetails.count == QUEUE_SIZE;
			}
			
			return false;
			
		}
		
	     private class QueueDetails{
			
	    	 /*ref next empty cell for insert*/
			private int queueTail;
			/*ref to element first inserted*/
			private int queueHead;
			/*queue count elements*/
			private int count;
			
			public QueueDetails(int queueTail,int queueHead){
				this.queueTail = queueTail;
				this.queueHead = queueHead;
			}
		}
	}
}
