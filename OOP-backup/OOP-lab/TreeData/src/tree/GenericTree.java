package tree;

import java.util.LinkedList;
import java.util.Queue;

public class GenericTree implements Tree {
    private TreeNode root;

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public Tree cloneTree() {
        GenericTree clonedTree = new GenericTree();
        if (this.root != null) {
            clonedTree.root = this.root.cloneNode();
        }
        return clonedTree;
    }

    @Override
    public void createRandomTree(int numberOfValues) {
        for (int i = 0; i < numberOfValues; i++) {
            insert((root == null) ? 0 : root.getValue(), (int) (Math.random() * 100));
        }
    }

    @Override
    public void insert(int parentValue, int newValue) {
        TreeNode newNode = new TreeNode(newValue);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.getValue() == parentValue) {
                current.addChild(newNode);
                return;
            } else {
                queue.addAll(current.getChildren());
            }
        }
    }

    @Override
    public void delete(int value) {
        if (root == null) return;

        if (root.getValue() == value) {
            root = null;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            for (TreeNode child : current.getChildren()) {
                if (child.getValue() == value) {
                    current.getChildren().remove(child);
                    return;
                } else {
                    queue.add(child);
                }
            }
        }
    }

    @Override
    public TreeNode search(int value) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.getValue() == value) {
                return current;
            } else {
                queue.addAll(current.getChildren());
            }
        }
        return null;
    }

    @Override
    public void bfsTraverse() {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.getValue() + " ");
            queue.addAll(current.getChildren());
        }
        System.out.println();
    }

    @Override
    public void dfsTraverse() {
        dfsHelper(root);
        System.out.println();
    }

    private void dfsHelper(TreeNode node) {
        if (node == null) return;

        System.out.print(node.getValue() + " ");
        for (TreeNode child : node.getChildren()) {
            dfsHelper(child);
        }
    }
}
