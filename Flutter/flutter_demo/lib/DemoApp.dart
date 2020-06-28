import 'package:flutter/material.dart';
import 'package:flutterdemo/DemoPage.dart';



void main(){
  runApp(ParentWidgetC());
}


class ParentWidget extends StatefulWidget{

  @override
  _ParentWidgetState createState() {
    return _ParentWidgetState();
  }
}

class _ParentWidgetState extends State<ParentWidget>{
  bool _active = false;

  void _handleTapBoxChanged(bool newValue){
    setState(() {
      _active = newValue;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Container(
      child: TapboxB(active: _active,onChanged: _handleTapBoxChanged),
    );
  }


}


class TapboxB extends StatelessWidget{

  TapboxB({Key key,this.active: false, @required this.onChanged}): super(key: key);


  final bool active;
  final ValueChanged<bool> onChanged;

  void _handleTap(){
    onChanged(!active);
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onLongPress: _handleTap,
      child: Container(
        child: Center(
          child: Text(active ? "active" : 'Inactive',
            style: TextStyle(fontSize: 32.0,color: Colors.white),
            textDirection: TextDirection.ltr,
          ),
        ),
        width: 200,
        height: 200,
        decoration: BoxDecoration(
          color: active ? Colors.lightGreen[700] : Colors.grey[600]
        ),
      ),

    );
  }

}



class ParentWidgetC extends StatefulWidget{

  @override
  ParentWidgetCState createState() {
    // TODO: implement createState
    return ParentWidgetCState();
  }

}


class ParentWidgetCState extends State<ParentWidgetC>{
  bool _active = false;

  void _handleTapboxCHanged(bool newValue){
    setState(() {
      _active = newValue;
    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return TapboxC(
      active: _active,
      onChanged: _handleTapboxCHanged
    );
  }




}

class TapboxC extends StatefulWidget {
  //@required 修饰的字段是必须传递的
  TapboxC({Key key,this.active: false,@required this.onChanged}): super(key: key);

  final bool active;
  final ValueChanged<bool> onChanged;

  @override
  _TapboxCState createState() {
    return _TapboxCState();
  }


}

class _TapboxCState extends State<TapboxC>{
  bool _highlight = false;

  void _handleTapDown(TapDownDetails details){
    setState(() {
      _highlight = true;
    });
  }

  void _handleTapUp(TapUpDetails details){
    setState(() {
      _highlight = false;
    });
  }

  void _handleTapCancel(){
    setState(() {
      _highlight = false;
    });
  }

  void _handleTap(){
    widget.onChanged(!widget.active);
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GestureDetector(
      onTapDown: _handleTapDown,
      onTapUp: _handleTapUp,
      onTap: _handleTap,
      onTapCancel: _handleTapCancel,
      child: new Container(
        child: new Center(
          child: new Text(widget.active ? 'Active' : 'Inactive',
              style: new TextStyle(fontSize: 32.0, color: Colors.white),
              textDirection: TextDirection.ltr,
          ),
        ),
        decoration: new BoxDecoration(
          color: widget.active ? Colors.lightGreen[700] : Colors.grey[600],
          border: _highlight
              ? new Border.all(
            color: Colors.teal[700],
            width: 10.0,
          )
              : null,
        ),
      ),

    );
  }

}


