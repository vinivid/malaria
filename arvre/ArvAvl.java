/* Renan Banci Catarin 14658181
 * Vinicius Souza Freitas 15491959
 */

public class ArvAvl extends ArvBin {
    public ArvAvl(int len) {
        super(len);
    }

    private int height(int index) {
        if (getEle(index) == null)
            return -1;
        
        int a = height(left_child(index));
        int b = height(right_child(index));

        return Math.max(a, b) + 1;
    }

    private void rotateLeft(int index) {
        int aLeft = left_child(index);
        int aRight = right_child(index);
        String A = getEle(index);
        String B = getEle(aRight);

        String[] g = copyArrayTree();
        cpyTre(aLeft, left_child(aLeft), g);
        cpyTre(left_child(aRight), right_child(aLeft), g);
        cpyTre(right_child(aRight), aRight, g);

        insEle(index, B);
        insEle(aLeft, A);
    }

    private void rotateRight(int index) {
        int aLeft = left_child(index);
        int aRight = right_child(index);
        String A = getEle(index);
        String B = getEle(aLeft);

        String[] g = copyArrayTree();
        cpyTre(aRight, right_child(aRight), g);
        cpyTre(right_child(aLeft), left_child(aRight), g);
        cpyTre(left_child(aLeft), aLeft, g);

        insEle(index, B);
        insEle(aRight, A);
    }

    private void fixTree(int index) {
        int dLeft = height(left_child(index));
        int dRight = height(right_child(index));
        int bf = dRight - dLeft;

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

        //caso base
        if (index <= 0)
            return;

        fixTree((index - 1)/2);
    }

    @Override
    public void insert(String v) {
        int insertedIndex = insertWithIndex(v);

        if (insertedIndex == -1)
            return;

        fixTree(insertedIndex);

        return;
    }

    @Override
    public boolean remove(String v) {
        int removedIndex = removeWithIndex(v);

        if (removedIndex == -1) 
            return false;

        fixTree(removedIndex);

        return true;
    }
}