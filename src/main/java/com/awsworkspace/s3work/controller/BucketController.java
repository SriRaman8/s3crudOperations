package com.awsworkspace.s3work.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.awsworkspace.s3work.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    BucketService bucketService;

    @GetMapping
    public void getBucketList() {
        List<Bucket> bucketList = bucketService.getBucketList();
        System.out.println("bucketList:"+bucketList);
    }

    @GetMapping("/downloadObj")
    public void downloadObject(@RequestParam("bucketName") String bucketName, @RequestParam("objName") String objName) throws Exception {
        bucketService.getObjectFromBucket(bucketName, objName);
    }

    @PostMapping("/uploadObj")
    public void uploadObject(@RequestParam("bucketName") String bucketName, @RequestParam("objName") String objName) throws Exception {
        bucketService.putObjectIntoBucket(bucketName, objName,"E:\\awspratice\\s3work\\src\\main\\resources\\inputfiles\\uploadfile.txt");
    }

    @PostMapping("/createBucket")
    public String createBucket(@RequestParam("bucketName") String bucketName) {
        bucketService.createBucket(bucketName);
        return "done";
    }

}