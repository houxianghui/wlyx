package com.blue.tools.login;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.blue.common.User;

public class VeryCD {
	private static Pattern p = Pattern.compile("play_frame\"\\).src=\"(.*?)\"");
	public static void login( User user){
		 HttpClient client = new DefaultHttpClient();
	       try{
	    	   HttpGet get = new HttpGet("http://game.verycd.com/hero/");
	    	   HttpResponse response = client.execute(get);
	    	   String s = EntityUtils.toString(response.getEntity(), "utf-8");
	    	   
	    	   HttpPost post = new HttpPost("http://secure.verycd.com/signin");
	    	   List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    	   nvps.add(new BasicNameValuePair("username", user.getUserName()));
	    	   nvps.add(new BasicNameValuePair("password", user.getPassword()));
	    	   nvps.add(new BasicNameValuePair("login_submit", "µÇÂ¼"));
	    	   nvps.add(new BasicNameValuePair("continue", "http://game.verycd.com/hero/"));
	    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
	    	   response = client.execute(post);
	    	   EntityUtils.consume(response.getEntity());
	    	   response = client.execute(new HttpPost("http://www.verycd.com/ajax/member?m=loginInfo&game=50hero"));
	    	   EntityUtils.consume(response.getEntity());
	    	   response = client.execute(new HttpPost("http://www.verycd.com/game/ajax/servers?game=50hero"));
	    	   EntityUtils.consume(response.getEntity());
	    	   get = new HttpGet("http://secure.verycd.com/signin?ak=50hero&sid=s4.verycd.9wee.com");
	    	   response = client.execute(get);
	    	   s = EntityUtils.toString(response.getEntity(), "utf-8");
	    	  
	    	   Matcher m = p.matcher(s);
	    	   if(m.find()){
	    		  get = new HttpGet(m.group(1));
	    		  response = client.execute(get);
	    		  EntityUtils.consume(response.getEntity());
	    	   }
	    	   user.setClient(client);
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
	}
	
}
