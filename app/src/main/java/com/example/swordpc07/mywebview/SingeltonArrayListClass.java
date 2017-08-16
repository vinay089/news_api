package com.example.swordpc07.mywebview;

import java.util.ArrayList;

/**
 * Created by swordpc07 on 1/18/2017.
 */

public class SingeltonArrayListClass {

    public static ArrayList<LoadAllNewsGetterSetter> allNewsArrayList;
    public static ArrayList<LoadSourceNewsGetterSetter> sourceNewsArrayList;

    public static SingeltonArrayListClass myObj;
    public SingeltonArrayListClass(){ }


    public static SingeltonArrayListClass getInstance(){

        if(myObj== null)
            myObj= new SingeltonArrayListClass();

        return myObj;
    }

}
