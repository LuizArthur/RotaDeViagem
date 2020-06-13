package com.rotadeviagem.utils;

import java.io.File;

public class FileUtils {
    public static boolean checkExistance(String path) {
        File file = new File(path);
        
        return file.exists();
    }
}