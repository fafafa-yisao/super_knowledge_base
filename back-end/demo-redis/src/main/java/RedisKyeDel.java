import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;

/**
 * 监控Redis key删除示例
 *
 * @author yi_sao
 * @date 2022/7/22
 */
public class RedisKyeDel {

    public static void main(String[] args) throws InterruptedException {


        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);

        RedisKeyExpiredListener redisKeyExpiredListener = new RedisKeyExpiredListener();

        Thread thread = new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            String s = jedis.configSet("notify-keyspace-events","KEA");
            System.out.println(s);
            String setex = jedis.setex("myKey", 5, "v1");
            System.out.println(setex);
            // 阻塞式订阅
            //使用subscribe订阅似乎不会成功
            jedis.psubscribe(redisKeyExpiredListener,"__keyevent@*__:expired");
        });
        thread.start();
    }
}
