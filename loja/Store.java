import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Store {
    private ArrayList<Produto> produtos;
    private HashMap<String, Integer> stoque;

    public Store() { 
        this.produtos = new ArrayList<Produto>();
        this.stoque = new HashMap<String, Integer>();
    }

    private Produto searchForCode(String codigo) {
        for (Produto prd : produtos) {
            if (prd.cmpCodigo(codigo) == 0)
                return prd;
        }

        return null;
    }

    private Produto searchForEither(String value) {
        String codVal = value.replaceFirst("^0+", "");
        for (Produto prd : produtos) {
            if (prd.cmpCodigo(codVal) == 0 || prd.cmpNome(value) == 0)
                return prd;
        }

        return null;
    }

    public void insertProduct(String value) {
        String[] prodSpec = value.split(",");
        String prodTipo   = prodSpec[0];
        String[] prodVals = Arrays.copyOfRange(prodSpec, 1, prodSpec.length);
        String remCod = prodVals[0].replaceFirst("^0+", "");

        stoque.put(remCod, 0);

        if (prodTipo.equals("Livro")) {
            System.out.printf("Operação inserir livro: %s\n", remCod);
            if (searchForCode(prodVals[0]) != null) {
                System.out.printf("***Erro: Código já cadastrado: %s\n", prodVals[0]);   
                return;         
            }
            Livro novoLivro = new Livro(remCod, prodVals[1], prodVals[2], 
                                        prodVals[3], prodVals[4], prodVals[5], 
                                        prodVals[6], prodVals[7]);
            produtos.add(novoLivro);
        } else if (prodTipo.equals("CD")) {
            System.out.printf("Operação inserir CD: %s\n", remCod);
            if (searchForCode(prodVals[0]) != null) {
                System.out.printf("***Erro: Código já cadastrado: %s\n", prodVals[0]);   
                return;         
            }
            CD novoCd = new CD(remCod, prodVals[1], prodVals[2], 
                               prodVals[3], prodVals[4], prodVals[5]);
            produtos.add(novoCd);
        } else if (prodTipo.equals("DVD")) {
            System.out.printf("Operação inserir DVD: %s\n", remCod);
            if (searchForCode(prodVals[0]) != null) {
                System.out.printf("***Erro: Código já cadastrado: %s\n", prodVals[0]);   
                return;         
            }
            DVD novoDvd = new DVD(remCod, prodVals[1], prodVals[2], 
                                  prodVals[3], prodVals[4], prodVals[5], 
                                  prodVals[6]);
            produtos.add(novoDvd);
        } else {
            System.out.println("Tipo de produto para inserir invalido");
        }

        System.out.println("Operação realizada com sucesso\n");
    }

    public void searchProd(String value) {
        System.out.printf("Procurando: %s\n", value);
        Produto prodProc = searchForEither(value);

        if (prodProc == null) {
            System.out.printf("Produto não encontrado: %s\n\n", value);
            return;
        }

        System.out.println("Encontrado:");
        System.out.println(prodProc);
    }

    public void addProduct(String valus) {
        String[] vals = valus.split(",");
        String codVal = vals[0].replaceFirst("^0+", "");
        System.out.printf("Operação de compra: %s\n", codVal);

        if (searchForCode(codVal) == null) {
            System.out.printf("***Erro: Código inexistente: %s\n\n", codVal);
            return;
        }
        
        System.out.printf("Operação realizada com sucesso: %s\n\n", codVal);        
        Integer v = stoque.get(codVal);
        stoque.replace(codVal, v + Integer.parseInt(vals[1]));
    }

    public void sellProduct(String valus) {
        String[] vals = valus.split(",");
        String codVal = vals[0].replaceFirst("^0+", "");
        Integer sVal  = Integer.parseInt(vals[1]); 
        System.out.printf("Operação de venda: %s\n", codVal);
        
        if (searchForCode(codVal) == null) {
            System.out.printf("***Erro: Código inexistente: %s\n\n", codVal);
            return;
        }

        Integer v = stoque.get(codVal);
        
        if (v < sVal) {
            System.out.printf("***Erro: Estoque insuficiente: %s Quantidade: %d\n\n", codVal, sVal);
            return;
        }

        stoque.replace(codVal, v - sVal);

        System.out.printf("Operação realizada com sucesso: %s\n\n", codVal);
    }

    public void summarize() {
        System.out.println("Operação de sumarização: ");

        ArrayList<Livro> livros = new ArrayList<Livro>();
        ArrayList<CD> cds = new ArrayList<CD>();
        ArrayList<DVD> dvds = new ArrayList<DVD>();

        for (Produto prod : produtos) {
            if (prod instanceof Livro) {
                livros.add((Livro)prod);
            } else if (prod instanceof CD) {
                cds.add((CD)prod);
            } else if (prod instanceof DVD) {
                dvds.add((DVD)prod);
            }
        }

        Integer accLivros = 0;
        for (Livro l : livros) {
            System.out.print(l);
            Integer qtt = stoque.get(l.getCodigo());
            System.out.printf("Quantidade: %d\n\n", qtt);
            accLivros += qtt;
        }

        System.out.printf("Quantidade de Livros: %d\n\n", accLivros);

        Integer accCd = 0;
        for (CD l : cds) {
            System.out.print(l);
            Integer qtt = stoque.get(l.getCodigo());
            System.out.printf("Quantidade: %d\n\n", qtt);
            accCd += qtt;
        }

        System.out.printf("Quantidade de CDs: %d\n\n", accCd);

        Integer accDvd = 0;
        for (DVD l : dvds) {
            System.out.print(l);
            Integer qtt = stoque.get(l.getCodigo());
            System.out.printf("Quantidade: %d\n\n", qtt);
            accDvd += qtt;
        }

        System.out.printf("Quantidade de DVDs: %d\n\n\n", accDvd);
    }
}
