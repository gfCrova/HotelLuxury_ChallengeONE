package com.hotel.Controllers;

import java.time.LocalDate;
import java.util.List;

import com.hotel.DAOS.ReservasDAO;
import com.hotel.Entities.Reservas;
import com.hotel.Factory.ConnectionFactory;

public class ReservasController {
	
	private final ReservasDAO reservas;

	public ReservasController() {
		this.reservas = new ReservasDAO(new ConnectionFactory().recuperateConnection());
	}

	public List<Reservas> listar() {
		return reservas.listarReservas();
	}
	
	public void guardar(Reservas reserva) {
    	reservas.agregarReserva(reserva);
	}

	public int modificar(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaDePago)  {
		return reservas.modificarReserva(id, fechaEntrada, fechaSalida, valor,  formaDePago);
	}

	public int eliminar(Integer id) {
		return reservas.eliminarReserva(id);
	}

	public List<Reservas> buscarReserva(String clave) {
		return reservas.buscarReserva(clave);
	}
}
