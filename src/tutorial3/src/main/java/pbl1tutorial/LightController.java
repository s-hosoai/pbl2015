package pbl1tutorial;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/light")
public class LightController {
	@Autowired
	private LightData data;
	@Autowired
	MqttClient subscriber;

	@RequestMapping(method=RequestMethod.GET)
	public LightData get(){
		return data;
	}
	
	@Autowired
	public void init(){
		try {
			if (!subscriber.isConnected()) {
				subscriber.connect();
			}
			subscriber.setCallback( new MqttCallback() {
				@Override
				public void messageArrived(String arg0, MqttMessage arg1)
						throws Exception {
					System.out.println("Message Arrived");
					try{
						int value = Integer.parseInt(new String(arg1.getPayload()));
						data.setValue(value);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				@Override
				public void deliveryComplete(IMqttDeliveryToken arg0) {
				}
				@Override
				public void connectionLost(Throwable arg0) {
				}
			});
			subscriber.subscribe("/pbl1/unit5/light", 0);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
