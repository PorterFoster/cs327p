import java.math.BigInteger;

public class FosterPorterValdesErickRSA2 {
    public BigInteger getN(){
        String nValue = "1010517893091632587989664773819175360110584414091408545389643";
        BigInteger n = new BigInteger(nValue, 10);
        return n;
    }

    public BigInteger getE(){
        String eValue = "65537";
        BigInteger E = new BigInteger(eValue, 10);
        return E;
    }
    public BigInteger getP(){
        String pValue = "863490933159798076360907020927";
        BigInteger P = new BigInteger(pValue, 10);
        return P;

    }
    public BigInteger getQ(){
        String qValue = "1170270415456262384144425721909";
        BigInteger Q = new BigInteger(qValue, 10);
        return Q;
    }

    public BigInteger getZ(){
        BigInteger pSubtracted = getP().subtract(BigInteger.ONE);
        BigInteger qSubtracted = getQ().subtract(BigInteger.ONE);

        BigInteger Z = pSubtracted.multiply(qSubtracted);
        return Z;
    }

    public BigInteger getD(){

        BigInteger D = getE().modInverse(getZ());
        return D;
    }

    public BigInteger getC(){
        String mValue = "3";
        BigInteger M = new BigInteger(mValue, 10);

        BigInteger C = M.modPow(getE(), getN());
        return C;
    }

    public BigInteger getM2(){
        BigInteger m2 = getC().modPow(getD(), getN());
        return m2;
    }

    public static void main(String[] args) {
        FosterPorterValdesErickRSA2 rsa = new FosterPorterValdesErickRSA2 ();

        BigInteger n = rsa.getN();
        BigInteger P = rsa.getP();
        BigInteger Q = rsa.getQ();
        BigInteger D = rsa.getD();
        BigInteger C = rsa.getC();
        BigInteger m2 = rsa.getM2();

        System.out.println("N = " + n.toString(16));
        System.out.println("p = " + P.toString(16));
        System.out.println("q = " + Q.toString(16));
        System.out.println("Bit-length of N = " + n.bitLength());
        System.out.println("Bit-length of p = " + P.bitLength());
        System.out.println("Bit-length of q = " + Q.bitLength());
        System.out.println("d = " + D.toString(16));
        System.out.println("c = " + C.toString(16));
        System.out.println("m2 = " + m2.toString(16));

        /*System.out.println("PART 2 of Project");
        System.out.println("RSA Decryptions took x milliseconds");
        System.out.println("RSA Decryptions in terms of kilobits/second");
        System.out.println("The speed is x gigabit/second Internet speed.");
        System.out.println("The CRT speed is x gigabit/second");*/
    }
}