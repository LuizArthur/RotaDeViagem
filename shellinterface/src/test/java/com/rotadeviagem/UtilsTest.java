package com.rotadeviagem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.rotadeviagem.utils.FileUtils;

import org.junit.Test;

public class UtilsTest {
    
    @Test
    public void FileUtilsReturnTrue() {
        String projectDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(projectDirectory, "src", "main", "java", "com", "rotadeviagem", "utils", "FileUtils.java");
        String filePathStr = filePath.toString();

        assertEquals(true, FileUtils.checkExistance(filePathStr));
    }

    @Test
    public void FileUtilsReturnFalse() {
        String projectDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(projectDirectory, "main", "java", "com", "rotadeviagem", "utils", "FileUtils.java");
        String filePathStr = filePath.toString();

        assertEquals(false, FileUtils.checkExistance(filePathStr));
    }

    @Test(expected = NullPointerException.class)
    public void FileUtilsWithNullParameter() {
        boolean exists = FileUtils.checkExistance(null);
    }    
}