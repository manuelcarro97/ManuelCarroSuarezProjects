
// ignore_for_file: prefer_const_constructors, annotate_overrides

import 'package:audioplayers/audioplayers.dart';
import 'package:clicker_v2/bola.dart';
import 'package:flutter/material.dart';


class Logros extends StatefulWidget {
  Principal2 createState() => Principal2();
}

class Principal2 extends State<Logros> {

//Atributos de Logros (cuando sean true se muestran)
static bool milmonedas= false;
static bool cienmilmonedas= false;
static bool millonmonedas= false;
static bool comprarMejoras= false;



  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image:AssetImage("assets/icons/logros.png"), 
            // opacity: 0.8,
            fit: BoxFit.fill,
          ),
        ),
        child: ListView(
        children:[ Column(     
                  children: [   
                //Mostramos linea por linea los tipos de logros           
                      Row(
                        children: [
                          Principal.clicks<100?logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Clicks", "Clickar 100 veces la Bola de Dragon"): logro(  "assets/imagenesMedallas/Mono.png" ,"Terricola", "Clickar 100 veces la Bola de Dragon"),
                          Principal.clicks<1000?logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Clicks", "Clickar 1.000 veces la Bola de Dragon"):logro(  "assets/imagenesMedallas/Mrpopo.png" ,"Super Saiyan", "Clickar 1.000 veces la Bola de Dragon"),
                          Principal.clicks<10000?logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Clicks", "Clickar 10.000 veces la Bola de Dragon"):logro(  "assets/imagenesMedallas/Korin.png" ,"Dios de la Destrucción", "Clickar 10.000 veces la Bola de Dragon"),
                          
                        ],
                      ) ,
                      Row(
                        children: [
                         Principal.clicksbonus<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Bonus", "Clicka al Maestro Roshi 10 veces"):logro(  "assets/imagenesMedallas/logroRoshi1.png" ,"Terricola", "Clicka al Maestro Roshi 10 veces"),
                         Principal.clicksbonus<25? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Bonus", "Clicka al Maestro Roshi 25 veces"):logro(  "assets/imagenesMedallas/logroRoshi2.png" ,"Super Saiyan", "Clicka al Maestro Roshi 25 veces"),
                         Principal.clicksbonus<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Bonus", "Clicka al Maestro Roshi 50 veces"):logro(  "assets/imagenesMedallas/logroRoshi3.png" ,"Dios de la Destrucción", "Clicka al Maestro Roshi 50 veces"),
                          
                        ],
                      ) ,
                      Row(
                        children: [
                          !milmonedas? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Monedas", "Consigue 1000 zenis"):logro(  "assets/imagenesMedallas/logroBaba1.png" ,"Terricola", "Consigue 1.000 zenis"),
                          !cienmilmonedas? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Monedas", "Consigue 100.000 zenis "):logro(  "assets/imagenesMedallas/logroBaba2.png" ,"Super Saiyan", "Consigue 100.000 zenis"),
                          !millonmonedas? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Monedas", "Consigue 1.000.000 zenis"):logro(  "assets/imagenesMedallas/logroBaba3.png" ,"Dios de la Destrucción", "Consigue 1.000.000 zenis"),
                        ],
                      ) ,
                      Row(
                        children: [
                         Principal.mejoraBola!=7 ? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logros Compras Totales", "Consigue todas las mejoras de las Bolas de Dragon"):logro(  "assets/imagenesMedallas/logroCompraTotalBolas.png" ,"Terricola", "Consigue todas las mejoras de las Bolas de Dragon"),
                         Principal.buus<1? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logros Compras Totales", "Consigue todos los personajes al menos 1 vez"):logro(  "assets/imagenesMedallas/logroCompraTotalPersonajes.png" ,"Logros Compras Totales", "Consigue todos los personajes al menos 1 vez"),
                         !comprarMejoras? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logros Compras Totales", "Consigue todas las mejoras de personajes"):logro(  "assets/imagenesMedallas/logroCompraTotalMejoras.png" ,"Logros Compras Totales", "Consigue todas las mejoras de personajes"),
                          
                        ],
                      ) ,
                    Divider(),
                    Row(
                        children: [
                         
                         Principal.gokus<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Goku", "Compra 5 Gokus"): logro(  "assets/imagenesMedallas/logroGoku1.png" ,"Logro Goku", "Compra 5 Gokus"),
                         Principal.gokus<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Goku", "Compra 10 Gokus"): logro(  "assets/imagenesMedallas/logroGoku2.png" ,"Logro Gokus", "Compra 10 Gokus"),
                         Principal.gokus<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Goku", "Compra 50 Gokus"): logro(  "assets/imagenesMedallas/logroGoku3.png" ,"Logro Goku", "Compra 50 Gokus"),
                         
                        ],
                      ) ,
                      Row(
                        children: [
                         
                         Principal.vegetas<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Vegeta", "Compra 5 Vegeta"): logro(  "assets/imagenesMedallas/logroVegeta1.png" ,"Compra 5 Vegeta", "Compra 5 Vegeta"),
                         Principal.vegetas<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Vegeta", "Compra 10 Vegeta"): logro(  "assets/imagenesMedallas/logroVegeta2.png" ,"Compra 5 Vegeta", "Compra 10 Vegeta"),
                         Principal.vegetas<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Vegeta", "Compra 50 Vegeta"): logro(  "assets/imagenesMedallas/logroVegeta3.png" ,"Compra 5 Vegeta", "Compra 50 Vegeta"),
                         
                        ],
                      ) ,
                      Row(
                        children: [
                         
                         Principal.gohans<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Gohan", "Compra 5 Gohan"): logro(  "assets/imagenesMedallas/logroGohan1.png" ,"Logro Gohan", "Compra 5 Gohan"),
                         Principal.gohans<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Gohan", "Compra 10 Gohan"): logro(  "assets/imagenesMedallas/logroGohan3.png" ,"Logro Gohan", "Compra 10 Gohan"),
                         Principal.gohans<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Gohan", "Compra 50 Gohan"): logro(  "assets/imagenesMedallas/logroGohan2.png" ,"Logro Gohan", "Compra 50 Gohan"),
                         
                        ],
                      ) ,
                      Row(
                        children: [
                         
                         Principal.trunks<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Trunks", "Compra 5 Trunks"): logro(  "assets/imagenesMedallas/logroTrunks1.png" ,"Logro Trunks", "Compra 5 Trunks"),
                         Principal.trunks<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Trunks", "Compra 10 Trunks"): logro(  "assets/imagenesMedallas/logroTrunks2.png" ,"Logro Trunks", "Compra 10 Trunks"),
                         Principal.trunks<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Trunks", "Compra 50 Trunks"): logro(  "assets/imagenesMedallas/logroTrunks3.png" ,"Logro Trunks", "Compra 50 Trunkss"),
                         
                        ],
                      ) ,
                      Row(
                        children: [
                         
                         Principal.freezers<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Freezer", "Compra 5 Freezer"): logro(  "assets/imagenesMedallas/logroFreezer1.png" ,"Logro Freezer", "Compra 5 Freezer"),
                         Principal.freezers<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Freezer", "Compra 10 Freezer"): logro(  "assets/imagenesMedallas/logroFreezer2.png" ,"Logro Freezer", "Compra 10 Freezer"),
                         Principal.freezers<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Freezer", "Compra 50 Freezer"): logro(  "assets/imagenesMedallas/logroFreezer3.png" ,"Logro Freezer", "Compra 50 Freezer"),
                         
                        ],
                      ) ,
                      Row(
                        children: [
                         
                         Principal.celulas<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Celula", "Compra 5 Celula"): logro(  "assets/imagenesMedallas/logroCell1.png" ,"Logro Celula", "Compra 5 Celula"),
                         Principal.celulas<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Celula", "Compra 10 Celula"): logro(  "assets/imagenesMedallas/logroCell2.png" ,"Logro Celula", "Compra 10 Celula"),
                         Principal.celulas<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Celula", "Compra 50 Celula"): logro(  "assets/imagenesMedallas/logroCell3.png" ,"Logro Celula", "hCompra 50 Celula"),
                         
                        ],
                      ) ,
                      Row(
                        children: [
                         
                         Principal.buus<5? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Buu", "Compra 5 Buu"): logro(  "assets/imagenesMedallas/logroBuu1.png" ,"Logro Buu", "Compra 5 Buu"),
                         Principal.buus<10? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Buu", "Compra 10 Buu"): logro(  "assets/imagenesMedallas/logroBuu2.png" ,"Logro Buu", "Compra 10 Buu"),
                         Principal.buus<50? logro(  "assets/imagenesMedallas/logroBloqueado.png" ,"Logro Buu", "Compra 50 Buu"): logro(  "assets/imagenesMedallas/logroBuu3.png" ,"Logro Buu", "Compra 50 Buu"),
                         
                        ],
                      ) ,
                   
                  ],
                ),]
        )     
      ),

  //Navigation Bar (permite movernos entre ventanas)
  bottomNavigationBar: BottomNavigationBar(
        onTap: (i) {
          if (i == 0) {
            mostrarClicker(context);
          }
          if (i == 1) {
            mostrarMejoras(context);
          }
        },
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
              "assets/icons/upgrade_icon_off.png",
              width: 25,
            ),
            label: 'MEJORAS',
          ),
          BottomNavigationBarItem(
            icon: Image.asset(
              "assets/icons/logros_icon.png",
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
  
 
  //Función para mostrar los logros
  Widget logro(String icono, String logro , String info){

    return InkWell(
              child: Container(margin: EdgeInsets.only(left: 20, top: 20, bottom: 20), height: 100,
                child: Image(image: AssetImage(icono)),
              ),
              onTap: () {
                mostrarInfo(context,logro , info);
               },
            );

  }

  //funcion para reproducir música en la ventana Logros

  initState() {
    
    musicaFondo("music/logros.mp3");
  }

  AudioPlayer player = AudioPlayer();

  musicaFondo(String rutaM){

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
  
  //Muestra la infromaciónd e cada logro con un showDialog
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


  
  
  
  }
    
    