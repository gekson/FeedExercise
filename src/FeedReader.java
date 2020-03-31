
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.DoubleToIntFunction;

import algorithmsandconstructs.FeedFactoryInterface;
import algorithmsandconstructs.FeedInterface;

// Modify this to match your package
import algorithmsandconstructs.s2019211.FeedFactory;

public class FeedReader {
	
	public static void main(String[] args) throws IOException {
	
		// This will produce an error until you create your own class
		// FeedFactory and fix the import at line 7 
		FeedFactoryInterface factory = new FeedFactory();
    
		
		String file = "test.rss";
		BufferedReader in = new BufferedReader(new FileReader(file));
    
		FeedInterface feed = factory.createFeed(in);
    
		System.out.println("=== News items ===");
		System.out.println(feed.getItem("BREAKING NEWS").getContent());
		System.out.println(feed.getItem("NOT QUITE SOME BREAKING NEWS").getContent());
		System.out.println(feed.getItem("EVEN LESS IMPORTANT NEWS").getContent());
		feed.findItems("BREAKING").stream().forEach(e-> System.out.println(e.getContent()));
		
		for(String title : feed.listTitles()) {
			System.out.println(title);
		}
		
	}
}
