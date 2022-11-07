package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected void saveResume(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(index, r);
            size++;
        }
    }

    @Override
    public void deleteResume(Integer index) {
        size--;
        removeResume(index);
        storage[size] = null;
    }

    @Override
    protected void updateResume(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    public List<Resume> copyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public Resume getResume(Integer index) {
        return storage[index];
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void removeResume(int index);

    protected abstract void insertResume(int index, Resume r);

}
