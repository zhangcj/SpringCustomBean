package com.beecho.springxoxo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 春哥大魔王
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface CacheResult {
    String key();
    String cacheName();
    String backupKey() default "";
    boolean needBloomFilter() default false;
    boolean needLock() default false;
}
