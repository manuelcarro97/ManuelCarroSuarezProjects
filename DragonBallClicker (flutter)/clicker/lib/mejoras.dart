// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/foundation.dart';

import '/bola.dart';
import 'package:flutter/material.dart';

class Mejoras extends StatefulWidget {
  Mejoras2 createState() => Mejoras2();
}

class Mejoras2 extends State<Mejoras> {
  //Variables de los costes de cada personaje
  static int costeGoku = 20;
  static int costeVEgeta = 50;
  static int costeGohan = 1250;
  static int costeTrunks = 2250;
  static int costeCelula = 3250;
  static int costeFreezer = 4250;
  static int costeBuu = 5250;
//Variables de los logros de cada personaje
  static bool logroGokus = false;
  static bool logroVegeta = false;
  static bool logroGohan = false;
  static bool logroTrunk = false;
  static bool logroFrezzer = false;
  static bool logroCelula = false;
  static bool logroBuu = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: Text("Mejoras"),
      ),
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/icons/tienda.png"),
            opacity: 0.8,
            fit: BoxFit.fill,
          ),
        ),
        // child: Stack(
        //   children: [
        child: ListView(
          children: [
            //Compra mejoras de las Bolas de Dragon, incremento de Clicks
            SizedBox(height: 10),
            Center(
                heightFactor: 1,
                child: Text(
                  "Mejoras Clicks",
                  style: TextStyle(
                    fontFamily: 'Digital7',
                    fontSize: 20,
                  ),
                )),
            //Función mostrar la bola que corresponde con la mejora comprada
            comprobaciones(),

            //Comprar cantidad de personajes del juego
            Divider(),
            SizedBox(height: 10),
            Center(
                heightFactor: 1,
                child: Text(
                  "Comprar Unidades",
                  style: TextStyle(fontFamily: 'Digital7', fontSize: 20),
                )),
            //Función: Comprobamos que mejora tiene cada personaje para mostrar por pantalla los datos e imagen correspondiente
            comprobacionesGoku(),
            comprobacionesVegeta(),
            comprobacionesGohan(),
            comprobacionesTrunks(),
            comprobacionesFreezer(),
            comprobacionesCelula(),
            comprobacionesBuu(),

            //Compra mejoras de tranformaciones de personaje
            Divider(),

            Center(
                heightFactor: 3,
                child: Text(
                  "mejoras timer",
                  style: TextStyle(fontFamily: 'Digital7', fontSize: 20),
                )),

            //Función comprueba las mejoreas de los personaje para aplicar su incremento
            comprobacionesMejoraGoku(),
            comprobacionesMejoraVegeta(),
            comprobacionesMejoraGohan(),
            comprobacionesMejoraTrunks(),
            comprobacionesMejoraFreezer(),
            comprobacionesMejoraCelula(),
            comprobacionesMejoraBuu(),
          ],
        ),

        // Visibility(
        //       child: Container(
        //           padding: EdgeInsets.only(left: 280),
        //           child: InkWell(
        //               child: Image.asset("assets/icons/baba.png"),
        //               ))),

        // ])
      ),

      //Funciones de moverse entre ventanas
      bottomNavigationBar: BottomNavigationBar(
        onTap: (i) {
          if (i == 0) {
            mostrarClicker(context);
          }
          if (i == 2) {
            mostrarLogros(context);
          }
        },
        //Logos del navegador
        backgroundColor: Colors.grey,
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Image.asset(
              "assets/icons/ball_icon_off.png",
              width: 25,
            ),
            label: 'CLICKER',
          ),
          BottomNavigationBarItem(
            icon: Image.asset(
              "assets/icons/upgrade_icon.png",
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
        selectedLabelStyle: TextStyle(
            fontSize: 12, color: Colors.black, fontWeight: FontWeight.bold),
        unselectedLabelStyle:
            TextStyle(fontSize: 12, fontWeight: FontWeight.bold),
      ),
    );
  }

//Funciones de moverse entre ventanas
  initState() {
    musicaFondo("music/mejoras.mp3");
  }

  AudioPlayer player = AudioPlayer();
  musicaFondo(String rutaM) {
    player.play(AssetSource(rutaM));
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

//Función para mostrar cartas
  Widget carta(double top, AssetImage img, int coste, String texto,
      String infoTitle, String infoText, int funcion) {
    return SizedBox(
        height: 102,
        child: Card(
            elevation: 0,
            color: Colors.transparent,
            semanticContainer: true,
            clipBehavior: Clip.antiAliasWithSaveLayer,
            margin: EdgeInsets.only(top: top, left: 20, right: 20, bottom: 10),
            child: Stack(
              children: [
                Image.asset(
                  "assets/icons/dibu.png",
                  // fit: BoxFit.cover,
                ),
                Material(
                    color: Colors.transparent,
                    child: InkWell(
                      child: Row(
                        // mainAxisSize: MainAxisSize.min,
                        children: [
                          Ink.image(
                            // alignment: Alignment.center,
                            image: img,
                            height: 72,
                            width: 120,
                          ),
                          Expanded(
                              child: ListTile(
                            iconColor: Colors.yellow.shade600,
                            title: Text(texto,
                                style: TextStyle(
                                    fontFamily: 'JetBrains',
                                    fontSize: 18,
                                    fontWeight: FontWeight.bold,
                                    color: Colors.yellow.shade600)),
                            subtitle: Text(coste.toString(),
                                style: TextStyle(
                                    fontFamily: 'JetBrains',
                                    fontSize: 16,
                                    color: Colors.yellow.shade600)),
                            trailing: IconButton(
                              icon: Icon(Icons.info_outline),
                              onPressed: () {
                                mostrarInfo(context, infoTitle, infoText);
                              },
                            ),
                          )),
                        ],
                      ),

                      //Función cuando clickemos en alguna mejora esta se incremente correctamente
                      onTap: () {
                        setState(() {
                          if (funcion == 1) {
                            sumarMejoraBola(coste);
                          } else if (funcion == 2) {
                            sumarMejoraGoku(coste);
                          } else if (funcion == 3) {
                            sumarMejoraVegeta(coste);
                          } else if (funcion == 4) {
                            sumarMejoraGohan(coste);
                          } else if (funcion == 5) {
                            sumarMejoraTrunks(coste);
                          } else if (funcion == 6) {
                            sumarMejoraFreezer(coste);
                          } else if (funcion == 7) {
                            sumarMejoraCelula(coste);
                          } else if (funcion == 8) {
                            sumarMejoraBuu(coste);
                          } else if (funcion == 9) {
                            sumarGoku(coste);
                          } else if (funcion == 10) {
                            sumarVegeta(coste);
                          } else if (funcion == 11) {
                            sumarGohan(coste);
                          } else if (funcion == 12) {
                            sumarTrunks(coste);
                          } else if (funcion == 13) {
                            sumarFreezer(coste);
                          } else if (funcion == 14) {
                            sumarCelula(coste);
                          } else if (funcion == 15) {
                            sumarBuu(coste);
                          }

                          //LLamamos a funciones de los logros
                          logroCel();
                          logroFrez();
                          logroGoh();
                          logroTRu();
                          logroVege();
                          logroB();
                          logroGok();

                          if (Principal.buus == 1) {
                            mensaje(context);
                          }
                        });
                      },
                    )),
              ],
            )));
  }

// Permite comprar las mejoras de los persobajes y cuando quitar o mostrarlas
  Widget showMejora(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return Principal.mejoraBola < 7
        ? carta(20, img, coste, texto, infoTitle, infoText, 1)
        : Container();
  }

  Widget showMejoraTiempo(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraGoku < 6
        ? carta(20, img, coste, texto, infoTitle, infoText, 2)
        : Container();
  }

  Widget showMejoraVegeta(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraVegeta < 6
        ? carta(20, img, coste, texto, infoTitle, infoText, 3)
        : Container();
  }

  Widget showMejoraGohan(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraGohan < 5
        ? carta(20, img, coste, texto, infoTitle, infoText, 4)
        : Container();
  }

  Widget showMejoraTrunks(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraTrunks < 4
        ? carta(20, img, coste, texto, infoTitle, infoText, 5)
        : Container();
  }

  Widget showMejoraFreezer(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraFreezer < 4
        ? carta(20, img, coste, texto, infoTitle, infoText, 6)
        : Container();
  }

  Widget showMejoraCelula(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraCelula < 3
        ? carta(20, img, coste, texto, infoTitle, infoText, 7)
        : Container();
  }

  Widget showMejoraBuu(AssetImage img, int coste, String texto,
      String infoTitle, String infoText) {
    return Principal.mejoraBuu < 3
        ? carta(20, img, coste, texto, infoTitle, infoText, 8)
        : Container();
  }

//Permite mostrar los personajes en la tienda
  Widget showGoku(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 9);
  }

  Widget showVegeta(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 10);
  }

  Widget showGohan(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 11);
  }

  Widget showTrunks(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 12);
  }

  Widget showFreezer(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 13);
  }

  Widget showCelula(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 14);
  }

  Widget showBuu(AssetImage img, int coste, String texto, String infoTitle,
      String infoText) {
    return carta(20, img, coste, texto, infoTitle, infoText, 15);
  }

  // Ventana de información del boton i
  mostrarInfo(BuildContext context, String info, String contenido) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text(info),
            content: Text(contenido),
            actions: [
              TextButton(
                  onPressed: () {
                    Navigator.pop(context);
                  },
                  child: Text("OK")),
            ],
          );
        });
  }

  // Compras de mejoras, workers y cambios de precios (segun la mejora comprada)
  sumarMejoraBola(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraBola++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraGoku(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraGoku++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraVegeta(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraVegeta++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraGohan(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraGohan++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraTrunks(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraTrunks++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraFreezer(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraFreezer++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraCelula(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraCelula++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarMejoraBuu(int coste) {
    if (Principal.contador >= coste) {
      Principal.mejoraBuu++;
      Principal.contador = Principal.contador - coste;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  //Suma la cantidad de cada personaje comprado
  sumarGoku(int coste) {
    if (Principal.contador >= coste) {
      Principal.gokus++;
      Principal.contador = Principal.contador - coste;
      costeGoku += 10;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarVegeta(int coste) {
    if (Principal.contador >= coste) {
      Principal.vegetas++;
      Principal.contador = Principal.contador - coste;
      costeVEgeta += 50;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarGohan(int coste) {
    if (Principal.contador >= coste) {
      Principal.gohans++;
      Principal.contador = Principal.contador - coste;
      costeGohan += 50;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarTrunks(int coste) {
    if (Principal.contador >= coste) {
      Principal.trunks++;
      Principal.contador = Principal.contador - coste;
      costeTrunks += 50;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarFreezer(int coste) {
    if (Principal.contador >= coste) {
      Principal.freezers++;
      Principal.contador = Principal.contador - coste;
      costeFreezer += 50;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarCelula(int coste) {
    if (Principal.contador >= coste) {
      Principal.celulas++;
      Principal.contador = Principal.contador - coste;
      costeCelula += 50;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  sumarBuu(int coste) {
    if (Principal.contador >= coste) {
      Principal.buus++;
      Principal.contador = Principal.contador - coste;
      costeBuu += 50;
    } else {
      mensajeError(context, "No tienes suficientes zenis.");
    }
  }

  //Comprobaciones para saber que carta imprirmir
  Widget comprobaciones() {
    if (Principal.mejoraBola == 1) {
      return showMejora(
          AssetImage("assets/icons/pjs/bola2.png"),
          20,
          "2 estrellas",
          "Bola de 2 estrellas",
          "Ganas 2 zenis por cada click.");
    }
    if (Principal.mejoraBola == 2) {
      return showMejora(
          AssetImage("assets/icons/pjs/bola3.png"),
          30,
          "3 estrellas",
          "Bola de 3 estrellas",
          "Ganas 3 zenis por cada click.");
    }
    if (Principal.mejoraBola == 3) {
      return showMejora(
          AssetImage("assets/icons/pjs/bola4.png"),
          40,
          "4 estrellas",
          "Bola de 4 estrellas",
          "Ganas 4 zenis por cada click.");
    }
    if (Principal.mejoraBola == 4) {
      return showMejora(
          AssetImage("assets/icons/pjs/bola5.png"),
          60,
          "5 estrellas",
          "Bola de 5 estrellas",
          "Ganas 5 zenis por cada click.");
    }
    if (Principal.mejoraBola == 5) {
      return showMejora(
          AssetImage("assets/icons/pjs/bola6.png"),
          70,
          "6 estrellas",
          "Bola de 6 estrellas",
          "Ganas 6 zenis por cada click.");
    }
    if (Principal.mejoraBola == 6) {
      return showMejora(
          AssetImage("assets/icons/pjs/bola7.png"),
          80,
          "7 estrellas",
          "Bola de 7 estrellas",
          "Ganas 7 zenis por cada click.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesGoku() {
    if (Principal.mejoraGoku == 0) {
      return showGoku(AssetImage("assets/icons/pjs/goku1.png"), costeGoku,
          "Goku", "Goku ", "Gana automáticamente 1 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 1) {
      return showGoku(
          AssetImage("assets/icons/pjs/goku2.png"),
          costeGoku * 2,
          "Goku SS",
          "Goku Super Saiyan ",
          "Gana automáticamente 5 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 2) {
      return showGoku(
          AssetImage("assets/icons/pjs/goku3.png"),
          costeGoku * 3,
          "Goku SS2",
          "Goku Super Saiyan 2",
          "Gana automáticamente 10 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 3) {
      return showGoku(
          AssetImage("assets/icons/pjs/goku4.png"),
          costeGoku * 4,
          "Goku SS3",
          "Goku Super Saiyan 3",
          "Gana automáticamente 50 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 4) {
      return showGoku(
          AssetImage("assets/icons/pjs/goku5.png"),
          costeGoku * 5,
          "Goku SS God",
          "Goku Super Saiyan Dios",
          "Gana automáticamente 100 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 5) {
      return showGoku(
          AssetImage("assets/icons/pjs/goku6.png"),
          costeGoku * 6,
          "Goku SS Blue",
          "Goku Super Saiyan Blue",
          "Gana automáticamente 200 zenis cada segundo.");
    } else {
      return showGoku(
          AssetImage("assets/icons/pjs/goku7.png"),
          costeGoku * 7,
          "Goku Ultra Instinto",
          "Ultra Instinto",
          "Gana automáticamente 500 zenis cada segundo.");
    }
  }

  Widget comprobacionesVegeta() {
    if (Principal.gokus >= 3 && Principal.mejoraVegeta == 0) {
      return showVegeta(AssetImage("assets/icons/pjs/vegeta1.png"), costeVEgeta,
          "Vegeta", "Vegeta", "Gana automáticamente 2 zenis cada segundo.");
    } else if (Principal.gokus < 3 && Principal.mejoraVegeta == 0) {
      return Container();
    } else if (Principal.mejoraVegeta == 1) {
      return showVegeta(
          AssetImage("assets/icons/pjs/vegeta2.png"),
          costeVEgeta * 2,
          "Vegeta SS",
          "Vegeta Super Saiyan",
          "Gana automáticamente 5 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 2) {
      return showVegeta(
          AssetImage("assets/icons/pjs/vegeta3.png"),
          costeVEgeta * 3,
          "Vegeta SS2 ",
          "Vegeta Super Saiyan 2",
          "Gana automáticamente 15 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 3) {
      return showVegeta(
          AssetImage("assets/icons/pjs/vegeta4.png"),
          costeVEgeta * 4,
          "Majin Vegeta",
          "Majin Vegeta",
          "Gana automáticamente 50 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 4) {
      return showVegeta(
          AssetImage("assets/icons/pjs/vegeta5.png"),
          costeVEgeta * 5,
          "Vegeta SS God",
          "Vegeta Super Saiyan Dios",
          "Gana automáticamente 100 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 5) {
      return showVegeta(
          AssetImage("assets/icons/pjs/vegeta6.png"),
          costeVEgeta * 6,
          "Vegeta SS blue",
          "Vegeta Super Saiyan Dios",
          "Gana automáticamente 500 zenis cada segundo.");
    } else {
      return showVegeta(
          AssetImage("assets/icons/pjs/vegeta7.png"),
          costeVEgeta * 7,
          "Vegeta SS Blue EVO",
          "Vegeta Super Saiyan Blue",
          "Gana automáticamente 1000 zenis cada segundo.");
    }
  }

  Widget comprobacionesGohan() {
    if (Principal.vegetas >= 10 && Principal.mejoraGohan == 0) {
      return showGohan(
          AssetImage("assets/icons/pjs/gohan1.png"),
          costeGohan * 2,
          "Gohan (pequeño)",
          "Gohan Pequeño",
          "Gana automáticamente 5 zenis cada segundo.");
    } else if (Principal.vegetas < 10 && Principal.mejoraGohan == 0) {
      return Container();
    } else if (Principal.mejoraGohan == 1) {
      return showGohan(
          AssetImage("assets/icons/pjs/gohan2.png"),
          costeGohan * 3,
          "Gohan SS",
          "Gohan Adulto",
          "Gana automáticamente 50 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 2) {
      return showGohan(
          AssetImage("assets/icons/pjs/gohan3.png"),
          costeGohan * 4,
          "Gohan SS2",
          "Gohan Super Saiyan",
          "Gana automáticamente 150 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 3) {
      return showGohan(
          AssetImage("assets/icons/pjs/gohan4.png"),
          costeGohan * 5,
          "Gohan SS2 (adulto)",
          "Gohan Super Sayain 2",
          "Gana automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 4) {
      return showGohan(
          AssetImage("assets/icons/pjs/gohan5.png"),
          costeGohan * 6,
          "Ultimate Gohan",
          "Ultimate Gohan",
          "Gana automáticamente 1000 zenis cada segundo.");
    } else {
      return showGohan(
          AssetImage("assets/icons/pjs/gohan5.png"),
          costeGohan * 6,
          "Gohan Bestia",
          "Gohan Bestia",
          "Gana automáticamente 5000 zenis cada segundo.");
    }
  }

  Widget comprobacionesTrunks() {
    if (Principal.gohans > 10 && Principal.mejoraTrunks == 0) {
      return showTrunks(
          AssetImage("assets/icons/pjs/trunks1.png"),
          costeTrunks * 2,
          "Trunks (adolescente)  ",
          "Trunks (adolescente)",
          "Gana automáticamente 20 zenis cada segundo.");
    } else if (Principal.gohans <= 10 && Principal.mejoraTrunks == 0) {
      return Container();
    } else if (Principal.mejoraTrunks == 1) {
      return showTrunks(
          AssetImage("assets/icons/pjs/trunks2.png"),
          costeTrunks * 3,
          "Trunks (futuro)",
          "Trunks (futuro)",
          "Gana automáticamente 100 zenis cada segundo.");
    } else if (Principal.mejoraTrunks == 2) {
      return showTrunks(
          AssetImage("assets/icons/pjs/trunks4.png"),
          costeTrunks * 4,
          "Trunks SS",
          "Trunks Super Saiyan",
          "Gana automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraTrunks == 3) {
      return showTrunks(
          AssetImage("assets/icons/pjs/trunks5.png"),
          costeTrunks * 5,
          "Super Trunks",
          "Super Trunks",
          "Gana automáticamente 1000 zenis cada segundo.");
    } else {
      return showTrunks(
          AssetImage("assets/icons/pjs/trunks6.png"),
          costeTrunks * 6,
          "Super Trunks SS",
          "Super Trunks SS",
          "Gana automáticamente 7500 zenis cada segundo.");
    }
  }

  Widget comprobacionesFreezer() {
    if (Principal.trunks > 10 && Principal.mejoraFreezer == 0) {
      return showFreezer(
          AssetImage("assets/icons/pjs/freezer1.png"),
          costeFreezer * 2,
          "Freezer",
          "Freezer",
          "Gana automáticamente 50 zenis cada segundo.");
    } else if (Principal.trunks <= 10 && Principal.mejoraFreezer == 0) {
      return Container();
    } else if (Principal.mejoraFreezer == 1) {
      return showFreezer(
          AssetImage("assets/icons/pjs/freezer2.png"),
          costeFreezer * 3,
          "Freezer lvl 2",
          "Freezer Forma 2",
          "Gana automáticamente 300 zenis cada segundo.");
    } else if (Principal.mejoraFreezer == 2) {
      return showFreezer(
          AssetImage("assets/icons/pjs/freezer3.png"),
          costeFreezer * 4,
          "Freezer lvl 3",
          "Freezer Forma 3",
          "Gana automáticamente 1000 zenis cada segundo.");
    } else if (Principal.mejoraFreezer == 3) {
      return showFreezer(
          AssetImage("assets/icons/pjs/freezer4.png"),
          costeFreezer * 5,
          "Forma Final ",
          "Freezer Forma Final ",
          "Gana automáticamente 5000 zenis cada segundo.");
    } else {
      return showFreezer(
          AssetImage("assets/icons/pjs/freezer5.png"),
          costeFreezer * 6,
          "Golden Freezer",
          "Golden Freezer",
          "Gana automáticamente 10000 zenis cada segundo.");
    }
  }

  Widget comprobacionesCelula() {
    if (Principal.freezers > 10 && Principal.mejoraCelula == 0) {
      return showCelula(
          AssetImage("assets/icons/pjs/cell1.png"),
          costeCelula * 2,
          "Celula ",
          "Celula",
          "Gana automáticamente 200 zenis cada segundo.");
    } else if (Principal.freezers <= 10 && Principal.mejoraCelula == 0) {
      return Container();
    } else if (Principal.mejoraCelula == 1) {
      return showCelula(
          AssetImage("assets/icons/pjs/cell2.png"),
          costeCelula * 3,
          "Celula Fase 2",
          "Celula Fase 2",
          "Gana automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraCelula == 2) {
      return showCelula(
          AssetImage("assets/icons/pjs/cell3.png"),
          costeCelula * 4,
          "Celula Perfecto",
          "Celula Perfecto",
          "Gana automáticamente 3000 zenis cada segundo.");
    } else {
      return showCelula(
          AssetImage("assets/icons/pjs/cell4.png"),
          costeCelula * 5,
          "Celula Super Perfecto",
          "Celula Super Percefto",
          "Gana automáticamente 12000 zenis cada segundo.");
    }
  }

  Widget comprobacionesBuu() {
    if (Principal.celulas > 10 && Principal.mejoraBuu == 0) {
      return showBuu(
          AssetImage("assets/icons/pjs/buu1.png"),
          costeBuu * 2,
          "Majin Buu",
          "Majin Buu",
          "Gana automáticamente 2000 zenis cada segundo.");
    } else if (Principal.celulas <= 10 && Principal.mejoraBuu == 0) {
      return Container();
    } else if (Principal.mejoraBuu == 1) {
      return showBuu(
          AssetImage("assets/icons/pjs/buu2.png"),
          costeBuu * 3,
          "Buu Pura Maldad",
          "Buu Pura Maldad",
          "Gana automáticamente 5000 zenis cada segundo.");
    } else if (Principal.mejoraBuu == 2) {
      return showBuu(
          AssetImage("assets/icons/pjs/buu3.png"),
          costeBuu * 4,
          "Super Buu",
          "Super Buu",
          "Gana automáticamente 15000 zenis cada segundo.");
    } else {
      return showBuu(
          AssetImage("assets/icons/pjs/buu4.png"),
          costeBuu * 5,
          "Pequeño Buu",
          "Pequeño Buu",
          "Gana automáticamente 50000 zenis cada segundo.");
    }
  }

//Comprobar de las mejoras
  Widget comprobacionesMejoraGoku() {
    if (Principal.mejoraGoku == 0 && Principal.gokus > 5) {
      return showMejoraTiempo(
          AssetImage("assets/icons/mejoras/goku/baston.png"),
          20,
          "Bastón",
          "Bastón",
          "Mejora para ganar automáticamente 5 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 1) {
      return showMejoraTiempo(
          AssetImage("assets/icons/mejoras/goku/nube.png"),
          50,
          "Nube Kinton",
          "Nube Kinton",
          "Mejora para ganar automáticamente 10 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 2) {
      return showMejoraTiempo(
          AssetImage("assets/icons/mejoras/goku/aureola.png"),
          100,
          "Aureola",
          "Aureola",
          "Mejora para ganar automáticamente 50 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 3) {
      return showMejoraTiempo(
          AssetImage("assets/icons/mejoras/goku/kanji.png"),
          500,
          "Mejora Goku SSGod",
          "Goku",
          "Mejora para ganar automáticamente 100 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 4) {
      return showMejoraTiempo(
          AssetImage("assets/icons/mejoras/goku/whis.png"),
          1000,
          "Mejora Goku SS blue",
          "Goku",
          "Mejora para ganar automáticamente 200 zenis cada segundo.");
    } else if (Principal.mejoraGoku == 5) {
      return showMejoraTiempo(
          AssetImage("assets/icons/mejoras/goku/genkidama.png"),
          5000,
          "Goku Ultra Instinto",
          "Goku",
          "Mejora para ganar automáticamente 500 zenis cada segundo.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesMejoraVegeta() {
    if (Principal.mejoraVegeta == 0 && Principal.vegetas > 4) {
      return showMejoraVegeta(
          AssetImage("assets/icons/mejoras/vegeta/nave.png"),
          75,
          "Mejora Vegeta SS",
          "Vegeta",
          "Mejora para ganar automáticamente 5 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 1) {
      return showMejoraVegeta(
          AssetImage("assets/icons/mejoras/vegeta/scouter.png"),
          100,
          "Mejora Vegeta SS2",
          "Mejora Vegeta SS2",
          "Mejora para ganar automáticamente 15 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 2) {
      return showMejoraVegeta(
          AssetImage("assets/icons/mejoras/vegeta/majin.png"),
          300,
          "Mejora Vegeta Majin",
          "Mejora Vegeta Majin",
          "Mejora para ganar automáticamente 50 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 3) {
      return showMejoraVegeta(
          AssetImage("assets/icons/mejoras/vegeta/capsule_corp.png"),
          500,
          "Mejora Vegeta God",
          "Mejora GOd",
          "Mejora para ganar automáticamente 100 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 4) {
      return showMejoraVegeta(
          AssetImage("assets/icons/mejoras/vegeta/bulma.png"),
          1000,
          "Mejora SS Blue",
          "Mejora Vegeta SS Blue",
          "Mejora para ganar automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraVegeta == 5) {
      return showMejoraVegeta(
          AssetImage("assets/icons/mejoras/vegeta/bills.png"),
          2000,
          "Mejora SS Blue EVO",
          "Mejora Vegeta SS Blue EVO",
          "Mejora para ganar automáticamente 1000 zenis cada segundo.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesMejoraGohan() {
    if (Principal.mejoraGohan == 0 && Principal.gohans > 4) {
      return showMejoraGohan(
          AssetImage("assets/icons/mejoras/gohan/gorro.png"),
          75,
          "Mejora Gohan (adulto)",
          "Gorro",
          "Mejora para ganar automáticamente 50 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 1) {
      return showMejoraGohan(
          AssetImage("assets/icons/mejoras/gohan/piccolo.png"),
          100,
          "Mejora Gohan SS",
          "Gohan",
          "Mejora para ganar automáticamente 150 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 2) {
      return showMejoraGohan(
          AssetImage("assets/icons/mejoras/gohan/casco.png"),
          300,
          "Mejora Gohan SS2",
          "Gohan",
          "Mejora para ganar automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 3) {
      return showMejoraGohan(
          AssetImage("assets/icons/mejoras/gohan/kaioshin.png"),
          500,
          "Mejora Gohan ultimate",
          "Gohan",
          "Mejora para ganar automáticamente 1000 zenis cada segundo.");
    } else if (Principal.mejoraGohan == 4) {
      return showMejoraGohan(
          AssetImage("assets/icons/mejoras/gohan/cellmax.png"),
          1000,
          "Mejora gohan bestia",
          "Gohan",
          "Mejora para ganar automáticamente 5000 zenis cada segundo.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesMejoraTrunks() {
    if (Principal.mejoraTrunks == 0 && Principal.trunks > 4) {
      return showMejoraTrunks(
          AssetImage("assets/icons/mejoras/trunks/espada.png"),
          75,
          "Mejora Trunk Futuro",
          "Trunks",
          "Mejora para ganar automáticamente 100 zenis cada segundo.");
    } else if (Principal.mejoraTrunks == 1) {
      return showMejoraTrunks(
          AssetImage("assets/icons/mejoras/trunks/maquina.png"),
          100,
          "Mejora Trunks SS",
          "Trunks",
          "Mejora para ganar automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraTrunks == 2) {
      return showMejoraTrunks(
          AssetImage("assets/icons/mejoras/trunks/capsula.png"),
          300,
          "Mejora Super Trunks",
          "Trunks",
          "Mejora para ganar automáticamente 1000 zenis cada segundo.");
    } else if (Principal.mejoraTrunks == 3) {
      return showMejoraTrunks(
          AssetImage("assets/icons/mejoras/trunks/espada2.png"),
          500,
          "Mejora Trunk SS Ultimate",
          "Trunks",
          "Mejora para ganar automáticamente 5000 zenis cada segundo.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesMejoraFreezer() {
    if (Principal.mejoraFreezer == 0 && Principal.freezers > 4) {
      return showMejoraFreezer(
          AssetImage("assets/icons/mejoras/freezer/naveFreezer.png"),
          75,
          "Mejora Freezer Nv 2",
          "Freezer",
          "Mejora para ganar automáticamente 300 zenis cada segundo.");
    } else if (Principal.mejoraFreezer == 1) {
      return showMejoraFreezer(
          AssetImage("assets/icons/mejoras/freezer/nave_grande.png"),
          100,
          "Mejora Freezer Nv 3",
          "Freezer",
          "Mejora para ganar automáticamente 1000 zenis cada segundo.");
    } else if (Principal.mejoraFreezer == 2) {
      return showMejoraFreezer(
          AssetImage("assets/icons/mejoras/freezer/scouterFreezer.png"),
          300,
          "Mejora Forma Final",
          "Freezer",
          "Mejora para ganar automáticamente 5000 zenis cada segundo.");
    } else if (Principal.mejoraFreezer == 3) {
      return showMejoraFreezer(
          AssetImage("assets/icons/mejoras/freezer/balls.png"),
          500,
          "Mejora Golden Freezer",
          "Freezer",
          "Mejora para ganar automáticamente 10000 zenis cada segundo.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesMejoraCelula() {
    if (Principal.mejoraCelula == 0 && Principal.celulas > 4) {
      return showMejoraCelula(
          AssetImage("assets/icons/mejoras/celula/a17.png"),
          75,
          "Mejora Celula lvl 2",
          "Celula",
          "Mejora para ganar automáticamente 500 zenis cada segundo.");
    } else if (Principal.mejoraCelula == 1) {
      return showMejoraCelula(
          AssetImage("assets/icons/mejoras/celula/a18.png"),
          100,
          "Mejora Cel Perfecto",
          "Celula",
          "Mejora para ganar automáticamente 3000 zenis cada segundo.");
    } else if (Principal.mejoraCelula == 2) {
      return showMejoraCelula(
          AssetImage("assets/icons/mejoras/celula/cell.png"),
          300,
          "Mejora Super Cel Perfeto",
          "Celula",
          "Mejora para ganar automáticamente 12000 zenis cada segundo.");
    } else {
      return Container();
    }
  }

  Widget comprobacionesMejoraBuu() {
    if (Principal.mejoraBuu == 0 && Principal.buus > 4) {
      return showMejoraBuu(
          AssetImage("assets/icons/mejoras/buu/dabra.png"),
          75,
          "Mejora Buu Pura maldad",
          "Buu",
          "Mejora para ganar automáticamente 5000 zenis cada segundo.");
    } else if (Principal.mejoraBuu == 1) {
      return showMejoraBuu(
          AssetImage("assets/icons/mejoras/buu/cinturon.png"),
          100,
          "Mejora Super Buu",
          "Buu",
          "Mejora para ganar automáticamente 15000 zenis cada segundo.");
    } else if (Principal.mejoraBuu == 2) {
      return showMejoraBuu(
          AssetImage("assets/icons/mejoras/buu/fantasma.png"),
          300,
          "Mejora Buu pequeño",
          "Buu",
          "Mejora para ganar automáticamente 50000 zenis cada segundo.");
    } else {
      return Container();
    }
  }

//Mensaje de eror por no tener suficientes zenis para comprar
  void mensajeError(BuildContext context, String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      content: Text(
        mensaje,
        textAlign: TextAlign.center,
      ),
      backgroundColor: Colors.red,
    ));
  }

  //Mensaje de logro conseguido
  void mensaje(BuildContext context) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      content: Text(
        "NUEVO LOGRO CONSEGUIDO",
        style: TextStyle(color: Colors.black),
        textAlign: TextAlign.center,
      ),
      backgroundColor: Colors.yellow,
    ));
  }

  //Función de los Logors, cuando esta se cumpla se mostrará los logros
  logroGok() {
    if (Principal.gokus == 5 && !logroGokus) {
      mensaje(context);
      logroGokus = true;
    } else if (Principal.gokus == 10 && !logroGokus) {
      mensaje(context);
      logroGokus = true;
    } else if (Principal.gokus == 50 && !logroGokus) {
      mensaje(context);
      logroGokus = true;
    }

    if (Principal.gokus != 5 &&
        Principal.gokus != 10 &&
        Principal.gokus != 50) {
      logroGokus = false;
    } else {
      logroGokus = true;
    }
  }

  logroVege() {
    if (Principal.vegetas == 5 && !logroVegeta) {
      mensaje(context);
      logroVegeta = true;
    } else if (Principal.vegetas == 10 && !logroVegeta) {
      mensaje(context);
      logroGokus = true;
    } else if (Principal.vegetas == 50 && !logroVegeta) {
      mensaje(context);
      logroVegeta = true;
    }

    if (Principal.vegetas != 5 &&
        Principal.vegetas != 10 &&
        Principal.vegetas != 50) {
      logroVegeta = false;
    } else {
      logroVegeta = true;
    }
  }

  logroGoh() {
    if (Principal.gohans == 5 && !logroGohan) {
      mensaje(context);
      logroGohan = true;
    } else if (Principal.gokus == 10 && !logroGohan) {
      mensaje(context);
      logroGohan = true;
    } else if (Principal.gokus == 50 && !logroGohan) {
      mensaje(context);
      logroGohan = true;
    }

    if (Principal.gohans != 5 &&
        Principal.gohans != 10 &&
        Principal.gohans != 50) {
      logroGohan = false;
    } else {
      logroGohan = true;
    }
  }

  logroTRu() {
    if (Principal.trunks == 5 && !logroTrunk) {
      mensaje(context);
      logroTrunk = true;
    } else if (Principal.trunks == 10 && !logroTrunk) {
      mensaje(context);
      logroTrunk = true;
    } else if (Principal.trunks == 50 && !logroTrunk) {
      mensaje(context);
      logroTrunk = true;
    }

    if (Principal.trunks != 5 &&
        Principal.trunks != 10 &&
        Principal.trunks != 50) {
      logroTrunk = false;
    } else {
      logroTrunk = true;
    }
  }

  logroFrez() {
    if (Principal.freezers == 5 && !logroFrezzer) {
      mensaje(context);
      logroFrezzer = true;
    } else if (Principal.freezers == 10 && !logroFrezzer) {
      mensaje(context);
      logroFrezzer = true;
    } else if (Principal.freezers == 50 && !logroFrezzer) {
      mensaje(context);
      logroFrezzer = true;
    }

    if (Principal.freezers != 5 &&
        Principal.freezers != 10 &&
        Principal.freezers != 50) {
      logroFrezzer = false;
    } else {
      logroFrezzer = true;
    }
  }

  logroCel() {
    if (Principal.celulas == 5 && !logroCelula) {
      mensaje(context);
      logroCelula = true;
    } else if (Principal.celulas == 10 && !logroCelula) {
      mensaje(context);
      logroCelula = true;
    } else if (Principal.celulas == 50 && !logroCelula) {
      mensaje(context);
      logroCelula = true;
    }

    if (Principal.celulas != 5 &&
        Principal.celulas != 10 &&
        Principal.celulas != 50) {
      logroCelula = false;
    } else {
      logroCelula = true;
    }
  }

  logroB() {
    if (Principal.buus == 5 && !logroBuu) {
      mensaje(context);
      logroBuu = true;
    } else if (Principal.buus == 10 && !logroBuu) {
      mensaje(context);
      logroBuu = true;
    } else if (Principal.buus == 50 && !logroBuu) {
      mensaje(context);
      logroBuu = true;
    }

    if (Principal.buus != 5 && Principal.buus != 10 && Principal.buus != 50) {
      logroBuu = false;
    } else {
      logroGokus = true;
    }
  }
}
