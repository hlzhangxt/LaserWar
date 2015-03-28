package col.lambton.laserwargame;

import android.app.Fragment;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class LaserWarGameFragment extends Fragment {
	
	private LaserwarView gameView;
	
	
	     
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_game, container, false);
		
		gameView = (LaserwarView) view.findViewById(R.id.laserwarView);
		
		return view;
		
		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		gameView.stopGame();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		gameView.releaseResources();
	}

}
