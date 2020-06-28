import 'package:flutter/material.dart';


class DemoPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return _DemoPageState();
  }

}

class _DemoPageState extends State<StatefulWidget>{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,

      appBar: AppBar(
        title: Text("第一个测试的",style: TextStyle(
          color: Colors.orangeAccent
        ),),
        backgroundColor: Colors.yellow,
      ),

      ///正式的页面开始
      body: ListView.builder(itemBuilder: (context,index){
        return _getBottomItem(Icons.add, "添加项目");
      }),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }

}

///返回一个居中带图标和文本的Item
_getBottomItem(IconData icon, String text) {
  ///充满 Row 横向的布局
  ///充满 Row 横向的布局
  return  new Center(
      ///横向布局
      child: new Row(
        ///主轴居中,即是横向居中
        mainAxisAlignment: MainAxisAlignment.center,
        ///大小按照最大充满
        mainAxisSize : MainAxisSize.max,
        ///竖向也居中
        crossAxisAlignment : CrossAxisAlignment.center,
        children: <Widget>[
          ///一个图标，大小16.0，灰色
          new Icon(
            icon,
            size: 16.0,
            color: Colors.deepOrangeAccent,
          ),
          ///间隔
          new Padding(padding: new EdgeInsets.only(left:5.0)),
          ///显示文本
          new Text(
            text,
            textDirection: TextDirection.ltr,
            //设置字体样式：颜色灰色，字体大小14.0
            style: new TextStyle(color: Colors.cyanAccent, fontSize: 14.0),
            //超过的省略为...显示
            overflow: TextOverflow.ellipsis,
            //最长一行
            maxLines: 1,
          ),
        ],
      ),
  );
}





