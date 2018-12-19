package br.senai.sp.forms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import br.senai.sp.cantor.Cantor;
import br.senai.sp.dao.CantorDAO;
import br.senai.sp.dao.MusicaDAO;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class CadastroCantor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNomeDoCantor;
	private JTextField txtCidadeNatal;
	private JTextField txtDataDeNascimento;
	private ButtonGroup bgEstilo;
	private JRadioButton rbRock;
	private JRadioButton rbMpb;
	private JRadioButton rbSamba;
	private JLabel lblNada;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private DefaultTableModel modeloTab;
	private JPanel painelTabela;
	private int id;

	public CadastroCantor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel painelTitulo_1 = new JPanel();
		painelTitulo_1.setBackground(Color.WHITE);
		painelTitulo_1.setBounds(0, 0, 664, 88);
		contentPane.add(painelTitulo_1);
		painelTitulo_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cadastro de Cantores");
		lblNewLabel.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/cantor.png")));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 11, 397, 50);
		painelTitulo_1.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 99, 644, 335);
		contentPane.add(tabbedPane);

		JPanel painelCantores = new JPanel();
		tabbedPane.addTab("Lista de Cantores", null, painelCantores, null);
		painelCantores.setLayout(null);

		painelTabela = new JPanel();
		painelTabela.setBounds(10, 11, 619, 196);
		painelCantores.add(painelTabela);
		painelTabela.setLayout(null);

		

		JPanel painelBotoes_1 = new JPanel();
		painelBotoes_1.setBounds(10, 218, 619, 82);
		painelCantores.add(painelBotoes_1);
		painelBotoes_1.setLayout(null);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setForeground(Color.BLUE);
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnAdicionar.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/adicionar.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Limpar();
				tabbedPane.setSelectedIndex(1);
				lblNada.setText("Adicionar");
			}
		});

		
		btnAdicionar.setBounds(10, 11, 65, 60);
		painelBotoes_1.add(btnAdicionar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/Excluir1.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta;
				
				resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o cantor " + txtNomeDoCantor.getText() + " e todas as suas músicas?");
				
				if (resposta==0) {
					MusicaDAO musicaDao = new MusicaDAO();
					musicaDao.DeleteMusica(txtNomeDoCantor.getText());
					CantorDAO cantorDao = new CantorDAO();
					cantorDao.excluir(id);
					CadastroDeMusica cadastro = new CadastroDeMusica();
					cadastro.MontarTabela();
				}
				MontarTabela();
				Limpar();
			}
		});
		btnExcluir.setBounds(89, 11, 65, 60);
		painelBotoes_1.add(btnExcluir);

		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/alterar.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreencherCampos(id);
				tabbedPane.setSelectedIndex(1);
				lblNada.setText("Alterar");
			}
		});
		btnAlterar.setBounds(164, 11, 65, 60);
		painelBotoes_1.add(btnAlterar);

		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/Sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setBounds(544, 11, 65, 60);
		painelBotoes_1.add(btnSair);

		JPanel painelDetalhes_1 = new JPanel();
		tabbedPane.addTab("Detalhes dos Cantores", null, painelDetalhes_1, null);
		painelDetalhes_1.setLayout(null);

		JPanel painelDados_1 = new JPanel();
		painelDados_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Cantor:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		painelDados_1.setBounds(10, 11, 619, 174);
		painelDetalhes_1.add(painelDados_1);
		painelDados_1.setLayout(null);

		JLabel lblNomeDoCantor = new JLabel("Nome do cantor / Banda:");
		lblNomeDoCantor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoCantor.setBounds(10, 26, 200, 28);
		painelDados_1.add(lblNomeDoCantor);

		txtNomeDoCantor = new JTextField();
		txtNomeDoCantor.setBounds(10, 59, 599, 28);
		painelDados_1.add(txtNomeDoCantor);
		txtNomeDoCantor.setColumns(10);

		JLabel lblCidadeNatal = new JLabel("Cidade natal:");
		lblCidadeNatal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidadeNatal.setBounds(10, 98, 200, 28);
		painelDados_1.add(lblCidadeNatal);

		txtCidadeNatal = new JTextField();
		txtCidadeNatal.setBounds(10, 126, 193, 28);
		painelDados_1.add(txtCidadeNatal);
		txtCidadeNatal.setColumns(10);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(213, 98, 133, 28);
		painelDados_1.add(lblDataDeNascimento);

		txtDataDeNascimento = new JTextField();
		txtDataDeNascimento.setBounds(213, 126, 133, 28);
		painelDados_1.add(txtDataDeNascimento);
		txtDataDeNascimento.setColumns(10);

		JLabel lblEstiloMusical_1 = new JLabel("Estilo musical:");
		lblEstiloMusical_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstiloMusical_1.setBounds(421, 98, 163, 28);
		painelDados_1.add(lblEstiloMusical_1);

		rbRock = new JRadioButton("Rock");
		rbRock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbRock.setBounds(366, 127, 70, 23);
		painelDados_1.add(rbRock);
		rbRock.setActionCommand("Rock");

		rbMpb = new JRadioButton("MPB");
		rbMpb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbMpb.setBounds(442, 127, 70, 23);
		painelDados_1.add(rbMpb);
		rbMpb.setActionCommand("MPB");

		rbSamba = new JRadioButton("Samba");
		rbSamba.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbSamba.setBounds(514, 127, 70, 23);
		painelDados_1.add(rbSamba);
		rbSamba.setActionCommand("Samba");

		bgEstilo = new ButtonGroup();
		bgEstilo.add(rbRock);
		bgEstilo.add(rbMpb);
		bgEstilo.add(rbSamba);

		JPanel painelBotao_1 = new JPanel();
		painelBotao_1.setBounds(10, 198, 619, 99);
		painelDetalhes_1.add(painelBotao_1);
		painelBotao_1.setLayout(null);

		btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/Salvar.png")));
		btnSalvar.setBounds(421, 24, 89, 53);
		painelBotao_1.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lblNada.getText().equals("Adicionar")) {
					Cantor cantor = new Cantor();
					cantor.setNome(txtNomeDoCantor.getText());
					cantor.setCidadeNatal(txtCidadeNatal.getText());
					cantor.setNascimento(txtDataDeNascimento.getText());
					cantor.setEstiloMusical(String.valueOf(bgEstilo.getSelection().getActionCommand()));

					CantorDAO cantorDao = new CantorDAO();
					cantorDao.gravar(cantor);
					
					Limpar();
					tabbedPane.setSelectedIndex(0);
					lblNada.setText("");
					
					MontarTabela();
				} else if (lblNada.getText().equals("Alterar")) {
					Cantor cantor = new Cantor();
					cantor.setNome(txtNomeDoCantor.getText());
					cantor.setCidadeNatal(txtCidadeNatal.getText());
					cantor.setNascimento(txtDataDeNascimento.getText());
					cantor.setEstiloMusical(String.valueOf(bgEstilo.getSelection().getActionCommand()));
					cantor.setId(id);

					CantorDAO cantorDao = new CantorDAO();
					cantorDao.Alterar(cantor);
					
					Limpar();
					tabbedPane.setSelectedIndex(0);
					lblNada.setText("");
					MontarTabela();
				}
			}
		});

		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNada.setText("");
				Limpar();
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnCancelar.setIcon(new ImageIcon(CadastroCantor.class.getResource("/br/senai/sp/imagens/cancelar.png")));
		btnCancelar.setBounds(520, 24, 89, 53);
		painelBotao_1.add(btnCancelar);

		lblNada = new JLabel("");
		lblNada.setForeground(Color.BLUE);
		lblNada.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNada.setBounds(10, 24, 185, 44);
		painelBotao_1.add(lblNada);
		
		MontarTabela();
	}

	public void Limpar() {
		txtNomeDoCantor.setText("");
		txtCidadeNatal.setText("");
		txtDataDeNascimento.setText("");
		bgEstilo.clearSelection();
	}

	public void MontarTabela() {
		painelTabela.removeAll();
		ArrayList<Cantor> listaCantor = new ArrayList<>();
		CantorDAO cantorDao = new CantorDAO();
		listaCantor = cantorDao.ExibirCantor();
		
		modeloTab = new DefaultTableModel();
		table = new JTable(modeloTab);
		
		modeloTab.addColumn("ID");
		modeloTab.addColumn("Cantor/Banda");
		modeloTab.addColumn("Estilo");
		
		for (Cantor cantor : listaCantor) {
			modeloTab.addRow(new Object[] {cantor.getId(), cantor.getNome(), cantor.getEstiloMusical()});
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 599, 176);
		scrollPane.setViewportView(table);
		
		painelTabela.add(scrollPane);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
				PreencherCampos(id);
				
			}
		});
		
	}
	
	public void PreencherCampos(int id) {
		Cantor cantor = new Cantor();
		CantorDAO cantorDao = new CantorDAO();
		cantor = cantorDao.getCantorById(id);
		
		txtNomeDoCantor.setText(cantor.getNome());
		txtCidadeNatal.setText(cantor.getCidadeNatal());
		txtDataDeNascimento.setText(cantor.getNascimento());
		if (cantor.getEstiloMusical().equals("Rock")) {
			rbRock.setSelected(true);
		} else if (cantor.getEstiloMusical().equals("MPB")) {	
			rbMpb.setSelected(true);
		} else {
			rbSamba.setSelected(true);
		}
	}

}
