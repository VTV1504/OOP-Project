package tree;

public interface Tree {
    void createRandomTree(int numberOfValues);
    void insert(int parentValue, int newValue);
    void delete(int value);
    TreeNode search(int value);
    void bfsTraverse();
    void dfsTraverse();
    TreeNode getRoot();
    Tree cloneTree();
}
