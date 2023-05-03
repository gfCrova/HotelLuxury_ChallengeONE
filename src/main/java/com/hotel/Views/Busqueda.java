package com.hotel.Views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hotel.Controllers.HuespedController;
import com.hotel.Controllers.ReservasController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedController huespedController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Busqueda() {
		
		reservasController = new ReservasController();
		huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/imagenes/lupa2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(12,13,18));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(539, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(255,191,0));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 22));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(255,191,0));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.setBounds(20, 169, 865, 328);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(255,222,173));
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 panel.setBackground(new Color(255,191,0));
			}
		});
		
		contentPane.add(panel);


		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setBackground(new Color(245,245,220));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		cargarTablaReservas();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon("src/main/resources/imagenes/reservado.png"), scroll_table, null);
		scroll_table.setVisible(true);
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setBackground(new Color(245,245,220));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		cargarTablaHuespedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon("src/main/resources/imagenes/pessoas.png"), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("src/main/resources/imagenes/luxury-100px.png"));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(new Color(8, 8, 8));
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelectedIndex() == 0){
						limpiarTablaR();
                		buscarReservas(txtBuscar.getText());
                } else if(panel.getSelectedIndex() == 1) {
						limpiarTablaH();
						buscarHuespedes(txtBuscar.getText());
                }
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnbuscar.setBackground(new Color(255,222,173));				
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnbuscar.setBackground(new Color(255,191,0));			    
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(255,191,0));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(255,191,0));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                int numeroPanel = panel.getSelectedIndex();
                if(numeroPanel == 0) {
                	modificarReserva();
                	limpiarTablaR();
                	cargarTablaReservas();
                } else if(numeroPanel == 1){
                	modificarHuesped();
                	limpiarTablaH();
                	cargarTablaHuespedes();
                }
            }
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(255,222,173));				
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(new Color(255,191,0));			    
			}
		});
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.BLACK);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(255,191,0));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                int numeroPanel = panel.getSelectedIndex();
                if(numeroPanel == 0) {
                	eliminarReserva();
                	limpiarTablaR();
                	cargarTablaReservas();
                } else if(numeroPanel == 1){
                	eliminarHuesped();
                	limpiarTablaH();
                	cargarTablaHuespedes();
                }
            }
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(255,222,173));				
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(new Color(255,191,0));			    
			}
        });
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.BLACK);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		// Logo header
	    JLabel logoHeader = new JLabel("Hotel Luxury - Challenge Alura");
		logoHeader.setForeground(SystemColor.WHITE);
		logoHeader.setBounds(0, 0, 350, 35);
		logoHeader.setHorizontalAlignment(SwingConstants.CENTER);
		logoHeader.setIcon(new ImageIcon("src/main/resources/imagenes/stars.png"));
		header.add(logoHeader);
	}
	
	private void limpiarTablaR() {
        modelo.getDataVector().clear();
    }
	
	private void limpiarTablaH() {
        modeloHuesped.getDataVector().clear();
    }
	
	private void cargarTablaReservas() {
        var reservas = this.reservasController.listar();
        reservas.forEach(reserva -> modelo.addRow(
        		new Object[]{ 
        				reserva.getId(), 
        				reserva.getFechaEntrada(), 
        				reserva.getFechaSalida(),
        				reserva.getValor(),
        				reserva.getFormaDePago()
	        		}
        		));
    }
	
	
	private void cargarTablaHuespedes() {
        var huespedes = this.huespedController.listar();
        huespedes.forEach(huesped -> modeloHuesped.addRow(
        		new Object[]{ 
        				huesped.getId(), 
        				huesped.getNombre(), 
        				huesped.getApellido(), 
        				huesped.getFechaNacimiento(),
        				huesped.getNacionalidad(),
        				huesped.getTelefono(),
        				huesped.getNumeroReservas()
	        		}
        		));
    }
	
	
	private void modificarHuesped() {
        if (filaElegidaHuesped()) {
            JOptionPane.showMessageDialog(this, "Por favor, elija un huésped");
            return;
        }

        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                    LocalDate fechaNac = LocalDate.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
                    //LocalDate fechaNac = LocalDate.parse(((JTextField)txtFechaN.getDateEditor().getUiComponent()).getText());
                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
                    Long telefono = (Long) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
					
					var filaModificadas = this.huespedController.modificar(id, nombre, apellido, fechaNac, nacionalidad, telefono);
					
					JOptionPane.showMessageDialog(this, filaModificadas + " Item modificado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

	private void modificarReserva() {
        if (filaElegidaReserva()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    LocalDate fechaEntrada = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
                    LocalDate fechaSalida = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
                    Double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
                    String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					
					var filaModificadas = this.reservasController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
					
					JOptionPane.showMessageDialog(this, filaModificadas + " Item modificado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }
	
	private void eliminarReserva() {
        if (filaElegidaReserva()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

					var idEliminado = this.reservasController.eliminar(id);

                    modelo.removeRow(tbReservas.getSelectedRow());

                    JOptionPane.showMessageDialog(this, "La reserva " + idEliminado + ", fue eliminada con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "No ha seleccionado ningún campo"));
    }
	
	private boolean filaElegidaReserva() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }
	
	
	
	private void eliminarHuesped() {
        if (filaElegidaHuesped()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

					var huespedEliminado = this.huespedController.eliminar(id);

                    modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                    JOptionPane.showMessageDialog(this, huespedEliminado + " huésped fue eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

	
	private boolean filaElegidaHuesped() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }
	
	
	private void buscarHuespedes(String clave) {
		
		if(clave.equals("")) {
			JOptionPane.showMessageDialog(null, String.format("Por favor, Ingresa un valor"));
			limpiarTablaH();
			cargarTablaHuespedes();
			
		} else {
	        var huespedes = this.huespedController.buscarHuesped(clave);
	        huespedes.forEach(huesped -> modeloHuesped.addRow(
	        		new Object[]{ 
	        				huesped.getId(), 
	        				huesped.getNombre(), 
	        				huesped.getApellido(), 
	        				huesped.getFechaNacimiento(),
	        				huesped.getNacionalidad(),
	        				huesped.getTelefono(),
	        				huesped.getNumeroReservas()
		        		}
	        		)
	        	);
		}
    }
	
	
	private void buscarReservas(String clave) {

		if(clave.equals("")) {
			JOptionPane.showMessageDialog(null, String.format("Por favor, Ingresa un valor"));
			limpiarTablaR();
			cargarTablaReservas();
			
		} else {
	        var reservas = this.reservasController.buscarReserva(clave);
	        reservas.forEach(reserva -> modelo.addRow(
	        		new Object[]{ 
	        				reserva.getId(), 
	        				reserva.getFechaEntrada(),
	        				reserva.getFechaSalida(), 
	        				reserva.getValor(),
	        				reserva.getFormaDePago()
		        		}
	        		)
	        	);
		}
    }
	

	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
