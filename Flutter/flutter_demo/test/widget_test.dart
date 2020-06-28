// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import 'package:flutterdemo/main.dart';

import 'Person.dart';

void main() {
  testWidgets('Counter increments smoke test', (WidgetTester tester) async {
//    //Build our app and trigger a frame.
//    await tester.pumpWidget(MyApp());
//
//    // Verify that our counter starts at 0.
//    expect(find.text('0'), findsOneWidget);
//    expect(find.text('1'), findsNothing);
//
//    // Tap the '+' icon and trigger a frame.
//    await tester.tap(find.byIcon(Icons.add));
//    await tester.pump();
//
//    // Verify that our counter has incremented.
//    expect(find.text('0'), findsNothing);
//    expect(find.text('1'), findsOneWidget);

//    //类型不固定可以显示dynamic object 来定义
//    dynamic value = 18;
//    print("$value");
//    value = "bruce";
//    print("$value");
//
//    //能够放在变量中的所有内容都是对象，所以如果一个变量没有初始化值，那它的默认值就为null。
//
//    //final const  final 只能设置一次   const初始化的时候必须赋值
//    var p = Person("COCO");
//
//    print(p.name);
//
//    //没有 float 类型  int double 都是num的子类
//    num n = 1;
//
//    var lis = List(4);
//
//    var smile = "\u{1f600}";
//    print(smile);
//
//
//    Runes runes = new Runes(
//        '\u2665  \u{1f605}  \u{1f60e}  \u{1f47b}  \u{1f596}  \u{1f44d}');
//    print(String.fromCharCodes(runes));
//
//
//    print(getName());

//    showDesc(name: "华为");
//    showDesc2("华为","18");


//    //直接传函数名就可以
//    functionTest("Ceshi",println);

//    //匿名函数的调用
//    noNameFunction("Coco小仙女", (name)// 这里是匿名函数的参数
//    {
//      print("$name");
//    });

    //dart 支持 ?.

    //~/ 使用

//    var num = 10;
//    var result = num ~/ 3; //得出一个小于等于(num/3)的最大整数
//    print("result = $result");
//
//    // 输出结果
//    result = 3

    //使用as 做类型转换  is来判断类型   ??的使用: name ?? "Coco" 如果name不为null，值为name 否则为Coco

    //..的使用，级联操作允许对同一个对象进行一系列操作。
    ///// 调用
    //Banana(20, 'yellow')
    //    ..showWeight()
    //    ..showColor();


    // ----------------------------------控制相关---------------------------------------
    var name = 1;
    ///if and else
    //for循环
    //while和do-while循环
    //break和continue
    //switch-case语句
    //
    //以上控制流语句和其他编程语言用法一样，switch-case有一个特殊的用法如下，可以使用continue语句和标签来执行指定case语句。
    //var fruit = 'apple';
    //switch (fruit) {
    //  case 'banana':
    //    print("this is a banana");
    //    continue anotherFruit;
    //
    //  anotherFruit:
    //  case 'apple':
    //    print("this is an apple");
    //    break;
    //}
    //
    //// 输出结果
    //this is an apple


    //----------------------异常-------------------------

//    void handleOperator() => throw Exception("这是一个异常");
//
//    try {
//      handleOperator();
//    } catch (e) {
//      print(e);
//    } finally {
//      println("finally");
//    }


    //-----------------------类---------------------------
    //Dart是一种面向对象的语言，具有类和基于mixin的继承。同Java一样，Dart的所有类也都继承自Object。


    //mixin是一种在多个类层次结构中重用类代码的方法。
//    Apple().log();
    var apple = Apple();
    apple.prt("Coco");
  });
}

String getName() => "Coco";


/// 可以设置可选参数  
/// （可通过两种方式设置 ： 命名参数 ，位置参数）
/// 当然默认的可选参数也可以设置默认值

//命名参数
void showDesc({var name,var age = 18}){
  if(name != null){
    print(name);
  }

  if(age != null){
    print("$age");
  }
}

//位置参数
void showDesc2(var name,[var age = "12"]){
  if (name != null){
    print(name);
  }

  if (age != null){
    print("$age");
  }
}

/// 函数可以作为另一个函数的参数

void println(String name){
  print("name = $name");
}

void functionTest(var name,Function log){
  log(name);
}


// 匿名函数

void noNameFunction(var name,Function log){
  //匿名函数也可以设置参数
  log(name);

  //按顺序执行的 嵌套函数
  void test(){
    print("嵌套函数测试  ----- 》$name");
  }

  test();
}


class LogUtil{
  void log(){
    print("this is a log");
  }
}


class Fruit{
  Fruit(){
    print("this is Fruit constructor with no param");
  }
}

class Apple extends Fruit with LogUtil{
  Apple():super(){
    print("this is a Apple");
  }

  void prt(var name){
    print(name);
  }
}

