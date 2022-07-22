import redis.clients.jedis.Connection;
import redis.clients.jedis.JedisPubSub;

/**
 * TODO
 *
 * @author yi_sao
 * @date 2022/7/22
 */
public class RedisKeyExpiredListener extends JedisPubSub {

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        super.onPUnsubscribe(pattern, subscribedChannels);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe:" + pattern + " : " + subscribedChannels);
        super.onPSubscribe(pattern, subscribedChannels);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("redis event key :" + message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(message);
    }
}
