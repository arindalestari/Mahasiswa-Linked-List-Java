
import java.util.Scanner;

class Mahasiswa {

    String nim;
    String nama;
    String jurusan;
    Mahasiswa next;

    Mahasiswa(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.next = null;
    }
}

public class MahasiswaLinkedList {

    private Mahasiswa head = null;
    private int count = 0;
    private final int MAX_DATA = 5;

    public void push(String nim, String nama, String jurusan) {
        if (count >= MAX_DATA) {
            System.out.println("\nData mahasiswa sudah mencapai batas maksimum.");
            return;
        }

        Mahasiswa newNode = new Mahasiswa(nim, nama, jurusan);
        if (head == null || head.nim.compareTo(nim) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Mahasiswa current = head;
            while (current.next != null && current.next.nim.compareTo(nim) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        count++;
        System.out.println("\nData mahasiswa berhasil ditambahkan.");
    }

    public void tampilkan() {
        if (head == null) {
            System.out.println("\nBelum ada data mahasiswa.");
            return;
        }

        System.out.println("\nDaftar Mahasiswa (berdasarkan NIM):");
        Mahasiswa current = head;
        while (current != null) {
            System.out.println("NIM     : " + current.nim);
            System.out.println("Nama    : " + current.nama);
            System.out.println("Jurusan : " + current.jurusan);
            System.out.println("--------------------------");
            current = current.next;
        }
    }

    public void popAll() {
        head = null;
        count = 0;
        System.out.println("\nSemua data mahasiswa telah dihapus.");
    }

    public void hapusByNIM(String nim) {
        if (head == null) {
            System.out.println("\nTidak ada data untuk dihapus.");
            return;
        }

        if (head.nim.equals(nim)) {
            head = head.next;
            count--;
            System.out.println("\nData mahasiswa dengan NIM " + nim + " berhasil dihapus.");
            return;
        }

        Mahasiswa current = head;
        while (current.next != null && !current.next.nim.equals(nim)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("\nData dengan NIM " + nim + " tidak ditemukan.");
        } else {
            current.next = current.next.next;
            count--;
            System.out.println("\nData mahasiswa dengan NIM " + nim + " berhasil dihapus.");
        }
    }

    public static void main(String[] args) {
        MahasiswaLinkedList db = new MahasiswaLinkedList();
        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan;

            do {
                System.out.println("\n=== MENU DATABASE MAHASISWA ===");
                System.out.println("1. Input Data Mahasiswa");
                System.out.println("2. Tampilkan Semua Data");
                System.out.println("3. Hapus Semua Data Mahasiswa");
                System.out.println("4. Hapus Data Mahasiswa (Berdasarkan NIM)");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu (1-5): ");
                pilihan = scanner.nextInt();
                scanner.nextLine(); // clear newline

                switch (pilihan) {
                    case 1 -> {
                        System.out.print("Masukkan NIM (maks 10 angka): ");
                        String nim = scanner.nextLine();
                        if (!nim.matches("\\d{1,10}")) {
                            System.out.println("NIM tidak valid!");
                            break;
                        }
                        System.out.print("Masukkan Nama (maks 30 karakter): ");
                        String nama = scanner.nextLine();
                        if (nama.length() > 30) {
                            System.out.println("Nama terlalu panjang!");
                            break;
                        }
                        System.out.print("Masukkan Jurusan (maks 50 karakter): ");
                        String jurusan = scanner.nextLine();
                        if (jurusan.length() > 50) {
                            System.out.println("Jurusan terlalu panjang!");
                            break;
                        }
                        db.push(nim, nama, jurusan);
                    }
                    case 2 ->
                        db.tampilkan();
                    case 3 ->
                        db.popAll();
                    case 4 -> {
                        System.out.print("Masukkan NIM yang ingin dihapus: ");
                        String nimToDelete = scanner.nextLine();
                        db.hapusByNIM(nimToDelete);
                    }
                    case 5 ->
                        System.out.println("\nTerima kasih telah menggunakan app ini.");
                    default ->
                        System.out.println("Pilihan tidak valid!");
                }
            } while (pilihan != 5);
        }
    }
}
