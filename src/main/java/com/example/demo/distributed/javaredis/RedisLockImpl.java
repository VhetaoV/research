package com.example.demo.distributed.javaredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: chenliang
 * @create: 2020-11-09 19:57
 **/
@Service
public class RedisLockImpl implements RedisDistributionLock{

    // 加锁超时时间，单位毫秒， 即：加锁时间内执行完操作，如果未完成会有并发现象
    private static final long LOCK_TIMEOUT = 5 * 1000;

    private static final Logger LOG = LoggerFactory.getLogger(RedisLockImpl.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁 取到锁加锁，取不到锁一直等待知道获得锁
     *
     * @param lockKey
     * @param threadName
     * @return
     */
    @Override
    public synchronized long lock(String lockKey, String threadName) {
        LOG.info(threadName+"开始执行加锁");
        //循环获取锁
        while (true) {
            // 锁超时时间
            Long lock_timeout = currtTimeForRedis() + LOCK_TIMEOUT + 1;
            Boolean execute = redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] value = serializer.serialize(lock_timeout.toString());
                    boolean flag = redisConnection.setNX(lockKey.getBytes(), value);
                    return flag;
                }
            });
            if (execute) {
                LOG.info(threadName +"加锁成功 ++++ 111111");
                redisTemplate.expire(lockKey, LOCK_TIMEOUT * 2, TimeUnit.MILLISECONDS);
                return lock_timeout;
            } else {
                // 获取上一个锁的时间
                String lastLockTimeStr = redisTemplate.opsForValue().get(lockKey);
                Long lastLockTime = lastLockTimeStr == null ? null : Long.parseLong(lastLockTimeStr);
                //当前时间和上一个锁的到期时间做比较
                long timeGap = lastLockTime - currtTimeForRedis();
                LOG.info("lastLockTime:"+lastLockTime+"---"+timeGap);
                // 锁已经失效
                if (lastLockTime != null && timeGap < 0) {
                    // 判断是否为空，不为空时且时间差值小于0，说明已经失效，如果被其他线程设置了值，则第二个条件判断无法执行
                    // 获取上一个锁到期时间，并设置现在的锁到期时间,通过这个原子操作，避免多线程竞争
                    Long lastLockTimeStr2 = Long.valueOf(redisTemplate.opsForValue().getAndSet(lockKey, lock_timeout.toString()));
                    LOG.info("lastLockTimeStr:"+lastLockTimeStr+"lastLockTimeStr2:"+lastLockTimeStr2);
                    if (lastLockTimeStr2 != null && lastLockTimeStr2.equals(lastLockTimeStr)) {
                        // 多线程运行时，多个线程恰好都到了这里，通过返回的锁超时时间判断是否加锁成功
                        LOG.info(threadName + "加锁成功 ++++ 22222");
                        redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
                        return lock_timeout;
                    }
                }
            }
            try {
                LOG.info(threadName +"等待加锁， 睡眠200毫秒");
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解锁
     * 这里不需要synchronized，方法级是class对象加锁
     * @param lockKey
     * @param lockValue
     * @param threadName
     */
    @Override
    public void unlock(String lockKey, long lockValue, String threadName) {
        LOG.info(threadName + "执行解锁==========");//正常直接删除 如果异常关闭判断加锁会判断过期时间
        // 获取redis中设置的时间
        String result = redisTemplate.opsForValue().get(lockKey);
        Long currt_lock_timeout_str = result == null ? null : Long.valueOf(result);

        // 如果是加锁者，则删除锁， 如果不是，则等待自动过期，重新竞争加锁
        if (currt_lock_timeout_str != null && currt_lock_timeout_str == lockValue) {
            redisTemplate.delete(lockKey);
            LOG.info(threadName + "解锁成功------------------");
        }else {
            LOG.info(threadName + "锁已被替换------------------");
        }
    }

    /**
     * 多服务器集群，使用下面的方法，代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
     *
     * @return
     */
    @Override
    public long currtTimeForRedis() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.time();
            }
        });
    }
}