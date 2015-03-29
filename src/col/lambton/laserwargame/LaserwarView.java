package col.lambton.laserwargame;


import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

public class LaserwarView extends SurfaceView implements SurfaceHolder.Callback {

	private Activity activity;

	// private LaserwarView view;

	private static final String TAG = "LaserWarView";

	SurfaceHolder holder; // = getHolder();

	private MainThread mainThread = null;

	public SoundPool soundPool; // plays sound effects
	public SparseIntArray soundMap; // maps IDs to SoundPool

/*	private Bitmap[] Bmspaceships = new Bitmap[36];

	private Bitmap BmShot;

	private Bitmap BmExploded;*/

	private Bitmap BmLand;
	private Bitmap BmShot;
	Paint pback = new Paint();

	public static final int EXPLODEDLOUD = 0;
	public static final int GOTEXPLOSION = 1;
	public static final int LASERSHOT = 2;

	// private Channel channel = null;
	// private ChannelFuture lastWriteFuture = null;
	// private EventLoopGroup group = null;
	// private Bootstrap b = null;

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

	/*	for (int i = R.raw.tank0; i <= R.raw.tank9; i++)
			Bmspaceships[i - R.raw.tank0] = BitmapFactory.decodeResource(
					activity.getResources(), i);

		BmShot = BitmapFactory.decodeResource(activity.getResources(),
				R.raw.shot);
		BmExploded = BitmapFactory.decodeResource(activity.getResources(),
				R.raw.exploded);*/
		BmLand = BitmapFactory.decodeResource(activity.getResources(),
				R.raw.land2);
		
		pback.setColor(Color.RED);
		pback.setTextSize(40);
		
		BmShot = BitmapFactory.decodeResource(activity.getResources(),
				R.raw.shot);

		// view = this;

	}
	
	public void initialGame() {
		// setBounds(200, 100, gameClientHandler.game.getWidth(),
		// gameClientHandler.game.getHeight());

		gameClientHandler.game.getTankImageForMain();

		//setBounds(0, 0, getWidth(), getHeight());

		// this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
		// / 2 - this.getSize().height / 2);

		//this.setTitle("Larser-War Game ID: "
			//	+ gameClientHandler.game.getGameid());

		//drawGameElements();

		String msg = "SIZE:" + getWidth()+ "," + getHeight() + "\n";
		NettyThread.channel.write(msg);

	}
	
	public void initialGameForSec() {
		// setBounds(200, 100, gameClientHandler.game.getWidth(),
		// gameClientHandler.game.getHeight());

		gameClientHandler.game.getTankImageForSec();

	//	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	//	setBounds(0, 0, dim.width, dim.height);

		// this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
		// / 2 - this.getSize().height / 2);

	//	this.setTitle("Larser-War Game ID: "
	//			+ gameClientHandler.game.getGameid());

	//	repaint();
		//drawGameElements();

	}
	

	/*
	 * private class NettyThread extends Thread {
	 * 
	 * public void run() { ChannelFactory factory = new
	 * NioClientSocketChannelFactory( Executors.newCachedThreadPool(),
	 * Executors.newCachedThreadPool());
	 * 
	 * ClientBootstrap bootstrap = new ClientBootstrap(factory);
	 * 
	 * bootstrap.setPipelineFactory(new ChannelPipelineFactory() { public
	 * ChannelPipeline getPipeline() {
	 * 
	 * return Channels.pipeline(
	 * 
	 * new gameClientHandler(view)); } });
	 * 
	 * bootstrap.setOption("tcpNoDelay", true); bootstrap.setOption("keepAlive",
	 * true);
	 * 
	 * try { channel = bootstrap.connect(new InetSocketAddress(HOST, PORT))
	 * .sync().getChannel(); } catch (Exception e) { Log.e(TAG,
	 * "Thread interrupted", e); }
	 * 
	 * 
	 * 
	 * //**********************************************************************
	 * 
	 * 
	 * while (!AGameSession.isGotGames()) ;
	 * 
	 * AlertDialog.Builder builder = new AlertDialog.Builder(activity); // Add
	 * the buttons builder.setTitle("Larser-War Game");
	 * 
	 * builder.setNegativeButton(R.string.newgame, new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) { // User clicked OK button } }); if
	 * (AGameSession.namelist.length > 0) {
	 * builder.setPositiveButton(R.string.existgame, new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) {
	 * 
	 * gameWay = "JOIN"; // User cancelled the dialog } }); // Set other dialog
	 * properties } // Create the AlertDialog AlertDialog dialog =
	 * builder.create(); dialog.show(); /* DlgMain dialog = new DlgMain(this,
	 * true); dialog.setTitle("Larser-War Game");
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * 
	 * DlgList dialogList = new DlgList();
	 * dialogList.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * 
	 * dialog.setVisible(true);
	 */
	/*
	 * if (gameWay.equals("NEW")) gameClientHandler.game.ID = 1; else
	 * gameClientHandler.game.ID = 2;
	 * 
	 * if (gameClientHandler.game.ID == 1) lastWriteFuture =
	 * channel.write("NEWGAME\r\n"); else {
	 * 
	 * AlertDialog.Builder builderList = new AlertDialog.Builder(activity);
	 * builderList.setTitle("Larser-War Game").setItems( AGameSession.namelist,
	 * new DialogInterface.OnClickListener() { public void
	 * onClick(DialogInterface dialog, int which) { // The 'which' argument
	 * contains the index position // of the selected item
	 * gameClientHandler.game .setGameid(AGameSession.namelist[which]);
	 * 
	 * } }); AlertDialog dialogList = builderList.create(); dialogList.show();
	 * 
	 * // dialogList.putAllGames(); // dialogList.setVisible(true);
	 * 
	 * lastWriteFuture = channel.write("JOIN:" +
	 * gameClientHandler.game.getGameid() + "\r\n");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //************************************************************************
	 * ***************
	 * 
	 * 
	 * } }
	 */

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
	 * @Override protected void onDraw(Canvas canvas) { // TODO Auto-generated
	 * method stub super.draw(canvas); //canvas.drawColor(Color.BLACK);
	 * 
	 * canvas.drawBitmap(BmLand, 10, 10, null);
	 * 
	 * }
	 */
	public void stopGame() {
		if (mainThread != null)
			mainThread.setRunning(false); // tell thread to terminate
	}

	public void releaseResources() {
		soundPool.release(); // release all resources used by the SoundPool
		soundPool = null;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// setWillNotDrawEnabled(false);

		
		
		AGameSession.activity = activity;

		new NettyThread(this, activity)
				.start();
		// *************************************************************************************

		while (!AGameSession.isGotGames())
			;
		
		
		FragmentManager manager = activity.getFragmentManager();
		NewJoinDlgActivity newJoinActivity = new NewJoinDlgActivity();
	    
		newJoinActivity.show(manager, "DialogActivity");
		
    
		mainThread = new MainThread(this); // create thread
		mainThread.setRunning(true); // start game running
		mainThread.start(); // start the game loop thread
		
		
		
		
		/*
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
			 setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
			 View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
			 View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
			 View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
			 | View.SYSTEM_UI_FLAG_IMMERSIVE);*/
		
	//	this.drawGameElements();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		boolean retry = true;
		mainThread.setRunning(false); // terminate cannonThread

		while (retry) {
			try {
				mainThread.join(); // wait for cannonThread to finish
				retry = false;
			} catch (InterruptedException e) {
				Log.e(TAG, "Thread interrupted", e);
			}
		}

	}

	public void drawGameElements() {

		Canvas canvas = null;
		

		try {
			canvas = holder.lockCanvas(null);
			synchronized (holder) {
				canvas.drawBitmap(BmLand, 0, 0, null);
				pback.setColor(Color.RED);
				pback.setTextSize(40);
				canvas.drawText(gameClientHandler.game.getGameid(), 40, 40, pback);
				
				pback.setTextSize(20);
				
				int imgwidth = Math.round((float)(43 * 1.0));
				//***************************************************************
				 
				
				int x = gameClientHandler.game.getX() - 22;
				int y = gameClientHandler.game.getY() - 17;

				//g.setColor(Color.YELLOW);
				pback.setColor(Color.BLUE);

				
				RectF rf = new RectF(x, y, x+imgwidth, y+imgwidth);
				Bitmap bmp = gameClientHandler.game.getImgTank();
				
				if (bmp != null)
				canvas.drawBitmap(bmp, null, rf, null);
				//g.drawImage(gameClientHandler.game.getImgTank(), x, y, 43, 43, null);

				canvas.drawText("" + gameClientHandler.game.getFule1(), x - 10, y - 10, pback);
				//g.drawString("" + gameClientHandler.game.getFule1(), x - 10, y - 10);
				
				
				rf = new RectF(x - 15, y + 10 - gameClientHandler.game.getFule1(), x - 15 + 5, y + 10 - gameClientHandler.game.getFule1() + gameClientHandler.game.getFule1());
			//	g.fillRect(x - 15, y + 10 - gameClientHandler.game.getFule1(), 5,
				//		gameClientHandler.game.getFule1());
				
				canvas.drawRect(rf, pback);
				
				canvas.drawText("" + gameClientHandler.game.getSpeed1(), x, y + 60, pback);
				
				
				if (gameClientHandler.game.getSpeed1() > 0)
				{
					rf = new RectF(x + 10, y + 50, x + 10 + gameClientHandler.game.getSpeed1()*3, y + 50 + 2);
					canvas.drawRect(rf, pback);
					
				 //   g.fillRect(x + 10, y + 50, gameClientHandler.game.getSpeed1()*3, 2);
				}else
				{
					
				//	g.fillRect(x - 5 + gameClientHandler.game.getSpeed1()*3, y + 50, -gameClientHandler.game.getSpeed1()*3, 2);	
					rf = new RectF(x - 5 + gameClientHandler.game.getSpeed1()*3, y + 50, x - 5 + gameClientHandler.game.getSpeed1()*3 -gameClientHandler.game.getSpeed1()*3, y + 50 + 2);
					canvas.drawRect(rf, pback);
				}
				

				if (gameClientHandler.game.ID == 2
						|| (gameClientHandler.game.ID == 1 && gameClientHandler.game
								.getImgTank2() != null)) {
					int x2 = gameClientHandler.game.getX2() - 22;
					int y2 = gameClientHandler.game.getY2() - 17;

					rf = new RectF(x2, y2, x2+imgwidth, y2+imgwidth);
					
					bmp = gameClientHandler.game.getImgTank2();
					
					if (bmp != null)
					canvas.drawBitmap(bmp,null, rf, null);

					pback.setColor(Color.RED);

					canvas.drawText("" + gameClientHandler.game.getFule2(), x2 + 35, y2 - 10, pback);

					
					int pleft = x2 + 50;
					int ptop = y2 + 10 - gameClientHandler.game.getFule2();
					int pright = pleft + 5;
					int pbottom = ptop + gameClientHandler.game.getFule2();
					
					rf = new RectF(pleft, ptop, pright, pbottom);
					
					canvas.drawRect(rf, pback);
				//	g.fillRect(x2 + 50, y2 + 10 - gameClientHandler.game.getFule2(), 5,
				//
				//	gameClientHandler.game.getFule2());
					
					canvas.drawText("" + gameClientHandler.game.getSpeed2(), x2, y2 + 60, pback);
					//g.drawString("" + gameClientHandler.game.getSpeed2(), x2, y2 + 60);
					
					
					if (gameClientHandler.game.getSpeed2() > 0)
					{
						pleft = x2 + 10;
						ptop = y2 + 50;
						pright = pleft + gameClientHandler.game.getSpeed2()*3;
						pbottom = ptop + 2;
						rf = new RectF(pleft, ptop, pright, pbottom);
						canvas.drawRect(rf, pback);
					  //  g.fillRect(x2 + 10, y2 + 50, gameClientHandler.game.getSpeed2()*3, 2);
					}else
					{
						pleft = x2 - 5 + gameClientHandler.game.getSpeed2()*3;
						ptop = y2 + 50;
						pright = pleft - gameClientHandler.game.getSpeed2()*3;
						pbottom = ptop + 2;
						rf = new RectF(pleft, ptop, pright, pbottom);
						canvas.drawRect(rf, pback);
					//	g.fillRect(x2 - 5 + gameClientHandler.game.getSpeed2()*3, y2 + 50, -gameClientHandler.game.getSpeed2()*3, 2);	
						
						
					}
					
					
					

				}

				int fireCount = gameClientHandler.game.getCountFires();

				for (int i = 0; i < fireCount; i++) {
					fireInfo fi = gameClientHandler.game.getNthFire(i);

					if (fi.time > 0) {
						if (fi.part == 1)
							pback.setColor(Color.BLUE);

						if (fi.part == 2)
							pback.setColor(Color.RED);

						canvas.drawLine(fi.x0, fi.y0, fi.x1, fi.y1, pback);

						if (fi.targeted)
						{
							rf = new RectF(fi.x1 - 10, fi.y1 - 7, fi.x1 - 10+imgwidth, fi.y1 - 7+imgwidth);
							canvas.drawBitmap(BmShot, null, rf, null);
							//g.drawImage(imgShot, fi.x1 - 10, fi.y1 - 7, 26, 26, null);
							
						}

					}

				}

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			//*************************************************************************************	
				
				
				
				
				

			}

		} finally {
			// display canvas's contents on the CannonView
			// and enable other threads to use the Canvas
			if (canvas != null)
				holder.unlockCanvasAndPost(canvas);
		}

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
		 * int x = gameClientHandler.game.getX() - 22; int y =
		 * gameClientHandler.game.getY() - 17;
		 * 
		 * g.setColor(Color.YELLOW);
		 * 
		 * canvas.drawImage(gameClientHandler.game.getImgTank(), x, y, 43, 43,
		 * null);
		 * 
		 * g.drawString("" + gameClientHandler.game.getFule1(), x - 10, y - 10);
		 * g.fillRect(x - 15, y + 10 - gameClientHandler.game.getFule1(), 5,
		 * gameClientHandler.game.getFule1());
		 * 
		 * 
		 * g.drawString("" + gameClientHandler.game.getSpeed1(), x, y + 60);
		 * 
		 * if (gameClientHandler.game.getSpeed1() > 0) { g.fillRect(x + 10, y +
		 * 50, gameClientHandler.game.getSpeed1()*3, 2); }else {
		 * 
		 * g.fillRect(x - 5 + gameClientHandler.game.getSpeed1()*3, y + 50,
		 * -gameClientHandler.game.getSpeed1()*3, 2);
		 * 
		 * 
		 * }
		 * 
		 * 
		 * if (gameClientHandler.game.ID == 2 || (gameClientHandler.game.ID == 1
		 * && gameClientHandler.game .getImgTank2() != null)) { int x2 =
		 * gameClientHandler.game.getX2() - 22; int y2 =
		 * gameClientHandler.game.getY2() - 17;
		 * 
		 * g.drawImage(gameClientHandler.game.getImgTank2(), x2, y2, 43, 43,
		 * null);
		 * 
		 * g.setColor(Color.RED);
		 * 
		 * g.drawString("" + gameClientHandler.game.getFule2(), x2 + 35, y2 -
		 * 10);
		 * 
		 * 
		 * g.fillRect(x2 + 50, y2 + 10 - gameClientHandler.game.getFule2(), 5,
		 * gameClientHandler.game.getFule2());
		 * 
		 * g.drawString("" + gameClientHandler.game.getSpeed2(), x2, y2 + 60);
		 * 
		 * 
		 * if (gameClientHandler.game.getSpeed2() > 0) { g.fillRect(x2 + 10, y2
		 * + 50, gameClientHandler.game.getSpeed2()*3, 2); }else {
		 * 
		 * g.fillRect(x2 - 5 + gameClientHandler.game.getSpeed2()*3, y2 + 50,
		 * -gameClientHandler.game.getSpeed2()*3, 2);
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * int fireCount = gameClientHandler.game.getCountFires();
		 * 
		 * for (int i = 0; i < fireCount; i++) { fireInfo fi =
		 * gameClientHandler.game.getNthFire(i);
		 * 
		 * if (fi.time > 0) { if (fi.part == 1) g.setColor(Color.YELLOW);
		 * 
		 * if (fi.part == 2) g.setColor(Color.RED);
		 * 
		 * g.drawLine(fi.x0, fi.y0, fi.x1, fi.y1);
		 * 
		 * if (fi.targeted) g.drawImage(imgShot, fi.x1 - 10, fi.y1 - 7, 26, 26,
		 * null);
		 * 
		 * }
		 * 
		 * }
		 */

	}

}
