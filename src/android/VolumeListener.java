/*
 * Phonegap VolumeControl Plugin for Android - Volume Listener
 * Cordova 3.0.0+
 * Author: Jamie Arseneault
 * Email: jamie[at]jamiearseneault[dot]com
 * Date: 12/23/2014
 */

package com.develcode.plugins.volumeControl;

import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.media.AudioManager;
import android.view.KeyEvent;

import java.util.ArrayList;

public class VolumeListener extends BroadcastReceiver {

	private static final String TAG = "VolumeListener";

	private VolumeControl volumeControl;
	private ArrayList<CallbackContext> callbacks;

	public VolumeListener(CallbackContext initial_callback, VolumeControl volumeControl) {
		this.volumeControl = volumeControl;
		callbacks = new ArrayList<CallbackContext>();

		if(initial_callback != null)
		{
			add(initial_callback);
		}
		else
		{
			LOG.d(TAG, "Null initial callback in listener");
		}
		LOG.d(TAG, "VolumeListener instantiated");
	}

	public void add(CallbackContext callback) {
		callbacks.add(callback);
		LOG.d(TAG, "Callback added");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		LOG.d(TAG, "Received intent: " + action);

		if (Intent.ACTION_MEDIA_BUTTON.equals(action)) {
			KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			int keycode = event.getKeyCode();

			if(keycode != KeyEvent.KEYCODE_VOLUME_DOWN && keycode != KeyEvent.KEYCODE_VOLUME_UP) {
				return;
			}

			int volume = volumeControl.getCurrentVolume();
			PluginResult result = new PluginResult(PluginResult.Status.OK, volume);
			result.setKeepCallback(true);

			for(CallbackContext callback : callbacks) {
				callback.sendPluginResult(result);
			}
		}
	}
}
