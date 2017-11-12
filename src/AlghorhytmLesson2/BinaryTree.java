package AlghorhytmLesson2;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    private void addBranch(Node<T> node, BinaryTree<T> tree) {
        tree.add(node.value);
        if (node.left != null)
            addBranch(node.left, tree);
        if (node.right != null)
            addBranch(node.right, tree);
    }
    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        if(isEmpty())
            return null;
        BinaryTree<T> newTree = new BinaryTree<>();
        Node<T> curNode= root;
        while(curNode.value.compareTo(toElement)!=0){
            if(curNode.value.compareTo(toElement)>0){
                if(curNode.left!=null)
                    curNode=curNode.left;
                else
                    break;
            }
            else{
                newTree.add(curNode.value);
                if(curNode.left!=null)
                    newTree.addBranch(curNode.left, newTree);
                if(curNode.right!=null)
                    curNode=curNode.right;
                else
                    break;
            }
        }
        if(curNode.value.compareTo(toElement)==0){
            if(curNode.left!=null)
                newTree.addBranch(curNode.left, newTree);
        }
        return newTree;
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        if(isEmpty())
            return null;
        BinaryTree<T> newTree = new BinaryTree<>();
        Node<T> curNode = root;
        while(curNode.value.compareTo(fromElement)!=0){
            if(curNode.value.compareTo(fromElement)<0){
                if(curNode.right!= null)
                    curNode= curNode.right;
                else
                    break;
            }
            else{
                newTree.add(curNode.value);
                if(curNode.right!=null)
                    newTree.addBranch(curNode.right, newTree);
                if(curNode.left!=null)
                    curNode=curNode.left;
                else
                    break;
            }
        }
        if(curNode.value.compareTo(fromElement)==0){
            newTree.add(fromElement);
            if(curNode.right!=null)
                newTree.addBranch(curNode.right, newTree);
        }
        return newTree;
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
