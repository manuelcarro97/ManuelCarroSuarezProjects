import 'package:clicker_v2/logros.dart';
import 'package:clicker_v2/signin.dart';

import '/bola.dart';
import '/login.dart';
import '/mejoras.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      title: 'DragonBall Clicker',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSwatch().copyWith(
          primary: Colors.grey.shade800,
        ),
      ),
      initialRoute: '/rutaLogin',
      routes: {
        '/rutaSignIn': (BuildContext context) => SignIn(),
        '/rutaLogin': (BuildContext context) => Login(),
        '/rutaPrincipal': (BuildContext context) => Bola(),
        '/rutaMejoras': (BuildContext context) => Mejoras(),
        '/rutaLogros': (BuildContext context) => Logros(),
      },
    );
  }
}
