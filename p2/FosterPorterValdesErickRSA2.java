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
        FosterPorterValdesErickRSA2 rsa = new FosterPorterValdesErickRSA2();

        // PART 1 - FLAWED RSA EXAMPLE
        System.out.println("===== FLAWED RSA EXAMPLE =====");

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

        System.out.println("e = " + rsa.getE().toString(16));
        System.out.println("d = " + d.toString(16));
        System.out.println("c = " + c.toString(16));
        System.out.println("Decrypted message = " + m2.toString(16));


        // PART 2 - REAL-WORLD RSA EXAMPLE
        System.out.println("\n===== REAL-WORLD RSA EXAMPLE =====");

        BigInteger realP = rsa.getRealP();
        BigInteger realQ = rsa.getRealQ();
        BigInteger realN = rsa.genPubKey();
        BigInteger realD = rsa.getRealD();
        BigInteger realC = rsa.getRealC();
        BigInteger realM2 = rsa.getRealM2();

        System.out.println("N = " + realN.toString(16));
        System.out.println("p = " + realP.toString(16));
        System.out.println("q = " + realQ.toString(16));

        System.out.println("Bit-length of N = " + realN.bitLength());
        System.out.println("Bit-length of p = " + realP.bitLength());
        System.out.println("Bit-length of q = " + realQ.bitLength());

        System.out.println("e = " + rsa.getE().toString(16));
        System.out.println("d = " + realD.toString(16));
        System.out.println("c = " + realC.toString(16));
        System.out.println("Decrypted message = " + realM2.toString(16));


        // PART 3 - CRT DECRYPTION
        System.out.println("\n===== CRT DECRYPTION =====");

        BigInteger dp = realD.mod(realP.subtract(BigInteger.ONE));
        BigInteger dq = realD.mod(realQ.subtract(BigInteger.ONE));
        BigInteger qInv = realQ.modInverse(realP);

        BigInteger crtMessage = rsa.decryptCRT(realC, realP, realQ, dp, dq, qInv);

        System.out.println("CRT decrypted message = " + crtMessage.toString(16));


        // PART 4 - DECRYPTION SPEED TESTS
        System.out.println("\n===== DECRYPTION SPEED TESTS =====");

        double rsaTime = rsa.rsaDecryptionSpeed();
        double crtTime = rsa.crtDecryptionSpeed();

        double speed = rsaTime / crtTime;

        System.out.println("CRT speed is " + speed + " times faster than regular RSA decryption speed.");



        /*System.out.println("PART 2 of Project");
        System.out.println("RSA Decryptions took x milliseconds");
        System.out.println("RSA Decryptions in terms of kilobits/second");
        System.out.println("The speed is x gigabit/second Internet speed.");
        System.out.println("The CRT speed is x gigabit/second");*/
    }

    // [REAL-WORLD EXAMPLE]
    // [REAL-WORLD EXAMPLE]
    public BigInteger getRealP(){
        String pValue = "c86422b12e3a0c79cbe9f10c47a436fc54a71309214a3455054fca5912e442c32247ffdc782e574c96d7d43e60e383d7f0f091f08909f2629c5ad1bd5ce2427333dedb281abb1bffcbfb2814b402dc29bf5406d6e749ba57e1e6843f0111875a34439aeb2aa6c554d71b8cbaefbe0f6a4c7a0145d75afda323c7520a29d4d3d799a676977a0d3bd0f3411023c3ab106ee5fb0d2fbaa64e85fb71b6b76ebebbbfcd1ffd1de93302e90a3fefe0ffcb95a3da7ce6e2dcdd973873b96dd939763025";
        return new BigInteger(pValue, 16);
    }

    public BigInteger getRealQ(){
        String qValue = "d062fa3d79191304a4a64d39dc3b5ca757b9b740304825b13d05d17ffcf3b1e033dbffbe31eeea0b908f8ed36ebc2d1b1db3a094e2ddd4976549e225981ac6f9586a31f79b90cc41ed4c069d1306482345c8cc61fc4567bd2043484f8ac2fca32d7690c0881889b44a365795b0af07046a6bc468b10570033e2b6ad8d7702266c6d6ca5eec75224039998f719f74ad8fdebe9c4347a71e6cd96e03796b570f459c23c6ca250761e741f957d4b9f24df783e6c2ebe91e64863b710989da81a261";
        return new BigInteger(qValue, 16);
    }

    public BigInteger genPubKey(){
        return getRealP().multiply(getRealQ());
    }

    public BigInteger getRealD(){
        BigInteger pSubtracted = getRealP().subtract(BigInteger.ONE);
        BigInteger qSubtracted = getRealQ().subtract(BigInteger.ONE);
        BigInteger z = pSubtracted.multiply(qSubtracted);
        return getE().modInverse(z);
    }

    public BigInteger getRealC(){
        BigInteger m = BigInteger.valueOf(3);
        return m.modPow(getE(), genPubKey());
    }

    public BigInteger getRealM2(){
        return getRealC().modPow(getRealD(), genPubKey());
    }

    public BigInteger decryptCRT(BigInteger c, BigInteger p, BigInteger q,
                                 BigInteger dp, BigInteger dq, BigInteger qInv){
        BigInteger m1 = c.modPow(dp, p);
        BigInteger m2 = c.modPow(dq, q);
        BigInteger h = qInv.multiply(m1.subtract(m2)).mod(p);
        return m2.add(h.multiply(q));
    }

    public double rsaDecryptionSpeed() {
        // Question 6 - RSA Decryption Speed

        BigInteger n = genPubKey();
        BigInteger d = getRealD();
        BigInteger c = getRealC();

        long startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            c.modPow(d, n);
        }

        long endTime = System.nanoTime();

        double totalTime = (endTime - startTime) / 1000000000.0;

        double bps = (n.bitLength() * 1000.0) / totalTime;

        double kbps = bps / 1000.0;

        System.out.println("RSA Decryptions took " + (totalTime * 1000) + " milliseconds");
        System.out.println("RSA decryption speed = " + kbps + " kbps");
        System.out.println("This speed is slower than gigabit/second Internet speed.");

        return totalTime;
    }

    public double crtDecryptionSpeed() {
        // Question 7 - CRT Decryption Speed

        BigInteger n = genPubKey();
        BigInteger p = getRealP();
        BigInteger q = getRealQ();
        BigInteger d = getRealD();
        BigInteger c = getRealC();

        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
        BigInteger qInv = q.modInverse(p);

        long startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            decryptCRT(c, p, q, dp, dq, qInv);
        }

        long endTime = System.nanoTime();

        double totalTime = (endTime - startTime) / 1000000000.0;

        double bps = (n.bitLength() * 1000.0) / totalTime;

        double kbps = bps / 1000.0;

        double gbps = kbps/ 1000000.0;

        System.out.println("CRT decryptions took " + (totalTime * 1000) + " milliseconds");
        System.out.println("CRT decryption speed = " + kbps + " kbps");
        System.out.println("The CRT speed is " + gbps + " gigabit/second");
        return totalTime;
    }
}