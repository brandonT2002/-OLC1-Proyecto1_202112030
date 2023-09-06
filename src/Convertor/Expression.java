package Convertor;
public class Expression {
    public Expression e1;
    public String signo;
    public Expression e2;
    public String primitive;
    public Expression(String primitive) {
        this.primitive = primitive;
    }
    public Expression(Expression e1, String signo, Expression e2) {
        this.e1 = e1;
        this.signo = signo;
        this.e2 = e2;
    }
    public String convert() {
        if(primitive == null && signo != null && e2 != null) {
            if(e1 != null) {
                return e1.convert() + " " + signo + " " + e2.convert();
            }
            return signo + " " + e2.convert();
        }
        if(e1 != null && signo == null && e2 == null) {
            return "(" + e1.convert() + ")";
        }
        return primitive;
    }
}