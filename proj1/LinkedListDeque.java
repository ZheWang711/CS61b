/**
 * Created by zhewang711 on 7/10/16.
 */


public class LinkedListDeque<Item> {

    /**
     * I choose to use "circular sentinel" @link{https://docs.google.com/presentation/d/1CqIFP2SPvgJvKKXCmzpRt6e57FYFsjK_Y7vVq0zRFuQ/edit#slide=id.g829fe3f43_0_376}
     * to implement the DLL.
     *
     * The invariant in this implementation
     *  1. F.prev = R
     *  2. L.next = R
     *  3. R.prev = L
     *  4. R.next = F
     * where F L R are first actual node, last actual node and sentinel node, respectively.
     *
     * We could even mathematically prove our code working correctly, for example, in removeFirst() method:
     *  (1) It is extremely easy to check invariants 1-4 holds if the DLL is empty.
     *  (2) For the case when DLL is not empty:
     *      (a) For `this.sentinel.next.next.prev = this.sentinel;`, i.e. R.n.n.p := R, from invariant 4, we know that
     *      R.n.n.p => F.n.p => F'.p (F' is the new first node after deleting F), thus F'.p = R which means invariant 1 holds
     *      after this line of code. (node that this line of code only change the value of F'.p, which means that invariants
     *      2-4 also hold after this line)
     *      (b) For ` this.sentinel.next = this.sentinel.next.next;`, i.e. R.n := R.n.n. From invariant 4, we know that
     *      R.n.n => F.n = F', thus R.n = F' which means invariant 4 still hold after this line, also note this line of
     *      code change nothing but the value of R.n, which means that invariant 1-3 also hold after this line.
     *  (3) Thus, all of the invariants hold after deleting the first node.
     *
     *
     */



    public class Node{
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;


    public LinkedListDeque(){
        this.size = 0;
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    public void addFirst(Item item){
        this.sentinel.next = new Node(item, this.sentinel.next, this.sentinel);
        this.size += 1;
    }

    public void addLast(Item item){
        this.sentinel.prev = new Node(item, this.sentinel, this.sentinel.prev);
        this.size += 1;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public void printDeque(){
        Node p = this.sentinel.next;
        for (int cnt = 0; cnt != this.size; ++ cnt){
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    public Item removeFirst(){
        if (size > 0){
            size -= 1;
        }
        Item res = this.sentinel.next.item;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        return res;
    }

    public Item removeLast(){
        if (size > 0){
            size -= 1;
        }
        Item res = this.sentinel.prev.item;
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
        return res;
    }

    public Item get(int index){
        int cnt = -1;
        Node p = this.sentinel;
        while (cnt < index){
            cnt += 1;
            p = p.next;
        }
        return p.item;
    }

    public Item getRecursive(int index){
        return this.getRecursive(index, this.sentinel.next);
    }

    private Item getRecursive(int index, Node p){
        if (index == 0){
            return p.item;
        }
        else{
            return getRecursive(index - 1, p.next);
        }
    }
}
