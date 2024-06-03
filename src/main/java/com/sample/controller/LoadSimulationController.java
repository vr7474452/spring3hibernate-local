package com.sample.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sample.service.LoadSimulatorService;




@Controller
public class LoadSimulationController {
	
	@Autowired
	private LoadSimulatorService loadSimulatorService;
	
	@RequestMapping(value="/controlled/startCpuLoad", method = RequestMethod.GET)
	@ResponseBody
	public String increaseCpuLoadControlled(@RequestParam int percentage, @RequestParam int duration, @RequestParam int threadCount) {
		System.out.println("increaseCpuLoad method called.");
		return loadSimulatorService.increaseCpuLoad(percentage, duration, threadCount, true);
    }
	
	@RequestMapping(value="/uncontrolled/startCpuLoad", method = RequestMethod.GET)
	@ResponseBody
	public String increaseCpuLoadUncontrolled(@RequestParam int duration, @RequestParam int threadCount) {
		System.out.println("increaseCpuLoad method called.");
		return loadSimulatorService.increaseCpuLoad(0, duration, threadCount, false);
    }
	
	
	@RequestMapping(value="/stopCpuLoad", method = RequestMethod.GET)
	@ResponseBody
	public String stopCpuLoad() {
		System.out.println("stopCpuLoad method called.");
		String response = loadSimulatorService.stopCpuLoad();
	        return response;
	}
	
}
