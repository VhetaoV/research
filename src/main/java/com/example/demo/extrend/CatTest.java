package com.example.demo.extrend;

class Cat {
    protected static String color = "random";

    public Cat() {
    }
    public void showCatColor() {
        System.out.println("Cat:" + color);
    }

    public static void  showColor() {
        System.out.println("Cat:" + color);
    }
}
class WhiteCat extends Cat {
    protected static String color = "white";

    public WhiteCat() {
        super();
    }

    public void showCatColor() {
        System.out.println("WhiteCat:" + color);
    }

    public static void showColor() {
        System.out.println("WhiteCat:" + color);
    }
}

public  class CatTest{
    public  static void main(String[] args){
        //===========================================
//        WhiteCat whiteCat= new WhiteCat();
//        Cat cat = whiteCat;
//        cat.showColor();
//        cat.showCatColor();
        //===========================================
//        Cat cat = new Cat();
//        WhiteCat whiteCat = (WhiteCat) cat;
//        cat.showColor();
//        cat.showCatColor();
        //===========================================
//        Cat cat = new WhiteCat();
//        WhiteCat whiteCat = (WhiteCat) cat;
//        cat.showColor();
//        cat.showCatColor();
        //===========================================
    }
}

//创建WhiteCat实例，然后把实例复制给cat, 在调用showColor方法，showColor方法是由static 声明的静态方法，
// 静态方法和属性是属于类的，调用的时候直接通过类名.方法名完成对，不需要继承机制及可以调用。
// 如果子类里面定义了静态方法和属性，那么这时候父类的静态方法或属性称之为"隐藏"。
// 如果你想要调用父类的静态方法和属性，直接通过父类名.方法或变量名完成，至于是否继承一说，子类是有继承静态方法和属性，
// 但是跟实例方法和属性不太一样，存在"隐藏"的这种情况。 而showCatColor方法属于非静态的，则可以被复习。