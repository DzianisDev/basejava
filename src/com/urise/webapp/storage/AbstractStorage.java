package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    protected final Logger LOG = Logger.getLogger(getClass().getName());

    protected abstract Resume getResume(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract void saveResume(Resume r, SK searchKey);

    protected abstract void updateResume(Resume r, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract List<Resume> copyAll();

    public void update(Resume r) {
        LOG.info("Resume: "+ r + " updated");
        SK searchKey = getExistedSearchKey(r.getUuid());
        updateResume(r, searchKey);
    }

    public void save(Resume r) {
        LOG.info("Resume: "+ r + " Saved");
        SK searchKey = getNotExistedSearchKey(r.getUuid());
        saveResume(r, searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = copyAll();
        Collections.sort(list);
        return list;
    }

    public void delete(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
