package me.dlevin.aspectdemo.aspect;

import me.dlevin.aspectdemo.exceptions.EntityNotFoundException;
import me.dlevin.aspectdemo.exceptions.UnauthenticatedException;
import me.dlevin.aspectdemo.exceptions.UnauthorizedException;
import me.dlevin.aspectdemo.model.Bucket;
import me.dlevin.aspectdemo.model.User;
import me.dlevin.aspectdemo.service.BucketService;
import me.dlevin.aspectdemo.service.UserService;
import me.dlevin.aspectdemo.util.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Aspect
@Component
public class BucketAwareAspect {

  /*
  Aspect Usage:
  1. On a method // Already handled by annotation
  2. Method must have a bucketId path variable // Should the name be configurable?
  3. Method must be a @RequestMapping // Why?

  Flow:
  1. Validate aspect usage
  1. Check user is authenticated // Why? This aspect is being auth already
  2. Check user has access to bucket

  Throws:
  401 Unauthorized if user is not authenticated (UnauthenticatedException)
  403 Forbidden if user does not have access to bucket (UnauthorizedException)
  404 Not found if user or bucket cannot be found (EntityNotFoundException)

  Todo:
  Before aspect can be used, ensure that method has @PathVariable in parameter list

  Notes:
  Execution will not enter controller method if exceptions are thrown in aspect
   */

  private static final String PATH_VARIABLE_BUCKET_ID = "bucketId";

  private final UserService userService;
  private final BucketService bucketService;

  @Autowired
  public BucketAwareAspect(final UserService userService, final BucketService bucketService) {
    this.userService = userService;
    this.bucketService = bucketService;
  }

  @Before("@annotation(me.dlevin.aspectdemo.aspect.BucketAware)")
  public void handle() {
    Logger.debug("Inside aspect!");

    this.checkAuthorization();
  }

  private void checkAuthorization() {
    Logger.debug("Checking authorization");

    final Authentication authentication = this.getAuthentication()
            .orElseThrow(() -> new UnauthenticatedException("User not authenticated"));
    final User user = this.userService.find(authentication.getName())
            .orElseThrow(() -> new EntityNotFoundException(User.class, authentication.getName()));

    final Set<Bucket> accessibleBuckets = user.getAccessibleBuckets();
    final Integer bucketId = this.extractBucketId(this.getHttpServletRequest());
    final Bucket bucket = this.bucketService.find(bucketId)
            .orElseThrow(() -> new EntityNotFoundException(Bucket.class, bucketId));

    if (!accessibleBuckets.contains(bucket)) {
      throw new UnauthorizedException(
              "User [" + user.getUsername() + "] not authorized to bucket [" + bucketId + "]"
      );
    }

    Logger.debug("User [" + user.getUsername() + "] had access to bucket [" + bucket.getId() + "]");
  }

  private HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
  }

  private Optional<Authentication> getAuthentication() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
  }

  private Map<String, Object> extractPathVariables(final HttpServletRequest request) {
    return (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
  }

  private Integer extractBucketId(final HttpServletRequest request) {
    final Object bucketId = this.extractPathVariables(request).get(PATH_VARIABLE_BUCKET_ID);
    return Integer.valueOf((String) bucketId);
  }

}
