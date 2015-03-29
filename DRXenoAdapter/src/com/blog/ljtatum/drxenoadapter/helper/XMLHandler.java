/** Project and code provided by Leonard Tatum
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-adapter */

package com.blog.ljtatum.drxenoadapter.helper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

public class XMLHandler extends DefaultHandler {
	private static final String TAG = XMLHandler.class.getSimpleName();

	private FeedStructure feedStr = new FeedStructure();
	private List<FeedStructure> rssList = new ArrayList<FeedStructure>();
	private StringBuilder chars;
	
	private int acticlesAdded = 0;
	
	/* The number of articles is limited to 20 server side on 
	 * my blog site, ljtatum.blog.com. This is why regardless of what 
	 * the ARTICLES_LIMIT is value > 20 it will only display 20 items */
	private static final int ARTICLES_LIMIT = 20;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) 
			throws SAXException {	
		// TODO Auto-generated method stub
		chars = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		
		if (localName.equalsIgnoreCase("title")) {
			feedStr.setTitle(chars.toString());
			Log.i(TAG, feedStr.getTitle());
		} else if (localName.equalsIgnoreCase("link")) {
			feedStr.setLink(chars.toString());
			Log.i(TAG, feedStr.getLink());
		} else if (localName.equalsIgnoreCase("pubDate")) {
			feedStr.setPubDate(chars.toString());
			Log.i(TAG, feedStr.getPubDate());
		} else if (localName.equalsIgnoreCase("creator")) {
			feedStr.setCreator(chars.toString());
			Log.i(TAG, feedStr.getCreator());
		} else if (localName.equalsIgnoreCase("description")) {
			feedStr.setDesc(chars.toString());
			Log.i(TAG, feedStr.getDesc());
		} else if (localName.equalsIgnoreCase("src")) {
			feedStr.setImgLink(chars.toString());
			Log.i(TAG, feedStr.getImgLink());
		}
		
		if (localName.equalsIgnoreCase("item")) {
			rssList.add(feedStr);
			feedStr = null;
			acticlesAdded++;
			if (acticlesAdded >= ARTICLES_LIMIT) {
				throw new SAXException();
			}
		}
	}	
	
	@Override
	public void characters(char ch[], int start, int length) {
		chars.append(new String(ch, start, length));
	}

	public List<FeedStructure> getLatestArticles(String feedUrl) {
		URL url = null;
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			url = new URL(feedUrl);
			xr.setContentHandler(this);
			xr.parse(new InputSource(url.openStream()));
		} catch (IOException e) { 
			Log.e(TAG, "IOException");
			e.printStackTrace();
		} catch (SAXException e) { 
			Log.e(TAG, "SAXException");
			e.printStackTrace();
		} catch (ParserConfigurationException e) { 
			Log.e(TAG, "ParserConfiguration");
			e.printStackTrace();
		}	
		return rssList;	
	}
}
