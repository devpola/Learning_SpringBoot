package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public User(){

    }

    public User(String name, int age, String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // object mapper가 object를 text로 바꿀 때, 해당 object의 class 내 get method를 참조하기 때문에
    // 아래와 같이 메소드 이름에 get이 포함되는 것이 있으면 Infinite recursion error 발생
//    public User getDefaultUser(){
//        return new User("default", 0);
//    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
