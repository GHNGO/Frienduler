package edu.csupomona.cs480.links.provider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LinkManager {
	private Document doc;
	
	public LinkManager(String url) 
	{
		try {
			doc = Jsoup.connect(url).get();
		}
		catch(IOException i)
		{
			System.out.println(i.toString());
		}
	}
	
	public void printImages()
	{
		
		Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		
		for(Element image: images) {
			System.out.println("\nsrc : " + image.attr("src"));
			System.out.println("height : " + image.attr("height"));
			System.out.println("width : " + image.attr("width"));
			System.out.println("alt : " + image.attr("alt"));	
		}
		
	}
}
