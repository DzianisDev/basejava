package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    private HashMap<String, Resume> hashMap = new HashMap<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return hashMap.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        hashMap.remove((String) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object key) {
        hashMap.put((String) key, r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        hashMap.put((String) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return hashMap.containsKey((String) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return hashMap.values().toArray(new Resume[hashMap.size()]);
    }

    @Override
    public int size() {
        return hashMap.size();
    }
}
