class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;

    HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode)
    {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int getHashCode() {
        return hashCode;
    }

    public HashNode<K, V> getNext() {
        return next;
    }
}