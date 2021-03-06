/*
 * Innovation Care Team confidential
 * 
 * Source Materials
 * 
 * Copyright Innovation Care 2013, all rights reserved.
 */
package innovationcare.app.antibioticguidelines.ui;

import innovationcare.app.antibioticguidelines.CategoryMenu;
import innovationcare.app.antibioticguidelines.R;
import innovationcare.app.antibioticguidelines.R.id;
import innovationcare.app.antibioticguidelines.R.layout;
import innovationcare.app.antibioticguidelines.database.GuidelineDataAccess;
import innovationcare.app.antibioticguidelines.ui.adapter.MenuListAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Modification History
 * --------------------
 * 15-Jul-2013  Jiefeng  Initial version.
 * 
 */
/**
 * The activity class for the infection category list screen.
 *
 */
public class InfectionCategoryListActivity extends Activity {
	
	/**
	 * Data access object.
	 */
	private final GuidelineDataAccess dao = new GuidelineDataAccess(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infection_category_list);
		
		final ListView catMenuListView = 
				(ListView) findViewById(R.id.infectionCategoryList);
		
		// Open the database.
		dao.open();
		
		final ArrayList<CategoryMenu> catMenuList = 
				dao.readAllCategoryMenus();
		
		// Close the database;
		dao.close();
		
		final MenuListAdapter<CategoryMenu> adapter = 
				new MenuListAdapter<CategoryMenu>(
						this, R.layout.list_item_row, catMenuList);
		
		catMenuListView.setAdapter(adapter);
		
		// Click handler.
		catMenuListView.setOnItemClickListener(
				new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				 final CategoryMenu catMenu = 
						 (CategoryMenu) parent.getItemAtPosition(position);
				 
				 Intent intent = new Intent(parent.getContext(), 
						 InfectionListActivity.class);
				 intent.putExtra("categoryMenuId", catMenu.getId());
				 intent.putExtra("categoryMenuName", catMenu.getName());
				 startActivity(intent);
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.returnToHomeButton:
	            Intent intent = new Intent(this, MainActivity.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
