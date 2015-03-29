/** Project and code provided by Leonard Tatum
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-adapter */

package com.blog.ljtatum.drxenoadapter.helper;

import java.util.ArrayList;
import java.util.List;

public class FeedStructure {

	private String title;
	private String link;
	private String pubDate;
	private String imgLink;
	private String creator;
	private String desc;
	private long item;

	//@param item set item
	public void setItem(long item) {
		this.item = item;
	}
	
	//@return item
	public long getItem() {
		return item;
	}

	//@param title set title
	public void setTitle(String title) {
		this.title = title;
	}
	
	//@return title
	public String getTitle() {
		return title;
	}
	
	//truncate title
	public String shortenTitle() {
		if (title.length() > 10) {
			return title.substring(0, 10) + "...";
		}
		return title;
	}
	
	//truncate description
	public String shortenDesc() {
		if (title.length() > 30) {
			return title.substring(0, 30) + "...";
		}
		return title;
	}
	
	//@param pubDate set pubDate
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	//@return pubDate
	public String getPubDate() {
		return pubDate;
	}
	
	//@param imgLink set imgLink
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	
	//@return imgLink
	public String getImgLink() {
		return imgLink;
	}
	
	//@param creator set creator
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	//@return creator
	public String getCreator() {
		return creator;
	}	
	
	//@param creator set URL
	public void setLink(String link) {
		this.link = link;
	}
	
	//@return URL
	public String getLink() {
		return link;
	}
	
	//@param desc set description
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	//@return description
	public String getDesc() {
		return desc;
	}
	
}

