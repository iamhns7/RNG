# Collatz Tabanlı Anahtar Üreteci

Bu projede Collatz algoritması kullanılarak ASCII hex tabanlı
anahtar üreten bir mekanizma geliştirilmiştir.

Proje, Bilgi Sistemleri Güvenliği dersi kapsamında,
rastgelelik, seed (başlangıç değeri), anahtar üretimi ve
kriptografik güvenlik kavramlarını incelemek amacıyla hazırlanmıştır.

---

## Algoritmanın Temeli

Algoritma, kullanıcı tarafından girilen bir başlangıç değeri (x) ile başlar.

- Eğer x çift ise: x = x / 2
- Eğer x tek ise: x = 3x + 1

Bu işlem x = 1 olana kadar devam eder.
Oluşan sayı dizisi düzensiz bir yapı gösterir ve
sözde rastgele bir davranış sergiler.

---

## Anahtar Üretim Yöntemi

1. **Collatz Dizisi Oluşturma**: Başlangıç değerinden 1'e kadar olan tüm sayılar diziye eklenir
2. **String Birleştirme**: Tüm sayılar birleştirilerek tek bir string oluşturulur (örn: "20105168421")
3. **UTF-8 Encoding**: String UTF-8 formatında byte dizisine çevrilir
4. **Hexadecimal Dönüşüm**: Her byte hexadecimal formata dönüştürülür
5. **Padding**: 256-bit (32 byte = 64 hex karakter) tamamlanması için sıfırlar eklenir

### Örnek:
- Başlangıç: 20
- Collatz Dizisi: [10, 5, 16, 8, 4, 2, 1]
- Birleştirilmiş: "20105168421"
- ASCII Byte: [50, 48, 49, 48, 53, 49, 54, 56, 52, 50, 49]
- Hex Anahtar: `3230313035313638343231000000000000000000000000000000000000000000`

---

## Seed (Başlangıç Değeri) Kavramı

Başlangıç değeri (seed), üretilen anahtar çıktısını tamamen belirler.

Aynı seed değeri her zaman aynı anahtarı üretir (deterministik).

---

## Güvenlik Analizi

### Zayıf Yönler:
- **Hash kullanılmıyor**: Direkt ASCII değerleri kullanıldığından tersine mühendislik kolay
- **Tahmin edilebilir**: Başlangıç değeri brute-force ile kolayca bulunabilir
- **Salt yok**: Ek entropi kaynağı bulunmuyor
- **Tek işlem**: İterasyon olmadığından kırma süresi çok kısa

### Sonuç:
Bu sistem **eğitim amaçlıdır** ve gerçek kriptografik uygulamalarda kullanılmamalıdır.
Güvenli anahtar üretimi için PBKDF2, bcrypt veya Argon2 gibi algoritmalar kullanılmalıdır.

