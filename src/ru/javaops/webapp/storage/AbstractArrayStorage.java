package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    protected int index;

    public void update(Resume resume) {
        index = getIndex(resume.getUuid());

        if (index < 0) {
            System.out.println("ERROR: Resume with " + resume.getUuid() + " uuid is not present in data base");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        index = getIndex(uuid);

        if (index < 0) {
            System.out.println("ERROR: Resume with " + uuid + " uuid is not present in data base");
        } else {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        index = getIndex(resume.getUuid());

        if (index >= 0) {
            System.out.println("ERROR: Resume with " + resume.getUuid() + " uuid is already present in data base");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Error: No space left in data base to save resume with " + resume.getUuid() + " uuid.");
        } else {
            insertResume(resume);
            size++;
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);

        if (index < 0) {
            System.out.println("ERROR: Resume with " + uuid + " uuid is not present in data base");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume);

}
