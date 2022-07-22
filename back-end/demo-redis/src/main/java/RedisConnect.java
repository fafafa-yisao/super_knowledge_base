import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis连接
 *
 * @author yi_sao
 * @date 2022/7/22
 */
public class RedisConnect {

    public static void main(String[] args) {
        //创建连接redis数据库的对象
//        Jedis jedis = new Jedis("localhost", 6379);
        //创建连接池对象
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        //通过连接池对象获取连接redis的连接对象
        Jedis jedis = jedisPool.getResource();

        //测试是否连接
        String pong = jedis.ping();
        System.out.println("是否连接：" + pong);

        //切换数据库
        String result = jedis.select(1);
        System.out.println("切换数据库：" + result);
        //查看当前数据库中所有键key
        Set<String> keys = jedis.keys("*");
        System.out.println("所有键：" + keys);
        //获取键的数量
        long keySize = jedis.dbSize();
        System.out.println("键的数量：" + keySize);

        System.out.println("---string类型---");
        //1.string类型
        //存储
        jedis.set("hello", "welcome to redis");
        //获取
        System.out.println(jedis.get("hello"));

        System.out.println("---list类型---");
        //2.list类型
        //存储
        jedis.lpush("names","张三","李四","王五");
        //获取
        List<String> names =  jedis.lrange("names",0,100);
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("---set类型---");
        //3.set类型
        //存储
        long setSize = jedis.sadd("city","北京","上海","深圳","上海");
        System.out.println(setSize);
        //获取
        Set<String> city = jedis.smembers("city");
        for (String s : city) {
            System.out.println(s);
        }

        System.out.println("---sorted set类型---");
        //4.sorted set类型
        //存储
        jedis.zadd("week",1,"周一");
        jedis.zadd("week",2,"周二");
        jedis.zadd("week",3,"周三");
        //获取
        List<String> week = jedis.zrange("week", 0, 100);
        for (String s : week) {
            System.out.println(s);
        }
        List<String> week2 = jedis.zrevrange("week",0,100);
        for (String s : week2) {
            System.out.println(s);
        }

        Map<String,Double> majorMap = new HashMap<>();
        majorMap.put("软件工程",100.0);
        majorMap.put("通信工程",80.0);
        majorMap.put("网络工程",90.0);
        jedis.zadd("major",majorMap);
        List<String> major = jedis.zrange("major",0,100);
        for (String s : major) {
            System.out.println(s);
        }

        System.out.println("---hash类型---");
        //5. hash类型
        jedis.hset("user1","id","101");
        jedis.hset("user1","name","小明");
        jedis.hset("user1","age","20");

        Map<String,String> userMap = new HashMap<>();
        userMap.put("id","102");
        userMap.put("name","小红");
        userMap.put("age","22");
        jedis.hmset("user2", userMap);

        List<String> user1 = jedis.hmget("user1","id","name","age");
        for (String s : user1) {
            System.out.println(s);
        }
        Map<String,String> user2 = jedis.hgetAll("user2");
        System.out.println(user2);

    }
}
