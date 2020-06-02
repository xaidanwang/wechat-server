package com.github.aidan.java8.test.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/12/4 10:46
 */
public class Main {
	private static final String path = "/tmp/facelib/data/";
	public static void main(String[] args) {
		File excel = new File("D:\\tmp\\facelib\\data\\"+"person.xlsx");
		File excel2 = new File("D:\\tmp\\facelib\\data\\"+"person2.xlsx");
		ExcelReader reader = ExcelUtil.getReader(excel,0);
		ExcelWriter writer = reader.getWriter();
		writer.setSheet(0);
		writer.passRows(10);
		writer.setDestFile(excel2);
		int rowcount = writer.getRowCount();
		Map<String, Object> row1 = new LinkedHashMap<>();
		row1.put("姓名", "张三");
		row1.put("年龄", 23);
		row1.put("成绩", 88.32);
		row1.put("是否合格", true);
		row1.put("考试日期", DateUtil.date());
		writer.writeRow(row1,false);
		writer.flush();
		CellStyle cellStyle = reader.createColumnStyle(1);
		StyleSet style = writer.getStyleSet();
		int currentRow = writer.getCurrentRow();
		Map<String, Object> row2 = new LinkedHashMap<>();
		row2.put("姓名", "李四");
		row2.put("年龄", 33);
		row2.put("成绩", 59.50);
		row2.put("是否合格", false);
		row2.put("考试日期", DateUtil.date());
		ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);
		writer.writeRow(row1,false);
		writer.flush();
	}
}
