class DVD extends Produto {
    private String linguagem;
    private String genero;
    private String nacionalidade;

    public DVD(String codigo, String nome, String criador,
               String linguagem, String genero, String ano, 
               String nacionalidade)
    {
        super(nome, codigo, criador, ano);
        this.linguagem = linguagem;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        String nstr = "DVD\n";
        nstr += "Código: " + getCodigo() + "\n";
        nstr += "Título: " + getName() + "\n";
        nstr += "Diretor: "  + getCreator() + "\n";
        nstr += "Gênero: " + this.genero + "\n";
        nstr += "Ano: " + getYear() + "\n";
        nstr += "Nacionalidade: " + this.nacionalidade + "\n";
        nstr += "Idioma: " + this.linguagem + "\n";
        return nstr;
    }
}