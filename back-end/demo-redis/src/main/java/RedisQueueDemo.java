import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 *
 * redis list 消息队列示例
 *
 * @author fafafa伐木工
 * @version 1.0
 * @date 2022/7/23 0:22
 */
public class RedisQueueDemo {

    private static final String key = "QUEUE_KEY";

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);


        new Thread(() ->{
            Jedis resource = jedisPool.getResource();
            String select = resource.select(2);
            System.out.println(select);
            synchronized (key){
                while (true){
                    System.out.println("一，进来了");
                    List<String> blpop = resource.blpop(0, key);
                    System.out.println(blpop);
                    try {
                        key.notify();
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                synchronized (key){
                    System.out.println("二，进来了");
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    key.notify();
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            Jedis resource = jedisPool.getResource();
            String select = resource.select(2);
            System.out.println(select);
            for (int i = 0; i < 10; i++) {
                resource.rpush(key, String.valueOf(i));
            }
        }).start();

    }
}
