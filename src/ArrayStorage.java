import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);;
    }

    void save(Resume r) {
        for(int i = 0; i < storage.length; i++)
            if (storage [i] == null) {
                storage[i] = r;
                break;
            }
    }

    Resume get(String uuid) {
        for (int i = 0; i <= storage.length-1; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i <= storage.length-1; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int newSize = 0;
        int newIndex = 0;

        for (int i = 0; i < storage.length-1; i++)    {
            if (storage[i] != null) {
                newSize++;
            }
        }

        Resume [] fullResumes = new Resume[newSize];
        for (int i = 0; i < storage.length-1; i++) {
            if (storage[i] != null) {
                fullResumes[newIndex] = storage[i];
                newIndex++;
            }
        }
        return fullResumes;
    }

    int size() {
        int newSize = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                newSize++;
            }
        }
        return newSize;
    }
}

