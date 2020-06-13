package com.rotadeviagem.utils;

import java.io.File;

public class FileUtils {
    public static boolean checkExistance(final String path) throws NullPointerException {
        if(path == null) {
            throw new NullPointerException("Path fornecido como parâmetro é null");
        }

        final File file = new File(path);
        
        return file.exists();
    }
}