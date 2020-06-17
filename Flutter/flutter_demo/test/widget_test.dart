// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

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

    //类型不固定可以显示dynamic object 来定义
    dynamic value = 18;
    print("$value");
    value = "bruce";
    print("$value");

    //能够放在变量中的所有内容都是对象，所以如果一个变量没有初始化值，那它的默认值就为null。

    //final const  final 只能设置一次   const初始化的时候必须赋值
    var p = Person("COCO");

    print(p.name);

    //没有 float 类型  int double 都是num的子类
    num n = 1;

    var lis = List(4);


  });



}
