package com.hotel.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hotel.Entities.Reservas;

public class ReservasDAO {
	
	Connection con;
	
	public ReservasDAO(Connection con) {
		this.con = con;
	}


	public List<Reservas> listarReservas() {

		List<Reservas> lista = new ArrayList<>();
		Reservas reserva = null;

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas");

			try (statement) {
				
				statement.execute();

				final ResultSet rs = statement.getResultSet();

				try (rs) {
					
					while (rs.next()) {
						
						int id = rs.getInt("id_reservas");
						LocalDate fechaEntrada = rs.getDate("fecha_entrada").toLocalDate();
						LocalDate fechaSalida = rs.getDate("fecha_salida").toLocalDate();
						Double valor = rs.getDouble("valor");
						String formaDePago = rs.getString("forma_de_pago");
						
						reserva = new Reservas(id, fechaEntrada, fechaSalida, valor, formaDePago);
						lista.add(reserva);
						System.out.println(reserva);
					} 
				} catch(SQLException e) {
					throw new RuntimeException(e);
				} 
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	
	
	public void agregarReserva(Reservas reserva) {

    	try {

			final PreparedStatement statement = con.prepareStatement("INSERT INTO reservas"
																+ "(fecha_entrada, fecha_salida, valor, forma_de_pago) "
																+ "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				
				statement.setObject(1, reserva.getFechaEntrada());
				statement.setObject(2, reserva.getFechaSalida());
				statement.setDouble(3, reserva.getValor());
				statement.setString(4, reserva.getFormaDePago());

				statement.execute();
				
				final ResultSet rs = statement.getGeneratedKeys();

				try (rs) {
					while(rs.next()) {
						reserva.setId(rs.getInt(1));
						System.out.println("Se asgregó una nueva reserva: " + reserva);
					}
				} catch(SQLException e) {
					throw new RuntimeException(e);
				}
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public int modificarReserva(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaDePago) {

		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET "
					+ "fecha_entrada = ? "
					+ ", fecha_salida = ? "
					+ ", valor = ? "
					+ ", forma_de_pago = ? "
					+ " WHERE id_reservas = ? ");

			try (statement) {
				
				statement.setObject(1, fechaEntrada);
				statement.setObject(2, fechaSalida);
				statement.setDouble(3, valor);
				statement.setString(4, formaDePago);
				statement.setInt(5, id);

				statement.execute();

				System.out.println("Se modificó la reserva!");
				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminarReserva(Integer id) {

		try {

			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id_reservas = ?");

			try (statement) {

				statement.setInt(1, id);
				statement.execute();

				System.out.println("Se eliminó la reserva!");
				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// BUSCAR
		public List<Reservas> buscarReserva(String clave) {
			
			List<Reservas> lista = new ArrayList<>();
			Reservas reserva;
			
			try {

				final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas WHERE "
						+ "id_reservas LIKE ? OR valor LIKE ? OR forma_de_pago LIKE ? "); // OR fecha_entrada LIKE ? OR fecha_salida LIKE ?  

				try (statement) {
					
					statement.setString(1, "%" + Integer.parseInt(clave) + "%");
					//statement.setString(2, "%" + LocalDate.parse(clave) + "%");
					//statement.setString(3, "%" + LocalDate.parse(clave) + "%");
					statement.setString(2, "%" + Double.parseDouble(clave) + "%");
					statement.setString(3, "%" + clave + "%");
					
					final ResultSet rs = statement.executeQuery();

					try (rs) {
						
						while (rs.next()) {
							
							Integer id = rs.getInt("id_reservas");
							LocalDate fechaEntrada = rs.getDate("fecha_entrada").toLocalDate();
							LocalDate fechaSalida = rs.getDate("fecha_salida").toLocalDate();
							Double valor = rs.getDouble("valor");
							String formaDePago = rs.getString("forma_de_pago");
							
							reserva = new Reservas(id, fechaEntrada, fechaSalida, valor, formaDePago);
							lista.add(reserva);
							System.out.println(reserva);
						} 
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return lista;
		}
}
