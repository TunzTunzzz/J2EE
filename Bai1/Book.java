import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void input() {
        Scanner x = new Scanner(System.in);
        System.out.print("Nhap ma sach: ");
        this.id = Integer.parseInt(x.nextLine());
        System.out.print("Nhap ten sach: ");
        this.title = x.nextLine();
        System.out.print("Nhap Tac gia: ");
        this.author = x.nextLine();
        System.out.print("Nhap Don gia: ");
        this.price = Double.parseDouble(x.nextLine());
    }

    public void output() {
        System.out.printf("BOOK: id= %d, title=%s, author=%s, price=%.2f%n", id, title, author, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
