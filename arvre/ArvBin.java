public class ArvBin {
    private String[] heap_tree;
    private int len;

    public ArvBin(int len) {
        heap_tree = new String[len];
        this.len = 0;
    }

    public boolean find(String v) {
        for (var val : heap_tree) {
            if (val != null) {
                if (val.equals(v))
                return true;
            }
        }
        return false;
    }

    private void swap_arr(int id1, int id2) {
        String tmp = heap_tree[id1];
        heap_tree[id1] = heap_tree[id2];
        heap_tree[id2] = tmp;
    }

    private void heapify_up(int i) {
        int parent = (i - 1)/2;
        if (parent >= 0) {
            if (heap_tree[i].compareTo(heap_tree[parent]) > 0) {
                swap_arr(i, parent);
                heapify_up(parent);
            }
        }
    }

    public void insert(String value) {
        // Sem valores repitidos
        if (this.find(value)) 
            return;
        
        heap_tree[len] = value;
        heapify_up(len);
        len += 1;
    }

    public int len() {
        return len;
    }

    private void heapify_down(int id) {
        int smallest = id;
        int leftChild = 2 * id + 1;
        int rightChild = 2 * id + 2;

        if (leftChild < len && (heap_tree[leftChild].compareTo(heap_tree[smallest]) < 0)) {
            smallest = leftChild;
        }
        if (rightChild < len && (heap_tree[rightChild].compareTo(heap_tree[smallest]) < 0)) {
            smallest = rightChild;
        }

        if (smallest != id) {
            swap_arr(id, smallest);
            heapify_down(smallest);
        }
    }

    public boolean remove(String v) {
        int index_to_remove = -1;
        for (int i = 0; i < heap_tree.length; ++i) {
            if (heap_tree[i].equals(v)) {
                index_to_remove = i;
                break;
            }
        }

        if (index_to_remove == -1) return false;

        heap_tree[index_to_remove] = heap_tree[len - 1];
        len -= 1;

        if (index_to_remove < len) {
            heapify_down(index_to_remove);
            
            if (heap_tree[index_to_remove].equals(heap_tree[len])) { 
                heapify_up(index_to_remove);
            }
        }

        return true;
    }

    @Override
    public String toString() { 
        String accu = "digraph {\n";

        for (int i = 0; i < len; ++i) {
            if (2 * i + 1 < len) 
                accu += "\"" + Integer.toString(i) + " " + heap_tree[i] + "\"" + " ->" + "\"" + Integer.toString(2*i + 1) + " " + heap_tree[2*i + 1] + "\"" + "\n"; 
            if (2 * i + 2 < len) 
                accu += "\"" + Integer.toString(i) + " " + heap_tree[i] + "\"" + " ->" + "\"" + Integer.toString(2*i + 2) + " " + heap_tree[2*i + 2] + "\"" + "\n"; 
        }

        accu += "}\n";
        return accu;
    } 
}