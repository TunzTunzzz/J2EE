import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        String msg = """
                Chuong trinh quan ly sach
                 1. Them 1 cuon sach
                 2. Xoa 1 cuon sach
                 3. Thay doi cuon sach
                 4. Xuat thong tin tat ca cac cuon sach
                 5. Tim cuon sach co tua de chua chu “Lap trinh” va khong phan biet hoa thuong
                 6. Lay sach: Nhap vao 1 so K va gia sach P mong muon tim kiem. Hay lay toi da K cuon sach deu thoa man co gia sach <= P
                 7. Nhap vao 1 danh sach cac tac gia tu ban phim. Hay cho biet tat ca cuon sach cua nhung tac gia nay?
                 0. Thoat
                Chon chuc nang: """;

        int chon = 0;
        do {
            System.out.println("\n" + msg);
            try {
                chon = Integer.parseInt(x.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                continue;
            }

            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("Them sach thanh cong!");
                }
                case 2 -> {
                    System.out.print("Nhap vao ma sach can xoa: ");
                    int bookId = Integer.parseInt(x.nextLine());
                    Optional<Book> find = listBook.stream().filter(p -> p.getId() == bookId).findFirst();
                    if (find.isPresent()) {
                        listBook.remove(find.get());
                        System.out.println("Da xoa sach thanh cong");
                    } else {
                        System.out.println("Khong tim thay sach voi ma: " + bookId);
                    }
                }
                case 3 -> {
                    System.out.print("Nhap vao ma sach can dieu chinh: ");
                    int bookId = Integer.parseInt(x.nextLine());
                    Optional<Book> find = listBook.stream().filter(p -> p.getId() == bookId).findFirst();
                    if (find.isPresent()) {
                        System.out.println("Nhap thong tin moi cho sach:");
                        find.get().input(); // Update info
                        System.out.println("Cap nhat thanh cong!");
                    } else {
                        System.out.println("Khong tim thay sach voi ma: " + bookId);
                    }
                }
                case 4 -> {
                    System.out.println("\nXuat thong tin danh sach: ");
                    if (listBook.isEmpty()) {
                        System.out.println("(Danh sach rong)");
                    } else {
                        listBook.forEach(Book::output);
                    }
                }
                case 5 -> {
                    System.out.println("Cac cuon sach co tua de chua 'Lap trinh':");
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lap trinh"))
                            .toList();
                    if (list5.isEmpty())
                        System.out.println("Khong tim thay.");
                    else
                        list5.forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhap so luong K: ");
                    int k = Integer.parseInt(x.nextLine());
                    System.out.print("Nhap gia P: ");
                    double p = Double.parseDouble(x.nextLine());

                    System.out.printf("Danh sach %d cuon sach co gia <= %.2f:%n", k, p);
                    listBook.stream()
                            .filter(book -> book.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.print("Nhap danh sach cac tac gia (cach nhau boi dau phay): ");
                    String authorsInput = x.nextLine();
                    Set<String> authorSet = Arrays.stream(authorsInput.split(","))
                            .map(String::trim)
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());

                    System.out.println("Sach cua cac tac gia " + authorSet + ":");
                    listBook.stream()
                            .filter(book -> authorSet.contains(book.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Chuc nang khong ton tai.");
            }
        } while (chon != 0);
    }
}
