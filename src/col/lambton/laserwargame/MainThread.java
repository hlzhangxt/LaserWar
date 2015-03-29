package col.lambton.laserwargame;

/*import io.netty.bootstrap.Bootstrap;
 import io.netty.channel.Channel;
 import io.netty.channel.ChannelFuture;
 import io.netty.channel.EventLoopGroup;
 import io.netty.channel.nio.NioEventLoopGroup;
 import io.netty.channel.socket.nio.NioSocketChannel; */

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import android.graphics.Canvas;

import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

	// private SurfaceHolder surfaceHolder; // for manipulating canvas
	private boolean threadIsRunning = true; // running by default
	LaserwarView view;

	// private Channel channel;
	// private ChannelFuture lastWriteFuture;

	// initializes the surface holder
	public MainThread(LaserwarView v) {
		// surfaceHolder = holder;

		view = v;
		setName("MainThread");
	}

	// changes running state
	public void setRunning(boolean running) {
		threadIsRunning = running;
	}

	// controls the game loop
	@Override
	public void run() {

		while (threadIsRunning) {

			view.drawGameElements();

			int fireCount = gameClientHandler.game.getCountFires();
			boolean isRepaint = false;

			for (int i = 0; i < fireCount; i++) {
				fireInfo fi = gameClientHandler.game.getNthFire(i);

				if (fi.time > 0) {

					fi.time--;
				}

			}

			if (fireCount > 0) {

				gameClientHandler.game.removeAllZeros();

			}

			String cmd = "";

			if (leftKey.isPressed()) {
				int times = leftKey.getTimes();
				if (times > 0) {
					cmd += "MOVE:" + gameClientHandler.game.getGameid() + ":"
							+ "37" + "\r\n";
					leftKey.setTimes(--times);
				} else
					leftKey.setPressed(false);

			}

			if (upKey.isPressed()) {

				cmd += "MOVE:" + gameClientHandler.game.getGameid() + ":"
						+ "38" + "\r\n";

				upKey.setPressed(false);

			}

			if (rightKey.isPressed()) {
				int times = rightKey.getTimes();
				if (times > 0) {
					cmd += "MOVE:" + gameClientHandler.game.getGameid() + ":"
							+ "39" + "\r\n";
					rightKey.setTimes(--times);
				} else
					rightKey.setPressed(false);

			}

			if (downKey.isPressed()) {

				cmd += "MOVE:" + gameClientHandler.game.getGameid() + ":"
						+ "40" + "\r\n";
				downKey.setPressed(false);
			}
			if (spaceKey.isPressed()) {
				if (leftKey.getTimes() == 0 && rightKey.getTimes() == 0) {
					cmd += "MOVE:" + gameClientHandler.game.getGameid() + ":"
							+ "32" + "\r\n";
					spaceKey.setPressed(false);
				}
			}

			if (!cmd.equals(""))
				NettyThread.channel.write(cmd);

			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/*
		 * EventLoopGroup workerGroup = new NioEventLoopGroup();
		 * 
		 * Bootstrap b = new Bootstrap(); // (1) b.group(workerGroup); // (2)
		 * b.channel(NioSocketChannel.class); // (3)
		 * b.option(ChannelOption.SO_KEEPALIVE, true); // (4) b.handler(new
		 * ChannelInitializer<SocketChannel>() {
		 * 
		 * @Override public void initChannel(SocketChannel ch) throws Exception
		 * { ch.pipeline().addLast(new gameClientInitializer(surfaceHolder)); }
		 * });
		 * 
		 * try { // Start the client. ChannelFuture f = b.connect(HOST,
		 * PORT).sync(); // (5)
		 * 
		 * // Wait until the connection is closed.
		 * f.channel().closeFuture().sync(); } catch (Exception e) {
		 * Log.e("Exceptions", e.getMessage()); return; }
		 */
		/*
		 * EventLoopGroup group; Bootstrap b; group = new NioEventLoopGroup(); b
		 * = new Bootstrap();
		 * 
		 * // long previousFrameTime = System.currentTimeMillis();
		 * 
		 * b.group(group).channel(NioSocketChannel.class) .handler(new
		 * gameClientInitializer(surfaceHolder));
		 * 
		 * try {
		 * 
		 * Channel channel = b.connect(HOST, PORT).sync().channel();
		 * 
		 * } catch (Exception e) { Log.e("Exceptions", e.getMessage()); return;
		 * }
		 */
		/*
		 * Canvas canvas = null;
		 * 
		 * while (threadIsRunning) { try { // get Canvas for exclusive drawing
		 * from this thread canvas = surfaceHolder.lockCanvas(null);
		 * 
		 * // lock the surfaceHolder for drawing synchronized (surfaceHolder) {
		 * // long currentTime = System.currentTimeMillis(); // double
		 * elapsedTimeMS = currentTime - previousFrameTime; // totalElapsedTime
		 * += elapsedTimeMS / 1000.0; // updatePositions(elapsedTimeMS); //
		 * update game state view.drawGameElements(canvas); // draw using the
		 * canvas // previousFrameTime = currentTime; // update previous time }
		 * } finally { // display canvas's contents on the CannonView // and
		 * enable other threads to use the Canvas if (canvas != null)
		 * surfaceHolder.unlockCanvasAndPost(canvas); } } // end while
		 */
	} // end method run

}
