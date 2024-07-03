import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryTreeGUI extends JFrame {
    private BinaryTree tree;
    private JTextField insertField;
    private JTextField deleteField;
    private JButton insertButton;
    private JButton deleteButton;
    private JPanel drawingPanel;

    public BinaryTreeGUI() {
        tree = new BinaryTree();

        setTitle("Binary Tree GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        insertField = new JTextField(10);
        insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(insertField.getText().trim());
                    tree.insert(value);
                    insertField.setText("");
                    drawingPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BinaryTreeGUI.this,
                            "Please enter a valid integer value",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteField = new JTextField(10);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(deleteField.getText().trim());
                    if (tree.delete(value)) {
                        deleteField.setText("");
                        drawingPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(BinaryTreeGUI.this,
                                "Value not found in the tree",
                                "Node Not Found",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BinaryTreeGUI.this,
                            "Please enter a valid integer value",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the binary tree
                if (tree.getRoot() != null) {
                    drawTree(g, getWidth() / 2, 30, tree.getRoot(), getWidth() / 4);
                }
            }
        };
        drawingPanel.setPreferredSize(new Dimension(800, 600));

        JPanel insertPanel = new JPanel();
        insertPanel.add(new JLabel("Insert value:"));
        insertPanel.add(insertField);
        insertPanel.add(insertButton);

        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("Delete value:"));
        deletePanel.add(deleteField);
        deletePanel.add(deleteButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));
        inputPanel.add(insertPanel);
        inputPanel.add(deletePanel);

        add(inputPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private void drawTree(Graphics g, int x, int y, BinaryTree.TreeNode node, int xOffset) {
        g.setColor(Color.black);
        g.drawString(String.valueOf(node.data), x, y);
        if (node.left != null) {
            // Draw left subtree
            int newX = x - xOffset;
            int newY = y + 50;
            g.drawLine(x, y, newX, newY);
            drawTree(g, newX, newY, node.left, xOffset / 2);
        }
        if (node.right != null) {
            // Draw right subtree
            int newX = x + xOffset;
            int newY = y + 50;
            g.drawLine(x, y, newX, newY);
            drawTree(g, newX, newY, node.right, xOffset / 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BinaryTreeGUI().setVisible(true);
            }
        });
    }

    // Inner class for BinaryTree implementation
    private class BinaryTree {
        private TreeNode root;

        public BinaryTree() {
            root = null;
        }

        public TreeNode getRoot() {
            return root;
        }

        public void insert(int value) {
            root = insertRec(root, value);
        }

        private TreeNode insertRec(TreeNode root, int value) {
            if (root == null) {
                root = new TreeNode(value);
                return root;
            }
            if (value < root.data) {
                root.left = insertRec(root.left, value);
            } else if (value > root.data) {
                root.right = insertRec(root.right, value);
            }
            return root;
        }

        public boolean delete(int value) {
            TreeNode parent = null;
            TreeNode current = root;
            boolean isLeftChild = false;

            // Find the node to delete and its parent
            while (current != null && current.data != value) {
                parent = current;
                if (value < current.data) {
                    current = current.left;
                    isLeftChild = true;
                } else {
                    current = current.right;
                    isLeftChild = false;
                }
            }

            // If node not found
            if (current == null) {
                return false;
            }

            // Case 1: Node to be deleted has no children (leaf node)
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // Case 2: Node to be deleted has only one child
            else if (current.right == null) {
                if (current == root) {
                    root = current.left;
                } else if (isLeftChild) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            } else if (current.left == null) {
                if (current == root) {
                    root = current.right;
                } else if (isLeftChild) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
            // Case 3: Node to be deleted has two children
            else {
                // Find the successor (smallest node in the right subtree)
                TreeNode successor = getSuccessor(current);

                // Connect parent of current to successor
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }

                // Connect successor to current's left child
                successor.left = current.left;
            }

            return true;
        }

        private TreeNode getSuccessor(TreeNode node) {
            TreeNode successorParent = node;
            TreeNode successor = node;
            TreeNode current = node.right;

            while (current != null) {
                successorParent = successor;
                successor = current;
                current = current.left;
            }

            // If successor is not the right child of node to be deleted,
            // link successor's right child to successorParent's left child.
            if (successor != node.right) {
                successorParent.left = successor.right;
                successor.right = node.right;
            }

            return successor;
        }

        // TreeNode class
        private class TreeNode {
            int data;
            TreeNode left, right;

            public TreeNode(int data) {
                this.data = data;
                left = right = null;
            }
        }
    }
}
