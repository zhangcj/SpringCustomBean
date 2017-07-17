package com.beecho.springxoxo.pool;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.StackKeyedObjectPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 春哥大魔王
 */

public class UnlimitedKeyedPoolableObjectFactory extends BaseKeyedPoolableObjectFactory<String, Runnable> {
    private static final Logger logger = LoggerFactory.getLogger(UnlimitedKeyedPoolableObjectFactory.class);

    private static final KeyedPoolableObjectFactory<String, Runnable> unlimitedFactory = new UnlimitedKeyedPoolableObjectFactory();
    private static final KeyedObjectPoolFactory<String, Runnable> unlimitedPoolFactory = new StackKeyedObjectPoolFactory<String, Runnable>(unlimitedFactory);
    public static KeyedObjectPool<String, Runnable> objectPool = unlimitedPoolFactory.createPool();

    public Runnable makeObject(String s) throws Exception {
        try {
            @SuppressWarnings("unchecked")
            Class<Runnable> cls = (Class<Runnable>) Class.forName(s);
            Runnable obj = cls.newInstance();
            logger.info("create class:{}", s);
            return obj;
        } catch(Exception e) {
            logger.error("unlimitedObjectPool exception", e);
            throw new RuntimeException(e);
        }
    }

    public void destroyObject(String key, Runnable obj) throws Exception {
        obj = null;
    }
}
