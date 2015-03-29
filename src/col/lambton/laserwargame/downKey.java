package col.lambton.laserwargame;

public class downKey {
	private static boolean pressed = false;

	public static synchronized boolean isPressed() {
		return pressed;
	}

	public static synchronized void setPressed(boolean pressed) {
		downKey.pressed = pressed;
	}

}
