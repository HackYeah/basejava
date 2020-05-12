package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("ERROR: Resume with " + resume.getUuid() +" uuid is already present in data base");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Error: No space left in data base to save resume with " + resume.getUuid() + " uuid.");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
         int index = getIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: Resume with " + resume.getUuid() +" uuid is not present in data base");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume with " + uuid +" uuid is not present in data base");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}

