package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedFactoryInterface;
import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.io.BufferedReader;
import java.io.IOException;

public class FeedFactory implements FeedFactoryInterface {
    @Override
    public FeedInterface createFeed(BufferedReader in) throws IOException {
        Feed feed = new Feed();

        String line;
        String title = "";
        int countLine = 1;

        while ((line = in.readLine()) != null) {
            if(countLine %2 ==0){
                FeedItem feedItem = new FeedItem(title, line);
                feed.addItem(feedItem);
            }
            title = line;
            countLine++;
        }

        return feed;
    }
}
