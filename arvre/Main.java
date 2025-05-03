public class Main {
    public static void main(String[] args) {
        ArvBin ok = new ArvBin(200);
        ok.insert("Jeremias");
        ok.insert("Marta");   
        ok.insert("Sara");    
        ok.remove("Helio");   
        ok.insert("Vania");   
        ok.remove("Queila");  
        ok.remove("Fred");    
        ok.remove("Vania");   
        ok.remove("Ze");
        ok.remove("Jeremias");
        ok.insert("Fred");
        ok.remove("Marta");
        ok.insert("Tadeu");
        ok.insert("Xena");
        ok.insert("Renata");
        System.out.println(ok.toString());
    }
}
