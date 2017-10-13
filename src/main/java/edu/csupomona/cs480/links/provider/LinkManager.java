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
	
	
	public String printImages()
	{
		
		Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		
		String str = "";
		
		for(Element image: images) {
			str += "\nsrc : " + image.attr("src") + "\n";
			str += "height : " + image.attr("height") + "\n";
			str += "width : " + image.attr("width") + "\n";
			str += "alt : " + image.attr("alt") + "\n";	
		}
		
		return str;
	}
}
