package ru.eremin;


public class MyOwnHashMap<K, V> {
    private Node<K, V>[] nodes;
    private final float loadFactor;

    // TODO
    //  Замечания:
    //  1) перепроверь метод put, по идее nodes[index].value = newNode.value будет перетирать 1 ноду, скорее всего нужно storedNode.value
    //  2) словишь npe, если будешь передавать null ключ на key.hashCode()
    //  3) checkResize выполняет за O(N), добавь поле size и упрости метод
    //  Улучшения:
    //  1) Добавить поддержку других методов интерфейса Map (итерация по entry Set, contains и тд)



    @SuppressWarnings("unchecked")
    public MyOwnHashMap() {
        this.nodes = (Node<K, V>[]) new Node[16];
        this.loadFactor = 0.75f;
    }

    @SuppressWarnings("unchecked")
    public MyOwnHashMap(int arraySize, float loadFactor) {
        this.nodes = (Node<K, V>[]) new Node[arraySize];
        this.loadFactor = loadFactor;
    }

    public void put(K key, V value) {
        if (checkResize()) {
            resize();
        }

        Node<K, V> newNode = new Node<>(key, value, null);
        int index = (nodes.length - 1) & newNode.key.hashCode();

        if (index >= nodes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (nodes[index] == null) {
            nodes[index] = newNode;
        } else {
            Node<K, V> storedNode = nodes[index];

            while (storedNode.hasNext()) {
                if (storedNode.key.equals(newNode.key)) {
                    nodes[index].value = newNode.value;
                    return;
                }
                storedNode = storedNode.next;
            }

            storedNode.setNext(newNode);
        }
    }

    public V get(K key) {
        int index = (nodes.length - 1) & key.hashCode();
        if (index >= nodes.length) {
            return null;
        }

        Node<K, V> node = nodes[index];
        if (node == null) {
            return null;
        }

        while (node.hasNext()) {
            if (node.key.equals(key)) {

                return node.value;
            }
            node = node.next;
        }
        if (node.key.equals(key)) {
            return node.value;
        } else {
            return null;
        }
    }

    public void remove(K key) {
        int index = (nodes.length - 1) & key.hashCode();
        if (index >= nodes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node<K, V> previousNode = null;
        Node<K, V> node = nodes[index];

        while (node.hasNext()) {
            if (node.key.equals(key) && previousNode == null) {
                nodes[index] = node.next;
                return;

            } else if (node.key.equals(key) && previousNode != null) {
                previousNode.setNext(node.next);
                return;
            }

            previousNode = node;
            node = node.next;
        }
        nodes[index] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Node<K, V> n : nodes) {
            Node<K, V> node = n;
            if (node != null) {
                sb.append(node).append("\n");

                while (node.hasNext()) {
                    sb.append(node.next).append("\n");
                    node = node.next;
                }
            }
        }
        return sb.toString();
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        Node<K, V>[] newArray = (Node<K, V>[]) new Node[nodes.length * 2];

        Node<K, V> previousNode;

        for (Node<K, V> kvNode : nodes) {
            Node<K, V> node = kvNode;
            if (node != null) {
                putNodeInNewArray(newArray, node);

                while (node.hasNext()) {
                    previousNode = node;
                    node = node.next;
                    previousNode.setNext(null);
                    putNodeInNewArray(newArray, node);
                }
            }
        }

        nodes = newArray;
    }

    private void putNodeInNewArray(Node<K, V>[] newArray, Node<K, V> node) {
        int newIndex;
        newIndex = (newArray.length - 1) & node.key.hashCode();
        if (newArray[newIndex] != null) {
            Node<K, V> storedNode = newArray[newIndex];
            while (storedNode.hasNext()) {
                storedNode = storedNode.next;
            }
            storedNode.setNext(node);
        } else {
            newArray[newIndex] = node;
        }
    }

    private boolean checkResize() {
        long count = 0L;
        for (Node<K, V> n : nodes) {
            Node<K, V> node = n;
            if (node != null) {
                count++;
                while (node.hasNext()) {
                    count++;
                    node = node.next;
                }
            }
        }
        return count / (float) nodes.length > loadFactor;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        private Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private boolean hasNext() {
            return next != null;
        }

        private void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
