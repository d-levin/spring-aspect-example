package me.dlevin.aspectdemo.controller;

import me.dlevin.aspectdemo.aspect.BucketAware;
import me.dlevin.aspectdemo.util.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectDemoController {

  @GetMapping("/buckets/{bucketId}/items")
  @BucketAware
  public String getItemsInBucket(@PathVariable final int bucketId) {
    Logger.debug("Inside controller!");
    return "You had access to bucket [" + bucketId + "]";
  }

}
