/*
 * Innovation Care Team confidential
 * 
 * Source Materials
 * 
 * Copyright Innovation Care 2013, all rights reserved.
 */
package innovationcare.app.antibioticguidelines.ui;

import innovationcare.app.antibioticguidelines.R;
import innovationcare.app.antibioticguidelines.UpgradeTask;
import innovationcare.app.antibioticguidelines.cloud.UpdateUtils;
import innovationcare.app.antibioticguidelines.database.GuidelineDataAccess;
<<<<<<< HEAD
import android.app.ActionBar;
import android.app.Activity;
=======

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
>>>>>>> bac665e2a53258f4685a4814cf9b48a49db1fac1
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.kumulos.android.jsonclient.Kumulos;
import com.kumulos.android.jsonclient.ResponseHandler;

/*
 * Modification History
 * --------------------
 * 15-Jul-2013  Jiefeng  Initial version.
 * 
 */
/**
 * The activity class for the home screen.
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		
		Kumulos.initWithAPIKeyAndSecretKey("z1y3x0sjyvsqwykcmpq0fd05rhcxr7zx",
				"r5dsh8d8", this);

		// Store the update info in the shared preferences.
		SharedPreferences settings = getSharedPreferences(
				"AntibioticAppSettings", 0);
		boolean isUpdateRequired = settings
				.getBoolean("isUpdateRequired", true);
		if (isUpdateRequired) {

			GuidelineDataAccess dao = new GuidelineDataAccess(MainActivity.this);
			UpdateUtils.getAllDataFromCloud(dao);

			dao.close();

			// Resetting to false.
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("isUpdateRequired", false);
			// Commit the edits!
			editor.commit();
		}
	}

	/**
	 * The on-click method for the "Contact Us" button to open the Contact Us
	 * screen.
	 * 
	 */
	public void openContactUsScreen(View v) {
		Intent intent = new Intent(this, ContactUsActivity.class);
		startActivity(intent);
	}

	public void openInfectionCategoryScreen(View v) {
		Intent intent = new Intent(this, InfectionCategoryListActivity.class);
		startActivity(intent);
	}

	public void openInteractionScreen(View v) {
		Intent intent = new Intent(this, InteractionActivity.class);
		startActivity(intent);
	}

	public void openCalculatorListScreen(View v) {
		Intent intent = new Intent(this, CalculatorListActivity.class);
		startActivity(intent);
	}

	public void openAntibioticListScreen(View v) {
		Intent intent = new Intent(this, AntibioticListActivity.class);
		startActivity(intent);
	}

	public void onUpgrade(View v) {
<<<<<<< HEAD
		update();
	}
	
	private void update() {
		UpgradeTask upgrade = new UpgradeTask(this);
		upgrade.execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//	    // Handle item selection
//	    switch (item.getItemId()) {
//	        case R.id.returnToHomeButton:
//	            Intent intent = new Intent(this, MainActivity.class);
//	            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//	            startActivity(intent);
//	            return true;
//	        default:
//	            return super.onOptionsItemSelected(item);
//	    }
		
		update();
		return true;
	}
	
	public void search(View v) {
		onSearchRequested();
=======
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final Context con = this;
		HashMap<String, String> param = new HashMap<String, String>();
		Kumulos.call("getVersion", param, new ResponseHandler() {
			@Override
			public void didCompleteWithResult(Object result) {
				int curVersion = Integer.parseInt(result.toString());
				GuidelineDataAccess dao;
				SQLiteDatabase dbByPath=SQLiteDatabase.openOrCreateDatabase("/data/data/innovationcare.app.antibioticguidelines/databases/AntibioticApp.db", null);
				int localversion=dbByPath.getVersion();
				dbByPath.close();
				dao= new GuidelineDataAccess(con,localversion);
				if (localversion == curVersion) {
					adb.setTitle("Upgrade")
							.setMessage(
									"Do not need Upgrade!Current version is:"
											+ curVersion)
							.setPositiveButton("OK", null).show();
				} else {
					UpgradeTask upgrade = new UpgradeTask(con, curVersion);
					upgrade.execute();
					dao.setVersion(curVersion);
					Log.e("4", "" + dao.getVersion());
				}
			}

		});
>>>>>>> bac665e2a53258f4685a4814cf9b48a49db1fac1
	}

}
