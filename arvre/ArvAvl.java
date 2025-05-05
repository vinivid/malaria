public class ArvAvl extends ArvBin {

    private int height(int index) {
        if (getEle(index) == null)
            return -1;
        
        int a = height(left_child(index));
        int b = height(right_child(index));

        return Math.max(a, b) + 1;
    }

    private void copyTree(int from, int into) {
        if (getEle(from) == null) {
            return;
        }

        insEle(into, getEle(from));

        copyTree(right_child(from), right_child(into));
        copyTree(left_child(from), left_child(into));
    }

    private void rotateLeft(int index) {
        int aLeft = left_child(index);
        int aRight = right_child(index);
        String A = getEle(index);
        String B = getEle(aRight);

        copyTree(aLeft, left_child(aLeft));
        copyTree(left_child(aRight), right_child(aLeft));
        copyTree(right_child(aRight), aRight);

        insEle(index, B);
        insEle(aLeft, A);
    }

    private void rotateRight(int index) {
        int aLeft = left_child(index);
        int aRight = right_child(index);
        String A = getEle(index);
        String B = getEle(aLeft);

        copyTree(aRight, right_child(aRight));
        copyTree(right_child(aLeft), left_child(aRight));
        copyTree(left_child(aLeft), aLeft);

        insEle(index, B);
        insEle(aRight, A);
    }

    private void fixTree(int index) {
        if (index < 0)
            return;
        
        int dLeft = height(left_child(index));
        int dRight = height(right_child(index));
        int bf = dLeft - dRight;

        if (bf == 2) {
            if (height(right_child(right_child(index))) >= height(right_child(left_child(index)))) {
                rotateLeft(index);
            } else {
                rotateRight(right_child(index));
                rotateLeft(index);
            }
        } else if (bf == -2) {
            if (height(right_child(right_child(index))) >= height(right_child(left_child(index)))) {
                rotateRight(index);
            } else {
                rotateLeft(left_child(index));
                rotateRight(index);
            }
        }

        fixTree((index - 1)/2);
    }

    @Override
    public void insert(String v) {
        int current = 0;

        while (getEle(current) != null) {
            String cElem = getEle(current);

            if (cElem.equals(v)) {
                return;
            } else if (v.compareTo(cElem) < 0){
                current = left_child(current);
            } else {
                current = right_child(current);
            }
        }

        insEle(current, v);

        fixTree(current);

        return;
    }
}