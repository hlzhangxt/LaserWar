package col.lambton.laserwargame;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class NewJoinDlgActivity extends DialogFragment {
	private String gameWay = "NEW";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Add the buttons
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
			 getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
			 View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
			 View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
			 View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
			 | View.SYSTEM_UI_FLAG_IMMERSIVE);*/
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.hide();
		
		builder.setTitle("Larser-War Game");

		builder.setNegativeButton(R.string.newgame,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button
						gameClientHandler.game.ID = 1;
						NettyThread.channel.write("NEWGAME\r\n");

					}
				});
		if (AGameSession.namelist.length > 0) {
			/*
			 * builder.setPositiveButton(R.string.existgame, new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int id) {
			 * 
			 * gameWay = "JOIN"; // User cancelled the dialog
			 * 
			 * } });
			 */

			builder.setItems(AGameSession.namelist,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// The 'which' argument contains the index position
							// of the selected item
							gameClientHandler.game
									.setGameid(AGameSession.namelist[which]);
							gameWay = "JOIN";
							gameClientHandler.game.ID = 2;

							NettyThread.channel.write("JOIN:"
									+ gameClientHandler.game.getGameid()
									+ "\r\n");

							// condDialog = false;

						}
					});

			// Set other dialog properties
		}
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		setCancelable(false);
		return dialog;
	}

	public String getGameWay() {
		return gameWay;
	}

	public void setGameWay(String gameWay) {
		this.gameWay = gameWay;
	}

}
