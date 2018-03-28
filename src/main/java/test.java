import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Steve on 28/03/2018.
 *
 *
 * BinarySearch Tree
 *
 * Main class
 */
public class test {

    Node root;                                              // every tree has a root value

    public static void main(String[] args) {

        BinaryTree theTree = new BinaryTree();              // create new binary tree

        theTree.addNode("spud"  , "4k 50\" TV");

        theTree.addNode("orange", "iPhone X");

        theTree.addNode("banana", "Surround Sound System");

        theTree.addNode("kiwi"  , "Holiday to London");

        theTree.addNode("lemon" , "1000e");

        theTree.addNode("apple" , "Macbook Pro 15\"");



    }

    /**
     * addNode
     *
     * adds a node with specific key and name value to the tree
     *
     * @param key  : key for node
     * @param name : name of value at key for node
     */
    public void addNode(String key, String name) {

        Node newNode = new Node(key, name);                 // create new node

        if (root == (null) ) {                              // Scenario 1:  if root is null

            root = (newNode) ;                              // sets newNode to be the root

        } else {                                            // Scenario 2:  root is not null

            Node focusNode = (root);                        // creates new node that we will be focusing on

            Node parent;                                    // create parent node

            while (true) {                                  // infinite loop so need to add return

                parent = (focusNode);                       // set the parent to be focusNode

                if (key.compareTo(focusNode.key) < 0 ) {   // Scenario 1: if newNode should be on left  of parent node

                    focusNode = (focusNode.leftChild) ;     // sets the value to be on the left as lower

                    if (focusNode == (null)) {              // checks is nothing at focusNode

                        parent.leftChild = (newNode) ;      // sets left child of parent to the new node

                        return;                             // exit infinite loop
                    }
                } else {                                    // Scenario 2: if newNode should be on right  of parent node

                    parent.rightChild = (newNode)  ;  // sets the value to be on the right as lower

                    if (focusNode == (null)) {              // checks is nothing at focusNode

                        parent.rightChild = (newNode);      // sets right child of parent to the new node

                        return;                             // exit infinite loop

                    } else {
                        return;
                    }

                }
            }
        }

    }
    /**
     * inOrderTraverseTree
     *
     * prints out tree in order of ascending key value
     *
     * @param focusNode : node to check
     */
    public static void inOrderTraverseTree(Node focusNode , Label label, VBox box) {

        if (focusNode != null) {                           // loops whilst nodes are not null

            inOrderTraverseTree(focusNode.leftChild,label,box );      // Traverse the left node

            Button prizeButton = new Button();            // creates new button for prize

            prizeButton.setMinWidth(150);                 // sets minimum width of prize button

            prizeButton.setMinHeight(70);                 // sets minimum height of prize button

            prizeButton.setText(focusNode.key);                     // changes text of button to be the key

            box.getChildren().add(prizeButton);   // adds button to VBox for prizes

            prizeButton.setOnAction(e ->{                 // when clicked

                label.setVisible(true);           // display the you won label

                box.getChildren().clear();        // remove Vbox children

                box.getChildren().addAll(label,prizeButton);

                        prizeButton.setText(focusNode.prize); // changes text to be actual prize

                        prizeButton.setStyle("-fx-background-color: #80ffbf;");   // change background color of prize choosen to green

                    }
            );

            inOrderTraverseTree(focusNode.rightChild,label,box );     // Traverse the right node
        }
    }
    /**
     * preorderTraverseTree
     *
     * prints out tree in order of left children first then right in preorder
     *
     * @param focusNode : node to check
     */
    public void preorderTraverseTree(Node focusNode) {

        if (focusNode != null) {                            // loops whilst nodes are not null

            System.out.println(focusNode);                  // Visit the currently focused on node

            preorderTraverseTree(focusNode.leftChild);      // Traverse the left node

            preorderTraverseTree(focusNode.rightChild);     // Traverse the right node
        }
    }
    /**
     * postOrderTraverseTree
     *
     * prints out tree in order of right children first then left in postorder
     *
     * @param focusNode : node to check
     */
    public void postOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {                            // loops whilst nodes are not null

            postOrderTraverseTree(focusNode.leftChild);     // Traverse the left node

            postOrderTraverseTree(focusNode.rightChild);    // Traverse the right node

            System.out.println(focusNode);                  // Visit the currently focused on node
        }
    }

    /**
     * find
     *
     *  Locates node using specific key value
     *
     * @param key : value to search for in Node
     *
     * @return Node of key value
     */
    public Node findNode(String key) {

        Node focusNode = root;                              // Start at the top of the tree

        while (focusNode.key.compareTo(key) != 0) {         // While haven't found the Node  keep looking

            if (key.compareTo(focusNode.key) < 0) {        // Scenario 1: if should search to the left

                focusNode = (focusNode.leftChild);      // Shift the focus Node to the left child

            } else {                                        // Scenario 2: if should search to the right

                focusNode = (focusNode.rightChild);     // Shift the focus Node to the right child
            }
            if (focusNode == (null))                     // The node wasn't found

                return null;                                // exit
        }
        return focusNode;                                   // return the found node

    }




}