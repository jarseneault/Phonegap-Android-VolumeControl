/*
 * Phonegap VolumeControl Plugin for Android
 * Cordova 2.2.0
 * Email: manusimpson[at]gmail[dot]com
 * Author: Manuel Simpson
 * Date: 12/28/2012
 */

var exec = require('cordova/exec');

var VolumeControl = {
	setVolume: function(vol, successCallback, failureCallback, playSound){
		return exec(
			successCallback,
			failureCallback,
			'VolumeControl',
			'setVolume',
			[vol, playSound]
		);
	},
	getVolume: function(successCallback,failureCallback){
		return exec(
			successCallback,
			failureCallback,
			'VolumeControl',
			'getVolume',
			[]
		);
	},
	registerVolumeListener: function(listener, successCallback, failureCallback) {
		return exec(
			successCallback,
			failureCallback,
			'VolumeControl',
			'registerVolumeListener',
			[listener]
		);
	}
};

module.exports = VolumeControl;
