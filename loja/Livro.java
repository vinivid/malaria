public class Livro extends Produto{
    private String editora;
    private String edicao;
    private String paginas;
    private String linguagem;

    public Livro(String codigo, String nome, String criador, 
                String editora, String ano, String edicao, 
                String paginas, String linguagem)
    {
        super(nome, codigo, criador, ano);
        this.editora = editora;
        this.edicao = edicao;
        this.paginas = paginas;
        this.linguagem = linguagem;
    }

    @Override
    public String toString() {
        String nstr = "Livro\n";
        nstr += "Código: " + getCodigo() + "\n";
        nstr += "Título: " + getName() + "\n";
        nstr += "Autor: "  + getCreator() + "\n";
        nstr += "Editora: " + this.editora + "\n";
        nstr += "Edição: " + this.edicao + "\n";
        nstr += "Ano: " + getYear() + "\n";
        nstr += "Páginas: " + this.paginas + "\n";
        nstr += "Idioma: " + this.linguagem + "\n";         
        return nstr;
    }
}