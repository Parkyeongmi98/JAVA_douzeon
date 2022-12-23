package tv;

public class TV {
	private int channel;    // 1~255
	private int volume;     // 0~100
	private boolean power;  
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	

	public void power(boolean on) {
		this.power = on;
	}
	
	public void channel(int channel) {
		if (channel > 255) {
			this.channel = 1;
		} else if (channel < 1) {
			this.channel = 255;
		}
	}
	
	public void channel(boolean up) {
		if(up) {
			if (this.channel < 255) {
				this.channel += 1; 
				} else {
					this.channel = 1;
				}
		} else {
			if (this.channel > 1) {
				this.channel -= 1; 
				}
		}
	}
	
	public void volume(int volume) {
		if (volume > 100) {
			this.volume = 0;
		} else if (volume < 1) {
			this.volume = 100;
		} else {
			this.volume = volume;
		}
	}
	
	public void volume(boolean up) {
		if(up) {
			if (this.volume < 100) {
				this.volume += 1; 
				} 
		} else {
			if (this.volume < 1) {
				this.volume = 100;
				} else {
					this.volume -= 1;
				}
		}
	}
	
	public void status() {
		System.out.println(
				"TV[power=" + (power ? "on" : "off") + ", " +
				"channel=" + channel + ", " +
				"volume=" + volume + "]"
			);
	}
	
}
