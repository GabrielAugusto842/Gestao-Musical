package br.senai.sp.forms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.senai.sp.banco.ConexaoMySql;
import br.senai.sp.cantor.Cantor;
import br.senai.sp.dao.CantorDAO;
import br.senai.sp.dao.MusicaDAO;
import br.senai.sp.musica.Musica;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class CadastroDeMusica extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTituloDaCancao;
	private JTextField txtAnoDeComposicao;
	private JTextField txtDuracao;
	private JLabel lblNada;
	private JComboBox cbIdioma;
	private JComboBox cbCantorbanda;
	private ButtonGroup bgEstilo;
	private JButton btnSalvar_1;
	private DefaultTableModel modeloTab;
	private JPanel painelTabela;
	private JRadioButton rbRock;
	private JRadioButton rbMpb;
	private JRadioButton rbSamba;
	private int id;

	public CadastroDeMusica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBackground(Color.WHITE);
		panelTitulo.setBounds(0, 0, 708, 94);
		contentPane.add(panelTitulo);

		JLabel lblCadastroDeMusica = new JLabel("Cadastro de Can\u00E7\u00F5es");
		lblCadastroDeMusica
				.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/musica.png")));
		lblCadastroDeMusica.setForeground(Color.BLUE);
		lblCadastroDeMusica.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCadastroDeMusica.setBounds(10, 11, 561, 72);
		panelTitulo.add(lblCadastroDeMusica);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 105, 688, 368);
		contentPane.add(tabbedPane);

		JPanel painelCancoes = new JPanel();
		tabbedPane.addTab("Lista de Can\u00E7\u00F5es", null, painelCancoes, null);
		painelCancoes.setLayout(null);

		painelTabela = new JPanel();
		painelTabela.setBorder(
				new TitledBorder(null, "Can\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		painelTabela.setBounds(0, 0, 683, 237);
		painelCancoes.add(painelTabela);
		painelTabela.setLayout(null);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setBounds(10, 248, 663, 81);
		painelCancoes.add(painelBotoes);
		painelBotoes.setLayout(null);

		JButton btnAdicionar_1 = new JButton("");
		btnAdicionar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Limpar();
				tabbedPane.setSelectedIndex(1);
				lblNada.setText("Adicionar");

			}
		});
		btnAdicionar_1.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/adicionar.png")));
		btnAdicionar_1.setBounds(10, 11, 56, 52);
		painelBotoes.add(btnAdicionar_1);

		JButton btnExcluir_1 = new JButton("");
		btnExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta;
				resposta = JOptionPane.showConfirmDialog(null,
						"Deseja excluir a música " + txtTituloDaCancao.getText() + "?");
				if (resposta == 0) {
					MusicaDAO musicaDao = new MusicaDAO();
					musicaDao.excluir(id);
				}
				MontarTabela();
				Limpar();
			}
		});
		btnExcluir_1.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/Excluir1.png")));
		btnExcluir_1.setBounds(84, 11, 56, 52);
		painelBotoes.add(btnExcluir_1);

		JButton btnAlterar_1 = new JButton("");
		btnAlterar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreencherCampos(id);
				tabbedPane.setSelectedIndex(1);
				lblNada.setText("Alterar");

			}
		});
		btnAlterar_1.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/alterar.png")));
		btnAlterar_1.setBounds(161, 11, 56, 52);
		painelBotoes.add(btnAlterar_1);

		JButton btnSair_1 = new JButton("");
		btnSair_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair_1.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/Sair.png")));
		btnSair_1.setBounds(597, 11, 56, 52);
		painelBotoes.add(btnSair_1);

		JPanel painelDetalhes = new JPanel();
		tabbedPane.addTab("Detalhes da Can\u00E7\u00E3o", null, painelDetalhes, null);
		painelDetalhes.setLayout(null);

		JPanel painelDados = new JPanel();
		painelDados.setBorder(new TitledBorder(null, "Dados da Can\u00E7\u00E3o", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLUE));
		painelDados.setBounds(10, 11, 663, 193);
		painelDetalhes.add(painelDados);
		painelDados.setLayout(null);

		JLabel lblTituloDaCano = new JLabel("Titulo da can\u00E7\u00E3o:");
		lblTituloDaCano.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTituloDaCano.setBounds(10, 22, 108, 26);
		painelDados.add(lblTituloDaCano);

		JLabel lblAnoDeComposicao = new JLabel("Ano de composi\u00E7\u00E3o:");
		lblAnoDeComposicao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnoDeComposicao.setBounds(343, 22, 130, 26);
		painelDados.add(lblAnoDeComposicao);

		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdioma.setBounds(506, 22, 62, 26);
		painelDados.add(lblIdioma);

		txtTituloDaCancao = new JTextField();
		txtTituloDaCancao.setBounds(10, 48, 308, 26);
		painelDados.add(txtTituloDaCancao);
		txtTituloDaCancao.setColumns(10);

		txtAnoDeComposicao = new JTextField();
		txtAnoDeComposicao.setBounds(343, 49, 130, 25);
		painelDados.add(txtAnoDeComposicao);
		txtAnoDeComposicao.setColumns(10);

		cbIdioma = new JComboBox();
		cbIdioma.setModel(new DefaultComboBoxModel(new String[] { "Mandarim", "Hindi", "Ingl\u00EAs", "Espanhol",
				"\u00C1rabe", "Portugu\u00EAs", "Bengal\u00EAs", "Russo", "Franc\u00EAs", "Japon\u00EAs" }));
		cbIdioma.setBounds(506, 51, 120, 23);
		painelDados.add(cbIdioma);

		JLabel lblCantorbanda = new JLabel("Cantor/Banda:");
		lblCantorbanda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantorbanda.setBounds(10, 92, 108, 26);
		painelDados.add(lblCantorbanda);

		cbCantorbanda = new JComboBox();
		getComboBox();
		cbCantorbanda.setBounds(10, 117, 308, 31);
		painelDados.add(cbCantorbanda);

		JLabel lblDuracao = new JLabel("Dura\u00E7\u00E3o:");
		lblDuracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDuracao.setBounds(343, 92, 120, 26);
		painelDados.add(lblDuracao);

		txtDuracao = new JTextField();
		txtDuracao.setBounds(343, 120, 86, 25);
		painelDados.add(txtDuracao);
		txtDuracao.setColumns(10);

		JLabel lblEstiloMusical = new JLabel("Estilo Musical:");
		lblEstiloMusical.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstiloMusical.setBounds(473, 95, 104, 20);
		painelDados.add(lblEstiloMusical);

		rbRock = new JRadioButton("Rock");
		rbRock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbRock.setBounds(463, 119, 62, 23);
		painelDados.add(rbRock);
		rbRock.setActionCommand("Rock");

		rbMpb = new JRadioButton("MPB");
		rbMpb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbMpb.setBounds(527, 119, 51, 23);
		painelDados.add(rbMpb);
		rbMpb.setActionCommand("MPB");

		rbSamba = new JRadioButton("Samba");
		rbSamba.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbSamba.setBounds(589, 119, 68, 23);
		painelDados.add(rbSamba);
		rbSamba.setActionCommand("Samba");

		bgEstilo = new ButtonGroup();
		bgEstilo.add(rbRock);
		bgEstilo.add(rbMpb);
		bgEstilo.add(rbSamba);

		JPanel painelBotao = new JPanel();
		painelBotao.setBounds(10, 215, 663, 114);
		painelDetalhes.add(painelBotao);
		painelBotao.setLayout(null);

		btnSalvar_1 = new JButton("");
		btnSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (lblNada.getText().equals("Adicionar")) {
					Musica musica = new Musica();
					musica.setTitulo(txtTituloDaCancao.getText());
					musica.setAnoDeComposicao(Integer.parseInt(txtAnoDeComposicao.getText()));
					musica.setIdioma(String.valueOf(cbIdioma.getSelectedItem()));
					musica.setCantor(String.valueOf(cbCantorbanda.getSelectedItem()));
					musica.setDuracao(txtDuracao.getText());
					musica.setEstiloMusical(String.valueOf(bgEstilo.getSelection().getActionCommand()));

					MusicaDAO musicaDAO = new MusicaDAO();
					musicaDAO.gravar(musica);

					Limpar();
					tabbedPane.setSelectedIndex(0);
					lblNada.setText("");

					MontarTabela();
				}else if (lblNada.getText().equals("Alterar")){
					
					Musica musica = new Musica();
					musica.setTitulo(txtTituloDaCancao.getText());
					musica.setAnoDeComposicao(Integer.parseInt(txtAnoDeComposicao.getText()));
					musica.setIdioma(String.valueOf(cbIdioma.getSelectedItem()));
					musica.setCantor(String.valueOf(cbCantorbanda.getSelectedItem()));
					musica.setDuracao(txtDuracao.getText());
					musica.setEstiloMusical(String.valueOf(bgEstilo.getSelection().getActionCommand()));
					musica.setId(id);
					
					MusicaDAO musicaDAO = new MusicaDAO();
					musicaDAO.Alterar(musica);
					
					Limpar();
					tabbedPane.setSelectedIndex(0);
					lblNada.setText("");
					MontarTabela();
				}
			}
		});
		btnSalvar_1.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/Salvar.png")));
		btnSalvar_1.setBounds(431, 39, 106, 64);
		painelBotao.add(btnSalvar_1);

		JButton btnCancelar_1 = new JButton("");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNada.setText("");
				Limpar();
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnCancelar_1.setIcon(new ImageIcon(CadastroDeMusica.class.getResource("/br/senai/sp/imagens/cancelar.png")));
		btnCancelar_1.setBounds(547, 39, 106, 64);
		painelBotao.add(btnCancelar_1);

		lblNada = new JLabel("");
		lblNada.setForeground(Color.BLUE);
		lblNada.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNada.setBounds(10, 24, 185, 44);
		painelBotao.add(lblNada);
		MontarTabela();
	}

	public void Limpar() {
		txtTituloDaCancao.setText("");
		txtAnoDeComposicao.setText("");
		cbCantorbanda.setSelectedItem(null);
		cbIdioma.setSelectedItem(null);
		txtDuracao.setText("");
		bgEstilo.clearSelection();
	}

	public void MontarTabela() {
		painelTabela.removeAll();
		ArrayList<Musica> listaMusica = new ArrayList<>();
		MusicaDAO musicaDao = new MusicaDAO();
		listaMusica = musicaDao.ExibirMusica();

		modeloTab = new DefaultTableModel();
		table = new JTable(modeloTab);

		modeloTab.addColumn("ID");
		modeloTab.addColumn("Titulo da Musica");
		modeloTab.addColumn("Cantor");
		modeloTab.addColumn("Estilo");

		for (Musica musica : listaMusica) {
			modeloTab.addRow(
					new Object[] { musica.getId(), musica.getTitulo(), musica.getCantor(), musica.getEstiloMusical() });
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 663, 204);
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
				// TODO Auto-generated method stub
				id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
				PreencherCampos(id);
			}
		});

	}

	public void getComboBox() {
		MusicaDAO musicaDao = new MusicaDAO();
		musicaDao.SetComboBox(cbCantorbanda);
	}

	public void PreencherCampos(int id) {
		Musica musica = new Musica();
		MusicaDAO musicaDao = new MusicaDAO();
		musica = musicaDao.getMusicaById(id);

		txtTituloDaCancao.setText(musica.getTitulo());
		txtAnoDeComposicao.setText(String.valueOf(musica.getAnoDeComposicao()));
		cbIdioma.setSelectedItem(musica.getIdioma());
		cbCantorbanda.setSelectedItem(musica.getCantor());
		txtDuracao.setText(musica.getDuracao());
		
		if (musica.getEstiloMusical().equals("Rock")) {
			rbRock.setSelected(true);
		} else if (musica.getEstiloMusical().equals("MPB")) {
			rbMpb.setSelected(true);
		} else {
			rbSamba.setSelected(true);
		}
	}

}
