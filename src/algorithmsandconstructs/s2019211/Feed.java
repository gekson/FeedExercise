package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.util.*;

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
        mergeSortFeedItem(feedList);
        List<String> list = new ArrayList<>();
        for (FeedItem feedItem : feedList) {
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
            mergeSortString(keys);
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

    private void mergeSortString(String[] array) {

        if (array.length > 1) {

            int firstHalfSize = array.length / 2;
            String[] firstHalf = new String[firstHalfSize];
            System.arraycopy(array, 0, firstHalf, 0, firstHalfSize);
            mergeSortString(firstHalf);

            mergeSortString(firstHalf);


            int secondHalfSize = array.length - firstHalfSize;
            String[] secondHalf = new String[secondHalfSize];
            System.arraycopy(array, firstHalfSize, secondHalf, 0, secondHalfSize);
            mergeSortString(secondHalf);

            merge(firstHalf, secondHalf, array);

        }
    }

    public void merge(String[] a, String[] b, String[] s) {

        int counterA = 0;
        int counterB = 0;
        int counterS = 0;

        while(counterA < a.length && counterB < b.length) {

            if(a[counterA].compareTo(b[counterB]) < 0) {
                s[counterS] = a[counterA];
                counterA++;
            }
            else {
                s[counterS] = b[counterB];
                counterB++;
            }

            counterS++;
        }

        while (counterA < a.length) {
            s[counterS] = a[counterA];
            counterA++;
            counterS++;
        }
        while(counterB < b.length) {
            s[counterS] = b[counterB];
            counterB++;
            counterS++;
        }
    }

    private void mergeSortFeedItem(List<FeedItem> array) {

        if (array.size() > 1) {

            int firstHalfSize = array.size() / 2;
            List<FeedItem> firstHalf = new ArrayList<FeedItem>();
            FeedItem[] firstHalfAux = new FeedItem[firstHalfSize];
            System.arraycopy(array.toArray(new FeedItem[0]), 0, firstHalfAux, 0, firstHalfSize);
            firstHalf.addAll(Arrays.asList(firstHalfAux));

            mergeSortFeedItem(firstHalf);


            int secondHalfSize = array.size() - firstHalfSize;
            FeedItem[] secondHalfAux = new FeedItem[secondHalfSize];
            List<FeedItem> secondHalf = new ArrayList<FeedItem>();
            System.arraycopy(array.toArray(new FeedItem[0]), firstHalfSize, secondHalfAux, 0, secondHalfSize);
            secondHalf.addAll(Arrays.asList(secondHalfAux));
            mergeSortFeedItem(secondHalf);

            merge(firstHalf, secondHalf, array);

        }
    }

    public void merge(List<FeedItem> a, List<FeedItem> b, List<FeedItem> s) {

        int counterA = 0;
        int counterB = 0;
        int counterS = 0;

        while(counterA < a.size() && counterB < b.size()) {

            if(a.get(counterA).getTitle().compareTo(b.get(counterB).getTitle()) < 0) {
                s.set(counterS, a.get(counterA));
                counterA++;
            }
            else {
                s.set(counterS, b.get(counterB));
                counterB++;
            }

            counterS++;
        }

        while (counterA < a.size()) {
            s.set(counterS,  a.get(counterA));
            counterA++;
            counterS++;
        }
        while(counterB < b.size()) {
            s.set(counterS, b.get(counterB));
            counterB++;
            counterS++;
        }
    }
}