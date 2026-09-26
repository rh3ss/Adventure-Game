package algorithm;

public class Node {
    public Node parent;
    public int column, row;
    public int gCost, hCost, fCost;
    public boolean isSolid, isOpen, isChecked;

    public Node(int column, int row) {
        this.column = column;
        this.row = row;
    }
}
