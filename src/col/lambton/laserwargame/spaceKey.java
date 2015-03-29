package col.lambton.laserwargame;

public class spaceKey {
	private static boolean pressed = false;

	public static synchronized boolean isPressed() {
		return pressed;
	}

	public static synchronized void setPressed(boolean pressed) {
		spaceKey.pressed = pressed;
	}

}
