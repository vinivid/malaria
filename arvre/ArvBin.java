public class ArvBin {
    private String[] tree;
    private int size;

    public ArvBin() {
        tree = new String[10000];
        size = 0;
    }

    public ArvBin(int len) {
        tree = new String[len];
        size = 0;
    }

    protected void addSize() {
        size += 1;
    }

    protected void removeSize() {
        size -= 1;
    }

    protected int left_child(int current) {
        return 2 * current + 1;
    }

    protected int right_child(int current) {
        return 2 * current + 2;
    }

    protected int arrSize() {
        return tree.length;
    }

    protected String[] copyArrayTree() {
        String[] cpy = new String[1000];
        for (int i = 0; i < 1000; ++i) {cpy[i] = tree[i];}
        return cpy;
    }

    protected void cpyTre(int from, int into, String[] cpy) {
        if (cpy[from] == null) {
            insEle(into, null);
            return;
        }

        insEle(from, null);

        cpyTre(right_child(from), right_child(into), cpy);
        cpyTre(left_child(from), left_child(into), cpy);

        insEle(into, cpy[from]);
    }

    public void insert(String v) {
        int current = 0;

        while (tree[current] != null) {
            // Sem elementos repitidos
            if (v.compareTo(tree[current]) == 0) {
                return;
            } else if (v.compareTo(tree[current]) < 0) {
                current = left_child(current);
            } else {
                current = right_child(current);
            }
        }
        tree[current] = v;
        size += 1;
    }

    public boolean find(String v) {
        int current = 0;
        
        while (tree[current] != null) {
            if (v.compareTo(tree[current]) == 0) {
                return true;
            } else if (v.compareTo(tree[current]) < 0) {
                current = left_child(current);
            } else {
                current = right_child(current);
            }
        }

        return false; 
    }

    protected int maximum(int root) {
        int prev = root;
        int right = right_child(prev);

        while (tree[right] != null) {
            prev = right;
            right = right_child(right);
        }

        return prev;
    }

    protected int minimum(int root) {
        int prev = root;
        int left = left_child(prev);

        while (tree[left] != null) {
            prev = left;
            left = right_child(prev);
        }

        return prev;
    }

    public boolean remove(String v) {
        int current = 0;

        boolean found = false;
        while (tree[current] != null) {
            // Sem elementos repitidos
            if (v.compareTo(tree[current]) == 0) {
                int left = left_child(current);
                int right = right_child(current);
                
                int min = minimum(right_child(current));
                int max = maximum(left_child(current));

                if(getEle(left) == null){
                    insEle(current, getEle(min));
                    insEle(min, null);
                    found = true;
                    size -= 1;
                    break;
                }

                insEle(current, getEle(max));
                insEle(max, null);
                found = true;
                size -= 1;
                break;
            } else if (v.compareTo(tree[current]) < 0) {
                current = left_child(current);
            } else {
                current = right_child(current);
            }
        }

        return found;
    }

    public int len() {return size;}

    @Override
    public String toString() {
        if (size == 0) {
            String ok = new String("digraph {\n}");
            return ok;
        } else if (size == 1) {
            String ok = new String("digraph {\n");
            ok += "\"0" + " " + tree[0] +  "\" }\n"; 
            return ok;
        } else {
            String ok = new String("digraph {\n");
            for (int i = 0; i < 1000; ++i) {
                if (getEle(i) != null) {
                    if (getEle(left_child(i)) != null) {
                        int left = left_child(i);
                        ok += "\"" + Integer.toString(i) + " " + tree[i] + "\" ->" + "\"" + Integer.toString(left) + " " + tree[left] + "\"\n"; 
                    }
    
                    if (getEle(right_child(i)) != null) {
                        int right = right_child(i);
                        ok += "\"" + Integer.toString(i) + " " + tree[i] + "\" ->" + "\"" + Integer.toString(right) + " " + tree[right] + "\"\n"; 
                    } 
                }
            }
            ok += "}\n";
            return ok;
        }
    }

    protected void insEle(int index, String val) {
        tree[index] = val;
    }

    protected String getEle(int index) {
        return tree[index];
    }
}