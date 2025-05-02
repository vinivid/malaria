public class Main {
    public static void main(String[] args) {
        ArvBin ok = new ArvBin(30);
        ok.insert("Bola");
        ok.insert("chigalaia");
        ok.insert("Begonstald");        
        ok.insert("deutestoma");
        ok.insert("zed");
        ok.insert("roraima");
        ok.insert("doga");
        ok.insert("loucura");
        ok.insert("engazopar");
        ok.remove("deutestoma");
        ok.remove("chigalaia");
        System.out.println(ok.toString());
    }
}
