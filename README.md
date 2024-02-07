# Pemrograman Lanjut A
> Tegar Wahyu Khisbulloh - 2206082032

## Module 1 - Coding Standards
### Reflection 1
You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code. Please write your reflection inside the repository's README.md file.

Pada saat menambahkan fitur baru menggunakan Spring Boot, saya telah menerapkan beberapa prinsip _clean code_ dan _secure coding practices_ yang telah saya pelajari pada modul ini. Beberapa contohnya adalah:
1. Memberikan nama yang jelas dan deskriptif pada variabel, method, dan class yang saya buat. Hal ini bertujuan agar orang lain yang membaca kode saya dapat dengan mudah memahami maksud dari kode tersebut.
2. Memecah kode menjadi beberapa fungsi yang kecil dan berfokus pada satu tugas seperti findById, edit, delete, dst. Hal ini bertujuan agar kode lebih mudah dibaca dan dimengerti.
3. Menggunakan format yang konsisten pada kode saya, seperti penamaan variabel, penulisan method, dan lain-lain. 

Namun, saya menyadari bahwa masih ada beberapa hal yang perlu diperbaiki pada kode saya. Contohnya saya hanya melakukan validasi input agar tidak negatif, mungkin selanjutnya bisa ditambahkan validasi lainnya agar lebih aman.

### Reflection 2
1. Menurut saya, tidak ada batasan pasti mengenai berapa banyak unit test yang harus dibuat dalam satu class. Namun, setidaknya kita harus membuat unit test untuk setiap method yang ada pada class tersebut. Untuk memastikan bahwa unit test yang kita buat sudah cukup untuk memverifikasi program kita, kita bisa menggunakan _code coverage_. Code coverage adalah suatu parameter yang dapat membantu kita memahami seberapa banyak kode yang sudah diuji. Namun, memiliki code coverage 100% tidak menjamin bahwa kode kita tidak memiliki bug atau error. Code coverage hanya memberikan informasi seberapa banyak kode yang sudah dicover/diuji, bukan seberapa baik kode tersebut.
2. Membuat functional test suite yang baru dengan setup procedures dan instance variables yang sama dengan functional test suite sebelumnya akan membuat kode menjadi kurang bersih. Hal ini dikarenakan kode yang kita buat akan menjadi duplikat. Duplikasi kode akan membuat kode kita menjadi sulit untuk dimaintenance dan diperbaiki. Selain itu, jika kita ingin menambahkan atau mengubah sesuatu pada kode, kita harus mengubah di semua tempat yang sama, yang tentunya akan memakan waktu dan tenaga.