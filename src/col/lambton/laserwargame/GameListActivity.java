package col.lambton.laserwargame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameListActivity extends DialogFragment {
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	
	
	builder.setTitle("Larser-War Game").setItems(
			AGameSession.namelist,
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// The 'which' argument contains the index position
					// of the selected item
					gameClientHandler.game
							.setGameid(AGameSession.namelist[which]);
					
					//condDialog = false;

				}
			});
	AlertDialog dialogList = builder.create();
	setCancelable(false);
	return dialogList;
	}

}
