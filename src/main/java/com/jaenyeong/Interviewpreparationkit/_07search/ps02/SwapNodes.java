package com.jaenyeong.Interviewpreparationkit._07search.ps02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SwapNodes {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int NULL_VALUE = -1;

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        /*
         * Write your code here.
         */

        final Node rootNode = generateNodeTree(indexes);

        final int[][] result = new int[queries.length][indexes.length];

        for (int idx = 0; idx < queries.length; idx++) {
            swap(rootNode, 1, queries[idx]);

            final List<Integer> inOrderNodeTree = new ArrayList<>();
            generateInOrderNodeTree(rootNode, inOrderNodeTree);
            result[idx] = inOrderNodeTree.stream()
                .mapToInt(value -> value)
                .toArray();
        }

        return result;
    }

    private static Node generateNodeTree(final int[][] indexes) {
        final Node rootNode = new Node(1, 1, null, null);

        final Queue<Node> nodes = new LinkedList<>();
        nodes.offer(rootNode);

        int indexesCursor = 0;
        while (!nodes.isEmpty()) {
            final Node currentNode = nodes.poll();

            final int leftIdxValue = indexes[indexesCursor][0];
            final int rightIdxValue = indexes[indexesCursor][1];

            final int nextDepth = currentNode.getDepth() + 1;

            if (leftIdxValue != NULL_VALUE) {
                currentNode.setLeftChild(new Node(nextDepth, leftIdxValue, null, null));
                nodes.offer(currentNode.getLeftChild());
            }

            if (rightIdxValue != NULL_VALUE) {
                currentNode.setRightChild(new Node(nextDepth, rightIdxValue, null, null));
                nodes.offer(currentNode.getRightChild());
            }

            indexesCursor++;
        }

        return rootNode;
    }

    private static void swap(final Node currentNode, final int currentDepth, final int queryDepth) {
        if (currentNode == null) {
            return;
        }

        final int nextDepth = currentDepth + 1;

        swap(currentNode.getLeftChild(), nextDepth, queryDepth);
        swap(currentNode.getRightChild(), nextDepth, queryDepth);

        if (currentDepth >= queryDepth && currentDepth % queryDepth == 0) {
            final Node tempNode = currentNode.getLeftChild();
            currentNode.setLeftChild(currentNode.getRightChild());
            currentNode.setRightChild(tempNode);
        }
    }

    private static void generateInOrderNodeTree(final Node currentNode, final List<Integer> inOrderNodeTree) {
        if (currentNode == null) {
            return;
        }

        generateInOrderNodeTree(currentNode.getLeftChild(), inOrderNodeTree);
        inOrderNodeTree.add(currentNode.getIdxValue());
        generateInOrderNodeTree(currentNode.getRightChild(), inOrderNodeTree);
    }

    private static class Node {
        private final int depth;
        private final int idxValue;
        private Node leftChild;
        private Node rightChild;

        private Node(final int depth, final int idxValue, final Node leftChild, final Node rightChild) {
            this.depth = depth;
            this.idxValue = idxValue;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        private int getDepth() {
            return depth;
        }

        private int getIdxValue() {
            return idxValue;
        }

        private Node getLeftChild() {
            return leftChild;
        }

        private void setLeftChild(final Node leftChild) {
            this.leftChild = leftChild;
        }

        private Node getRightChild() {
            return rightChild;
        }

        private void setRightChild(final Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}
