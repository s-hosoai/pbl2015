package pbl1tutorial;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;

@SpringBootApplication
@ComponentScan
@EnableIntegration
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public MqttClient mqttClient(){
		MqttClient client = null;
		try {
			client = new DefaultMqttPahoClientFactory().getClientInstance("tcp://mqtt.f.ait.kyushu-u.ac.jp:1883", "pbl1-unit5");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return client;
	}
}
