package Client.Model;

import java.util.Comparator;

public class SortComparators {
    static class SortAlphabetically implements Comparator<Good>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Good a, Good b)
        {
            return a.getName().compareTo(b.getName());
        }
    }
    static class SortPrice implements Comparator<Good>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Good a, Good b)
        {
            return a.getPrice()-b.getPrice();
        }
    }
    static class SortDateModified implements Comparator<Good>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Good a, Good b)
        {
            return (int) (a.getDateModified()-b.getDateModified());
        }
    }
    static class SortDateCreated implements Comparator<Good>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Good a, Good b)
        {
            return (int) (a.getDateCreated()-b.getDateCreated());
        }
    }
}
