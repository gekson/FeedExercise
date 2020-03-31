package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.util.*;
import java.util.stream.Collectors;

public class FeedStream implements FeedInterface {
    private List<FeedItem> feedList;

    public FeedStream() {
        feedList = new ArrayList<>();
    }

    @Override
    public void addItem(FeedItem item) {
        feedList.add(item);
    }

    @Override
    public Collection<String> listTitles() {
        return feedList.stream().map(e->e.getTitle()).collect(Collectors.toList());
    }

    @Override
    public FeedItem getItem(String title) {
        return feedList.stream().filter(e->e.getTitle().equals(title)).findAny().get();
    }

    @Override
    public int numItems() {
        return feedList.size();
    }

    @Override
    public Collection<FeedItem> findItems(String keyword) {
        return feedList.stream().filter(e->e.getContent().contains(keyword)).collect(Collectors.toList());
    }
}

