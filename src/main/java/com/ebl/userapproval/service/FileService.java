package com.ebl.userapproval.service;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    public void save(MultipartFile multipartFile, String filePath, String fileName) {
        try {
            Files.copy(multipartFile.getInputStream(), Paths.get(filePath).resolve(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteSingleFile(String filePath) {
        try {
            if(filePath!=null){
                Files.deleteIfExists(Paths.get(filePath));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex < 0) {
            return null;
        }
        return fileName.substring(dotIndex + 1);
    }

}