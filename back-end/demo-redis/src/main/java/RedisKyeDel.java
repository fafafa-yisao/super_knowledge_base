import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author yi_sao
 * @date 2022/7/22
 */
public class RedisKyeDel {

    public static void main(String[] args) {

        RedisKeyExpiredListener redisKeyExpiredListener = new RedisKeyExpiredListener();

        new Thread(new Runnable() {
            @Override
            public void run() {
                JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
                System.out.println("启动了Redis过期监听");
                System.out.println(redisKeyExpiredListener.toString());
                Jedis jedis = jedisPool.getResource();

                String parameter = "notify-keyspace-events";
//                jedis.configSet(parameter, "Ex");
                List<String> notify = jedis.configGet(parameter);
                System.out.println(notify);

                Set<String> keys = jedis.keys("*");
                keys.stream().forEach(System.out::println);
                jedis.set("123", "456");
                jedis.pexpire("123", 10000);
                // 订阅redis key过期时间，需要reids 服务器配置notify-keyspace-events Ex
                jedis.subscribe(redisKeyExpiredListener, "__keyevent@*__:expired");
                System.out.println("-----");
            }
        }).start();
    }
}
