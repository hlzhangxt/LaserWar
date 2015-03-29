package col.lambton.laserwargame;

public class rightKey {
	private static boolean pressed = false;
	private static int times = 0;

	public static synchronized int getTimes() {
		return times;
	}

	public static synchronized void setTimes(int times) {
		rightKey.times = times;
	}

	public static synchronized boolean isPressed() {
		return pressed;
	}

	public static synchronized void setPressed(boolean pressed) {
		rightKey.pressed = pressed;
	}

}
