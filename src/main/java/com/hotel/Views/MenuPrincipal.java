package com.hotel.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("src/main/resources/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		
		Panel panel = new Panel();
		panel.setBackground(new Color(19,16,15)); // new Color(119,136,153)
		panel.setBounds(0, 0, 910, 537);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(-55, 13, 732, 501);
		imagenFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("src/main/resources/imagenes/hotel-luxury.jpg")));
		panel.add(imagenFondo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 491, 910, 46);
		panel_1.setBackground(SystemColor.BLACK);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCopyR = new JLabel("Gian Franco Crova © 2023");
		lblCopyR.setBounds(700, 14, 284, 19);
		lblCopyR.setForeground(new Color(229,183,59));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel_1.add(lblCopyR);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("src/main/resources/imagenes/luxury-40px.png")));
		lblNewLabel_1.setBounds(15, 2, 40, 40);
		panel_1.add(lblNewLabel_1);
		
		//Barra para controlar la ventana 
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);
		
		//Botón salir
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		//Botón Login
		JPanel btnLogin = new JPanel(); 
		btnLogin.setBounds(745, 325, 100, 100);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(new Color(19,16,15));
		panel.add(btnLogin);
		
		JLabel imagenLogin = new JLabel("");
		imagenLogin.setBounds(0, 0, 100, 100);
		btnLogin.add(imagenLogin);
		imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("src/main/resources/imagenes/login-blue.png")));
		
		JLabel lblTitulo = new JLabel("LOGIN");
		lblTitulo.setBounds(755, 280, 83, 24);
		lblTitulo.setBackground(new Color(19,16,15));
		panel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(229,183,59));
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 19));
		
		JLabel logoHeader = new JLabel("Hotel Luxury - Challenge Alura");
		logoHeader.setForeground(SystemColor.WHITE);
		logoHeader.setBounds(0, 0, 250, 35);
		logoHeader.setHorizontalAlignment(SwingConstants.CENTER);
		logoHeader.setIcon(new ImageIcon(MenuPrincipal.class.getResource("src/main/resources/imagenes/stars.png")));
		header.add(logoHeader);
	
	    JLabel logoPanel = new JLabel();
	    logoPanel.setBounds(660, 35, 250, 205);
	    logoPanel.setHorizontalAlignment(SwingConstants.CENTER);
	    logoPanel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("src/main/resources/imagenes/logo-luxury.png")));
		panel.add(logoPanel);
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
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
