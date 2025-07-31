package randomizedtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.StdRandom;
import timingtest.AList;

/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class BuggyAList<Item> {
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public BuggyAList() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            a[i] = items[i];
        }
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        if ((size < items.length ) && (size > 0)) {
            resize(items.length);
        }
        Item x = getLast();
        items[size - 1] = null;
        size = size - 1;
        return x;
    }

    @Test
    public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> good = new AListNoResizing<>();
    BuggyAList<Integer> buggy = new BuggyAList<>();
    int[] val = {4, 5, 6};
    for (int x : val) {
        good.addLast(x);
        buggy.addLast(x);
    }
    for (int i = 2; i >= 0; i--) {
        assertEquals(good.removeLast(), buggy.removeLast());

    }

    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> good = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int op = StdRandom.uniform(0, 4); // 0:add 1:remove 2:get 3:size
            switch (op){
                case 0:
                    int val  = StdRandom.uniform(0, 1000);
                    good.addLast(val);
                    buggy.addLast(val);
                    break;

                case 1:
                    if (good.size() > 0){
                        assertEquals(good.removeLast(), buggy.removeLast());
                    }
                    break;

                case 2:
                    if (good.size() > 0){
                        assertEquals(good.getLast(), buggy.getLast());
                    }
                    break;

                case 3:
                    if (good.size() > 0){
                        assertEquals(good.size(), buggy.size());
                    }
                    break;
            }
        }

    }

}
