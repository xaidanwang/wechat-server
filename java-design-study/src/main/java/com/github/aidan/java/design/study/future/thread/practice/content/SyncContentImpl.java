package com.github.aidan.java.design.study.future.thread.practice.content;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wang yi fei
 * @date 2020/5/19 15:15
 */
class SyncContentImpl implements Content{

	private byte[] contentbytes;

	public SyncContentImpl(String urlstr) {
		System.out.println(Thread.currentThread().getName() + ": Getting " + urlstr);
		try {
			URL url = new URL(urlstr);
			DataInputStream in  = new DataInputStream(url.openStream());
			byte[] buffer =  new byte[1];
			int index  = 0;
			try {
				while (true){
					int c = in.readUnsignedByte();
					if (buffer.length < index){
						byte[] largerbuffer = new byte[buffer.length * 2];
						System.arraycopy(buffer,0,largerbuffer,0,index);
						buffer =  largerbuffer;
					}
					buffer[index++] = (byte) c;
				}
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				in.close();
			}
			contentbytes = new byte[index];
			System.arraycopy(buffer,0,contentbytes,0,index);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] getBytes() {
		return contentbytes;
	}
}
