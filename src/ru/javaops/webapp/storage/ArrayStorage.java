package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (resumeAlreadyPresent(r.getUuid())) {
            System.out.println("ERROR: Such resume is already present in data base");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume resume) {
        if (resumeAlreadyPresent(resume.getUuid())) {
            storage[indexOfResume(resume.getUuid())] = resume;
        }else {
            System.out.println("ERROR: Such resume is already present in data base");
        }

    }

    public Resume get(String uuid) {
        if (resumeAlreadyPresent(uuid)) {
            for (int i = 0; i <= size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }else {
            System.out.println("ERROR: Such resume is not present in data base");
        }
        return null;
    }

    public void delete(String uuid) {
        if (resumeAlreadyPresent(uuid)) {
            storage[indexOfResume(uuid)] = storage[size-1];
            storage[size-1] = null;
            size--;
        }else {
            System.out.println("ERROR: Such resume is not present in data base");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private Boolean resumeAlreadyPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private Integer indexOfResume(String uuid) {
        for (int i = 0; i <= size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

}

