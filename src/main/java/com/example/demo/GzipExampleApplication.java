package com.example.demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GzipExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzipExampleApplication.class, args);
    }

    @Component
    static class Runner implements CommandLineRunner {

        private final GzipService gzipService;

        public Runner(GzipService gzipService) {
            this.gzipService = gzipService;
        }

        @Override
        public void run(String... args) throws Exception {
            File file = new File("src/main/resources/testfile.txt");
            File compressed = gzipService.compress(file);
            System.out.println(compressed.getName());
            File uncompressed = gzipService.uncompress(compressed);
            System.out.println(uncompressed.getName());
            Files.delete(Paths.get("src/main/resources/testfile.txt.gz"));
            Files.delete(Paths.get("src/main/resources/testfile.txt2"));
        }
        
    }

}

