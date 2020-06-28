import 'package:flutter/material.dart';



void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        onGenerateRoute: (RouteSettings settings){
        // ignore: missing_return
        return MaterialPageRoute(builder: (context){
            var name = settings.name;
            switch(name){
              case "new_route":
                print("请登录");
                break;
            }
        }
        );
      },

      title: "Flutter Demo",
      theme: ThemeData(
        primarySwatch: Colors.cyan
      ),
      routes: {
        "new_route":(context) => NewRoute(),
        "tip_route":(context) => TipRoute(title: "册数塔塔"),
      },

      home: MyHomePage(title: "首页")
    );
  }
}



class MyHomePage extends StatefulWidget{

  MyHomePage({Key key,this.title}):super(key: key);

  final String title;

  @override
  State<StatefulWidget> createState() {
    return _CreateHomeState();
  }
}

class _CreateHomeState extends State<MyHomePage>{

  num _count = 0;

  void _incrementCounter(){
    setState(() {
      _count++;
    });
  }


  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          children: <Widget>[
            _getBottomItem(Icons.add,"添加"),
            _getBottomItem(Icons.title,"标题"),
            _getBottomItem(Icons.add_location,"位置"),

            FlatButton(onPressed: (){
              Navigator.push(context, MaterialPageRoute(builder: (context){
                return NewRoute();
              }));
//                Navigator.pushNamed(context, "new_route",arguments: "hi ~");

            }, child: Text("open new route"),
              textColor: Colors.blue,)

          ],

        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        child: Icon(Icons.add),
      ),
    );
  }

  Widget _getBottomItem(IconData icon,String title){
    return Center(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
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
            title,
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


}


class NewRoute extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    //通过这个方法可以获得参数
//    String arg = ModalRoute.of(context).settings.arguments;

    return Scaffold(
      appBar: AppBar(
        title: Text("ssasda"),
      ),
      body: Center(
        child: Text("this is a Route"),
      ),
    );

  }
}


class TipRoute extends StatelessWidget{

  TipRoute({Key key,@required this.title}): super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("提示"),
      ),
      body: Padding(
          padding: EdgeInsets.all(18),
          child: Center(
            child: Column(
              children: <Widget>[
                Text(title),
                RaisedButton(onPressed: (){
                  Navigator.pop(context,"这是一个返回值");
                },child: Text("返回"),)
              ],
            ),
          ),
      ),

    );
  }
}

class RouterTestRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: RaisedButton(
        onPressed: () async {
          // 打开`TipRoute`，并等待返回结果
          var result = await Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) {
                return TipRoute(
                  // 路由参数
                  title: "我是提示xxxx",
                );
              },
            ),
          );
          //输出`TipRoute`路由返回结果
          print("路由返回值: $result");
        },
        child: Text("打开提示页"),
      ),
    );
  }
}