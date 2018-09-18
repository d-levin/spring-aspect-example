package me.dlevin.aspectdemo.repository;

import me.dlevin.aspectdemo.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Integer> {

}
