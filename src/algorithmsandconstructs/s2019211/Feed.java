package algorithmsandconstructs.s2019211;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

import java.util.*;

public class Feed implements FeedInterface {
    private List<FeedItem> feedList;

    public Feed() {
        feedList = new ArrayList<>();
    }

    /**
     * Add an Item in a List and sort after using a mergesort method
     * @param item -New Feed item
     */
    @Override
    public void addItem(FeedItem item) {
        feedList.add(item);
        mergeSortFeedItem(feedList);
    }

    /**
     * Read the list of FeedItem and return all titles
     * @return
     */
    @Override
    public Collection<String> listTitles() {
        List<String> list = new ArrayList<>();
        for (FeedItem feedItem : feedList) {
            list.add(feedItem.getTitle());
        }
        return list;
    }

    /**
     * Get a list of titles and get a binary search for a required title, after get the correct item in List of FeedItem
     * @param  title the title of the item to find.
     * @return
     */
    @Override
    public FeedItem getItem(String title) {
        String[] titles = listTitles().toArray(new String[0]);
        int item = binarySearch(titles, 0, titles.length - 1, title);
        return item >= 0 ? feedList.get(item) : null;
    }

    /**
     * Get total list items
     * @return
     */
    @Override
    public int numItems() {
        return feedList.size();
    }

    /**
     * Get the titles, then scrolls through the list of titles, for each title,
     * creates an array of words, performs an order using the mergesort method,
     * then performs a binary search for the required word,
     * if found stores the title in a return list and stops loop processing and go to the next title.
     * @param keyword the keyword to search for.
     * @return
     */
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

    /**
     * Auxiliary method to perform a binary recursive search on an array of titles.
     * The binarySearch method takes a sortedArray, key & the low & high indexes of the sortedArray as arguments.
     * When the method runs for the first time the low, the first index of the sortedArray, is 0, while the high,
     * the last index of the sortedArray, is equal to its length – 1.
     *
     * The middle is the middle index of the sortedArray.
     * Now the algorithm runs a while loop comparing the key with the array value of the middle index of the sortedArray.
     * @param titles the Array to search for
     * @param low
     * @param high
     * @param key the key to search for
     * @return
     */
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

    /**
     * Auxiliary method to sort a array of String.
     * The base condition checks if the array length is greater 1.
     * For the rest of the cases, the recursive call will be executed.
     * For the recursive case, we get the middle index and create two temporary arrays.
     * The mergeSort function is then called recursively for both the sub-arrays.
     *
     * We then call the merge function which takes in the input and both the sub-arrays
     * @param array
     */
    private void mergeSortString(String[] array) {

        if (array.length > 1) {

            int firstHalfSize = array.length / 2;
            String[] firstHalf = new String[firstHalfSize];
            System.arraycopy(array, 0, firstHalf, 0, firstHalfSize);
            mergeSortString(firstHalf);

            int secondHalfSize = array.length - firstHalfSize;
            String[] secondHalf = new String[secondHalfSize];
            System.arraycopy(array, firstHalfSize, secondHalf, 0, secondHalfSize);
            mergeSortString(secondHalf);

            merge(firstHalf, secondHalf, array);

        }
    }

    /**
     * Auxiliary method to Merge arrays.
     * The merge function compares the elements of both sub-arrays one by one and places the smaller element into the input array.
     *
     * When we reach the end of one of the sub-arrays,
     * the rest of the elements from the other array are copied into the input array thereby giving us the final sorted array
     * @param a
     * @param b
     * @param s
     */
    private void merge(String[] a, String[] b, String[] s) {

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

    /**
     * Auxiliary method to sort a List of FeedItem.
     * Auxiliary method to sort a array of String.
     * The base condition checks if the array length is greater 1.
     * For the rest of the cases, the recursive call will be executed.
     * For the recursive case, we get the middle index and create two temporary arrays.
     * The mergeSort function is then called recursively for both the sub-arrays.
     *
     * We then call the merge function which takes in the input and both the sub-arrays
     * @param array
     */
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

    /**
     * Auxiliary method to Merge List.
     * The merge function compares the elements of both sub-arrays one by one and places the smaller element into the input array.
     *
     * When we reach the end of one of the sub-arrays,
     * the rest of the elements from the other array are copied into the input array thereby giving us the final sorted array
     * @param a
     * @param b
     * @param s
     */
    private void merge(List<FeedItem> a, List<FeedItem> b, List<FeedItem> s) {

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