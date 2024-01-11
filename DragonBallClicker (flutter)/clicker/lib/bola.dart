// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, annotate_overrides, must_call_super, prefer_interpolation_to_compose_strings, sort_child_properties_last

import 'dart:async';
import 'package:audioplayers/audioplayers.dart';
import 'package:clicker_v2/logros.dart';
import 'package:flutter/material.dart';
import 'dart:math';
import '/mejoras.dart';

class Bola extends StatefulWidget {
  Principal createState() => Principal();
}

class Principal extends State<Bola> {
  //Variables
  static int clicks =0;
  static int clicksbonus=0;
  static double contador = 0;
  String ruta = "";
  String personaje = "";
  //Variables de mejoras de cada personaje
  static int mejoraBola = 1;
  static int mejoraGoku = 0;
  static int mejoraVegeta = 0;
  static int mejoraGohan = 0;
  static int mejoraTrunks = 0;
  static int mejoraCelula = 0;
  static int mejoraFreezer = 0;
  static int mejoraBuu = 0;
  //Variables de contadores de cada personaje
  static int gokus = 0;
  static int vegetas = 0;
  static int gohans = 0;
  static int trunks = 0;
  static int celulas = 0;
  static int buus = 0;
  static int freezers = 0;
  //Variables mostrar texto mejoras de zenis/s y total zeni/s
  int produccion = 0;
  int workers = 0;
  //Variables timepo por segundo
  static Timer? timer;
  double porSeg = 0;
  //Variables botton random
  int boton = 0;
  int x = 0;
  int y = 0;
  bool show = false;
  //Variable de ruta y mostrar imagenes
  String imagenGrande = "";
  bool imagenVisible = false;

  bool primeravez = true;

  static int tiempo = 1000;

  static int cont = 0;

  @override
  Widget build(BuildContext context) {
    if (mejoraBola == 1) {
      ruta = "assets/balls/ball1.png";
    } else if (mejoraBola == 2) {
      ruta = "assets/balls/ball2.png";
    } else if (mejoraBola == 3) {
      ruta = "assets/balls/ball3.png";
    } else if (mejoraBola == 4) {
      ruta = "assets/balls/ball4.png";
    } else if (mejoraBola == 5) {
      ruta = "assets/balls/ball5.png";
    } else if (mejoraBola == 6) {
      ruta = "assets/balls/ball6.png";
    } else if (mejoraBola == 7) {
      ruta = "assets/balls/ball7.png";
    }

    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: tiempo == 1000 ? AssetImage("assets/icons/bg.png") : AssetImage("assets/icons/bg_bonus.png"),
            // opacity: 0.8,
            fit: BoxFit.fill,
          ),
        ),
        child: Center(
            child: Stack(
          children: [
            Column(crossAxisAlignment: CrossAxisAlignment.center, children: [
              monedas(contador),
              Container(
                margin: EdgeInsets.only(top: 22),
                child: tiempo == 1000
                    ? Text(
                        "Zenis /s: " + porSeg.toString(),
                        style: TextStyle(fontSize: 20, fontFamily: 'Digital7', color: Colors.yellow.shade600),
                      )
                    : Text(
                        "Zenis /s: " + (porSeg * 2).toString(),
                        style: TextStyle(fontSize: 20, fontFamily: 'Digital7', color: Colors.red),
                      ),
                padding: EdgeInsets.only(bottom: 30),
              ),
              IconButton(
                highlightColor: Colors.transparent,
                focusColor: Colors.transparent,
                hoverColor: Colors.transparent,
                splashColor: Colors.transparent,
                icon: Image.asset(ruta),
                iconSize: 280,
                onPressed: () {
                  setState(() {
                    clicks++;
                    if (mejoraBola == 1) {
                      contador+=1000;
                    } else if (mejoraBola == 2) {
                      contador += 2;
                    } else if (mejoraBola == 3) {
                      contador += 3;
                    } else if (mejoraBola == 4) {
                      contador += 4;
                    } else if (mejoraBola == 5) {
                      contador += 5;
                    } else if (mejoraBola == 6) {
                      contador += 6;
                    } else if (mejoraBola == 7) {
                      contador += 7;
                    }
                  });
                },
              ),
              Divider(color: Colors.transparent),
              Expanded(
                  child: Row(
                children: <Widget>[
                  Container(
                    padding: EdgeInsets.only(left: 10, top: 18),
                    width: 155,
                    height: 380,
                    color: Colors.transparent,
                    child: ListView(physics: const NeverScrollableScrollPhysics(), padding: EdgeInsets.zero, children: <Widget>[
                      gokus != 0 ? mostrarPJ(" Goku     ", mostrarImagenGoku(mejoraGoku, personaje), gokus) : Container(),
                      vegetas != 0 ? mostrarPJ(" Vegeta   ", mostrarImagenVegeta(mejoraVegeta, personaje), vegetas) : Container(),
                      gohans != 0 ? mostrarPJ(" Gohan    ", mostrarImagenGohan(mejoraGohan, personaje), gohans) : Container(),
                      trunks != 0 ? mostrarPJ(" Trunks   ", mostrarImagenTrunks(mejoraTrunks, personaje), trunks) : Container(),
                      freezers != 0 ? mostrarPJ(" Freezer  ", mostrarImagenFreezer(mejoraFreezer, personaje), freezers) : Container(),
                      celulas != 0 ? mostrarPJ(" Célula   ", mostrarImagenCelula(mejoraCelula, personaje), celulas) : Container(),
                      buus != 0 ? mostrarPJ(" Buu      ", mostrarImagenBuu(mejoraBuu, personaje), buus) : Container(),
                    ]),
                  ),
                  Container(
                    padding: EdgeInsets.only(left: 15, top: 6),
                    width: 232,
                    height: 380,
                    color: Colors.transparent,
                    child: Column(
                      children: [
                        Container(child: imagenVisible ? Image.asset(imagenGrande) : null),
                        imagenVisible
                            ? Container(
                                padding: EdgeInsets.only(top: 12),
                                child: Column(
                                  children: [
                                    texto1(),
                                    SizedBox(height:7),
                                    texto2(),
                                  ],
                                ),
                              )
                            : Text(""),
                      ],
                    ),
                  ),
                ],
              )),
            ]),
            tiempo == 1000
                ? Visibility(
                    child: Container(
                        padding: EdgeInsets.only(left: x.toDouble(), top: y.toDouble()),
                        child: InkWell(
                          child: Image.asset("assets/icons/muten.png", width: 100),
                          onTap: () {
                            setState(() {
                              tiempo = 500;
                              initState();
                              clicksbonus++;
                              player.pause();
                              musicabonus("music/bonus.mp3");
                              
                            });
                          },
                        )),
                    visible: show,
                  )
                : Visibility(
                    child: Container(),
                  ),
            Visibility(
                child: Container(
                    padding: EdgeInsets.only(left: 295, top: 33),
                    child: InkWell(
                        child: tiempo == 1000 ? Image.asset("assets/icons/reset.png", height: 25) : Image.asset("assets/icons/reset_b.png", height: 25),
                        onTap: () {
                          reset();
                        }))),
          ],
        )),
      ),
      bottomNavigationBar: BottomNavigationBar(
        onTap: (i) {
          if (i == 1) {
            mostrarMejoras(context);
          }
          if (i == 2) {
            mostrarLogros(context);
          }
        },
        backgroundColor: Colors.grey,
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Image.asset(
              "assets/icons/ball_icon.png",
              width: 25,
            ),
            label: 'CLICKER',
          ),
          BottomNavigationBarItem(
            icon: Image.asset(
              "assets/icons/upgrade_icon_off.png",
              width: 25,
            ),
            label: 'MEJORAS',
          ),
          BottomNavigationBarItem(
            icon: Image.asset(
              "assets/icons/logros_icon_off.png",
              width: 25,
            ),
            label: 'LOGROS',
          ),
        ],
        selectedLabelStyle: TextStyle(fontSize: 12, color: Colors.black, fontWeight: FontWeight.bold),
        unselectedLabelStyle: TextStyle(fontSize: 12, fontWeight: FontWeight.bold),
      ),
    );
  }

  Widget mostrarPJ(String nombre, String imagen, int cont) {
    return InkWell(
        child: Container(
            height: 38,
            color: Colors.transparent,
            child: Row(children: [
              Center(
                  child: Text(
                nombre,
                style: TextStyle(fontFamily: 'JetBrains', fontSize: 18, color: Colors.yellow.shade600, fontWeight: FontWeight.bold),
              )),
              Center(
                  child: Text(
                cont.toString(),
                style: TextStyle(fontFamily: 'JetBrains', fontSize: 18, color: Colors.yellow.shade600, fontWeight: FontWeight.bold),
              ))
            ])),
        onTap: () {
          setState(() {
            imagenVisible = true;
            imagenGrande = imagen;

            if (nombre == " Goku     ") {
              workers = gokus;
              if (mejoraGoku == 0) {
                produccion = 1;
              } else if (mejoraGoku == 1) {
                produccion = 5;
              } else if (mejoraGoku == 2) {
                produccion = 10;
              } else if (mejoraGoku == 3) {
                produccion = 50;
              } else if (mejoraGoku == 4) {
                produccion = 100;
              } else if (mejoraGoku == 5) {
                produccion = 200;
              } else if (mejoraGoku == 6) {
                produccion = 500;
              }
            }

            if (nombre == " Vegeta   ") {
              workers = vegetas;
              if (mejoraVegeta == 0) {
                produccion = 2;
              } else if (mejoraVegeta == 1) {
                produccion = 5;
              } else if (mejoraVegeta == 2) {
                produccion = 15;
              } else if (mejoraVegeta == 3) {
                produccion = 50;
              } else if (mejoraVegeta == 4) {
                produccion = 100;
              } else if (mejoraVegeta == 5) {
                produccion = 500;
              } else if (mejoraVegeta == 6) {
                produccion = 1000;
              } 
            }
            if (nombre == " Gohan    ") {
              workers = gohans;
              if (mejoraGohan == 0) {
                produccion = 5;
              } else if (mejoraGohan == 1) {
                produccion = 50;
              } else if (mejoraGohan == 2) {
                produccion = 150;
              } else if (mejoraGohan == 3) {
                produccion = 500;
              } else if (mejoraGohan == 4) {
                produccion = 1000;
              } else if (mejoraGohan == 5) {
                produccion = 5000;
              }
            }

            if (nombre == " Trunks   ") {
              workers = trunks;
              if (mejoraTrunks == 0) {
                produccion = 20;
              } else if (mejoraTrunks == 1) {
                produccion = 100;
              } else if (mejoraTrunks == 2) {
                produccion = 500;
              } else if (mejoraTrunks == 3) {
                produccion = 1000;
              } else if (mejoraTrunks == 4) {
                produccion = 5000;
              }
            }

            if (nombre == " Freezer  ") {
              workers = freezers;
              if (mejoraFreezer == 0) {
                produccion = 50;
              } else if (mejoraFreezer == 1) {
                produccion = 300;
              } else if (mejoraFreezer == 2) {
                produccion = 1000;
              } else if (mejoraFreezer == 3) {
                produccion = 5000;
              } else if (mejoraFreezer == 4) {
                produccion = 10000;
              } 
            }

            if (nombre == " Célula   ") {
              workers = celulas;
              if (mejoraCelula == 0) {
                produccion = 200;
              } else if (mejoraCelula == 1) {
                produccion = 500;
              } else if (mejoraCelula == 2) {
                produccion = 3000;
              } else if (mejoraCelula == 3) {
                produccion = 12000;
              } 
            }

            if (nombre == " Buu      ") {
              workers = buus;
              if (mejoraBuu == 0) {
                produccion = 2000;
              } else if (mejoraBuu == 1) {
                produccion = 5000;
              } else if (mejoraBuu == 2) {
                produccion = 15000;
              } else if (mejoraBuu == 3) {
                produccion = 50000;
              } 
            }
          });
        });
  }

  String mostrarImagenGoku(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/gokuGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/gokuGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/gokuGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/gokuGrande4.png";
    } else if (cont == 4) {
      personaje = "assets/icons/pjsGrandes/gokuGrande6.png";
    } else if (cont == 5) {
      personaje = "assets/icons/pjsGrandes/gokuGrande7.png";
    } else if (cont == 6) {
      personaje = "assets/icons/pjsGrandes/gokuGrande5.png";
    }
   

    return personaje;
  }

  String mostrarImagenVegeta(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande4.png";
    } else if (cont == 4) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande5.png";
    } else if (cont == 5) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande6.png";
    } else if (cont == 6) {
      personaje = "assets/icons/pjsGrandes/vegetaGrande7.png";
    }
   
    return personaje;
  }

  String mostrarImagenGohan(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/gohanGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/gohanGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/gohanGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/gohanGrande5.png";
    } else if (cont == 4) {
      personaje = "assets/icons/pjsGrandes/gohanGrande4.png";
    } else if (cont == 5) {
      personaje = "assets/icons/pjsGrandes/gohanGrande6.png";
    }
    return personaje;
  }

  String mostrarImagenTrunks(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/trunksGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/trunksGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/trunksGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/trunksGrande4.png";
    } else if (cont == 4) {
      personaje = "assets/icons/pjsGrandes/trunksGrande6.png";
    } 
    return personaje;
  }

  String mostrarImagenFreezer(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/freezerGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/freezerGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/freezerGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/freezerGrande4.png";
    } else if (cont == 4) {
      personaje = "assets/icons/pjsGrandes/freezerGrande5.png";
    }
    return personaje;
  }

  String mostrarImagenCelula(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/celulaGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/celulaGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/celulaGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/celulaGrande4.png";
    }

    return personaje;
  }

  String mostrarImagenBuu(int cont, String personaje) {
    if (cont < 1) {
      personaje = "assets/icons/pjsGrandes/buuGrande1.png";
    } else if (cont == 1) {
      personaje = "assets/icons/pjsGrandes/buuGrande2.png";
    } else if (cont == 2) {
      personaje = "assets/icons/pjsGrandes/buuGrande3.png";
    } else if (cont == 3) {
      personaje = "assets/icons/pjsGrandes/buuGrande4.png";
    }
    return personaje;
  }

  Widget monedas(double contadorM) {
    if (contadorM >= 0 && contadorM < 1000) {
      return containerMonedas(contadorM.toInt().toString());
    } else if (contadorM >= 1000 && contadorM < 1000000) {
      return containerMonedas((contadorM.toInt() / 1000).toStringAsFixed(3));
    } else if (contadorM >= 1000000 && contadorM < 1000000000000) {
      return containerMonedas((contadorM.toInt() / 1000000).toStringAsFixed(3) + ' M');
    } else {
      return containerMonedas((contadorM.toInt() / 1000000000000).toStringAsFixed(3) + ' MM');
    }
  }

  String totalPj(int produccion, int workers) {
    if ((produccion * workers) >= 0 && (produccion * workers) < 1000) {
      return ((produccion * workers).toString());
    } else if ((produccion * workers) >= 1000 && (produccion * workers) < 1000000) {
      return (((produccion * workers) / 1000).toStringAsFixed(1) + "k");
    } else {
      return (((produccion * workers) / 1000000).toStringAsFixed(1) + ' M');
    }
  }


  Widget containerMonedas(String contadorM) {
    return Container(
      padding: EdgeInsets.only(top: 30),
      child: tiempo == 1000
          ? Text(contadorM, style: TextStyle(fontSize: 40, fontFamily: 'Digital7', color: Colors.yellow.shade600))
          : Text(contadorM, style: TextStyle(fontSize: 40, fontFamily: 'Digital7', color: Colors.red)),
    );
  }



  initState() {
    main();
    if (primeravez) {
      comprobart();
      primeravez = false;
      musicaFondo("music/bola.mp3");
    }
  }

  AudioPlayer player = AudioPlayer();
  musicaFondo(String rutaM) {
    player.play(AssetSource(rutaM));
  }

  AudioPlayer audiobonus = AudioPlayer();
  musicabonus(String rutaM) {
    audiobonus.play(AssetSource(rutaM));
  }

//Funciones de moverse entre ventanas
  mostrarClicker(BuildContext context) {
    Navigator.of(context).pushNamed("/rutaPrincipal");
    player.pause();
  }

  mostrarMejoras(BuildContext context) {
    Navigator.of(context).pushNamed("/rutaMejoras");
    player.pause();
  }

  mostrarLogros(BuildContext context) {
    Navigator.of(context).pushNamed("/rutaLogros");
    player.pause();
  }

  main() async {
    if (timer != null) {
      timer!.cancel();
    }

    Random random = new Random();

    timer = Timer.periodic(Duration(milliseconds: tiempo), (timer) {
      if (tiempo == 500) {
        setState(() {
          cont++;
          if (cont == 10) {
            cont = 0;
            tiempo = 1000;
            audiobonus.pause();
            musicaFondo("music/bola.mp3");
            initState();
          }
        });
      }

      if(clicks==100){
        mensaje(context);
      }     
      else if(clicks==1000){
        mensaje(context);
      }     
      else if(clicks==10000){
        mensaje(context);
      }

      if(clicksbonus==10){
        mensaje(context);
      }     
      else if(clicksbonus==25){
        mensaje(context);
      }     
      else if(clicksbonus==50){
        mensaje(context);
      }
      
      if(Principal2.milmonedas==false && contador>1000  ){
        Principal2.milmonedas=true;
        mensaje(context);
      }  
      else if(  !Principal2.cienmilmonedas && contador>100000){
        Principal2.cienmilmonedas=true;
        mensaje(context);
      }   
      else if( !Principal2.millonmonedas && contador>1000000 ){
        Principal2.millonmonedas=true;
        mensaje(context);
      }

      if(mejoraGoku==6 && !Principal2.comprarMejoras){
        if(mejoraVegeta==6){
          if(mejoraGohan==5){
            if(mejoraTrunks==4){
              if(mejoraFreezer==4){
                if(mejoraCelula==3){
                  if(mejoraBuu==3){
                    Principal2.comprarMejoras=true;
                    mensaje(context);
                  }
                }
             }
           }
          }
        }
      }
       

      boton = random.nextInt(5);
      x = random.nextInt(352);
      y = random.nextInt(753);
      show = false;
      if (boton == 1) {
        setState(() {
          show = true;
        });
      }

      if (mejoraGoku == 0) {
        setState(() {
          contador += 1 * gokus;
        });
      } else if (mejoraGoku == 1) {
        setState(() {
          contador += 5 * gokus;
        });
      } else if (mejoraGoku == 2) {
        setState(() {
          contador += 10 * gokus;
        });
      } else if (mejoraGoku == 3) {
        setState(() {
          contador += 50 * gokus;
        });
      } else if (mejoraGoku == 4) {
        setState(() {
          contador += 100 * gokus;
        });
      } else if (mejoraGoku == 5) {
        setState(() {
          contador += 200 * gokus;
        });
      } else if (mejoraGoku == 6) {
        setState(() {
          contador += 500 * gokus;
        });
      }

      //Vegeta
      if (mejoraVegeta == 0) {
        setState(() {
          contador += 2 * vegetas;
        });
      } else if (mejoraVegeta == 1) {
        setState(() {
          contador += 5 * vegetas;
        });
      } else if (mejoraVegeta == 2) {
        setState(() {
          contador += 15 * vegetas;
        });
      } else if (mejoraVegeta == 3) {
        setState(() {
          contador += 50 * vegetas;
        });
      } else if (mejoraVegeta == 4) {
        setState(() {
          contador += 100 * vegetas;
        });
      } else if (mejoraVegeta == 5) {
        setState(() {
          contador += 500 * vegetas;
        });
      } else if (mejoraVegeta == 6) {
        setState(() {
          contador += 1000 * vegetas;
        });
      } 

      //Gohan
      if (mejoraGohan == 0) {
        setState(() {
          contador += 5* gohans;
        });
      } else if (mejoraGohan == 1) {
        setState(() {
          contador += 50 * gohans;
        });
      } else if (mejoraGohan == 2) {
        setState(() {
          contador += 150 * gohans;
        });
      } else if (mejoraGohan == 3) {
        setState(() {
          contador += 500 * gohans;
        });
      } else if (mejoraGohan == 4) {
        setState(() {
          contador += 1000 * gohans;
        });
      } else if (mejoraGohan == 5) {
        setState(() {
          contador += 5000 * gohans;
        });
      }

      //Trunks
      if (mejoraTrunks == 0) {
        setState(() {
          contador += 20 * trunks;
        });
      } else if (mejoraTrunks == 1) {
        setState(() {
          contador +=100 * trunks;
        });
      } else if (mejoraTrunks == 2) {
        setState(() {
          contador += 500 * trunks;
        });
      } else if (mejoraTrunks == 3) {
        setState(() {
          contador += 1000* trunks;
        });
      } else if (mejoraTrunks == 4) {
        setState(() {
          contador +=  7500* trunks;
        });
      }
      //Freezer
      if (mejoraFreezer == 0) {
        setState(() {
          contador += 50 * freezers;
        });
      } else if (mejoraFreezer == 1) {
        setState(() {
          contador += 300 * freezers;
        });
      } else if (mejoraFreezer == 2) {
        setState(() {
          contador += 1000* freezers;
        });
      } else if (mejoraFreezer == 3) {
        setState(() {
          contador += 5000 * freezers;
        });
      } else if (mejoraFreezer == 4) {
        setState(() {
          contador += 10000 * freezers;
        });
      } 

      //Celula
      if (mejoraCelula == 0) {
        setState(() {
          contador += 200 * celulas;
        });
      } else if (mejoraCelula == 1) {
        setState(() {
          contador += 500 * celulas;
        });
      } else if (mejoraCelula == 2) {
        setState(() {
          contador += 3000 * celulas;
        });
      } else if (mejoraCelula == 3) {
        setState(() {
          contador += 12000 * celulas;
        });
      } 

      //Buu
      if (mejoraBuu == 0) {
        setState(() {
          contador += 2000 * buus;
        });
      } else if (mejoraBuu == 1) {
        setState(() {
          contador += 5000 * buus;
        });
      } else if (mejoraBuu == 2) {
        setState(() {
          contador += 15000 * buus;
        });
      } else if (mejoraBuu == 3) {
        setState(() {
          contador += 50000 * buus;
        });
      } 
    });
  }

  Widget texto1 (){
    return Text("" +(produccion).toString() +" zenis /s.",
                                      style: TextStyle(
                                          fontFamily: 'JetBrains',
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold,
                                          color: Colors.yellow.shade600),
                                      textAlign: TextAlign.center,
                                    );
}
 Widget texto2() {
  return Text("Total: " + totalPj(produccion, workers) +" zenis /s.",
                                      style: TextStyle(
                                          fontFamily: 'JetBrains',
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold,
                                          color: Colors.yellow.shade600),
                                      textAlign: TextAlign.center,
                                    );
 }

  comprobart() {
    if (mejoraGoku == 0) {
      setState(() {
        porSeg = porSeg + 1 * gokus;
      });
    } else if (mejoraGoku == 1) {
      setState(() {
        porSeg = porSeg + 5 * gokus;
      });
    } else if (mejoraGoku == 2) {
      setState(() {
        porSeg = porSeg + 10 * gokus;
      });
    } else if (mejoraGoku == 3) {
      setState(() {
        porSeg = porSeg + 50 * gokus;
      });
    } else if (mejoraGoku == 4) {
      setState(() {
        porSeg = porSeg + 100 * gokus;
      });
    } else if (mejoraGoku == 5) {
      setState(() {
        porSeg = porSeg + 200 * gokus;
      });
    } else if (mejoraGoku == 6) {
      setState(() {
        porSeg = porSeg + 500 * gokus;
      });
    } 
    //Vegeta
    if (mejoraVegeta == 0) {
      setState(() {
        porSeg = porSeg + 2 * vegetas;
      });
    } else if (mejoraVegeta == 1) {
      setState(() {
        porSeg = porSeg + 5 * vegetas;
      });
    } else if (mejoraVegeta == 2) {
      setState(() {
        porSeg = porSeg + 15 * vegetas;
      });
    } else if (mejoraVegeta == 3) {
      setState(() {
        porSeg = porSeg + 50 * vegetas;
      });
    } else if (mejoraVegeta == 4) {
      setState(() {
        porSeg = porSeg + 100 * vegetas;
      });
    } else if (mejoraVegeta == 5) {
      setState(() {
        porSeg = porSeg + 500 * vegetas;
      });
    } else if (mejoraVegeta == 6) {
      setState(() {
        porSeg = porSeg + 1000 * vegetas;
      });
    } 
    //Gohan
    if (mejoraGohan == 0) {
      setState(() {
        porSeg = porSeg + 2 * gohans;
      });
    } else if (mejoraGohan == 1) {
      setState(() {
        porSeg = porSeg + 5 * gohans;
      });
    } else if (mejoraGohan == 2) {
      setState(() {
        porSeg = porSeg + 15 * gohans;
      });
    } else if (mejoraGohan == 3) {
      setState(() {
        porSeg = porSeg + 50 * gohans;
      });
    } else if (mejoraGohan == 4) {
      setState(() {
        porSeg = porSeg + 100 * gohans;
      });
    } else if (mejoraGohan == 5) {
      setState(() {
        porSeg = porSeg + 500 * gohans;
      });
    } 
    //Trunks
    if (mejoraTrunks == 0) {
      setState(() {
        porSeg = porSeg + 2 * trunks;
      });
    } else if (mejoraTrunks == 1) {
      setState(() {
        porSeg = porSeg + 5 * trunks;
      });
    } else if (mejoraTrunks == 2) {
      setState(() {
        porSeg = porSeg + 15 * trunks;
      });
    } else if (mejoraTrunks == 3) {
      setState(() {
        porSeg = porSeg + 50 * trunks;
      });
    } else if (mejoraTrunks == 4) {
      setState(() {
        porSeg = porSeg + 100 * trunks;
      });
    }
    //Freezer
    if (mejoraFreezer == 0) {
      setState(() {
        porSeg = porSeg + 2 * freezers;
      });
    } else if (mejoraFreezer == 1) {
      setState(() {
        porSeg = porSeg + 5 * freezers;
      });
    } else if (mejoraFreezer == 2) {
      setState(() {
        porSeg = porSeg + 15 * freezers;
      });
    } else if (mejoraFreezer == 3) {
      setState(() {
        porSeg = porSeg + 50 * freezers;
      });
    } else if (mejoraFreezer == 4) {
      setState(() {
        porSeg = porSeg + 100 * freezers;
      });
    } 
    //Celula
    if (mejoraCelula == 0) {
      setState(() {
        porSeg = porSeg + 2 * celulas;
      });
    } else if (mejoraCelula == 1) {
      setState(() {
        porSeg = porSeg + 5 * celulas;
      });
    } else if (mejoraCelula == 2) {
      setState(() {
        porSeg = porSeg + 15 * celulas;
      });
    } else if (mejoraCelula == 3) {
      setState(() {
        porSeg = porSeg + 50 * celulas;
      });
    } 
    //Buu
    if (mejoraBuu == 0) {
      setState(() {
        porSeg = porSeg + 2 * buus;
      });
    } else if (mejoraBuu == 1) {
      setState(() {
        porSeg = porSeg + 5 * buus;
      });
    } else if (mejoraBuu == 2) {
      setState(() {
        porSeg = porSeg + 15 * buus;
      });
    } else if (mejoraBuu == 3) {
      setState(() {
        porSeg = porSeg + 50 * buus;
      });
    } 
  }


  void reset() {
    contador = 0;
    ruta = "";
    personaje = "";
    mejoraBola = 1;
    mejoraGoku = 0;
    mejoraVegeta = 0;
    mejoraGohan = 0;
    mejoraTrunks = 0;
    mejoraCelula = 0;
    mejoraFreezer = 0;
    mejoraBuu = 0;
    gokus = 0;
    vegetas = 0;
    gohans = 0;
    trunks = 0;
    celulas = 0;
    buus = 0;
    freezers = 0;
    produccion = 0;
    workers = 0;
    timer;
    porSeg = 0;
    boton = 0;
    x = 0;
    y = 0;
    show = false;
    imagenGrande = "";
    imagenVisible = false;
    primeravez = true;
    tiempo = 1000;
    cont = 0;

    Mejoras2.costeGoku = 20;
    Mejoras2.costeVEgeta = 50;
    Mejoras2.costeGohan = 1250;
    Mejoras2.costeTrunks = 2250;
    Mejoras2.costeCelula = 3250;
    Mejoras2.costeFreezer = 4250;
    Mejoras2.costeBuu = 5250;
    Principal2.milmonedas=false;
    Principal2.cienmilmonedas=false;
    Principal2.millonmonedas=false;
    Principal2.comprarMejoras=false;
  }

   void mensaje(BuildContext context) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      content: Text(
        "NUEVO LOGRO CONSEGUIDO",style: TextStyle(color: Colors.black),
        textAlign: TextAlign.center,
      ),
      backgroundColor: Colors.yellow,
    ));
  }


}
