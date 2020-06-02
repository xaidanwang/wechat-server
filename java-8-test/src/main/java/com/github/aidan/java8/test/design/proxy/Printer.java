package com.github.aidan.java8.test.design.proxy;

/**
 * @author wang yi fei
 * @date 2019/6/12 20:22
 */
public class Printer implements Printable {

	private String name;

	public Printer() {
		heavyJob("Printer 的实例生成中");
	}

	public Printer(String name) {
		this.name = name;
		heavyJob("Printer  的实例生成中（"+ name + "）");
	}

	@Override
	public void setPrinterName(String name) {
		this.name = name;
	}

	@Override
	public String getPrinterName() {
		return name;
	}

	@Override
	public void print(String string) {
		System.out.println("=== "+ name +" ===");
		System.out.println(string);
	}

	private void heavyJob(String msg){
		System.out.print(msg);
		for (int i = 0; i < 5; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(".");
		}
		System.out.println("结束。");
	}
}
