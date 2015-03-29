/** Project and code provided by Leonard Tatum
 * For any questions or comments regarding the use of this code
 * or issues please contact LJTATUM@HOTMAIL.COM
 * ONLINE MOBILE TUTORIALS: ljtatum.blog.com/
 * GITHUB: https://github.com/drxeno02/androidprojects-book1-adapter */

package com.blog.ljtatum.drxenoadapter.helper;

public class FeedStructure {

	private String mTitle;
	private String mLink;
	private String mPubDate;
	private String mImgLink;
	private String mCreator;
	private String mDesc;
	private long mItem;

	//@param item set item
	public void setItem(long item) {
		this.mItem = item;
	}
	
	//@return item
	public long getItem() {
		return mItem;
	}

	//@param title set title
	public void setTitle(String title) {
		this.mTitle = shortenTitle(title);
	}
	
	//@return title
	public String getTitle() {
		return mTitle;
	}
	
	// truncate title
	public String shortenTitle(String title) {
		if (title.length() > 30) {
			return title.substring(0, 30) + "...";
		}
		return title;
	}
	
	// truncate description
	public String shortenDesc(String desc) {
		if (desc.length() > 100) {
			return desc.substring(0, 100) + "...";
		}
		return desc;
	}
	
	//@param pubDate set pubDate
	public void setPubDate(String pubDate) {
		this.mPubDate = pubDate;
	}
	
	//@return pubDate
	public String getPubDate() {
		return mPubDate;
	}
	
	//@param imgLink set imgLink
	public void setImgLink(String imgLink) {
		this.mImgLink = imgLink;
	}
	
	//@return imgLink
	public String getImgLink() {
		return mImgLink;
	}
	
	//@param creator set creator
	public void setCreator(String creator) {
		this.mCreator = creator;
	}
	
	//@return creator
	public String getCreator() {
		return mCreator;
	}	
	
	//@param creator set URL
	public void setLink(String link) {
		this.mLink = link;
	}
	
	//@return URL
	public String getLink() {
		return mLink;
	}
	
	//@param desc set description
	public void setDesc(String desc) {
		this.mDesc = shortenDesc(desc);
	}
	
	//@return description
	public String getDesc() {
		return mDesc;
	}
	
}

