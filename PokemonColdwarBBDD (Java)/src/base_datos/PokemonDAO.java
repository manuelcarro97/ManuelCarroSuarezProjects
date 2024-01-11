package base_datos;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.C01_Principal;
import clases.C09_Combate;
import interfaces.I00_Pokemon;
import interfaces.I01_PokemonNormal;
import interfaces.I02_PokemonFuego;
import interfaces.I03_PokemonAgua;
import interfaces.I04_PokemonPlanta;
import interfaces.I05_PokemonDragon;
import interfaces.I06_PokemonBicho;


public class PokemonDAO {

	private static ConnectionDB conBD;


	public static void guardarPartida(ArrayList<I00_Pokemon> pokemon){


		for (int i=0; i<pokemon.size(); i++) {
			conBD = new ConnectionDB();
			Connection con = conBD.openConnection();

			String insert = "INSERT INTO coldwar.guardarpartida (nombre, tipo, ps, pp, estado, ronda) VALUES (" + 

				"'"+ "0" + i + pokemon.get(i).getNombreEquipo() + "', " +
				"'"+ pokemon.get(i).getTipoPlaneta() + "'," +
				pokemon.get(i).getVidas()+ "," +
				pokemon.get(i).getMisilesRonda()+
				",1, " +
				C09_Combate.ronda + ")";

			Statement st = null;

			try {
				st = con.createStatement();

				st.executeUpdate(insert);

				st.close();

			} catch (SQLException e) {
				System.out.println("The Insert had problems!! " + e);

			}

			conBD.closeConnection(con);
		}
	}

	public static ArrayList<I00_Pokemon> cargarPartida (ArrayList<I00_Pokemon> pokemon){

		conBD = new ConnectionDB();
		Connection con = conBD.openConnection();

		I00_Pokemon p = null;

		String sql = "select * FROM coldwar.guardarpartida";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				int tipo = Integer.parseInt(rs.getString("tipo"));
				int ps = Integer.parseInt(rs.getString("ps"));
				int pp = Integer.parseInt(rs.getString("pp"));

				nombre = nombre.substring(2);

				if (tipo == 1) {

					pokemon.add(new I01_PokemonNormal (nombre, tipo, ps, pp));

				}else if (tipo == 2) {

					pokemon.add(new I02_PokemonFuego (nombre, tipo, ps, pp));

				}else if (tipo == 3) {

					pokemon.add(new I03_PokemonAgua (nombre, tipo, ps, pp));

				}else if (tipo == 4) {

					pokemon.add(new I04_PokemonPlanta (nombre, tipo, ps, pp));

				}else if (tipo == 5) {

					pokemon.add(new I05_PokemonDragon(nombre, tipo, ps, pp));

				}else if (tipo == 6) {

					pokemon.add(new  I06_PokemonBicho (nombre, tipo, ps, pp));

				}


			}

			st.close();

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);

		}

		conBD.closeConnection(con);
		return pokemon;

	}

	public static int borrarPartida() {

		int ronda = 0;

		conBD = new ConnectionDB();
		Connection con2 = conBD.openConnection();

		String sql2 = "select ronda FROM coldwar.guardarpartida";

		Statement st2 = null;

		try {
			st2 = con2.createStatement();

			ResultSet rs = st2.executeQuery(sql2);

			while (rs.next()) {
				ronda = Integer.parseInt(rs.getString("ronda"));

			}

			st2.close();

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);

		}

		conBD.closeConnection(con2);


		conBD = new ConnectionDB();
		Connection con = conBD.openConnection();

		String insert = "DELETE FROM coldwar.guardarpartida";

		Statement st = null;

		try {
			st = con.createStatement();

			st.executeUpdate(insert);

			st.close();

		} catch (SQLException e) {
			System.out.println("The Insert had problems!! " + e);

		}

		conBD.closeConnection(con);
		return ronda;
	}

	public static boolean comprobarBBDD(){

		String nombre="";

		conBD = new ConnectionDB();
		Connection con = conBD.openConnection();

		I00_Pokemon p = null;

		String sql = "select * FROM coldwar.guardarpartida";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				nombre = rs.getString("nombre");

			}

			st.close();

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);

		}

		conBD.closeConnection(con);

		if (nombre.equals("")) {
			return false;
		}
		else {
			return true;
		}


	}

	public static void ganadores(ArrayList<I00_Pokemon> pokemon){



		conBD = new ConnectionDB();
		Connection con = conBD.openConnection();

		String nombre="";


		String sql = "select nombre_ganador FROM coldwar.ganadores ;";

		Statement st1 = null;

		try {
			st1 = con.createStatement();

			ResultSet rs = st1.executeQuery(sql);

			while (rs.next()) {

				nombre = rs.getString("nombre_ganador");
				
				if (!pokemon.get(0).getNombreEquipo().equals(nombre)) {


					String insert = "insert into coldwar.ganadores (nombre_ganador,victorias) VALUES (" + 

							"'" + pokemon.get(0).getNombreEquipo() + "'," + 1 + ");";

					Statement st = null;

					try {
						st = con.createStatement();

						st.executeUpdate(insert);

						st.close();

					} catch (SQLException e) {
						System.out.println("The Insert had problems!! " + e);

					}

				}
				else {



					String insert = "UPDATE coldwar.ganadores set victorias=victorias+1 where nombre_ganador = '" +pokemon.get(0).getNombreEquipo()+"';";

					Statement st = null;

					try {
						st = con.createStatement();

						st.executeUpdate(insert);

						st.close();

					} catch (SQLException e) {
						System.out.println("The Insert had problems!! " + e);

					}

				}



			}

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);

		}

		conBD.closeConnection(con);

	}
	
	public static String escribir_gandores (){

		conBD = new ConnectionDB();
		Connection con = conBD.openConnection();
		
		String s ="";
		
		I00_Pokemon p = null;

		String sql = "select * FROM coldwar.ganadores order by victorias desc";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String nombre = rs.getString("nombre_ganador");
				String tipo = rs.getString("victorias");
			


				s+= (nombre + " --> " + tipo) +"\n";


			}

			st.close();

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);

		}

		conBD.closeConnection(con);
		return s;

	}


}
