class CD extends Produto {
    private String numero;
    private String gravadora;

    public CD(String codigo, String nome, String criador, 
              String numero, String gravadora, String ano) 
    {
        super(nome, codigo, criador, ano);
        this.numero = numero;
        this.gravadora  = gravadora;
    }

    @Override
    public String toString() {
        String nstr = "CD\n";
        nstr += "Código: " + getCodigo() + "\n";
        nstr += "Título: " + getName() + "\n";
        nstr += "Banda: "  + getCreator() + "\n";
        nstr += "Gravadora: " + this.gravadora + "\n";
        nstr += "Ano: " + getYear() + "\n";
        nstr += "trilhas: " + this.numero + "\n";
        return nstr;
    }
    
}