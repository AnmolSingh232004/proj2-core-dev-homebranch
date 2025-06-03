package org.tasos.proj2.domain.hackerrank;

// Node class to represent each element in the linked list
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    // Method to swap alternate elements
    public void swapAlternate() {
        // If list is empty or has only one node
        if (head == null || head.next == null)
            return;

        Node current = head;

        // Traverse the list and swap adjacent nodes
        while (current != null && current.next != null) {
            // Swap data of current and next node
            int temp = current.data;
            current.data = current.next.data;
            current.next.data = temp;

            // Move to next pair of nodes
            current = current.next.next;
        }
    }

    // Utility method to insert a new node at the end
    public void append(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node last = head;
        while (last.next != null)
            last = last.next;

        last.next = newNode;
    }

    // Utility method to print the linked list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Create a sample linked list: 1->2->3->4->5->6
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);

        System.out.println("Original List:");
        list.printList();

        list.swapAlternate();

        System.out.println("After swapping alternate elements:");
        list.printList();
    }
}