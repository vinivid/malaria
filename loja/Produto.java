abstract class Produto {
    private String nome;
    private String codigo;
    private String criador;
    private String ano;

    public Produto(String nome, String codigo, String criador, String ano) {
        this.nome = nome;
        this.codigo = codigo;
        this.criador = criador;
        this.ano = ano;
    }

    @Override
    public String toString() {return "Bola";} 

    public int cmpNome(String nome) { return this.nome.compareTo(nome); }

    public int cmpCodigo(String codigo) { return this.codigo.compareTo(codigo); }

    public String getCodigo() { return codigo; }
    
    public String getCreator() { return criador; }

    public String getName() { return nome; }

    public String getYear() { return ano; }
}