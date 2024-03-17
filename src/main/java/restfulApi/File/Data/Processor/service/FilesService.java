package restfulApi.File.Data.Processor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import restfulApi.File.Data.Processor.entity.FileRepository;
import restfulApi.File.Data.Processor.entity.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FilesService {

    @Autowired
    private FileRepository fileRepository;
    private final String FILE_PATH = "E:\\projects\\images\\storage\\";

    public String storeFile(MultipartFile file) throws IOException {
        Files files = Files
                .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
               .data(file.getBytes())
                .build();

        files = fileRepository.save(files);

        if (files.getName() != null &&  files.getData() != null &&  files.getType() != null) {
            return "File uploaded successfully into database";
        }
        else{
            return "File was not uploaded into database";
        }
    }

    public Files getFile(String fileName) {
        return fileRepository.findByName(fileName);
    }


    public List<Files> getFiles(){
        return fileRepository.findAll();
    }


    public byte[] downloadFilesFromFileSystem(String fileName) throws IOException {
        String path = fileRepository.findByName(fileName).getPath();

        return java.nio.file.Files.readAllBytes(new File(path).toPath());
    }
}
