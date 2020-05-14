package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void removeResume(int index) {
        ArrayList<Resume> tempList = new ArrayList<>(Arrays.asList(storage));
        tempList.remove(index);
        storage = tempList.toArray(new Resume[STORAGE_LIMIT]);
        }


}



