import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Entry> {

    private Node root;
    private int size;

    //Inner Node
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    //Public class entry
    public class Entry {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey()   { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() {
            return "{" + key + " -> " + value + "}";
        }
    }

    //size
    public int size() { return size; }

    //put
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left  = put(node.left,  key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else              node.val   = val;
        return node;
    }

    //get
    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if      (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else              return node.val;
        }
        return null;
    }

    //delete
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left  = delete(node.left,  key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            // Node to delete found
            size--;
            if (node.right == null) return node.left;
            if (node.left  == null) return node.right;

            // Replace with in-order succescor
            Node successor = min(node.right);
            node.key = successor.key;
            node.val = successor.val;
            node.right = deleteMin(node.right);
            size++; // deleteMin decrements, we already decremented above
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) { size--; return node.right; }
        node.left = deleteMin(node.left);
        return node;
    }

    //iterator() — in-order traversal
    @Override
    public Iterator<Entry> iterator() {
        List<Entry> entries = new ArrayList<>();
        inOrder(root, entries);
        return entries.iterator();
    }

    private void inOrder(Node node, List<Entry> list) {
        if (node == null) return;
        inOrder(node.left,  list);
        list.add(new Entry(node.key, node.val));
        inOrder(node.right, list);
    }
}