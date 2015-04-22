package pbl1tutorial;

import org.springframework.stereotype.Component;

@Component
public class LightData {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public LightData(int value) {
		this.value=value;
	}
	public LightData(){}
}
