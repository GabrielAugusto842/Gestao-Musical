package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.senai.sp.banco.ConexaoMySql;
import br.senai.sp.cantor.Cantor;
import br.senai.sp.musica.Musica;

public class MusicaDAO {
private Connection con =  ConexaoMySql.getConexao();
	
	public void gravar(Musica musica) {
		String sql = "INSERT INTO tbl_musica" + "(id, titulo, ano_composicao, idioma, cantor, duracao, estilo_musical)" + "values (null,?,?,?,?,?,?)";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, musica.getTitulo());
			stm.setInt(2, musica.getAnoDeComposicao());
			stm.setString(3, musica.getIdioma());
			stm.setString(4, musica.getCantor());
			stm.setString(5, musica.getDuracao());
			stm.setString(6, musica.getEstiloMusical());
			
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na gravação da musica.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Musica salva com sucesso.", "Salvo",
						JOptionPane.INFORMATION_MESSAGE);
			}
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Musica> ExibirMusica() {
		ArrayList<Musica> listaMusica = new ArrayList<>();
		
		String sql = "SELECT * from tbl_musica";
		
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Musica musica = new Musica();
				musica.setId(rs.getInt("id"));
				musica.setTitulo(rs.getString("titulo"));
				musica.setAnoDeComposicao(rs.getInt("ano_composicao"));
				musica.setIdioma(rs.getString("idioma"));
				musica.setCantor(rs.getString("cantor"));
				musica.setDuracao(rs.getString("duracao"));
				musica.setEstiloMusical(rs.getString("estilo_musical"));
				listaMusica.add(musica);
			}
				
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaMusica;
	}
	public void excluir(int id) {
		String sql = "DELETE FROM tbl_musica WHERE id=?";
		
		try {
			PreparedStatement stm= con.prepareStatement(sql);
			stm.setInt(1, id);
			
			if(stm.execute()){
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar excluir a musica. \nEntre em contato com o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Musica excluida com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
			} 
			ConexaoMySql.fecharConexão();
	
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar apagar a musica. \nEntre em contato com o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	public Musica getMusicaById(int id) {
		Musica musica = new Musica();
		
		String sql = "SELECT * FROM tbl_musica WHERE id=?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				musica.setId(rs.getInt("id"));
				musica.setTitulo(rs.getString("titulo"));
				musica.setAnoDeComposicao(rs.getInt("ano_composicao"));
				musica.setIdioma(rs.getString("idioma"));
				musica.setCantor(rs.getString("cantor"));
				musica.setDuracao(rs.getString("duracao"));
				musica.setEstiloMusical(rs.getString("estilo_musical"));
				
			}
			ConexaoMySql.fecharConexão();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musica;
	}
	
	public void SetComboBox(JComboBox cb) {
		String sql = "SELECT * FROM tbl_banda_cantor";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				cb.addItem(rs.getString("nome"));
			}
			ConexaoMySql.fecharConexão();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Alterar(Musica musica){	
		String sql="UPDATE tbl_musica set " + "titulo = ?, ano_composicao = ?, idioma = ?, cantor = ?, duracao = ?, estilo_musical = ?" + "  WHERE id = ?";
	
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, musica.getTitulo());
			stm.setInt(2, musica.getAnoDeComposicao());
			stm.setString(3, musica.getIdioma());
			stm.setString(4, musica.getCantor());
			stm.setString(5, musica.getDuracao());
			stm.setString(6, musica.getEstiloMusical());
			stm.setInt(7, musica.getId());
			
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de alterar os dados da música.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Dados da música alterados com sucesso.", "ALTERAÇÃO CONCLUÍDA",
						JOptionPane.INFORMATION_MESSAGE);
			}
			ConexaoMySql.fecharConexão();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void DeleteMusica (String cantor) {
		String sql = "DELETE FROM tbl_musica where cantor = ?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cantor);
			stm.execute();
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

