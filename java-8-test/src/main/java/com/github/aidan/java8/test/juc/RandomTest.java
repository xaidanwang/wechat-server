package com.github.aidan.java8.test.juc;

import javax.jws.soap.SOAPBinding;
import java.time.Instant;
import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/4/24 16:23
 */
public class RandomTest {

	public static void main(String[] args) {
/*		Random random = new Random(10);
		for (int i=0; i < 5; i++){
			System.out.print(random.nextInt(10)+" ");
		}
		Random random1 = new Random(10);
		System.out.println();
		for (int i=0; i < 5; i++){
			System.out.print(random1.nextInt(10)+" ");
		}*/

		long startTime = System.currentTimeMillis();

		System.out.println(startTime);
		System.out.println(~startTime+1);
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Instant.now().plusMillis(~startTime).getEpochSecond());
	}
}
