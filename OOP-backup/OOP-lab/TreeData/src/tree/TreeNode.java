package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int value;
    private int height;
    private List<TreeNode> children;

    public TreeNode(int value) {
        this.value = value;
        this.height = 1;
        this.children = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(int index, TreeNode child) {
        if (index >= 0 && index < children.size()) {
            children.set(index, child);
        } else {
            throw new IllegalArgumentException("Invalid index for setting child.");
        }
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public void removeChild(TreeNode child) {
        children.remove(child);
    }

    public TreeNode cloneNode() {
        TreeNode clone = new TreeNode(this.value);
        clone.setHeight(this.height);
        for (TreeNode child : this.children) {
            clone.addChild(child.cloneNode());
        }
        return clone;
    }
}
