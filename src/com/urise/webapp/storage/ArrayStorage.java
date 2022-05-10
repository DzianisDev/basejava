package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < STORAGE_LIMIT) {
            int position = findByUuid(r.getUuid());
            if (position != -1) {
                System.out.println("Resume with uuid: " + r.getUuid() + " is existing.");
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r) {
        int position = findByUuid(r.getUuid());
        if (position != -1) {
            storage[position] = r;
            System.out.println("Resume with uuid: " + r.getUuid() + " was updated");
        } else {
            System.out.println("Resume with uuid: " + r.getUuid() + " doesn't exist.");
        }
    }

    public Resume get(String uuid) {
        int position = findByUuid(uuid);
        if (position != -1) {
            return storage[position];
        } else {
            System.out.println("Resume with uuid: " + uuid + " doesn't exist.");
            return null;
        }

    }

    public void delete(String uuid) {
        int position = findByUuid(uuid);
        if (position != -1) {
            size--;
            storage[position] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume with uuid: " + uuid + " doesn't exist.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
