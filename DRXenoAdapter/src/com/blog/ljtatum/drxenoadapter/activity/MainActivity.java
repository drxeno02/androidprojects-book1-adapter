/** Project and code provided by Leonard Tatum
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-adapter */

package com.blog.ljtatum.drxenoadapter.activity;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.blog.ljtatum.drxenoadapter.R;
import com.blog.ljtatum.drxenoadapter.adapter.FeedListAdapter;
import com.blog.ljtatum.drxenoadapter.helper.FeedStructure;
import com.blog.ljtatum.drxenoadapter.helper.XMLHandler;
import com.blog.ljtatum.drxenoadapter.utils.Utils;

public class MainActivity extends Activity implements OnItemClickListener {
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private ListView lv;
	private static List<FeedStructure> listFeedStructure;
	private FeedListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getIds();
	}
	
	// instantiate views
	private void getIds() {
		lv = (ListView) findViewById(R.id.list);
		lv.setVerticalFadingEdgeEnabled(true);
		lv.setOnItemClickListener(this);
		RssFeedTask rssTask = new RssFeedTask();
		rssTask.execute();		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Log.i(TAG, "redirecting user: " + listFeedStructure.get(pos).getLink());
		String webLink = listFeedStructure.get(pos).getLink();
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(webLink));
		startActivity(i);
	}
	
	// setup AsyncTask
	private class RssFeedTask extends AsyncTask<String, Void, String> {
		
		private ProgressDialog Dialog;
		String response = "";
		
		@Override
		protected void onPreExecute() {
			Dialog = new ProgressDialog(MainActivity.this);
			Dialog.setMessage("Loading Feeds...");
			Dialog.show();
		}
		
		@Override 
		protected String doInBackground(String... urls) {
			try {
				String feed = "http://feeds.feedburner.com/drxeno/home";
				XMLHandler xh = new XMLHandler();
				listFeedStructure = xh.getLatestArticles(feed);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return response;		
		}
		
		@Override
		protected void onPostExecute(String result) {
			if (!Utils.checkIfNull(listFeedStructure)) {
				adapter = new FeedListAdapter(MainActivity.this, listFeedStructure);
				lv.setAdapter(adapter);
			}
			Dialog.dismiss();
		}	
	}		
}
