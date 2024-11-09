package com.sample.service;

import org.springframework.stereotype.Service;

@Service("LoadSimulatorService")
public class LoadSimulatorServiceImpl implements LoadSimulatorService {

	private volatile boolean cpuLoadEnabled = false;
	private static final int NUM_CORES = Runtime.getRuntime().availableProcessors();
	private volatile int count = 0;

	@Override
	public String increaseCpuLoad(int percentage, int duration, int threadCount, boolean control) {

		System.out.println("ServiceImpl increaseCpuLoad called");
		cpuLoadEnabled = true;
		if (percentage < 0 || percentage > 100) {
			return "Percentage must be between 0 and 100";
		}
		if (duration <= 0) {
			return "Duration must be greater than 0";
		}
		final int loadPercentage = percentage;
		final int loadDuration = duration;


		// Calculate the maximum number of threads based on available memory
        long maxMemory = Runtime.getRuntime().maxMemory();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long availableMemory = maxMemory - usedMemory;
        
        // Approximate memory usage per thread (this is a rough estimate)
        long memoryPerThread = 1 * 1024 * 1024; // 1 MB per thread
        int maxThreadsBasedOnMemory = (int) (availableMemory / memoryPerThread);

        // Calculate the final number of threads to use
        int numThreads = Math.min(threadCount, maxThreadsBasedOnMemory);
        numThreads = Math.max(numThreads, 1); // Ensure at least one thread is used

        System.out.println("Number of cores: " + NUM_CORES + ", Requested threads: " + threadCount + 
                    ", Adjusted threads: " + numThreads + ", Max threads based on memory: " + maxThreadsBasedOnMemory);
        final boolean isControlled = control;
		for (int i = 0; i < numThreads; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("Thread ID: " + Thread.currentThread().getId());
						if(isControlled) {
							generateControlledCpuLoad(loadPercentage, loadDuration);
						}else {
							generateUncontrolledCpuLoad(loadDuration);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		if(!isControlled) {
			return "CPU load increased for "+ duration + " seconds";
		}else {
			return "CPU load increased by " + percentage + "% for " + duration + " seconds";
		}
		
	}

	@Override
	public String stopCpuLoad() {
		System.out.println("ServiceImpl stopCpuLoad called");
		cpuLoadEnabled = false;
		count = 0;
		return "CPU load stopped.";
	}

	private void generateUncontrolledCpuLoad(int duration) throws InterruptedException {
		
		long endTime = System.currentTimeMillis() + duration * 1000L;

		while (System.currentTimeMillis() < endTime && cpuLoadEnabled) {
			count++;
			if (!cpuLoadEnabled) {
				System.out.println("Cpu load disabled/stop is called");
				return; // Exit if CPU load is disabled
			}
		}
	}
	
	private void generateControlledCpuLoad(int percentage, int duration) throws InterruptedException {
	    long endTime = System.currentTimeMillis() + duration * 1000L;
	    long busyTime = (long) (10 * percentage); // Busy time in milliseconds
	    long idleTime = 10 - busyTime; // Idle time in milliseconds

	    while (System.currentTimeMillis() < endTime && cpuLoadEnabled) {
	        long startTime = System.currentTimeMillis();

	        // Busy loop for the calculated busy time
	        while ((System.currentTimeMillis() - startTime) < busyTime) {
	            count ++;
	            System.out.println("Cpu is busy");
	        }

	        // Sleep for the calculated idle time
	        if (idleTime > 0) {
	           
	        	Thread.sleep(idleTime);
	        }
	    }
	}
}