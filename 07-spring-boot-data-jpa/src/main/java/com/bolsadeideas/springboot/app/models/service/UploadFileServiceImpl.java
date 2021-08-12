package com.bolsadeideas.springboot.app.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final static String UPLOADS_FOLDER = "uploads";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = this.getPath(filename);
        log.info("pathFoto : " + pathFoto);
        Resource recurso = null;

        recurso = new UrlResource(pathFoto.toUri());

        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error: No se puede cargar la imagen: " + pathFoto.toString());
        }

        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {

        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = this.getPath(uniqueFilename);

        log.info("rootPath: " + rootPath);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = this.getPath(filename);
        File archivo = rootPath.toFile();

        if (archivo.exists() && archivo.canRead()) {
            return archivo.delete();
        }

        return false;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        if (Files.notExists(Paths.get(UPLOADS_FOLDER))) {
            Files.createDirectory(Paths.get(UPLOADS_FOLDER));
            log.info("No existe el folder " + UPLOADS_FOLDER + " y lo vamos a crear");
        } else {
            log.info("El folder " + UPLOADS_FOLDER + " existe para guardar archivos");
        }
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

}
