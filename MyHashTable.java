public class MyHashTable<K, V> {
    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        createArray();
    }

    public MyHashTable(int M) {
        this.M = M;
        createArray();
    }

    private void createArray() {
        chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();
        if (hash < 0) {
            hash = -hash;
        }
        return hash % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> previous = null;

        while (current != null) {
            if (equals(current.key, key)) {
                if (previous == null) {
                    chainArray[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (equals(current.value, value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (equals(current.value, value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int getBucketCount() {
        return M;
    }

    public int getBucketSize(int index) {
        int count = 0;
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    private boolean equals(Object first, Object second) {
        if (first == null) {
            return second == null;
        }
        return first.equals(second);
    }
}
