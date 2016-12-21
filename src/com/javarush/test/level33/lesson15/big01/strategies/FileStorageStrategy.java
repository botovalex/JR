package com.javarush.test.level33.lesson15.big01.strategies;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private float loadFactor = DEFAULT_LOAD_FACTOR;
    private long bucketSizeLimit = 10000L;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    /*
    10.3.	Работать аналогично тому, как это делает OurHashMapStorageStrategy, но
    удваивать количество ведер не когда количество элементов size станет больше
    какого-то порога, а когда размер одного из ведер (файлов) стал больше
    bucketSizeLimit.
    10.4.	При реализации метода resize(int newCapacity) проследи, чтобы уже не нужные
    файлы были удалены (вызови метод  remove()).
    Проверь новую стратегию в методе main(). Учти, что стратегия FileStorageStrategy гораздо
    более медленная, чем остальные. Не используй большое количество элементов для теста,
    это может занять оооочень много времени.
         */


    int hash(Long k) {
        int h = 0;
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        if (size == 0) return null;

        int hash = (key == null) ? 0 : hash(key);
        FileBucket e = table[indexFor(hash, table.length)];
        if (e.getEntry().getKey() == key) return e.getEntry();
        return null;

    }

    void resize(int newCapacity) {
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == 1 << 30) {
            bucketSizeLimit = Integer.MAX_VALUE;
            return;
        }

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        bucketSizeLimit = (int) Math.min(newCapacity * loadFactor, 1 << 30 + 1);
    }

    void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        for (FileBucket e : table) {
            int hash = (e.getEntry().getKey() == null) ? 0 : hash(e.getEntry().getKey());
            int i = indexFor(hash, newCapacity);
            newTable[i] = e;
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        boolean isSizeTooBug = false;
        for (FileBucket bucket : table) {
            if (bucket.getFileSize() > bucketSizeLimit) {
                isSizeTooBug = true;
                break;
            }
        }

        if (isSizeTooBug && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket e = table[bucketIndex];
//        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }


    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {

    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }
}
