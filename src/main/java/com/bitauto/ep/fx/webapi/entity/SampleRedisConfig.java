package com.bitauto.ep.fx.webapi.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * You may set up data like the following in Apollo:
 * <pre>
 * redis.cache.enabled = true
 * redis.cache.expireSeconds = 100
 * redis.cache.clusterNodes = 1,2
 * redis.cache.commandTimeout = 50
 * redis.cache.someMap.key1 = a
 * redis.cache.someMap.key2 = b
 * redis.cache.someList[0] = c
 * redis.cache.someList[1] = d
 * </pre>
 *
 * To make <code>@ConditionalOnProperty</code> work properly, <code>apollo.bootstrap.enabled</code> should be set to true
 * and <code>redis.cache.enabled</code> should also be set to true. Check 'src/main/resources/application.yml' for more information.
 *
 * @author Jason Song(song_s@ctrip.com)
 */

@ConfigurationProperties(prefix = "redis.cache")  //配置文件的前缀
@Component("sampleRedisConfig")
@RefreshScope
public class SampleRedisConfig {

  private static final Logger logger = LoggerFactory.getLogger(SampleRedisConfig.class);

  private int expireSeconds;
  private String clusterNodes;
  private int commandTimeout;

  @PostConstruct
  private void initialize() {
    logger.info(
        "SampleRedisConfig initialized - expireSeconds: {}, clusterNodes: {}, commandTimeout: {}",
        expireSeconds, clusterNodes, commandTimeout);
  }

  public void setExpireSeconds(int expireSeconds) {
    this.expireSeconds = expireSeconds;
  }

  public void setClusterNodes(String clusterNodes) {
    this.clusterNodes = clusterNodes;
  }

  public void setCommandTimeout(int commandTimeout) {
    this.commandTimeout = commandTimeout;
  }

  public int getExpireSeconds() {
    return expireSeconds;
  }

  public String getClusterNodes() {
    return clusterNodes;
  }

  public int getCommandTimeout() {
    return commandTimeout;
  }

  @Override
  public String toString() {
    return "SampleRedisConfig{" +
            "expireSeconds=" + expireSeconds +
            ", clusterNodes='" + clusterNodes + '\'' +
            ", commandTimeout=" + commandTimeout +
            '}';
  }
}
