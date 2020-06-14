package com.rotadeviagem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.rotadeviagem.utils.FileUtils;

import org.junit.Test;

public class UtilsTest {
    
    @Test
    public void FileUtilsCheckExistancePathExists() {
        final String projectDirectory = System.getProperty("user.dir");
        final Path filePath = Paths.get(projectDirectory, "src", "main", "java", "com", "rotadeviagem", "utils",
                "FileUtils.java");
        final String filePathStr = filePath.toString();

        assertEquals(true, FileUtils.checkExistance(filePathStr));
    }

    @Test
    public void FileUtilsCheckExistancePathDoesNotExists() {
        final String projectDirectory = System.getProperty("user.dir");
        final Path filePath = Paths.get(projectDirectory, "main", "java", "com", "rotadeviagem", "utils",
                "FileUtils.java");
        final String filePathStr = filePath.toString();

        assertEquals(false, FileUtils.checkExistance(filePathStr));
    }

    @Test(expected = NullPointerException.class)
    public void FileUtilsCheckExistanceWithNullParameter() {
        final boolean exists = FileUtils.checkExistance(null);
    }    
}