/*
 * BinaryHeap(PriorityHeap) implementation.
 * */
public class BinaryHeapDemo {
    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap();
        heap.insert(10);
        heap.insert(6);
        heap.insert(8);
        heap.insert(1);
        heap.insert(2);
        heap.insert(20);
        heap.insert(15);
        
        System.out.println("HEAP SIZE: " + heap.size());
        heap.print();

        int max = heap.getMax();
        System.out.println("MAX ELEMENT: " + max);
        System.out.println("HEAP SIZE: " + heap.size());
        heap.print();

        heap.remove(2);
        System.out.println("HEAP SIZE:" + heap.size());
        heap.print();

        heap.remove(5);
        System.out.println("HEAP SIZE:" + heap.size());
        heap.print();

        heap.changePriority(2, 20);
        heap.print();

        
        int[] arr = {0, 33, 14, 12, 18, 3, 2, 89};
        BinaryHeap heap1 = new BinaryHeap(arr);
        System.out.println("Heap representation");
        heap1.print();

        System.out.println("SORTED heap");
        int[] sorted = heap1.sort();
        for(int i = 1; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }

    }
}


/*
 * array is 1 based
 * */
class BinaryHeap {
    private int maxSize;
    private int size;
    private int[] arr;

    public BinaryHeap() {
        maxSize = 8;
        size = 0;
        arr = new int[maxSize];
    }

    public BinaryHeap(int[] arr) {
        int n = arr.length;
        this.arr = arr;

        size = n-1;
        maxSize = n;

        for(int i = size/2; i >= 1; i--) {
            siftDown(i);
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(int num) {
        if(size == maxSize) {
            int[] temp = new int[maxSize*2];
            for(int i = 1; i < arr.length; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
            temp = null;
        }
        size += 1;
        arr[size] = num;
        siftUp(size);
    }

    public int getMax() {
        int result = arr[1];
        arr[1] = arr[size];

        size -= 1;

        siftDown(1);
        return result;
    }

    public void remove(int i) {
        arr[i] = Integer.MAX_VALUE;
        siftUp(i);
        getMax();
    }

    public void changePriority(int i,int p) {
        int oldP = arr[i];
        arr[i] = p;
        if(p > oldP) {
            siftUp(i);
        } else {
            siftDown(i);    
        }
    }

    public int[] sort() {
        int[] result = new int[size+1];
        for(int i = size; i >= 1; i--) {
            result[i] = getMax();
        }
        return result;
    }

    public void siftUp(int i) {
        while(i > 1 && (arr[i/2] < arr[i])) {
            swap(i, i/2);
            i /= 2;
        }
    }
    
    public void siftDown(int i) {
        int maxIndex = i;
        int l = i*2;
        if(l <= size && arr[l] > arr[maxIndex]) {
            maxIndex = l;
        }

        int r = i*2 + 1;
        if(r <= size && arr[r] > arr[maxIndex]) {
            maxIndex = r;
        }

        if(i != maxIndex) {
            swap(i, maxIndex);
            siftDown(maxIndex);
        }
    }

    public void swap(int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[i] ^ arr[j];
    }

    public void print() {
        for(int i = 1; i <= size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
