package edu.csupomona.cs480.links.provider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LinkManager {
	private Document doc;
	
	public LinkManager() 
	{
	}
	
	public void connect(String url)
	{
		try {
			doc = Jsoup.connect(url).get();
		}
		catch(IOException i)
		{
			System.out.println(i.toString());
		}
	}
	
	
	public String printImageDetails()
	{
		
		Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		
		String str = "";
		
		for(Element image: images) {
			String imgSrc = image.attr("src");
			str += "src : <a href = \"" + imgSrc + "\">" + imgSrc + "\"</a><br>";
			str += "<br>height : " + image.attr("height");
			str += "<br>width : " + image.attr("width");
			str += "<br>alt : " + image.attr("alt") + "<br><br><br><br><br>";	
		}
		
		return str;
	}
}
