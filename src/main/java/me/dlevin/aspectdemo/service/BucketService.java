package me.dlevin.aspectdemo.service;

import me.dlevin.aspectdemo.model.Bucket;
import me.dlevin.aspectdemo.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BucketService {

  private final BucketRepository bucketRepository;

  @Autowired
  public BucketService(final BucketRepository bucketRepository) {
    this.bucketRepository = bucketRepository;
  }

  public Optional<Bucket> find(final Integer id) {
    return this.bucketRepository.findById(id);
  }

}
