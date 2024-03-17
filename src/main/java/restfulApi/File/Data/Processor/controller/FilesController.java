package restfulApi.File.Data.Processor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restfulApi.File.Data.Processor.entity.Files;
import restfulApi.File.Data.Processor.service.FilesService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    private FilesService filesService;

    @PostMapping("/uploadFilesIntoDB")
    public ResponseEntity<String> storeFilesIntoDB(@RequestParam("file") MultipartFile file) throws IOException {
        String response = filesService.storeFile(file);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/getFileByName/{fileName}")
    public Files getFile(@PathVariable String fileName) {
        Files fileData = filesService.getFile(fileName);
        return fileData;
    }

    @GetMapping("/getAllFiles")
    public List<Files> getAllFiles(){
        return filesService.getFiles();
    }


    @GetMapping("/getFilesFromFileSystem/{fileName}")
    public ResponseEntity<Files> downloadImageFromFileSystem(@PathVariable String fileName) {
        Files data = filesService.getFile(fileName);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
