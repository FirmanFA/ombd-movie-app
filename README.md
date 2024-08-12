
# OMDB Movie App

OMDB Movie App adalah sebuah aplikasi yang dapat digunakan untuk melihat katalog film. dibangun dalam rangka untuk menyelesaikan project test pada Brighton Real Estate


## Tech Stack

**Architecture:** MVVM - projek ini menggunakan arsitektur MVVM sebagai struktur utamanya, seperti penggunaan repository pattern, usecase, dan viewModel pada projek.

**Networking:** Retrofit, OkHttp3, OkHttp3 Interceptor - library ini digunakan untuk melakukan network request ke API pihak ketiga, juga dapat digunakan untuk menambahkan interceptor untuk setiap request menggunakan OkHttp3.

**JSON Converter:** GSON - project ini menggunakan GSON sebagai library untuk konversi dari json ke kotlin data class.

**Jetpack Compose:** library ini duigunakan untuk menulis UI dengan lebih mudah, karena bersifat deklaratif, sebagai pengganti penggunaan XML.

**Coil:** Library ini digunakan untuk menampilkan gambar yang bersumber dari URL, pada projek ini digunakan untuk menampilkan poster film.

**Dependency Injection:** Koin - projek ini menggunakan Koin sebagai library untuk Dependency Injection, library ini digunakan karena relatif lebih cepat untuk diimplementasikan daripada menggunakan hilt, sehingga cocok untuk digunakan pada test yang memiliki waktu terbatas seperti ini.


## Screenshots

![App Screenshot](https://firebasestorage.googleapis.com/v0/b/percobaan-firebase-10308.appspot.com/o/img1.jpeg?alt=media&token=9defd06c-9dfa-4571-8460-a471a0d74d13)

![App Screenshot](https://firebasestorage.googleapis.com/v0/b/percobaan-firebase-10308.appspot.com/o/img2.jpeg?alt=media&token=d4fda176-2ad0-47dc-92ce-a2df8c458aeb)

![App Screenshot](https://firebasestorage.googleapis.com/v0/b/percobaan-firebase-10308.appspot.com/o/img3.jpeg?alt=media&token=6f4e5d49-a6c0-4fac-8602-34f85942749a)
