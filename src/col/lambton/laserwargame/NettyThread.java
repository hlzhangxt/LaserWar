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
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class NettyThread extends Thread {

	static final String HOST = "IDEVUSR011.FRANKENI.COM";
	static final int PORT = 8998;
	public static Channel channel = null;
	public static ChannelFuture lastWriteFuture = null;

	LaserwarView view;
	Activity activity;
	private static final String TAG = "Netty Thread";

	public NettyThread(LaserwarView v, Activity a) {

		view = v;
		activity = a;

	}

	public void run() {
		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {

				return Channels.pipeline(
						  new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()),
						//  new StringDecoder(),
					
						  new StringEncoder(),

				new gameClientHandler(view));
			}
		});

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);

		try {
			channel = bootstrap.connect(new InetSocketAddress(HOST, PORT))
					.sync().getChannel();
			((MainActivity) activity).setChannel(channel);
		} catch (Exception e) {
			Log.e(TAG, "Thread interrupted", e);
		}

		// **********************************************************************

		// ***************************************************************************************

	}

}
