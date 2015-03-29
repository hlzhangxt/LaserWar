package col.lambton.laserwargame;

public class leftKey {
	private static boolean pressed = false;
	private static int times = 0;
	
	public static synchronized int getTimes() {
		return times;
	}

	public static synchronized void setTimes(int times) {
		leftKey.times = times;
	}
	
	
	public static synchronized boolean isPressed() {
		return pressed;
	}

	public static synchronized void setPressed(boolean pressed) {
		leftKey.pressed = pressed;
	}

}
