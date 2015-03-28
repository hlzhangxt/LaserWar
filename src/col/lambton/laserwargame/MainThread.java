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

	private SurfaceHolder surfaceHolder; // for manipulating canvas
	private boolean threadIsRunning = true; // running by default
	LaserwarView view;
	// private Channel channel;
	// private ChannelFuture lastWriteFuture;

	static final String HOST = "IDEVUSR011.FRANKENI.COM";
	static final int PORT = 8998;

	// initializes the surface holder
	public MainThread(SurfaceHolder holder, LaserwarView v) {
		surfaceHolder = holder;

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
		
		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				
				return Channels.pipeline(
												
						new gameClientHandler(surfaceHolder));
			}
		});

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);

		bootstrap.connect(new InetSocketAddress(HOST, PORT));
		
		/*
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		Bootstrap b = new Bootstrap(); // (1)
		b.group(workerGroup); // (2)
		b.channel(NioSocketChannel.class); // (3)
		b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
		b.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new gameClientInitializer(surfaceHolder));
			}
		});

		try {
			// Start the client.
			ChannelFuture f = b.connect(HOST, PORT).sync(); // (5)

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			Log.e("Exceptions", e.getMessage());
			return;
		}
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

		Canvas canvas = null;

		while (threadIsRunning) {
			try {
				// get Canvas for exclusive drawing from this thread
				canvas = surfaceHolder.lockCanvas(null);

				// lock the surfaceHolder for drawing
				synchronized (surfaceHolder) {
					// long currentTime = System.currentTimeMillis();
					// double elapsedTimeMS = currentTime - previousFrameTime;
					// totalElapsedTime += elapsedTimeMS / 1000.0;
					// updatePositions(elapsedTimeMS); // update game state
					view.drawGameElements(canvas); // draw using the canvas
					// previousFrameTime = currentTime; // update previous time
				}
			} finally {
				// display canvas's contents on the CannonView
				// and enable other threads to use the Canvas
				if (canvas != null)
					surfaceHolder.unlockCanvasAndPost(canvas);
			}
		} // end while
	} // end method run

}
