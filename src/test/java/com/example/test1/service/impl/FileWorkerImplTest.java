package com.example.test1.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@SpringJUnitConfig(TestConfig.class)
public class FileWorkerImplTest {
    private static final String DATABASE = "D:\\test1\\testDatabase.txt";
    private static final String SEPARATOR = "||";
    private FileWorkerImpl fileWorker;

    @Autowired
    public FileWorkerImplTest(FileWorkerImpl fileWorker) {
        this.fileWorker = fileWorker;
    }

    @BeforeAll
    public void beforeAll() throws IOException {
        FileWriter writer = new FileWriter(DATABASE);
        writer.close();
    }

    @Test
    public void testWrite() {
        fileWorker.write(DATABASE, "test");

        String read = fileWorker.read(DATABASE);

        assertEquals("test" + SEPARATOR, read);
    }
}
