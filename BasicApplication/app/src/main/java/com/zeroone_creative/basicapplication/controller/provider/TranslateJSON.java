package com.zeroone_creative.basicapplication.controller.provider;import java.util.HashMap;import java.util.Map;import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject;public class TranslateJSON {	//大量に画像を入手する際のパーサー	public static Object objectParse(JSONObject rootObje){		/*		try {			JSONArray productObjeArry =rootObje.getJSONArray("products");			for (int i=0; i < productObjeArry.length(); i++) {				JSONObject itemObje = productObjeArry.getJSONObject(i);				JSONArray photoArray = itemObje.getJSONArray("photos");				//画像がない場合は弾く				if(photoArray.length()==0)continue;				Item item;				JSONObject photoObje = photoArray.getJSONObject(photoArray.length()-1);				item = new Item(itemObje.getInt("id"),photoObje.getString("image_url"));				JSONObject largePhotoObje = photoArray.getJSONObject(0);				item.picUrlLearge=largePhotoObje.getString("image_url");				try{					item.price = itemObje.getInt("price");					item.sale_price = itemObje.getInt("sale_price");					item.discountLate = itemObje.getInt("discount_rate");					item.wanted = itemObje.getBoolean("wanted");					JSONObject storeObje = itemObje.getJSONObject("store");					item.store = storeObje.getString("name");				}catch(JSONException e) {				    e.printStackTrace();				}				itemList.add(item);		    }		} catch (JSONException e) {		    e.printStackTrace();		}		*/		return new Object();	}}