import java.util.Scanner;
import java.util.ArrayList;

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
        String anahtar256 = anahtarUret(dizi, baslangicDegeri, 256);

        System.out.println("\n--- Uretilen Guclu Anahtar ---");
        System.out.println("256-bit AES Anahtar: " + anahtar256);

        scanner.close();
    }

    /**
     * Collatz dizisinden anahtar üretir (Hash kullanmadan)
     * @param dizi Collatz sayı dizisi
     * @param baslangic Başlangıç değeri
     * @param bitUzunlugu İstenen anahtar bit uzunluğu (128, 256, 512)
     * @return Hexadecimal formatta anahtar
     */
    private static String anahtarUret(ArrayList<Long> dizi, long baslangic, int bitUzunlugu) {
        try {
            // Collatz dizisini string olarak birleştir
            StringBuilder sb = new StringBuilder();
            sb.append(baslangic);
            for (Long sayi : dizi) {
                sb.append(sayi);
            }

            // String'i byte dizisine çevir (UTF-8)
            byte[] bytes = sb.toString().getBytes("UTF-8");
            
            // Hexadecimal stringe dönüştür
            StringBuilder hexString = new StringBuilder();
            int byteUzunlugu = bitUzunlugu / 8;
            
            for (int i = 0; i < byteUzunlugu; i++) {
                if (i < bytes.length) {
                    // Gerçek byte değerini hex'e çevir
                    String hex = Integer.toHexString(0xff & bytes[i]);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                } else {
                    // Padding için 00 ekle
                    hexString.append("00");
                }
            }

            return hexString.toString().toUpperCase();

        } catch (Exception e) {
            return "Anahtar üretimi sırasında hata: " + e.getMessage();
        }
    }
}
