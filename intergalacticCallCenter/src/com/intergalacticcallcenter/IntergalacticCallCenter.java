package com.intergalacticcallcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.intergalacticcallcenter.attentionbuckect.AttentionBucket;
import com.intergalacticcallcenter.attentionbuckect.AttentionBucketController;
import com.intergalacticcallcenter.dispatcher.Dispatcher;
import com.intergalacticcallcenter.dispatcher.DispatcherConsumerImpl;
import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.CallResponse;
import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.EmployeeType;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.employee.Lobby;
import com.intergalacticcallcenter.oncall.ExecutorServiceWrap;
import com.intergalacticcallcenter.oncall.OnCallConsumerImpl;

@SpringBootApplication
public class IntergalacticCallCenter implements CommandLineRunner{
	
	@Autowired
	private AttentionBucketController attentionBucketController;
	
	@Autowired
	private EmployeeStorageController employeeStorageController;
	
	@Autowired
	private ExecutorServiceWrap executorServiceWrap;
	
	@Autowired
	private CallFactory callFactory;
	
	@Autowired
	private Lobby lobby;
	
	@Autowired
	private AttentionBucket attentionBucket;
	
	@Autowired
	private Dispatcher dispatcher;
	
	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(IntergalacticCallCenter.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		Thread onCallConsumer = new OnCallConsumerImpl(executorServiceWrap, callFactory);
		onCallConsumer.start();
		Thread dispatcherConsumer = new DispatcherConsumerImpl(lobby, attentionBucket, dispatcher);
		dispatcherConsumer.start();
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		CallResponse callResponse1 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse1);
		CallResponse callResponse2 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse2);
		CallResponse callResponse3 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse3);
		CallResponse callResponse4 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse4);
		CallResponse callResponse5 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse5);
		CallResponse callResponse6 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse6);
		CallResponse callResponse7 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse7);
		CallResponse callResponse8 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse8);
		CallResponse callResponse9 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse9);
		CallResponse callResponse10 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse10);
		CallResponse callResponse11 = attentionBucketController.addCall(new Call());
		System.out.println(callResponse11);
		
		while (true) {
			@SuppressWarnings("unused")
			int i = 0;
		}
		
//		exit(0);
	}

}
