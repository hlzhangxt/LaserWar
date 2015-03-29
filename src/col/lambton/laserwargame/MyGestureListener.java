package col.lambton.laserwargame;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector;

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
	private static final String DEBUG_TAG = "Gestures";
	private boolean firstTouch = false;
	private long time = 0;

	@Override
	public boolean onDown(MotionEvent event) {

		

		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		
		float x = e.getX();
		float y = e.getY();

		spaceShipRotate(x, y);
		
		
		return super.onSingleTapConfirmed(e);
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
			float velocityX, float velocityY) {
		Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

		double sinA = velocityY
				/ Math.sqrt(velocityX * velocityX + velocityY * velocityY);

		double angle = Math.asin(sinA) * 180 / Math.PI;

		if (angle > 0 && velocityX < 0)
			angle = 180 - angle;

		if (angle < 0 && velocityX < 0)
			angle = -180 - angle;

		double angDiff = 0;

		if (gameClientHandler.game.ID == 1)
			angDiff = angle - gameClientHandler.game.getAngle();
		else
			angDiff = angle - gameClientHandler.game.getAngle2();

		if (angDiff > 180)
			angDiff -= 360;
		if (angDiff < -180)
			angDiff += 360;

		if (Math.abs(angDiff) <= 90)
			upKey.setPressed(true);
		else
			downKey.setPressed(true);

		return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub

		float x = e.getX();
		float y = e.getY();

		spaceShipRotate(x, y);
		
		
		
		spaceShipFire();

		return true;
	}

	private void spaceShipRotate(float x, float y) {
		float velocityY, velocityX;

		if (gameClientHandler.game.ID == 1) {
			velocityY = y - gameClientHandler.game.getY();
			velocityX = x - gameClientHandler.game.getX();

		} else {
			velocityY = y - gameClientHandler.game.getY2();
			velocityX = x - gameClientHandler.game.getX2();

		}

		double sinA = velocityY
				/ Math.sqrt(velocityX * velocityX + velocityY * velocityY);

		double angle = Math.asin(sinA) * 180 / Math.PI;

		if (angle > 0 && velocityX < 0)
			angle = 180 - angle;

		if (angle < 0 && velocityX < 0)
			angle = -180 - angle;

		double angDiff = 0;

		if (gameClientHandler.game.ID == 1)
			angDiff = angle - gameClientHandler.game.getAngle();
		else
			angDiff = angle - gameClientHandler.game.getAngle2();

		if (angDiff > 180)
			angDiff -= 360;
		if (angDiff < -180)
			angDiff += 360;

		int times = (int) Math.round(angDiff / 10);

		if (times > 0) {
			rightKey.setPressed(true);
			rightKey.setTimes(times);
		}
		if (times < 0) {
			leftKey.setPressed(true);
			leftKey.setTimes(-times);
		}

	}

	private void spaceShipFire() {

		spaceKey.setPressed(true);

	}

}
