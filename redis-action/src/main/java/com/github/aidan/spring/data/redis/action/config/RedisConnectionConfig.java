package com.github.aidan.spring.data.redis.action.config;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.Duration;
import java.util.Enumeration;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * @author wang yi fei
 * @date 2019/6/22 19:14
 */
@Configuration
public class RedisConnectionConfig {

/*	private StringRedisTemplate redisTemplate;

	private RedisTemplate redisTemplate1;*/

/*	@Bean
	public HyperLogLogOperations getHyperLogLogOperations(){
		return redisTemplate.opsForHyperLogLog();
	}*/

/*	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("192.168.9.190", 6379);
		config.setDatabase(0);

//		List<Integer> list = new ArrayList<>();
//		Set<Integer> set =  Optional.ofNullable(list).orElse(new ArrayList<>()).stream().filter(code->code.equals(state.getOutCode().)).collect(Collectors.toSet());
		redis.clients.jedis.Client client = new redis.clients.jedis.Client();
		RedisClient client1 = RedisClient.create("redis://localhost");
		StatefulRedisConnection<String, String> connection = client1.connect();
		RedisStringCommands sync = connection.sync();

		return new JedisConnectionFactory(config);


	}*/
/*
	@Bean
	public RedisConnectionFactory lettuceConnectionFactory() {
		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
				.master("mymaster")
				.sentinel("192.168.9.173", 26379)
				.sentinel("192.168.9.178", 26379)
				.sentinel("192.168.9.188", 26379);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(sentinelConfig);
		return factory;
	}*/

	@Bean
	public RedisConnectionFactory lettuceConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("192.168.9.47");
		configuration.setPort(6379);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
		return factory;
	}

	@Bean(name = "redisTemplate")
	@SuppressWarnings("unchecked")
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		// 配置连接工厂
		template.setConnectionFactory(redisConnectionFactory);

		//使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
		Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

		ObjectMapper om = new ObjectMapper();
		// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jacksonSeial.setObjectMapper(om);

		// 值采用json序列化
		template.setValueSerializer(jacksonSeial);
		//使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());

		// 设置hash key 和value序列化模式
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(jacksonSeial);
		template.afterPropertiesSet();
		return template;
	}

/*
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer());
		return redisTemplate;
	}*/

/*	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {

		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("192.168.9.173", 6379);
		configuration.setPassword("123456");
		return new LettuceConnectionFactory(configuration);
	}*/
final static Semaphore semaphore = new Semaphore(100, false);
public static void main(String[] args) throws Exception {
/*	System.out.println("==============");
	HttpServer	server = HttpServer.create(new InetSocketAddress(8000), 0);
	server.createContext("/", new MyHandler("wang",25));
	semaphore.acquire();
	semaphore.tryAcquire(1,5, TimeUnit.SECONDS);
	System.out.println("22222222222222222");
	server.setExecutor(null); // creates a default executor
	server.start();
	System.out.println("111111111111111111");

	System.out.println("==============");*/


/*	System.out.println(getLocalIpAddr());
	InetAddress addr = InetAddress.getLocalHost();
	System.out.println("Local HostAddress: "+addr.getHostAddress());
	String hostname = addr.getHostName();
	System.out.println("Local host name: "+hostname);*/
}

	public static String getLocalIpAddr() {

		String clientIP = null;
		Enumeration<NetworkInterface> networks = null;
		try {
			//获取所有网卡设备
			networks = NetworkInterface.getNetworkInterfaces();
			if (networks == null) {
				//没有网卡设备 打印日志  返回null结束
				System.out.println("networks  is null");
				return null;
			}
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
		InetAddress ip;
		Enumeration<InetAddress> addrs;
		// 遍历网卡设备
		while (networks.hasMoreElements()) {
			NetworkInterface ni = networks.nextElement();
			try {
				//过滤掉 loopback设备、虚拟网卡
				if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
					continue;
				}
			} catch (SocketException e) {
				System.out.println(e.getMessage());
			}
			addrs = ni.getInetAddresses();
			if (addrs == null) {
				System.out.println("InetAddress is null");
				continue;
			}
			// 遍历InetAddress信息
			while (addrs.hasMoreElements()) {
				ip = addrs.nextElement();
				if (!ip.isLoopbackAddress() && ip.isSiteLocalAddress() && ip.getHostAddress().indexOf(":") == -1) {
					try {
						clientIP = ip.toString().split("/")[1];
						System.out.println(clientIP);
					} catch (ArrayIndexOutOfBoundsException e) {
						clientIP = null;
					}
				}
			}
		}
		return clientIP;
	}
}
