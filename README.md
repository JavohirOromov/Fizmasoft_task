# Mini App

Tashqi API'lar, xaritalar, joylashuvga asoslangan funksiyalar va lokal keshlash bilan ishlashni namoyish etuvchi kichik, **offline-first** Android ilova. Ilova foydalanuvchini **lokal** autentifikatsiya qiladi, qurilmada **yuz aniqlash** (face detection) tekshiruvini o'tkazadi, so'ngra **to'rt tabli** asosiy ekranни ochadi.

> **Asosiy tamoyil:** har bir ekran offline holatda ishlashi shart. Barcha tarmoq ma'lumotlari birinchi olinganda lokal keshga saqlanadi va keyin keshdan o'qiladi.

---

##  Imkoniyatlar

### Ekranlar oqimi

```
Splash  →  Login  →  Face Detection  →  Asosiy ilova (4 tab)
```

| # | Ekran | Tavsif |
|---|-------|--------|
| 1 | **Splash** | Ishga tushish paytida logo/brending ko'rsatiladi, so'ng Login'ga o'tadi. |
| 2 | **Login** | Login = tug'ilgan sana, parol = passport ma'lumoti. **Faqat lokal** tekshiriladi — serverga so'rov yo'q. Aniq format uchun [Login tafsilotlari](#-login-tafsilotlari) bo'limiga qarang. |
| 3 | **Face Detection** | Kamerani ochadi va qurilmada yuz aniqlashni (ML Kit) ishga tushiradi. Muvaffaqiyatli aniqlash **serversiz** login'ni yakunlaydi. |
| 4 | **Asosiy ilova** | To'rtta tabli pastki navigatsiya paneli (quyida qarang). |

###  Login tafsilotlari

Login ekranida **ikkita maydon** bo'lib, ikkalasi ham to'liq qurilmada tekshiriladi. **Ikkalasi ham to'g'ri** bo'lsa, **Login** tugmasi bosilganda **Face Detection** ekraniga o'tadi (serverga hech qanday so'rov yuborilmaydi).

| Maydon | Ma'nosi | Format | Qoidalar | To'g'ri namuna |
|--------|---------|--------|----------|----------------|
| **Login** | Tug'ilgan sana | `DDMMYYYY` (8 ta raqam) | Kun `01–31` (oyga mos, kabisa yillar hisobga olinadi), oy `01–12`, yil `1900–2012` | `15081995` → 15.08.1995 |
| **Parol** | Passport ma'lumoti | 2 harf + 7 raqam (9 ta belgi) | `^[A-Z]{2}\d{7}$` shabloni; 2 harfli seriya to'g'ri O'zbekiston passport seriyasi bo'lishi kerak (`AA`–`BZ`, masalan `AA`, `AB`, `AD`… — belgilangan ro'yxat) | `AA1234567` |

**Oqim:**

```
Tug'ilgan sana (DDMMYYYY)  +  passport (AA1234567)
        └── ikkalasi to'g'ri ──►  "Login" bosiladi  ──►  Face Detection ekrani
```

- Agar biror maydon noto'g'ri bo'lsa, xato ko'rsatiladi va ekran Login'da qoladi.
- Tekshiruv butunlay lokal (`ValidateBirthDateUseCase`, `ValidatePassportUseCase`) — **backend chaqiruvi yo'q**.

### Tablar va ma'lumot manbalari

| Tab | Funksiya | Ma'lumot manbai | Asosiy jihatlar |
|-----|----------|-----------------|-----------------|
|  **Kinolar** | Kinolar ro'yxati + tafsilotlar | [TMDB API](https://www.themoviedb.org/) | Poster, nom, reyting, tafsilotlar. Offline uchun keshlanadi. |
|  **Xarita** | Joriy joylashuvga markazlangan xarita | Google Maps SDK + qurilma joylashuvi | Foydalanuvchi markeri, pan/zoom. Oxirgi joylashuv keshlanadi. |
|  **Namoz vaqtlari** | Joylashuv bo'yicha kunlik namoz vaqtlari | [Aladhan API](https://aladhan.com/prayer-times-api) | 5 vaqt, keyingi namozgacha jonli sanoq, har vaqtga **lokal bildirishnoma**. Offline uchun keshlanadi. |
|  **Ob-havo** | Joylashuv uchun joriy ob-havo | [Open-Meteo API](https://open-meteo.com/) | Harorat, holat, namlik/shamol/his etiladi, soatlik prognoz. Offline keshlanadi. |

### Funksional bo'lmagan jihatlar

- **Offline-first / lokal keshlash** — barcha API javoblari va oxirgi xarita holati lokal (Room) saqlanadi va offline'da keshdan beriladi.
- **Lokal bildirishnomalar** — namoz vaqti eslatmalari `AlarmManager` orqali qurilmada rejalashtiriladi, shuning uchun internetdan qat'i nazar o'z vaqtida otiladi.
- **Lokal autentifikatsiya** — login tekshiruvi va yuz aniqlash butunlay qurilmada, backend'siz ishlaydi.
- **Clean Architecture** — ma'lumot qatlami (API + kesh), biznes-logika va UI qat'iy ajratilgan.

---

##  Arxitektura

Loyiha **Clean Architecture** tamoyiliga amal qiladi: `data`, `domain` va `presentation` qatlamlariga aniq ajratilgan va **Hilt** dependency injection bilan bog'langan.

```
com.javohir.fizmasofttask
├── activity          # MainActivity (yagona activity host)
├── app               # Application klass (MiniApp), bildirishnoma kanali
├── core              # Umumiy komponentlar
│   ├── detector      # ML Kit yuz aniqlagichi
│   ├── di            # App darajasidagi DI modullar (location, detector)
│   ├── notification  # Namoz alarm scheduler + BroadcastReceiver
│   ├── ui            # Mavzu, ranglar, umumiy UI
│   └── util          # Resource<T> wrapper, yordamchilar
├── data              # Ma'lumot qatlami
│   ├── di            # Network / Database / Repository modullar
│   ├── local         # Room bazasi, DAO'lar, entity'lar, lokal mapper'lar
│   ├── remote        # Retrofit API'lar, DTO'lar, remote mapper'lar
│   └── repository    # Repository implementatsiyalari
├── domain            # Biznes qatlami (sof Kotlin)
│   ├── model         # Domain modellar
│   ├── repository    # Repository interfeyslari
│   └── usecase       # Use case'lar
└── presentation      # UI qatlami (Jetpack Compose, MVI uslubi)
    ├── auth          # Login + Face detection
    ├── main          # Pastki navigatsiya + Kinolar / Xarita / Namoz / Ob-havo tablari
    ├── navigation    # Navigatsiya grafi
    └── splash        # Splash ekrani
```

**Pattern:** har bir ekran MVI uslubidagi kontraktdan foydalanadi — `State`, `Intent` (foydalanuvchi harakati) va `Event` (bir martalik effektlar) — bularni `StateFlow<State>` chiqaruvchi `@HiltViewModel` boshqaradi.

**Offline strategiyasi:** repository'lar tarmoqdan oladi, DTO → domain modelga o'giradi, Room'ga keshlaydi va tarmoq bo'lmaganda keshdagi nusxaga qaytadi.

---

## 🛠️Texnologiyalar

| Soha | Kutubxona |
|------|-----------|
| Til | Kotlin 2.2 |
| UI | Jetpack Compose (Material 3), Navigation Compose |
| DI | Hilt |
| Tarmoq | Retrofit + OkHttp (logging interceptor) + Gson |
| Lokal kesh | Room |
| Rasmlar | Coil |
| Kamera | CameraX |
| Yuz aniqlash | ML Kit Face Detection |
| Xaritalar | Maps Compose (Google Maps SDK) |
| Joylashuv | Play Services Location |
| Ruxsatlar | Accompanist Permissions |
| Bildirishnomalar | `AlarmManager` + `NotificationCompat` |

---

##  Ma'lumot manbalari

| API | Base URL | Auth |
|-----|----------|------|
| TMDB (kinolar) | `https://api.themoviedb.org/3/` | API kalit (OkHttp interceptor orqali qo'shiladi) |
| Open-Meteo (ob-havo) | `https://api.open-meteo.com/` | Yo'q |
| Aladhan (namoz vaqtlari) | `https://api.aladhan.com/` | Yo'q |

---

##  Ishga tushirish

### Talablar

- Android Studio (oxirgi barqaror versiya)
- JDK 11
- **compileSdk 37**, **minSdk 24**, **targetSdk 36**
- Haqiqiy qurilma yoki emulyator (kamera + bildirishnomalar uchun haqiqiy qurilma tavsiya etiladi)



##  Namoz bildirishnomalari

Namoz vaqti eslatmalari qurilmada rejalashtiriladi, shuning uchun internetsiz ham otiladi:

- **Namoz vaqtlari** tabi yuklanganda ilova joylashuv bo'yicha bugungi vaqtlarni oladi va har bir qolgan namoz uchun `AlarmManager` orqali aniq alarm (`setExactAndAllowWhileIdle`) rejalashtiradi.
- Statik `BroadcastReceiver` (`PrayerAlarmReceiver`) alarm otganda bildirishnomani ko'rsatadi, shuning uchun ilova jarayoni o'chirilgan bo'lsa ham ishlaydi.
- Android 13+ da ilova `POST_NOTIFICATIONS` ruxsatini so'raydi; Android 12+ da `SCHEDULE_EXACT_ALARM` ruxsatidan foydalanadi (aniq bo'lmagan alarmga to'g'ri fallback bilan).
MIUI telefonlarida notifcation ishlashi uchun battery cheksizni yoqish kerak va autostart permession berilishi kerak

> **Eslatma:** alarm'lar joriy kun uchun rejalashtiriladi va Namoz tabi har ochilganda qayta o'rnatiladi. Reboot'dan keyin / keyingi kunga qayta rejalashtirish — tabiiy keyingi yaxshilanish (`BOOT_COMPLETED` receiver + kunlik `WorkManager` jobi).

---

## Ruxsatlar

| Ruxsat | Nima uchun |
|--------|-----------|
| `INTERNET` | API chaqiruvlari |
| `CAMERA` | Yuz aniqlash |
| `ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION` | Xarita, ob-havo, namoz vaqtlari |
| `POST_NOTIFICATIONS` | Namoz eslatmalari (Android 13+) |
| `SCHEDULE_EXACT_ALARM` | Aniq namoz vaqti alarm'lari (Android 12+) |

---

