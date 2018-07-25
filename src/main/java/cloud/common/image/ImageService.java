package cloud.common.image;


import cloud.common.image.ImageRepository;
import cloud.common.Result;
import cloud.common.image.Image;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

//     for mac
    private String currentPath = "/Users/mac/Desktop/backend/upload/";

    // for aliyun
    private String serverPath = "https://mushroom-server.oss-us-west-1.aliyuncs.com/";
    private String endpoint = "http://oss-us-west-1.aliyuncs.com";
    private String accessKeyId = "LTAILFyLtkB3kAKk";
    private String accessKeySecret = "icfVv3qypFczNjWnQYX0kVqiyAb4Zl";
    private String bucketName = "mushroom-server";


//     for ubuntu server
//    private String currentPath = "/home/backend/upload/";

    public byte[] showImage(String fileName) {

        byte[] data = null;
        Path path = Paths.get(currentPath + fileName);
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String imageIdToImagePath(String imageId) {

        Image image = imageRepository.findByImageId(imageId);

        return  image.getName();
    }

    public Image imageIdToImage(String imageId) {

        Image image = imageRepository.findByImageId(imageId);

        return image;

    }

    public Image saveImage(MultipartFile file, String parentId, String type) {

        String target;
        try {
            // save image to server, return the filename
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + suffix;

//            BufferedOutputStream serverFile = new BufferedOutputStream(
//                    new FileOutputStream(
//                            new File(currentPath + fileName)));
//
//            serverFile.write(file.getBytes());
//            serverFile.flush();
//            serverFile.close();

            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(file.getBytes()));

            ossClient.shutdown();


            // save image path to database
            Image image = new Image();
            image.setName(fileName);
            image.setParentId(parentId);
            image.setType(type);
            imageRepository.save(image);
            return image;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return null;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

    }

    public List<Image> saveImages(MultipartFile[] files, String parent, String type) {

        List<Image> targets = new ArrayList<>();

        for(int i =0 ;i< files.length; i++){
            Image image = saveImage(files[i], parent, type);
            targets.add(image);
        }

        return targets;
    }

    public void deleteImageByImageId(String imageId) {

        Image image = imageRepository.findByImageId(imageId);

        try {

            String objectName = image.getName();
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject(bucketName, objectName);
            ossClient.shutdown();

            imageRepository.deleteByImageId(image.getImageId());

        } catch(Exception e) {
            e.printStackTrace();
        }


        imageRepository.deleteByImageId(imageId);
    }

    public void deleteAllByParentId(String parentId) {

        Iterable<Image> images = imageRepository.findAllByParentId(parentId);

        for (Image image: images) {
            try {

                deleteImageByImageId(image.getImageId());

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void updateParentId(String parentId, String imageId) {

        imageRepository.updateParentByImageId(parentId, imageId);

    }

    public void updateType(String type, String imageId) {

        imageRepository.updateTypeImageId(type, imageId);

    }

}
