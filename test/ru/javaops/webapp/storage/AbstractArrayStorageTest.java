package ru.javaops.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javaops.webapp.exception.NotExistStorageException;
import ru.javaops.webapp.exception.StorageException;
import ru.javaops.webapp.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

    }
    @Test
    public void update() {
        Resume resumeToUpdate = new Resume(UUID_3);
        storage.update(resumeToUpdate);
        Assert.assertEquals(storage.get(UUID_3), resumeToUpdate);

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());

        for (Resume resume: storage.getAll()) {
            Assert.assertEquals(storage.get(resume.getUuid()), resume);
        }
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void save() {
        Resume resumeToSave = new Resume(UUID_4);
        storage.save(resumeToSave);

        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storage.get(UUID_4), resumeToSave);
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_1, storage.get(UUID_1).toString());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception{
        storage.get("dummy");
    }

    @Test
    public void storageOverflow(){
        try {
            for (int i = 3; i <= 10; i++) {
                storage.save(new Resume());
                if (i == 10) {
                    Assert.fail("Should cause storage overflow");
                }
            }
        } catch(StorageException e) {
            Assert.assertTrue(true);
        }


    }
}
