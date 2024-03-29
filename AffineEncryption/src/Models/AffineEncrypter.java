package Models;

import java.util.ArrayList;

public class AffineEncrypter {

    public static final int[] ourKeys = new int[]{17, 3};
    public final int[] key;
    public static ArrayList<Character> alphabet;
    public static int[][] inverse;
    static boolean isInited = false;

    public AffineEncrypter(int[] key) {
        if (!isInited) {
            init();
        }
        if (isValid(key)) {
            this.key = key;
        } else {
            this.key = ourKeys;
            System.err.println("we didn't like its params we will use ours -_-");
        }

    }

    public AffineEncrypter() {
        if (!isInited) {
            init();
        }
        this.key = ourKeys;
    }

    public String encrypte(String note) {
        if (note == null) {
            return null;
        }
        String noteEncrypted = "";
        char[] array = note.toCharArray();
        for (char currentChar : array) {
            if (currentChar != ' ') {
                int currentCharIndex = alphabet.indexOf(currentChar);
                int currentCharEncryption = (key[0] * currentCharIndex + key[1]) % 26;
                noteEncrypted += alphabet.get(currentCharEncryption).toString();
            } else {
                noteEncrypted += " ";
            }
        }
        return noteEncrypted;

    }

    public String decrypte(String note) {
        if (note == null) {
            return null;
        }
        String noteDecrypted = "";
        char[] array = note.toCharArray();
        for (char currentChar : array) {
            if (currentChar != ' ') {
                int a = getInverse(key[0]);
                int currentCharIndex = alphabet.indexOf(currentChar);
                int currentCharDecryption = Math.floorMod(((currentCharIndex - key[1]) * a), 26);
                noteDecrypted += alphabet.get(currentCharDecryption).toString();
            } else {
                noteDecrypted += " ";
            }
        }
        return noteDecrypted;

    }

    public static boolean isValid(int[] key) {
        if (key.length == 2) {
            for (int i = 0; i < 13; i++) {
                if (inverse[0][i] == key[0]) {
                    if (key[1] >= 0) {
                        System.out.println("is valid");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getInverse(int a) {
        for (int i = 0; i < 13; i++) {
            if (inverse[0][i] == a) {
                return inverse[1][i];
            }
        }
        return -1;
    }

    public static void init() {
        inverse = new int[2][12];
        inverse[0][0] = 1;
        inverse[0][1] = 3;
        inverse[0][2] = 5;
        inverse[0][3] = 7;
        inverse[0][4] = 9;
        inverse[0][5] = 11;
        inverse[0][6] = 15;
        inverse[0][7] = 17;
        inverse[0][8] = 19;
        inverse[0][9] = 21;
        inverse[0][10] = 23;
        inverse[0][11] = 25;
        inverse[1][0] = 1;
        inverse[1][1] = 9;
        inverse[1][2] = 21;
        inverse[1][3] = 15;
        inverse[1][4] = 3;
        inverse[1][5] = 19;
        inverse[1][6] = 7;
        inverse[1][7] = 23;
        inverse[1][8] = 11;
        inverse[1][9] = 5;
        inverse[1][10] = 17;
        inverse[1][11] = 25;
        alphabet = new ArrayList<>();
        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('c');
        alphabet.add('d');
        alphabet.add('e');
        alphabet.add('f');
        alphabet.add('g');
        alphabet.add('h');
        alphabet.add('i');
        alphabet.add('j');
        alphabet.add('k');
        alphabet.add('l');
        alphabet.add('m');
        alphabet.add('n');
        alphabet.add('o');
        alphabet.add('p');
        alphabet.add('q');
        alphabet.add('r');
        alphabet.add('s');
        alphabet.add('t');
        alphabet.add('u');
        alphabet.add('v');
        alphabet.add('w');
        alphabet.add('x');
        alphabet.add('y');
        alphabet.add('z');
        isInited = true;
    }
}
