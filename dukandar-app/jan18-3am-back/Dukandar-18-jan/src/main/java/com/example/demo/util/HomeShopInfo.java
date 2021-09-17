package com.example.demo.util;

import java.time.LocalTime;

public class HomeShopInfo {

	private LocalTime opentime;
	private LocalTime closetime;
	private String shopname;
	private Integer shopid;
	private String ShopPrifilepic;
	private String Attractionpic;
	private int rating;
	private int like;
	
	public HomeShopInfo() {
		// TODO Auto-generated constructor stub
	}
	



	public HomeShopInfo(LocalTime opentime, LocalTime closetime, String shopname, Integer shopid, String shopPrifilepic,
			String attractionpic, int rating, int like) {
		super();
		this.opentime = opentime;
		this.closetime = closetime;
		this.shopname = shopname;
		this.shopid = shopid;
		ShopPrifilepic = shopPrifilepic;
		Attractionpic = attractionpic;
		this.rating = rating;
		this.like = like;
	}




	public int getLike() {
		return like;
	}




	public void setLike(int like) {
		this.like = like;
	}




	public LocalTime getOpentime() {
		return opentime;
	}

	public void setOpentime(LocalTime opentime) {
		this.opentime = opentime;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public LocalTime getClosetime() {
		return closetime;
	}

	public void setClosetime(LocalTime closetime) {
		this.closetime = closetime;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShopPrifilepic() {
		return ShopPrifilepic;
	}

	public void setShopPrifilepic(String shopPrifilepic) {
		ShopPrifilepic = shopPrifilepic;
	}

	public String getAttractionpic() {
		return Attractionpic;
	}

	public void setAttractionpic(String attractionpic) {
		Attractionpic = attractionpic;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
	@Override
	public String toString() {
		return "HomeShopInfo [opentime=" + opentime + ", closetime=" + closetime + ", shopname=" + shopname
				+ ", ShopPrifilepic=" + ShopPrifilepic + ", Attractionpic=" + Attractionpic + ", rating=" + rating
				+ "]";
	}
	
	
}
