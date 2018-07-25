package cloud.common.image;

import cloud.common.BaseController;
import cloud.common.Result;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class ImageController extends BaseController {

    @Autowired
    private ImageRepository imageRepository;

    @Resource
    private ImageService imageService;

    @PostMapping("/image/all")
    public Result all() {

        Iterable<Image> images = imageRepository.findAll();

        return new Result("success", "all images", images);

    }

    @PostMapping("/image/delAll")
    public Result delAll() {

        imageService.delAll();

        return new Result("success", "delete all images");
    }

    @PostMapping("/image/deleteByImageId")
    public Result del(HttpServletRequest request) {

        String imageId = request.getParameter("imageId");

        imageService.deleteByImageId(imageId);

        return new Result("success", "delete by image id");

    }

    @PostMapping("/image/deleteByParentId")
    public Result deleteAllByParentId(HttpServletRequest request) {

        String parentId = request.getParameter("parentId");

        imageService.deleteAllByParentId(parentId);

        return new Result("success", "delete by parent id");

    }

    @PostMapping("/image/imageIdToImage")
    public Result imageIdToImage(HttpServletRequest request) {

        String imageId = request.getParameter("imageId");

        Image image = imageService.imageIdToImage(imageId);

        return new Result("success", "image id to image", image);

    }

    @PostMapping("/image/saveImage")
    public Result saveImage(HttpServletRequest request, @RequestParam("image") MultipartFile image) {

        String parent = request.getParameter("parentId");
        String type = request.getParameter("type");

        Image file = imageService.saveImage(image, parent, type);



        return new Result("success", "save one image", file);
    }

    @PostMapping("/image/saveImages")
    public Result saveImages(HttpServletRequest request, @RequestParam("image") MultipartFile[] images) {

        String parent = request.getParameter("parentId");
        String type = request.getParameter("type");

        Iterable<Image> files = imageService.saveImages(images, parent, type);

        return new Result("success", "save images", files);
    }

//    @GetMapping(value = { "/image/{filename:.+}" },
//            produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE })
//    public byte[] showImageById(@PathVariable String filename) {
//
//        return imageService.showImage(filename);
//    }

}

