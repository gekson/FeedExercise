package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.util.*;

public class Feed implements FeedInterface {
    private List<FeedItem> feedList;
    private FeedItem[] feedListArray;

    public Feed() {
        feedList = new ArrayList<>();
    }

    @Override
    public void addItem(FeedItem item) {
        feedList.add(item);
    }

    @Override
    public Collection<String> listTitles() {
        feedListArray = feedList.toArray(new FeedItem[feedList.size()]);
        mergeSortFeedItem(feedListArray);
        List<String> list = new ArrayList<>();
        for (FeedItem feedItem : feedListArray) {
            list.add(feedItem.getTitle());
        }
        return list;
    }

    @Override
    public FeedItem getItem(String title) {
        String[] titles = listTitles().toArray(new String[0]);
        int item = binarySearch(titles, 0, titles.length - 1, title);
        return item >= 0 ? feedListArray[item] : null;
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
            mergeSortString(listKeys);
//            keys = quickSort(listKeys).toArray(new String[list.size()]);
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

    private void mergeSortFeedItem(FeedItem[] array) {

        if (array.length > 1) {

            int firstHalfSize = array.length / 2;
            FeedItem[] firstHalf = new FeedItem[firstHalfSize];
            System.arraycopy(array, 0, firstHalf, 0, firstHalfSize);
            mergeSortFeedItem(firstHalf);

            mergeSortFeedItem(firstHalf);


            int secondHalfSize = array.length - firstHalfSize;
            FeedItem[] secondHalf = new FeedItem[secondHalfSize];
            System.arraycopy(array, firstHalfSize, secondHalf, 0, secondHalfSize);
            mergeSortFeedItem(secondHalf);

            merge(firstHalf, secondHalf, array);

        }
    }

    public void merge(FeedItem[] a, FeedItem[] b, FeedItem[] s) {

        int counterA = 0;
        int counterB = 0;
        int counterS = 0;

        while(counterA < a.length && counterB < b.length) {

            if(a[counterA].getTitle().compareTo(b[counterB].getTitle()) < 0) {
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

    private void mergeSortString(List<String> array) {

        if (array.size() > 1) {

            int firstHalfSize = array.size() / 2;
            List<String> firstHalf = new ArrayList<String>();
            String[] firstHalfAux = new String[firstHalfSize];
            System.arraycopy(array.toArray(new String[0]), 0, firstHalfAux, 0, firstHalfSize);
            firstHalf.addAll(Arrays.asList(firstHalfAux));

            mergeSortString(firstHalf);


            int secondHalfSize = array.size() - firstHalfSize;
            String[] secondHalfAux = new String[secondHalfSize];
            List<String> secondHalf = new ArrayList<String>();
            System.arraycopy(array.toArray(new String[0]), firstHalfSize, secondHalfAux, 0, secondHalfSize);
            secondHalf.addAll(Arrays.asList(secondHalfAux));
            mergeSortString(secondHalf);

            merge(firstHalf, secondHalf, array);

        }
    }

    public void merge(List<String> a, List<String> b, List<String> s) {

        int counterA = 0;
        int counterB = 0;
        int counterS = 0;

        while(counterA < a.size() && counterB < b.size()) {

//            if(a[counterA].getTitle().compareTo(b[counterB].getTitle()) < 0) {
            if(a.get(counterA).compareTo(b.get(counterB)) < 0) {
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