package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> hashMap = new HashMap<>();

    @Override
    protected Resume getResume(String searchKey) {
        return hashMap.get(searchKey);
    }

    @Override
    protected void deleteResume(String searchKey) {
        hashMap.remove(searchKey);
    }

    @Override
    protected void saveResume(Resume r, String key) {
        hashMap.put(key, r);
    }

    @Override
    protected void updateResume(Resume r, String searchKey) {
        hashMap.put(searchKey, r);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return hashMap.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public List<Resume> copyAll() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public int size() {
        return hashMap.size();
    }
}
