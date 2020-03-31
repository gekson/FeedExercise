package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedMap implements FeedInterface {
    private Map<String, FeedItem> feedListMap;

    public FeedMap() {
        feedListMap = new HashMap<>();
    }

    @Override
    public void addItem(FeedItem item) {
        feedListMap.put(item.title, item);
    }

    @Override
    public Collection<String> listTitles() {
        return feedListMap.values().stream().map(e->e.getTitle()).collect(Collectors.toList());
    }

    @Override
    public FeedItem getItem(String title) {
        return feedListMap.get(title);
    }

    @Override
    public int numItems() {
        return feedListMap.size();
    }

    @Override
    public Collection<FeedItem> findItems(String keyword) {
        return feedListMap.values().stream().filter(e->e.getContent().contains(keyword)).collect(Collectors.toList());
    }
}
