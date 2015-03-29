package col.lambton.laserwargame;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	static final String HOST = "IDEVUSR011.FRANKENI.COM";
	static final int PORT = 8998;
	private Channel channel = null;
	private ChannelFuture lastWriteFuture = null;

	String gameWay = "NEW";
	
	boolean firstDialog = true;
	boolean secondDialog = true;
	private static final String TAG = "Main Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//  requestWindowFeature(Window.FEATURE_NO_TITLE);
		//  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  
		  
		  
		  
		  View decorView = getWindow().getDecorView();
			// Hide the status bar.
			int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
			decorView.setSystemUiVisibility(uiOptions);
			// Remember that you should never show the action bar if the
			// status bar is hidden, so hide that too if necessary.
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		 
			setContentView(R.layout.activity_main);
		
	/*	
		AGameSession.activity = this;

		new NettyThread((LaserwarView) findViewById(R.id.laserwarView), this)
				.start();
		// *************************************************************************************

		while (!AGameSession.isGotGames())
			;*/
   /*
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setTitle("Larser-War Game");
		builder.setCancelable(false);
		builder.setNegativeButton(R.string.newgame,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button
						firstDialog = false;
					}
				});
		if (AGameSession.namelist.length > 0) {
			builder.setPositiveButton(R.string.existgame,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							gameWay = "JOIN";
							// User cancelled the dialog
							firstDialog = false;
						}
					});
			// Set other dialog properties
		}
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.setCancelable(false);
		dialog.show();
		
		
		*/
		
		
	    
	    
	    
		
		

		
		
		
		
	//	while (firstDialog);
		
		/*
		 * DlgMain dialog = new DlgMain(this, true);
		 * dialog.setTitle("Larser-War Game");
		 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 * 
		 * DlgList dialogList = new DlgList();
		 * dialogList.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 * 
		 * dialog.setVisible(true);
		 */
        
	//	if (gameWay.equals("NEW"))
		//	gameClientHandler.game.ID = 1;
		//else
		//	gameClientHandler.game.ID = 2;

		//if (gameClientHandler.game.ID == 1)
			;// lastWriteFuture = channel.write("NEWGAME\r\n");
	//	else {
			
		/*	
			GameListActivity listActivity = new GameListActivity();
		    
			listActivity.show(manager, "DialogActivity");
			
			*/

			/*
			AlertDialog.Builder builderList = new AlertDialog.Builder(this);
			builderList.setCancelable(false);
			builderList.setTitle("Larser-War Game").setItems(
					AGameSession.namelist,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// The 'which' argument contains the index position
							// of the selected item
							gameClientHandler.game
									.setGameid(AGameSession.namelist[which]);
							
							secondDialog = false;

						}
					});
			AlertDialog dialogList = builderList.create();
			dialogList.setCancelable(false);
			dialogList.show();
   
			//while (secondDialog);
			// dialogList.putAllGames();
			// dialogList.setVisible(true);

			// lastWriteFuture = channel.write("JOIN:"
			// + gameClientHandler.game.getGameid() + "\r\n"); */

		}

		// /
		// *****************************************************************************************/
//	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		LaserwarView view = (LaserwarView)findViewById(R.id.laserwarView);
		view.stopGame();
		view.releaseResources();
		
		
		super.onPause();
		finish();
	}
	
	
}
