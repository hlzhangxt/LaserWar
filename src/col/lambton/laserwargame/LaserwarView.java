package col.lambton.laserwargame;




import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LaserwarView extends SurfaceView implements SurfaceHolder.Callback{
    
	private Activity activity;
	
	private SoundPool soundPool; // plays sound effects
	private SparseIntArray soundMap; // maps IDs to SoundPool
	
	private Bitmap[] Bmspaceships = new Bitmap[36];
	
	private Bitmap BmShot;
	
	private Bitmap BmExploded;
	
	private Bitmap BmLand;
	
	
	
	private static final int EXPLODEDLOUD = 0;
	   private static final int GOTEXPLOSION = 1;
	   private static final int LASERSHOT = 2;
	
	public LaserwarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}

	public LaserwarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		activity = (Activity) context;
		getHolder().addCallback(this);
		
		
		 soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

	      // create Map of sounds and pre-load sounds
	      soundMap = new SparseIntArray(3); // create new HashMap
	      soundMap.put(EXPLODEDLOUD,
	         soundPool.load(context, R.raw.explosion_loud, 1));
	      soundMap.put(GOTEXPLOSION,
	         soundPool.load(context, R.raw.got_explosion, 1));
	      soundMap.put(LASERSHOT,
	         soundPool.load(context, R.raw.lasershot, 1));
	      
	      for (int i=R.drawable.tank0; i<=R.drawable.tank9; i++)
	    	  Bmspaceships[i] = BitmapFactory.decodeResource(getResources(), i);
	      
	      
		  BmShot = BitmapFactory.decodeResource(getResources(), R.drawable.shot);
		  BmExploded = BitmapFactory.decodeResource(getResources(), R.drawable.exploded);
		  BmLand = BitmapFactory.decodeResource(getResources(), R.drawable.land);
		
		
	}

	/*public LaserwarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public LaserwarView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}*/

	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
	//	Paint p = new Paint(Color.RED);
		canvas.drawBitmap(BmLand, new Rect(100,100,500,500), new Rect(0,0,300,300), null);
	}

	public void stopGame() {
		// if (cannonThread != null)
		// cannonThread.setRunning(false); // tell thread to terminate
	}

	public void releaseResources() {
		// soundPool.release(); // release all resources used by the SoundPool
		// soundPool = null;
	}
	
	
	
	 @Override
	   public void surfaceChanged(SurfaceHolder holder, int format,
	      int width, int height)
	   {
	   } 

	   // called when surface is first created
	   @Override
	   public void surfaceCreated(SurfaceHolder holder)
	   {
	     /* if (!dialogIsDisplayed)
	      {
	         cannonThread = new CannonThread(holder); // create thread
	         cannonThread.setRunning(true); // start game running
	         cannonThread.start(); // start the game loop thread
	      } */
	   } 

	   // called when the surface is destroyed
	   @Override
	   public void surfaceDestroyed(SurfaceHolder holder)
	   {
	      // ensure that thread terminates properly
	   /*   boolean retry = true;
	      cannonThread.setRunning(false); // terminate cannonThread
	      
	      while (retry)
	      {
	         try
	         {
	            cannonThread.join(); // wait for cannonThread to finish
	            retry = false;
	         } 
	         catch (InterruptedException e)
	         {
	            Log.e(TAG, "Thread interrupted", e);
	         } 
	      }*/
	   } // end method surfaceDestroyed
	

}
