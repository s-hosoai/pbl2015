package pbl1tutorial;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateController {
	@Autowired
	MqttClient client;

	@RequestMapping("/create")
	public void post(@ModelAttribute CreateData data) {
		try {
			if (!client.isConnected()) {
				client.connect();
			}
			client.publish("/pbl1/unit5/create/"+data.getAction(), new MqttMessage(" ".getBytes()));//data.getValue().getBytes()));
		} catch (MqttSecurityException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
