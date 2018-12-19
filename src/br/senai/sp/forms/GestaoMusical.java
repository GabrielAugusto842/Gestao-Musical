package br.senai.sp.forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class GestaoMusical extends JFrame {

	private JPanel contentPane;

	public GestaoMusical() {
		setTitle("Gest\u00E3o Musical");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 610, 104);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cadastro de m\u00FAsica");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setIcon(new ImageIcon(GestaoMusical.class.getResource("/br/senai/sp/imagens/headphones.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 551, 71);
		panel_1.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(15, 118, 571, 285);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnCadastrarCantor = new JButton("Cadastrar Cantor");
		btnCadastrarCantor.setIcon(new ImageIcon(GestaoMusical.class.getResource("/br/senai/sp/imagens/cantor.png")));
		btnCadastrarCantor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				CadastroCantor cantor = new CadastroCantor();
				cantor.setVisible(true);
				/*JFrame GestaoMusical.setExtendedState(JFrame.ICONIFIED);*/
				
			}
		});
		btnCadastrarCantor.setBounds(59, 88, 195, 87);
		panel.add(btnCadastrarCantor);

		JButton btnCadastrarMusica = new JButton("Cadastrar Can\u00E7\u00E3o");
		btnCadastrarMusica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				CadastroDeMusica musica = new CadastroDeMusica();
				musica.setVisible(true);
			}
		});
		btnCadastrarMusica.setIcon(new ImageIcon(GestaoMusical.class.getResource("/br/senai/sp/imagens/musica.png")));
		btnCadastrarMusica.setBounds(303, 88, 195, 87);
		panel.add(btnCadastrarMusica);
		
		JButton btnFechar = new JButton("");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sair();
			}
		});
		btnFechar.setIcon(new ImageIcon(GestaoMusical.class.getResource("/br/senai/sp/imagens/Sair.png")));
		btnFechar.setBounds(496, 224, 65, 50);
		panel.add(btnFechar);
	}
	
	public void Sair() {
		int resposta;
		
		resposta = JOptionPane.showConfirmDialog(null, "Deseja fechar esse programa?");
		
		if (resposta==0) {
			System.exit(0);
		}
	}
	
}
