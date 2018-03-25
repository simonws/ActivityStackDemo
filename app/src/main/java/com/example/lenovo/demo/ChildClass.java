package com.example.lenovo.demo;

/**
 * Created by lenovo on 2018/3/9.
 */

public class ChildClass extends ParentClass {
    public ChildClass(){
        super();
    }

    enum Color{
        RED(1),GREEN(2),BLUE(3);
        private int code;
        Color(int code){
            this.code=code;
        }
        public int getCode(){
            return code;
        }
    }

}
