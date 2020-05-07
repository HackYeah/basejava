package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10_000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;
    private int index;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

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
        index = getIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: Resume with " + resume.getUuid() +" uuid is not present in data base");
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Resume with " + uuid + " uuid is not present in data base");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        index = getIndex(uuid);

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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}

