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

## Module 2 - CI/CD & DevOps
### Reflection


#### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. (minimum 3 sentences)

Setelah melakukan analisis menggunakan SonarCloud, saya menemukan beberapa issue pada kode saya. Beberapa issue tersebut adalah:
- **Issue: Remove Unnecessary public modifier.**
   ```java
  import static org.mockito.Mockito.*;
    
  @ExtendWith(MockitoExtension.class)
  public class ProductServiceImplTest {
  @Mock
  ProductRepository productRepository;
   ...
  ```
- **Solusi:**
  ```java
   import static org.mockito.Mockito.*;
    
   @ExtendWith(MockitoExtension.class)
   class ProductServiceImplTest {
   @Mock
   ProductRepository productRepository;
  ...
   ```
  Saya menghapus public modifier pada class ProductServiceImplTest karena class tersebut hanya digunakan untuk testing dan tidak perlu diakses dari luar class.
- **Issue: Remove this unused import on HomePageController.java**
   ```java
  package id.ac.ui.cs.advprog.eshop.controller;
    
  import id.ac.ui.cs.advprog.eshop.model.Product;
  import id.ac.ui.cs.advprog.eshop.service.ProductService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.*;
    
  import java.util.List;
    
    
  @Controller
  @RequestMapping ("/")
  public class HomePageController {
  ...
  ```
- **Solusi:**
   ```java
  package id.ac.ui.cs.advprog.eshop.controller;

  @Controller
  @RequestMapping ("/")
  public class HomePageController {
  ...
  ```
  Saya menghapus import yang tidak digunakan pada class HomePageController agar kode saya lebih bersih dan mudah dibaca.
- **Issue: Remove unnecessary public modifier on CreateProductFunctionalTest.java**
   ```java
  ...
    @SpringBootTest(webEnvironment = RANDOM_PORT)
    @ExtendWith(SeleniumJupiter.class)
    public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;
  ...
  ```
- **Solusi:**
   ```java
  ...
    @SpringBootTest(webEnvironment = RANDOM_PORT)
    @ExtendWith(SeleniumJupiter.class)
    class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;
  ...
  ```
  Saya menghapus public modifier pada class CreateProductFunctionalTest karena class tersebut hanya digunakan untuk testing dan tidak perlu diakses dari luar class.
2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

   Menurut saya, implementasi CI/CD yang saya buat sudah memenuhi definisi CI/CD. Implementasi CI yaitu pada GitHub Actions akan menjalankan workflows `sonarcloud.yml`, `ci.yml`, dan `scorecard.yml`. Selain itu, saya juga sudah membuat pipeline yang akan melakukan deploy otomatis ke Koyeb setiap kali saya melakukan push ke repository. Hal ini sudah memenuhi definisi CD.