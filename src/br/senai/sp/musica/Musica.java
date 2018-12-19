package br.senai.sp.musica;

public class Musica {

		private int id;
		private String titulo;
		private int anoDeComposicao;
		private String idioma;
		private String cantor;
		private String duracao;
		private String estiloMusical;			
		
		public void setId(int id) {
			this.id = id;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public int getAnoDeComposicao() {
			return anoDeComposicao;
		}
		public void setAnoDeComposicao(int anoDeComposicao) {
			this.anoDeComposicao = anoDeComposicao;
		}
		public String getIdioma() {
			return idioma;
		}
		public void setIdioma(String idioma) {
			this.idioma = idioma;
		}
		public String getCantor() {
			return cantor;
		}
		public void setCantor(String cantor) {
			this.cantor = cantor;
		}
		public String getDuracao() {
			return duracao;
		}
		public void setDuracao(String duracao) {
			this.duracao = duracao;
		}
		public String getEstiloMusical() {
			return estiloMusical;
		}
		public void setEstiloMusical(String estiloMusical) {
			this.estiloMusical = estiloMusical;
		}
		public int getId() {
			return id;
		}
		
}
