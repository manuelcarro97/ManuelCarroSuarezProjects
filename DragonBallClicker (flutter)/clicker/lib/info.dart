import 'package:flutter/material.dart';

class Info extends StatefulWidget {
  Info2 createState() => Info2();
}

class Info2 extends State<Info> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("Información"),
      ),
      body: Icon(Icons.abc),
    );
  }
}
