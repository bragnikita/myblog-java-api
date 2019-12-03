package jp.bragnikita.myblogapi.controllers;

import jp.bragnikita.myblogapi.models.Types;
import jp.bragnikita.myblogapi.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    private final FileStorageService service;

    public ResourcesController(FileStorageService service) {
        this.service = service;
    }

    @PostMapping("/images/post/{id}")
    public Types.ImageRef uploadImage(@RequestParam("file") MultipartFile file,
                                      @PathVariable("id") String postId,
                                      UriComponentsBuilder uri) {
        FileStorageService.ResourceRef ref = this.service.save("posts/" + postId, file);
        Types.ImageRef iRef = new Types.ImageRef();
        iRef.url = uri.path(ref.getLocation()).toUriString();
        return iRef;
    }

}
