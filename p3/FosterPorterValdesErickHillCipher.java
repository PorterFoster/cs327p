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

    
    public int[] encrypt(int[] plaintext, int[][] encryptionKey) {
        //Get length of plaintext
        int length = plaintext.length;

        //Add one more letter if length is odd
        if (length % 2 != 0){
            length += 1;
        }

        int[] ciphertext = new int[length];

        //encrypt two letters at a time
        for (int i = 0; i < length; i += 2){
            int first = plaintext[i];
            int second = 23; //X is 23

            //get second letter if there is one
            if (i + 1 < plaintext.length){
                second = plaintext[i + 1];
            }

            //calculate encrypted values
            ciphertext[i] = ((encryptionKey[0][0] * first) +
                            (encryptionKey[0][1] * second)) % 26;

            ciphertext[i + 1] = ((encryptionKey[1][0] * first) +
                                (encryptionKey[1][1] * second)) % 26;

            //fix negative values
            if (ciphertext[i] < 0){
                ciphertext[i] += 26;
            }

            if (ciphertext[i + 1] < 0){
                ciphertext[i + 1] += 26;
            }
        }

        return ciphertext;
    }


    public int[] decrypt(int[] ciphertext, int[][] decryptionKey) {
        //get length of ciphertext
        int length = ciphertext.length;

        //add one more letter if length is odd
        if (length % 2 != 0){
            length += 1;
        }

        int[] plaintext = new int[length];

        //decrypt two letters at a time
        for (int i = 0; i < length; i += 2){
            int first = ciphertext[i];
            int second = 23;

            //get second letter if there is one
            if (i + 1 < ciphertext.length){
                second = ciphertext[i + 1];
            }

            //calculate decrypted values
            plaintext[i] = ((decryptionKey[0][0] * first) +
                            (decryptionKey[0][1] * second)) % 26;

            plaintext[i + 1] = ((decryptionKey[1][0] * first) +
                                (decryptionKey[1][1] * second)) % 26;

            //fix negative values
            if (plaintext[i] < 0){
                plaintext[i] += 26;
            }

            if (plaintext[i + 1] < 0){
                plaintext[i + 1] += 26;
            }
        }

        return plaintext;
    }


    public static void main(String[] args){
        //Print encryption Key
        int[][] encryptionKey = {{16, 7}, {9, 14}};

        System.out.println("Encryption Key:");
        System.out.println(encryptionKey[0][0] + " " + encryptionKey[0][1]);
        System.out.println(encryptionKey[1][0] + " " + encryptionKey[1][1]);

        FosterPorterValdesErickHillCipher hillCipher = new FosterPorterValdesErickHillCipher();
        int[][] decryptionKey = hillCipher.findDecryptionKey(encryptionKey);

        //print decryption key
        System.out.println("Decryption Key:");
        System.out.println(decryptionKey[0][0] + " " + decryptionKey[0][1]);
        System.out.println(decryptionKey[1][0] + " " + decryptionKey[1][1]);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //encrypt plaintext
        int[] plaintext = {9, 12, 20, 2, 18, 8, 18, 2, 14, 14, 11};
        int[] ciphertext = hillCipher.encrypt(plaintext, encryptionKey);

        System.out.println("Encrypted Message:");
        for (int i = 0; i < ciphertext.length; i++){
            System.out.print(alphabet.charAt(ciphertext[i]));
        }
        System.out.println();

        //decrypt ciphertext
        int[] encrypted = {12, 16, 6, 21, 6, 16, 18, 12, 9, 8};
        int[] decrypted = hillCipher.decrypt(encrypted, decryptionKey);

        System.out.println("Decrypted Message:");
        for (int i = 0; i < decrypted.length; i++){
            System.out.print(alphabet.charAt(decrypted[i]));
        }
        System.out.println();
        }
}
