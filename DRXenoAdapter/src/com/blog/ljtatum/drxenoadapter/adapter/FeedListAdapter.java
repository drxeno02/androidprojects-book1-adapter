/** Project and code provided by Leonard Tatum
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-adapter */

package com.blog.ljtatum.drxenoadapter.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blog.ljtatum.drxenoadapter.R;
import com.blog.ljtatum.drxenoadapter.helper.FeedStructure;
import com.blog.ljtatum.drxenoadapter.utils.Utils;

public class FeedListAdapter extends ArrayAdapter<FeedStructure> {
	private static final String TAG = FeedListAdapter.class.getSimpleName();

	private List<FeedStructure> metaData = null;
	public FeedListAdapter(Activity activity, List<FeedStructure> metaDataParse) {		
		super(activity, 0, metaDataParse);
		this.metaData = metaDataParse;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;
		
		if (Utils.checkIfNull(view)) {
			Activity activity = (Activity) getContext();
			LayoutInflater inflater = activity.getLayoutInflater();
			view = inflater.inflate(R.layout.item_main, null);
			holder = new ViewHolder();
			
			holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
			holder.tvCreator = (TextView) view.findViewById(R.id.tv_creator);
			holder.tvDate = (TextView) view.findViewById(R.id.tv_date);
			holder.tvDesc = (TextView) view.findViewById(R.id.tv_desc);
			holder.iv = (ImageView) view.findViewById(R.id.iv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		try {
			holder.tvTitle.setText(metaData.get(position).getTitle());
			holder.tvCreator.setText("Creator: " + metaData.get(position).getCreator());
			holder.tvDesc.setText(metaData.get(position).getDesc());	
			
			//reformat current date into another format		
			if (!Utils.checkIfNull(metaData.get(position).getPubDate())) {
				String pubDate = metaData.get(position).getPubDate();
					
				/* You can even add characters such as @, !, ect to your date reformats
				 * SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy @ hh:mm a", Locale.ENGLISH);
				 * is an example */
				SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH);

				try {
					Date pDate = df.parse(pubDate);
					holder.tvDate.setText("Published: " + df.format(pDate));
				} catch (ParseException e) {
					Log.e(TAG, "Error parsing date");
					e.printStackTrace();
				}
			}
				
			//pull in ImgLink, if there is no image use static image
			if (!Utils.checkIfNull(metaData.get(position).getImgLink())) {
				URL url = new URL (metaData.get(position).getImgLink().toString());
				Log.i(TAG, metaData.get(position).getImgLink().toString());
				if (!Utils.checkIfNull(url)) {
					Bitmap img = getBitmapFromURL(url);
					holder.iv.setImageBitmap(img);
				} else {
					holder.iv.setBackgroundResource(R.drawable.img);
				}
			}
		} catch (MalformedURLException e) { 
			Log.e(TAG, "MalformedURLException");
			e.printStackTrace(); 
		} 
		return view;
	}
	
	private Bitmap getBitmapFromURL(URL url) {
		try { 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace(); 
	        return null; 
	    } 
	}
	
	private static class ViewHolder {
		private TextView tvTitle, tvCreator, tvDate, tvDesc;
		private ImageView iv;
	}
}
