package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.stereotype.Service;

@Service
public class GzipService {

    public File compress(File file) throws IOException {
        String gzipFile = file.getAbsolutePath() + ".gz";
        try (FileInputStream fis = new FileInputStream(file); GZIPOutputStream gzipOS = new GZIPOutputStream(new FileOutputStream(gzipFile))) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, len);
            }
        }
        return new File(gzipFile);
    }

    public File uncompress(File gzipFile) throws IOException {
        String file = gzipFile.getAbsolutePath().substring(0, gzipFile.getAbsolutePath().lastIndexOf(".")) + "2";
        try (FileOutputStream fos = new FileOutputStream(file); GZIPInputStream gzipIS = new GZIPInputStream(new FileInputStream(gzipFile))) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipIS.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        }
        return new File(file);
    }
}
