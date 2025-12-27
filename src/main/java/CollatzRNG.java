import java.util.Scanner;
import java.util.ArrayList;
import java.security.MessageDigest;

public class CollatzRNG {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Baslangic degeri (x) giriniz: ");
        long x = scanner.nextLong();

        int ciftSayisi = 0;
        int tekSayisi = 0;
        int toplamAdim = 0;
        long maksimumDeger = x;
        long baslangicDegeri = x;
        ArrayList<Long> dizi = new ArrayList<>();

        System.out.println("\nUretilen sayi dizisi:");
        System.out.println(x);

        while (x != 1) {
            if (x % 2 == 0) {
                x = x / 2;
                ciftSayisi++;
            } else {
                x = 3 * x + 1;
                tekSayisi++;
            }

            if (x > maksimumDeger) {
                maksimumDeger = x;
            }

            dizi.add(x);
            toplamAdim++;
            System.out.println(x);
        }

        System.out.println("\n--- Istatistiksel Analiz ---");
        System.out.println("Toplam adim sayisi: " + toplamAdim);
        System.out.println("Cift sayilarin sayisi: " + ciftSayisi);
        System.out.println("Tek sayilarin sayisi: " + tekSayisi);
        System.out.println("Ulasilan maksimum deger: " + maksimumDeger);

        // Güçlü anahtar üretimi
        String anahtar128 = anahtarUret(dizi, baslangicDegeri, 128);
        String anahtar256 = anahtarUret(dizi, baslangicDegeri, 256);
        String anahtar512 = anahtarUret(dizi, baslangicDegeri, 512);

        System.out.println("\n--- Uretilen Guclu Anahtarlar ---");
        System.out.println("128-bit AES Anahtar: " + anahtar128);
        System.out.println("256-bit AES Anahtar: " + anahtar256);
        System.out.println("512-bit SHA-512 Anahtar: " + anahtar512);

        scanner.close();
    }

    /**
     * Collatz dizisinden kriptografik anahtar üretir
     * @param dizi Collatz sayı dizisi
     * @param baslangic Başlangıç değeri
     * @param bitUzunlugu İstenen anahtar bit uzunluğu (128, 256, 512)
     * @return Hexadecimal formatta anahtar
     */
    private static String anahtarUret(ArrayList<Long> dizi, long baslangic, int bitUzunlugu) {
        try {
            // Diziyi byte dizisine dönüştür
            StringBuilder sb = new StringBuilder();
            sb.append(baslangic);
            for (Long sayi : dizi) {
                sb.append(sayi);
            }

            String algoritma = bitUzunlugu == 512 ? "SHA-512" : "SHA-256";
            MessageDigest digest = MessageDigest.getInstance(algoritma);
            byte[] hash = digest.digest(sb.toString().getBytes("UTF-8"));

            // Hash'i hexadecimal stringe dönüştür
            StringBuilder hexString = new StringBuilder();
            int byteUzunlugu = bitUzunlugu / 8;
            for (int i = 0; i < byteUzunlugu && i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString().toUpperCase();

        } catch (Exception e) {
            return "Anahtar üretimi sırasında hata: " + e.getMessage();
        }
    }
}
