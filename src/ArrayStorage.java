import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int SIZE = 0;

    void clear() {
        if (SIZE == 0) {
            System.out.println("Array is clear");
        } else {
            Arrays.fill(storage, 0, SIZE, null);
            SIZE = 0;
        }
    }

    void save(Resume r) {
        if (SIZE < 10000) {
            storage[SIZE] = r;
            SIZE++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < SIZE; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < SIZE; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[SIZE - 1];
                storage[SIZE - 1] = null;
                SIZE--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, SIZE);
    }

    int size() {
        return SIZE;
    }
}
