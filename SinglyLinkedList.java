import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        // 0 1 2 null
        // build ascending list first
        // then array access the sorted list for each
        // element then find the corresponding swap, on^2
        if (size < 2) return;
        // in place swapping of sll
        // copy sll into arraylist
        ArrayList<Node<E>> orig = new ArrayList<>();
        Node<E> walk = head;
        while(walk != null) {
            orig.add(walk);
            walk = walk.getNext();
        }
        // copy the orig into another arraylist then sort nodes by elements rather than nodes b
        // because java doesnt know how to sort nodes by themselves, so you need
        // to provide the logic to compare nodes
        // if java actually knows how to compare the collection of objects, it means
        // the object implements comparable already, so you use collections.sort(<listname>)
        // Collections.sort(sorted, comparator)
        // sorted.sort(comparator)
        ArrayList<Node<E>> sorted = new ArrayList<>(orig);
        sorted.sort((a,b) -> a.getElement().compareTo(b.getElement()));

        // build map mapping element to their rank in sorted
        HashMap<E, Integer>ranking = new HashMap<>();
        for (int i = 0 ; i < size; i++ ) {
            ranking.put(sorted.get(i).getElement(), i);
        } 
        // build mirrored list now or build mirrored list now
        Node<E> prev = null;
        for (int i = 0 ; i < size; i++) {
            Node<E> mirrornode = sorted.get(size - 1 - ranking.get(orig.get(i).getElement()));
            if (prev == null) {
                head = mirrornode;
            } else {
                prev.setNext(mirrornode);
            }
            prev = mirrornode;
        }
        tail = prev;
        prev.setNext(null);

        // Node<E> walk = head;
        // // int[] arr = new int[size];
        // // use arraylist to store elements instead off primitive arr
        // ArrayList<E> sorted = new ArrayList<>();

        // while (walk != null) {
        //     // arr[i] = (int)walk.getElement();
        //     // i++;
        //     sorted.add(walk.getElement());
        //     walk = walk.getNext();
        // }
        // Collections.sort(sorted);
        // int arr_length = sorted.size();
        // // use hashmap to bring f(n^2) to f(n) replacement but overall is still o(nlogn) due to collections.sort
        // HashMap<E,Integer>rank_indices = new HashMap<>();
        // for (int z = 0; z < arr_length; z++) rank_indices.put(sorted.get(z),z);
        // walk = head;
        // SinglyLinkedList<E> newlist = new SinglyLinkedList<>();
        // while (walk != null) {
        //     E curr_element = walk.getElement();
        //     int index = rank_indices.get(curr_element);
        //     E replacement = sorted.get(arr_length - 1 - index);
        //     newlist.addLast(replacement);
        //     // for (int j = 0; j < arr_length; j++) {
        //     //     if (curr_element == sorted.get(j)) {
        //     //         // while walking list, build new list one at a time
        //     //         E replace = sorted.get(arr_length - 1 - j);
        //     //         newlist.addLast(replace);
        //     //         break;
        //     //     }
        //     // }
        //     walk = walk.getNext();
        // }
        // head = newlist.head;
        // tail = newlist.tail;
    }
   
}

