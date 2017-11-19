package AlghorhytmLesson2;

import org.junit.Test;

import org.junit.Assert.*;
import java.util.SortedSet;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
public class BinaryTreeTest {
    private BinaryTree<Integer> tree;
    private BinaryTree<Integer> emptyTree;
    @Before
    public void treeTest(){
        //Пустое дерево
        emptyTree = new BinaryTree<>();
        //В произвольном порядке добавим числа от 1 до 10
        tree = new BinaryTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(2);
        tree.add(7);
        tree.add(9);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.add(3);
        tree.add(6);
    }


    @org.junit.Test
    public void headSet() throws Exception {
        SortedSet<Integer> set;
        set = tree.headSet(5);
        assertEquals(true, set.contains(1));
        assertEquals(true, set.contains(2));
        assertEquals(true, set.contains(3));
        assertEquals(true, set.contains(4));
        assertEquals(false, set.contains(5));
        assertEquals(false, set.contains(6));
        assertEquals(false, set.contains(7));
        assertEquals(false, set.contains(8));
        assertEquals(false, set.contains(9));
        assertEquals(false, set.contains(10));

        set = tree.headSet(-128);
        assertEquals(null, set.toString());

        set = tree.headSet(127);
        for (int i = 1; i <= 10; i++)
            assertEquals(true, set.contains(i));

        assertEquals(null, emptyTree.headSet(0));
    }

    @org.junit.Test
    public void tailSet() throws Exception {
        SortedSet<Integer> set;
        set = tree.tailSet(5);
        assertEquals(false, set.contains(1));
        assertEquals(false, set.contains(2));
        assertEquals(false, set.contains(3));
        assertEquals(false, set.contains(4));
        assertEquals(true, set.contains(5));
        assertEquals(true, set.contains(6));
        assertEquals(true, set.contains(7));
        assertEquals(true, set.contains(8));
        assertEquals(true, set.contains(9));
        assertEquals(true, set.contains(10));

        set = tree.tailSet(-128);
        for (int i = 1; i <= 10; i++)
            assertEquals(true, set.contains(i));

    }

}