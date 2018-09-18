package me.dlevin.aspectdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Bucket {

  @Id
  @GeneratedValue
  private Integer id;

  private String name;

  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "accessibleBuckets")
  private final Set<User> users = new HashSet<>();

  public Bucket(final String name) {
    this.name = name;
  }

}
