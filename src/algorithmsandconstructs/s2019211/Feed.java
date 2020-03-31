package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Feed implements FeedInterface {
    private List<FeedItem> feedList;

    public Feed() {
        feedList = new ArrayList<>();
    }

    @Override
    public void addItem(FeedItem item) {
        feedList.add(item);
    }

    @Override
    public Collection<String> listTitles() {
        feedList = quickSortFeedItem(feedList);
        List<String> list = new ArrayList<>();
        for (FeedItem feedItem : quickSortFeedItem(feedList)) {
            list.add(feedItem.getTitle());
        }
        return list;
    }

    @Override
    public FeedItem getItem(String title) {
        String[] titles = listTitles().toArray(new String[0]);
        int item = binarySearch(titles, 0, titles.length - 1, title);
        return item >= 0 ? feedList.get(item) : null;
    }

    @Override
    public int numItems() {
        return feedList.size();
    }

    @Override
    public Collection<FeedItem> findItems(String keyword) {
        List<FeedItem> feedItems = new ArrayList<>();
        Collection<String> list = listTitles();
        for (String title: list) {
            String[] keys = title.split(" ");
            List<String> listKeys = Arrays.asList(keys);
            keys = quickSort(listKeys).toArray(new String[list.size()]);
            for (String key: keys){
                int item = binarySearch(keys, 0, keys.length - 1, keyword);
                if(item >= 0) {
                    feedItems.add(getItem(title));
                    break;
                }
            }
        }

        return feedItems;
    }

    private int binarySearch(String[] titles, int low, int high, String key) {
        if(low > high)
            return -low - 1;
        int mid = (low + high) / 2;
        if (titles[mid].compareTo(key) == 0)
            return mid;
        if(titles[mid].compareTo(key) > 0)
            return binarySearch(titles, low, mid-1, key);
        else
            return binarySearch(titles, mid +1, high, key);
    }

    private List<String> quickSort(List<String> input){

        if(input.size() <= 1){
            return input;
        }

        int middle = (int) Math.ceil((double)input.size() / 2);
        String pivot = input.get(middle);

        List<String> less = new ArrayList<String>();
        List<String> greater = new ArrayList<String>();

        for (int i = 0; i < input.size(); i++) {
            if(input.get(i).compareTo(pivot) <= 0){
                if(i == middle){
                    continue;
                }
                less.add(input.get(i));
            }
            else{
                greater.add(input.get(i));
            }
        }

        return concatenate(quickSort(less), pivot, quickSort(greater));
    }

    private List<String> concatenate(List<String> less, String pivot, List<String> greater){

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < less.size(); i++) {
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = 0; i < greater.size(); i++) {
            list.add(greater.get(i));
        }

        return list;
    }

    private List<FeedItem> quickSortFeedItem(List<FeedItem> input){

        if(input.size() <= 1){
            return input;
        }

        int middle = (int) Math.ceil((double)input.size() / 2);
        FeedItem pivot = input.get(middle);

        List<FeedItem> less = new ArrayList<FeedItem>();
        List<FeedItem> greater = new ArrayList<FeedItem>();

        for (int i = 0; i < input.size(); i++) {
            if(input.get(i).getTitle().compareTo(pivot.getTitle()) <= 0){
                if(i == middle){
                    continue;
                }
                less.add(input.get(i));
            }
            else{
                greater.add(input.get(i));
            }
        }

        return concatenate(quickSortFeedItem(less), pivot, quickSortFeedItem(greater));
    }

    private List<FeedItem> concatenate(List<FeedItem> less, FeedItem pivot, List<FeedItem> greater){

        List<FeedItem> list = new ArrayList<FeedItem>();

        for (int i = 0; i < less.size(); i++) {
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = 0; i < greater.size(); i++) {
            list.add(greater.get(i));
        }

        return list;
    }
}