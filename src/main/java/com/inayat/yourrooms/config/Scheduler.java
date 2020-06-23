package com.inayat.yourrooms.config;

import org.springframework.stereotype.Service;

@Service
public class Scheduler implements Runnable {
	@Override
	public void run() {
		
			try {
				Thread.sleep(5000);
				System.out.println("TEST SCHEDULAR");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
}