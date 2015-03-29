package col.lambton.laserwargame;

public class upKey {
	private static boolean pressed = false;

	public static synchronized boolean isPressed() {
		return pressed;
	}

	public static synchronized void setPressed(boolean pressed) {
		upKey.pressed = pressed;
	}

}
