package com.sample.service;

public interface LoadSimulatorService {

	public String increaseCpuLoad(int percentage, int duration , int threads, boolean isControlled);
	public String stopCpuLoad();
}
