package p3;

public class FosterPorterValdesErickHillCipher{
    //Method to find modular multiplicative inverse
    public int xgcd (int inE, int inZ) {
        int d1 = inZ;
        int d2 = inE;
        int s1 = 1;
        int s2 = 0;
        int t1 = 0;
        int t2 = 1;

        while (d2 != 0){
            //calculates new d2
            int q = Math.floorDiv(d1, d2);
            int r = d1 -(q * d2);

            d1 = d2;
            d2 = r;

            //calculates new t2
            int remainderT = t1 -(q * t2);
            t1 = t2;
            t2 = remainderT;

            //Calculates new s2
            int remainderS = s1 - (q * s2);
            s1 = s2;
            s2 = remainderS;
        }
        if (d1 != 1){
            return -1;
        }
        while (t1 < 0){
            t1 += inZ;
        }

        return Math.abs(t1);
    }

    public int[][] findDecryptionKey(int[][] encryptionKey){
        int determinant = (encryptionKey[0][0] * encryptionKey[1][1]) - (encryptionKey[0][1] * encryptionKey[1][0]);
        int modDeterminant = determinant % 26;
        //Added this in case we get negative values for the mod
        //Delete or keep if you want
        if(modDeterminant < 0){
            modDeterminant += 26;
        }
        int invDeterminant = xgcd(modDeterminant, 26);

        int[][] decryptionKey = new int[2][2];

        decryptionKey[0][0] = (invDeterminant * encryptionKey[1][1] % 26);
        decryptionKey[0][1] = (invDeterminant * -encryptionKey[0][1] % 26);
        decryptionKey[1][0] = (invDeterminant * -encryptionKey[1][0] % 26);
        decryptionKey[1][1] = (invDeterminant * encryptionKey[0][0] % 26);

        //Added these in case we get negative values for the mod
        //Delete or keep if you want
        if (decryptionKey[0][0] < 0){
            decryptionKey[0][0] += 26;
        }

        if (decryptionKey[0][1] < 0){
            decryptionKey[0][1] += 26;
        }

        if (decryptionKey[1][0] < 0){
            decryptionKey[1][0] += 26;
        }

        if (decryptionKey[1][1] < 0){
            decryptionKey[1][1] += 26;
        }


        return decryptionKey;
    }

    public static void main(String[] args){
        //Print encryption Key
        int[][] encryptionKey = {{16, 7}, {9, 14}};

        System.out.println("Encryption Key:");
        System.out.println(encryptionKey[0][0] + " " + encryptionKey[0][1]);
        System.out.println(encryptionKey[1][0] + " " + encryptionKey[1][1]);

        FosterPorterValdesErickHillCipher hillCipher = new FosterPorterValdesErickHillCipher();
        int[][] decryptionKey = hillCipher.findDecryptionKey(encryptionKey);

        System.out.println("Decryption Key:");
        System.out.println(decryptionKey[0][0] + " " + decryptionKey[0][1]);
        System.out.println(decryptionKey[1][0] + " " + decryptionKey[1][1]);
    }
}
