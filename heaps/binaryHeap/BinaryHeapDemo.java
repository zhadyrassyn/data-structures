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
    }
}

class BinaryHeap {
    private int maxSize;
    private int size;
    private int[] arr;

    public BinaryHeap() {
        maxSize = 8;
        size = 0;
        arr = new int[maxSize];
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
