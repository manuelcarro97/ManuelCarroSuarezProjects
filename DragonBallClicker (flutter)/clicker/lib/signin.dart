

// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class SignIn extends StatelessWidget{

  //Atributos que recogeremos del usuario en el Sing in
  String nombre = "";
  String password = "";
  String correo = "";

  final formkey = GlobalKey<FormState>();
  
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text("Nuevo jugador"),
        backgroundColor: Colors.orange[400],
      ),
      body: Container(
          decoration: BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/icons/bg.jpg"),
              opacity: 0.8,
              fit: BoxFit.cover,
            ),
          ),
          child:Center(  
            child:Form(              
              key: formkey,
              //Containers para cada dato que le pediremos al usuario
              child:Container(
                  margin: EdgeInsets.only( right: 20, left: 20),
                  width: 500,
                  height: 390,
                  color: Colors.orange[200],
                child:Column(
                 children:[               
                //Container Nombre
                  Container(
                    margin: EdgeInsets.only(top:20),
                    width: 300,
                    height: 80,
                    color: Colors.orange[100],
                    child:TextFormField(
                      decoration: InputDecoration(labelText: "   Nombre de usuario"),
                      onSaved: (value){
                      nombre = value!;
                      },
                      validator: (value) {
                        if(value!.isEmpty){
                          return "Campo obligatorio";
                        }
                      },
                    ),
                  ),
                  //Container Correo Electrónico
                  Container(
                    margin: EdgeInsets.only(top:20),
                    width: 300,
                    height: 80,
                    color: Colors.orange[100],
                    child: TextFormField(
                      decoration: InputDecoration(labelText: "   Correo electrónico"),
                      obscureText: true,
                      onSaved: (value){
                      correo = value!;
                      },
                       validator: (value) {
                        if(value!.isEmpty){
                          return "Campo obligatorio";
                        }
                      },
                    ),
                  ),
                  //Container Contraseña
                  Container(
                    margin: EdgeInsets.only(top:20),
                    width: 300,
                    height: 80,
                    color: Colors.orange[100],
                    child: TextFormField(
                      decoration: InputDecoration(labelText: "   Contraseña"),
                      obscureText: true,
                      onSaved: (value){
                      password = value!;
                      },
                       validator: (value) {
                        if(value!.isEmpty){
                          return "Campo obligatorio";
                        }
                      },
                    ),
                  ),
                 //Container Botón START, para empezar juego.
                  Container(
                    margin: EdgeInsets.only(top:20),
                    width: 100,
                    height: 50,
                    color: Colors.white,
                    child: TextButton(onPressed: () =>recogerDatos(context), child: Text("New Player"))
                  ),  
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
 
  //Función del botón "START", nos lleva a la ventana principal.
  recogerDatos(BuildContext context) {
      if(formkey.currentState!.validate()){
        formkey.currentState!.save();
        Navigator.of(context).pushNamed("/rutaLogin");
      }
  }
}