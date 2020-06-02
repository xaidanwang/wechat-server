package com.github.aidan.java8.test.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wang yi fei
 * @date 2019/4/2 20:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;

    private Integer age;

/*    private int sex;//性别
    private String professional;//专业*/

    private String parentuuid;

    private String uuid;

//    public static void main(String[] args) {
//        List<Student> allList = new ArrayList<Student>();
//
//        Student st1 = new Student("小王",21,"1","2");
//        allList.add(st1);
//        Student st2 = new Student("小张",22,"1","3");
//        allList.add(st2);
//        Student st3 = new Student("小红1",23,"2","4");
//        allList.add(st3);
//        Student st4 = new Student("小李1",24,"2","5");
//        allList.add(st4);
//        Student st5 = new Student("小李2",25,"3","6");
//        allList.add(st5);
//        Student st6 = new Student("小李3",26,"3","7");
//        allList.add(st6);
//
//        Map<String, List<Student>> MapStudent = allList.stream().collect(Collectors.groupingBy(Student::getParentuuid));
//
//        // 遍历获取对象信息
//        for (Map.Entry<String, List<Student>> entry: MapStudent.entrySet()) {
//            List<Student> student = entry.getValue();
//            System.out.println(student.toString());
//            System.out.println(entry.toString());
//        }
//
//        System.out.println("------------分割线---------------");
//
//        Map<String, List<String>> MapStudent1 = allList.stream().collect(Collectors.groupingBy(Student::getParentuuid,Collectors.mapping(Student::getName,Collectors.toList())));
//
//        // 遍历获取对象信息
//        for (Map.Entry<String, List<String>> entry: MapStudent1.entrySet()) {
//            List<String> student = entry.getValue();
//            System.out.println(student.toString());
//            System.out.println(entry.toString());
//        }
//
//        // 排序测试
//        allList.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2){
//                float i1 = o1.getAge();
//                float i2 = o2.getAge();
//                if (i1 == i2){
//                    return 0;
//                }else if (i1 > i2){
//                    return 1;
//                }else {
//                    return -1;
//                }
//            }
//        });
//        for (Student student:allList){
//            System.out.println(student);
//        }


/*        // 以专业分组
        Map<String, List<Student>> MapStudent = allList.stream().collect(
                Collectors.groupingBy(Student::getProfessional));
        // 遍历获取对象信息
        for (Map.Entry<String, List<Student>> entry: MapStudent.entrySet()) {
            List<Student> student = entry.getValue();
            System.out.println(student.toString());
            System.out.println(entry.toString());
        }
        System.out.println("------------分割线---------------");
        // 以专业分组，并选取年龄最大的 学生
        Map<String, Optional<Student>> MapStudent1 = allList.stream().collect(
                Collectors.groupingBy(Student::getProfessional, Collectors.maxBy((o1, o2) -> o1.getAge().compareTo(o2.getAge()))));

        // 遍历获取对象信息
        for (Map.Entry<String, Optional<Student>> entry: MapStudent1.entrySet()) {
            Student student = entry.getValue().get();
            System.out.println(student.getName().toString());
        }*/
//    }

    private void test(Class tClass){
    }
}
