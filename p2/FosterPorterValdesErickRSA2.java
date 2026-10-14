package p2;
import java.math.BigInteger;

public class FosterPorterValdesErickRSA2 {
    public BigInteger getN(){
        String nValue = "1010517893091632587989664773819175360110584414091408545389643";
        BigInteger n = new BigInteger(nValue, 10);
        return n;
    }

    public BigInteger getE(){
        String eValue = "65537";
        BigInteger e = new BigInteger(eValue, 10);
        return e;
    }
    public BigInteger getP(){
        String pValue = "863490933159798076360907020927";
        BigInteger p = new BigInteger(pValue, 10);
        return p;

    }
    public BigInteger getQ(){
        String qValue = "1170270415456262384144425721909";
        BigInteger q = new BigInteger(qValue, 10);
        return q;
    }

    public BigInteger getZ(){
        BigInteger pSubtracted = getP().subtract(BigInteger.ONE);
        BigInteger qSubtracted = getQ().subtract(BigInteger.ONE);

        BigInteger z = pSubtracted.multiply(qSubtracted);
        return z;
    }

    public BigInteger getD(){

        BigInteger d = getE().modInverse(getZ());
        return d;
    }

    public BigInteger getC(){
        String mValue = "3";
        BigInteger m = new BigInteger(mValue, 10);

        BigInteger c = m.modPow(getE(), getN());
        return c;
    }

    public BigInteger getM2(){
        BigInteger m2 = getC().modPow(getD(), getN());
        return m2;
    }

    public static void main(String[] args) {
        FosterPorterValdesErickRSA2 rsa = new FosterPorterValdesErickRSA2 ();

        BigInteger n = rsa.getN();
        BigInteger p = rsa.getP();
        BigInteger q = rsa.getQ();
        BigInteger d = rsa.getD();
        BigInteger c = rsa.getC();
        BigInteger m2 = rsa.getM2();

        System.out.println("N = " + n.toString(16));
        System.out.println("p = " + p.toString(16));
        System.out.println("q = " + q.toString(16));
        System.out.println("Bit-length of N = " + n.bitLength());
        System.out.println("Bit-length of p = " + p.bitLength());
        System.out.println("Bit-length of q = " + q.bitLength());
        System.out.println("d = " + d.toString(16));
        System.out.println("c = " + c.toString(16));
        System.out.println("m2 = " + m2.toString(16));

        /*System.out.println("PART 2 of Project");
        System.out.println("RSA Decryptions took x milliseconds");
        System.out.println("RSA Decryptions in terms of kilobits/second");
        System.out.println("The speed is x gigabit/second Internet speed.");
        System.out.println("The CRT speed is x gigabit/second");*/
    }
}