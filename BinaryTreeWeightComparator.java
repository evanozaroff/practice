import java.util.Comparator;

public class BinaryTreeWeightComparator implements Comparator<BinaryTree>{

    @Override
    public int compare(BinaryTree t1, BinaryTree t2) {
        return t1.getWeight() - t2.getWeight();
    }
}
