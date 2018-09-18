package me.dlevin.aspectdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  private String username;

  private String password;

  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "user_bucket",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "bucket_id")
  )
  private final Set<Bucket> accessibleBuckets = new HashSet<>();

  public User(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

}
