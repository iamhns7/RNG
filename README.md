# Collatz Tabanlı Sözde Rastgele Sayı Üreteci

Bu projede Collatz algoritması kullanılarak sözde rastgele sayı üreten
bir mekanizma geliştirilmiştir.

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

## Seed (Anahtar) Kavramı

Başlangıç değeri (seed), kriptografideki anahtar (key) kavramına benzer şekilde
üretilen tüm çıktıyı belirlemektedir.

Aynı seed değeri her zaman aynı sayı dizisini üretmektedir.

---

## İstatistiksel Değerlendirme

Üretilen sayı dizisi üzerinde yapılan analizler,
çift ve tek sayı dağılımının dengeli olmadığını ve
algoritmanın deterministik yapıda olduğunu göstermektedir.

Bu nedenle algoritma gerçek rastgelelik sağlamamaktadır.

---

## Kriptografik Değerlendirme

AES, RSA ve ECC gibi kriptografik algoritmalar,
kriptografik olarak güvenli rastgele sayı üreteçlerine (CSPRNG) ihtiyaç duyar.

Bu projede kullanılan yöntem deterministik olduğu için:
- CSPRNG değildir
- Kriptografik anahtar üretimi için uygun değildir

Bu çalışma, kriptografide rastgeleliğin neden kritik olduğunu
göstermek amacıyla hazırlanmıştır.
