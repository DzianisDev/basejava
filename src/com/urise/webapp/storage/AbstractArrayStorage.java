package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public void save(Resume r) {
        if (size < STORAGE_LIMIT) {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                System.out.println("Resume with uuid: " + r.getUuid() + " is existing.");
            } else {
                insertResume(index, r);
                size++;
            }
        } else {
            System.out.println("Storage overflow!");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            size--;
            removeResume(index);
            storage[size] = null;
        } else {
            System.out.println("Resume with uuid: " + uuid + " doesn't exist.");
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
            System.out.println("Resume with uuid: " + r.getUuid() + " was updated");
        } else {
            System.out.println("Resume with uuid: " + r.getUuid() + " doesn't exist.");
        }
    }
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Resume with uuid: " + uuid + " doesn't exist.");
            return null;
        }
    }

    protected abstract int getIndex(String uuid);
    protected abstract void removeResume(int index);
    protected abstract void insertResume(int index, Resume r);

}
