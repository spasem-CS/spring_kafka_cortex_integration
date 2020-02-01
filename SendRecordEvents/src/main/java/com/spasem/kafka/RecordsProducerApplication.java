package com.spasem.kafka;

import com.spasem.kafka.producer.RecordsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RecordsProducerApplication {

	public static void main(String[] args) {
		System.out.println("Records Producer Application Starts");

		ConfigurableApplicationContext content= SpringApplication.run( RecordsProducerApplication.class, args);
		//RecordsProducer producer = content.getBeanFactory().getBean( RecordsProducer.class );
		//producer.produce();
	}

}
