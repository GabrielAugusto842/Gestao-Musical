package br.senai.sp.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoMySql {
	
	private static Connection con = null;
	public static Connection getConexao() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gestao_musical", "root", "bcd127");
			System.out.println("Conexão aberta com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void fecharConexão() {
		try {
			con.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ocorreu um erro na tentativa de fechar o banco", "Erro",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
