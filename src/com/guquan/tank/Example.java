package com.guquan.tank;

public class Example {
    public Example(String[] args) {
        Student mst = new Student();
        mst.name = "Joe";
        mst.ID = 1;
        System.out.println(mst.Display());
    }
      public class Student {
        String name;
        int ID;

        String Display() {
            return name +ID;
        }
    }
}
