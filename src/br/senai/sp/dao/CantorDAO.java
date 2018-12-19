package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sp.cantor.Cantor;
import br.senai.sp.banco.ConexaoMySql;


public class CantorDAO {
	private Connection con =  ConexaoMySql.getConexao();
	
	
	public void gravar(Cantor cantor) {
		String sql = "INSERT INTO tbl_banda_cantor" + "(id, nome, cidade_natal, nascimento, estilo_musical)" + "values (null,?,?,?,?)";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cantor.getNome());
			stm.setString(2, cantor.getCidadeNatal());
			stm.setString(3, cantor.getNascimento());
			stm.setString(4, cantor.getEstiloMusical());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na gravação do cantor.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Cantor salvo com sucesso.", "Salvo",
						JOptionPane.INFORMATION_MESSAGE);
			}
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cantor> ExibirCantor() {
		ArrayList<Cantor> listaCantor = new ArrayList<>();
		
		String sql = "SELECT * from tbl_banda_cantor";
		
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Cantor cantor = new Cantor();
				cantor.setId(rs.getInt("id"));
				cantor.setNome(rs.getString("nome"));
				cantor.setCidadeNatal(rs.getString("cidade_natal"));
				cantor.setNascimento(rs.getString("nascimento"));
				cantor.setEstiloMusical(rs.getString("estilo_musical"));
				listaCantor.add(cantor);
			}
				
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaCantor;
	}
	
	public void excluir(int id) {
		String sql = "DELETE FROM tbl_banda_cantor WHERE id=?";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar excluir o cantor.\nEntre em contado com o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Cantor excluído com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar apagar o cantor.\nEntre em contado com o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Cantor getCantorById(int id) {
		Cantor cantor = new Cantor();
		
		String sql = "SELECT * FROM tbl_banda_cantor WHERE id=?";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				cantor.setId(rs.getInt("id"));
				cantor.setNome(rs.getString("nome"));
				cantor.setCidadeNatal(rs.getString("cidade_natal"));
				cantor.setNascimento(rs.getString("nascimento"));
				cantor.setEstiloMusical(rs.getString("estilo_musical"));
			}
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cantor;
	}
	
	public void Alterar(Cantor cantor) {
		//Cantor cantor_1 = new Cantor();
		
		String sql = "UPDATE tbl_banda_cantor set nome = ?, cidade_natal = ?, nascimento = ?, estilo_musical = ?"
		+ " WHERE id = ?";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cantor.getNome());
			stm.setString(2, cantor.getCidadeNatal());
			stm.setString(3, cantor.getNascimento());
			stm.setString(4, cantor.getEstiloMusical());
			stm.setInt(5, cantor.getId());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de alterar os dados do cantor.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Dados do cantor alterados com sucesso.", "ALTERAÇÃO CONCLUÍDA",
						JOptionPane.INFORMATION_MESSAGE);
			}
			ConexaoMySql.fecharConexão();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
