package col.lambton.laserwargame;





import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

public class LaserwarView extends SurfaceView implements SurfaceHolder.Callback {

	private Activity activity;

	SurfaceHolder holder; // = getHolder();
	
	private MainThread mainThread;

	private SoundPool soundPool; // plays sound effects
	private SparseIntArray soundMap; // maps IDs to SoundPool

	private Bitmap[] Bmspaceships = new Bitmap[36];

	private Bitmap BmShot;

	private Bitmap BmExploded;

	private Bitmap BmLand;

	Paint pback = new Paint();

	private static final int EXPLODEDLOUD = 0;
	private static final int GOTEXPLOSION = 1;
	private static final int LASERSHOT = 2;
	
	
	//private Channel channel = null;
	//private ChannelFuture lastWriteFuture = null;
	//private EventLoopGroup group = null;
	//private Bootstrap b = null;
	

	/*
	 * private OnClickListener userButtonListener = new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Canvas canvas = null;
	 * SurfaceHolder holder = getHolder(); try {
	 * 
	 * canvas = getHolder().lockCanvas(null); synchronized (holder) {
	 * draw(canvas); } } catch (Exception e) { e.printStackTrace(); } finally {
	 * if (canvas != null) { holder.unlockCanvasAndPost(canvas); } }
	 * 
	 * }
	 * 
	 * };
	 */

	public LaserwarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	/*
	 * private Bitmap Scale(Bitmap bitmap) { Matrix matrix = new Matrix();
	 * matrix
	 * .postScale((float)(getWidth()*1.0/bitmap.getWidth()),(float)(getHeight
	 * ()*1.0/bitmap.getHeight())); //长和宽放大缩小的比例 Bitmap resizeBmp =
	 * Bitmap.createBitmap
	 * (bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true); return
	 * resizeBmp; }
	 */

	public LaserwarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		/*
		 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		 * setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
		 * View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
		 * View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
		 * View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
		 * | View.SYSTEM_UI_FLAG_IMMERSIVE);
		 */

		// TODO Auto-generated constructor stub
		activity = (Activity) context;
		holder = getHolder();
		holder.addCallback(this);

		// sound files
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

		// create Map of sounds and pre-load sounds
		soundMap = new SparseIntArray(3); // create new HashMap
		soundMap.put(EXPLODEDLOUD,
				soundPool.load(context, R.raw.explosion_loud, 1));
		soundMap.put(GOTEXPLOSION,
				soundPool.load(context, R.raw.got_explosion, 1));
		soundMap.put(LASERSHOT, soundPool.load(context, R.raw.lasershot, 1));

		// images
		
		for (int i = R.raw.tank0; i <= R.raw.tank9; i++)
			Bmspaceships[i - R.raw.tank0] = BitmapFactory.decodeResource(
					activity.getResources(), i);

		BmShot = BitmapFactory.decodeResource(activity.getResources(), R.raw.shot);
		BmExploded = BitmapFactory.decodeResource(activity.getResources(), R.raw.exploded);
		BmLand = BitmapFactory.decodeResource(activity.getResources(), R.raw.land2);
		
		

	}

	/*
	 * public LaserwarView(Context context, AttributeSet attrs, int defStyle) {
	 * super(context, attrs, defStyle); // TODO Auto-generated constructor stub
	 * }
	 * 
	 * public LaserwarView(Context context, AttributeSet attrs, int
	 * defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr,
	 * defStyleRes); // TODO Auto-generated constructor stub }
	 */

	/*
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		//canvas.drawColor(Color.BLACK);
		
		canvas.drawBitmap(BmLand, 10, 10, null);

	}
     */
	public void stopGame() {
		// if (cannonThread != null)
		// cannonThread.setRunning(false); // tell thread to terminate
	}

	public void releaseResources() {
		 soundPool.release(); // release all resources used by the SoundPool
		 soundPool = null;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//setWillNotDrawEnabled(false);
		
		
		mainThread = new MainThread(holder, this); // create thread
		mainThread.setRunning(true); // start game running
		mainThread.start(); // start the game loop thread
		
		
	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void drawGameElements(Canvas canvas)
	{
		canvas.drawBitmap(BmLand, 0, 0, null);
        
		/*
		 * g.setColor(Color.red);
		 * 
		 * g.drawRect(gameClientHandler.game.getC1().getX0() - 20,
		 * gameClientHandler.game.getC1().getY0() - 20,
		 * gameClientHandler.game.getC1().getWidth() + 40,
		 * gameClientHandler.game.getC1().getHeight() + 40);
		 * 
		 * g.drawRect(gameClientHandler.game.getC2().getX0() - 20,
		 * gameClientHandler.game.getC2().getY0() - 20,
		 * gameClientHandler.game.getC2().getWidth() + 40,
		 * gameClientHandler.game.getC2().getHeight() + 40);
		 */
		// g.setColor(Color.YELLOW);
		
		/*
		int x = gameClientHandler.game.getX() - 22;
		int y = gameClientHandler.game.getY() - 17;

		g.setColor(Color.YELLOW);

		canvas.drawImage(gameClientHandler.game.getImgTank(), x, y, 43, 43, null);

		g.drawString("" + gameClientHandler.game.getFule1(), x - 10, y - 10);
		g.fillRect(x - 15, y + 10 - gameClientHandler.game.getFule1(), 5,
				gameClientHandler.game.getFule1());
		
		
		g.drawString("" + gameClientHandler.game.getSpeed1(), x, y + 60);
		
		if (gameClientHandler.game.getSpeed1() > 0)
		{
		    g.fillRect(x + 10, y + 50, gameClientHandler.game.getSpeed1()*3, 2);
		}else
		{
			
			g.fillRect(x - 5 + gameClientHandler.game.getSpeed1()*3, y + 50, -gameClientHandler.game.getSpeed1()*3, 2);	
			
			
		}
		

		if (gameClientHandler.game.ID == 2
				|| (gameClientHandler.game.ID == 1 && gameClientHandler.game
						.getImgTank2() != null)) {
			int x2 = gameClientHandler.game.getX2() - 22;
			int y2 = gameClientHandler.game.getY2() - 17;

			g.drawImage(gameClientHandler.game.getImgTank2(), x2, y2, 43, 43,
					null);

			g.setColor(Color.RED);

			g.drawString("" + gameClientHandler.game.getFule2(), x2 + 35,
					y2 - 10);

			
			g.fillRect(x2 + 50, y2 + 10 - gameClientHandler.game.getFule2(), 5,
					gameClientHandler.game.getFule2());
			
			g.drawString("" + gameClientHandler.game.getSpeed2(), x2, y2 + 60);
			
			
			if (gameClientHandler.game.getSpeed2() > 0)
			{
			    g.fillRect(x2 + 10, y2 + 50, gameClientHandler.game.getSpeed2()*3, 2);
			}else
			{
				
				g.fillRect(x2 - 5 + gameClientHandler.game.getSpeed2()*3, y2 + 50, -gameClientHandler.game.getSpeed2()*3, 2);	
				
				
			}
			
			
			

		}

		int fireCount = gameClientHandler.game.getCountFires();

		for (int i = 0; i < fireCount; i++) {
			fireInfo fi = gameClientHandler.game.getNthFire(i);

			if (fi.time > 0) {
				if (fi.part == 1)
					g.setColor(Color.YELLOW);

				if (fi.part == 2)
					g.setColor(Color.RED);

				g.drawLine(fi.x0, fi.y0, fi.x1, fi.y1);

				if (fi.targeted)
					g.drawImage(imgShot, fi.x1 - 10, fi.y1 - 7, 26, 26, null);

			}

		}

		*/
		
		
	}

	


}
