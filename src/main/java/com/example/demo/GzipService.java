package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.stereotype.Service;

@Service
public class GzipService {

    public File compress(final File file) throws IOException {
        final String gzipFile = file.getAbsolutePath() + ".gz";
        try (final FileInputStream fis = new FileInputStream(file); final GZIPOutputStream gzipOS = new GZIPOutputStream(new FileOutputStream(gzipFile))) {
            write(fis, gzipOS);
        }
        return new File(gzipFile);
    }

    public File uncompress(final File gzipFile) throws IOException {
        final String file = gzipFile.getAbsolutePath().substring(0, gzipFile.getAbsolutePath().lastIndexOf(".")) + "2";
        try (final FileOutputStream fos = new FileOutputStream(file); final GZIPInputStream gzipIS = new GZIPInputStream(new FileInputStream(gzipFile))) {
            write(gzipIS, fos);
        }
        return new File(file);
    }

    private void write(final InputStream is, final OutputStream os) throws IOException {
        final byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
    }
}
