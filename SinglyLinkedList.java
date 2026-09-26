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
        Node<E> walk = head;
        // int[] arr = new int[size];
        ArrayList<E> sorted = new ArrayList<>();

        while (walk != null) {
            // arr[i] = (int)walk.getElement();
            // i++;
            sorted.add(walk.getElement());
            walk = walk.getNext();
        }
        Collections.sort(sorted);
        walk = head;
        int arr_length = sorted.size();
        SinglyLinkedList<E> newlist = new SinglyLinkedList<>();
        while (walk != null) {
            E curr_element = walk.getElement();
            for (int j = 0; j < arr_length; j++) {
                if (curr_element == sorted.get(j)) {
                    // while walking list, build new list one at a time
                    E replace = sorted.get(arr_length - 1 - j);
                    newlist.addLast(replace);
                    break;
                }
            }
            walk = walk.getNext();
        }
        head = newlist.head;
        tail = newlist.tail;
    }
   
}

