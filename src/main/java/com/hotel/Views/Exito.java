package com.hotel.Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Exito extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Exito dialog = new Exito();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Exito() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("src/main/resources/imagenes/luxury-40px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("src/main/resources/imagenes/luxury-100px.png")));
			lblNewLabel.setBounds(130, 11, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
			lblNewLabel_1.setForeground(new Color (255,191,0));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(27, 122, 322, 21);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(8, 8, 8));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(255,191,0));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();//sirve para cerrar la ventana actual
						MenuUsuario usuario = new MenuUsuario(); 
						usuario.setVisible(true);
					}
				});
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						okButton.setBackground(new Color(255,222,173));
						okButton.setForeground(SystemColor.BLACK);
					}
				
					@Override
					public void mouseExited(MouseEvent e) {
						okButton.setBackground(new Color(255,191,0));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(new Color(255,191,0));
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						cancelButton.setBackground(new Color(255,222,173));
						cancelButton.setForeground(SystemColor.BLACK);
					}
				
					@Override
					public void mouseExited(MouseEvent e) {
						cancelButton.setBackground(new Color(255,191,0));
					}
				});
			}
		}
	}

}
