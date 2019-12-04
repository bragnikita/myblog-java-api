package jp.bragnikita.myblogapi.controllers;

import jp.bragnikita.myblogapi.models.ModelObjectRefs;
import jp.bragnikita.myblogapi.services.FileStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    private final FileStorageService service;

    public ResourcesController(FileStorageService service) {
        this.service = service;
    }

    @PostMapping("/images/post/{id}")
    public ModelObjectRefs.ImageRef uploadImage(@RequestParam("file") MultipartFile file,
                                                @PathVariable("id") String postId,
                                                UriComponentsBuilder uri) {
        FileStorageService.ResourceRef ref = this.service.save("posts/" + postId, file);
        ModelObjectRefs.ImageRef iRef = new ModelObjectRefs.ImageRef();
        iRef.locator = uri.path(ref.getLocation()).toUriString();
        return iRef;
    }

}
