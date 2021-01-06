import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQuery 

{

	public String searchKeyword;

	public String url;

	public String content;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=25";

	}

	

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public HashMap<String, String> query() throws IOException
	 {
	  if(content==null)
	  {
	   content= fetchContent();
	  }
	  HashMap<String, String> retVal = new HashMap<String, String>();
	  Document doc = Jsoup.parse(content);
	  ///System.out.println(doc.text());
	  Elements lis = doc.select("div");
	  ///System.out.println(lis);
	  lis = lis.select(".kCrYT");//.kCrYT .yuRUbf class�W�� �w�q�Ρ�
	  /// System.out.println(lis.size());
	  for(Element li : lis)
	  {
	   try 
	   {
	    String title = li.select("a").get(0).select(".vvjwJb").text();//li.select("h3").text();
	    String citeUrl = "";
	    if(title.length()>2)
	    {
	     if(li.select("a").get(0).attr("href").contains("http://")||li.select("a").get(0).attr("href").contains("https://"))
	     {
	      citeUrl = li.select("a").get(0).attr("href");//li.select("a").get(0).attr("href");
	      if(citeUrl.substring(citeUrl.indexOf("&")-1,citeUrl.indexOf("&")).equals("/"))
	      {
	       citeUrl=URLDecoder.decode(citeUrl.substring(citeUrl.indexOf('=') + 1, citeUrl.indexOf('&')-1), "UTF-8");
	       retVal.put(title, citeUrl);
	      }
	      citeUrl=URLDecoder.decode(citeUrl.substring(citeUrl.indexOf('=') + 1, citeUrl.indexOf('&')), "UTF-8");
	      //System.out.println(title + ","+citeUrl);//absUrl
	      retVal.put(title, citeUrl);
	     }
	    }
	    
	   } catch (IndexOutOfBoundsException e) {
//	    e.printStackTrace();
	   }
	  }
	  return retVal;
	 }

	

	

}
