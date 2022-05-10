package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.service.FileStoreService;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UtilApiController {

    private final FileStoreService fileStoreService;

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStoreService.getFullPath(filename));
    }
}
