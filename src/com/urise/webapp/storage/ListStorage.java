package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected Resume getResume(Integer searchKey) {
        int index = searchKey;
        return list.get(index);
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        int index = searchKey;
        list.remove(index);
    }

    @Override
    protected void saveResume(Resume r, Integer searchKey) {
        list.add(r);
    }

    @Override
    protected void updateResume(Resume r, Integer searchKey) {
        list.set(searchKey, r);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> copyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
