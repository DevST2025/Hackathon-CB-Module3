package ra.run;

import ra.bussiness.Book;
import ra.util.InputMethods;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BookManagement {
    static final Book[] arrBook = new Book[100];
    static int curIndex = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;
        do {
            System.out.println(
                    "****************MENU*************** \n" +
                            "" +
                            "1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách\n" +
                            "2. Hiển thị thông tin tất cả sách trong thư viện\n" +
                            "3. Sắp xếp sách theo lợi nhuận tăng dần\n" +
                            "4. Xóa sách theo mã sách\n" +
                            "5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả\n" +
                            "6. Thay đổi thông tin sách theo mã sách\n" +
                            "7. Thoát \n"+ "Hãy nhập  lưa chọn : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    inputDataBook();
                    break;
                case 2:
                    displayDataBook();
                    break;
                case 3:
                    sortByProfit();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    System.out.println("Thoát khỏi chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn của bạn  không hợp lệ. Vui lòng chọn lại　.");
            }
        } while (choice != 7);
    }

    //1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách
    public static void inputDataBook() {
        int bookSize;
        System.out.print("Nhập số sách mà bạn muốn điền thông tin: ");
        while (true) {
            bookSize = InputMethods.getInteger();
            if (bookSize > 0) {
                break;
            } else {
                System.err.println("Số lượng sách phải lớn hơn 0");
            }
        }

            for (int i = 0; i < bookSize; i++) {
                System.out.printf("Nhập thông tin sách thứ %d: \n", i + 1);
                arrBook[curIndex] = new Book();
                arrBook[curIndex].increaseId(arrBook, curIndex);
                arrBook[curIndex].inputData();
                arrBook[curIndex].calProfit();
                curIndex++;
                System.out.println("--------------------");
            }
    }

    //2. Hiển thị thông tin tất cả sách trong thư viện
    public static void displayDataBook() {
        System.out.println("-----Thông tin tất cả sách trong thư viện-----");
        if (curIndex == 0) {
            System.err.println("Hiện tại không có sách trong thư viện");
            return;
        }
        for (int i = 0; i < curIndex; i++) {
            System.out.printf("Thông tin sách thứ %d: \n", i + 1);
            arrBook[i].displayData();
            System.out.println("--------------------");
        }
    }

    //3. Sắp xếp sách theo lợi nhuận tăng dần
    public static void sortByProfit() {
        for (int i = 0; i < curIndex - 1; i++) {
            for (int j = i + 1; j < curIndex; j++) {
                if (arrBook[i].getInterest() > arrBook[j].getInterest()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[j];
                    arrBook[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp sách theo lợi nhuận tăng dần");
    }

    //4. Xóa sách theo mã sách
    public static void removeBook() {
        int inputId;
        System.out.print("Nhập mã sách mà bạn muốn xoá: ");
        while (true) {
            inputId = InputMethods.getInteger();
            if (inputId < 1 && inputId > curIndex) {
                System.err.println("Mã sách không tồn tại");
            } else {
                break;
            }
        }
        for (int i = 0; i < curIndex - 1; i++) {
            if (arrBook[i].getBookId() == inputId) {
                arrBook[i] = arrBook[i + 1];
            }
        }
        arrBook[curIndex] = null;
        curIndex--;
        System.out.printf("Đã xoá sách có mã %d\n", inputId);
    }

    //5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả
    public static void searchBook() {
        int index = -1;
        int count = 0;
        System.out.print("Nhập tên sách hoặc mô tả mà bạn muốn tìm: ");
        String inputText = InputMethods.getString();

        for (int i = 0; i < curIndex; i++) {
            if (arrBook[i].getBookName().toLowerCase().contains(inputText.toLowerCase()) || arrBook[i].getDescriptions().toLowerCase().contains(inputText.toLowerCase())) {
                index = i;
                arrBook[i].displayData();
                count++;
                System.out.println("--------------------");
            }
        }
        if (index == -1) {
            System.out.printf("Không tìm thấy sách nào có tên hay mô tả là \"%s\"\n",inputText);
        } else {
            System.out.printf("Đã tìm thấy %d sách có tên hay mô tả là \"%s\"\n", count, inputText);
        }
    }

    //6. Thay đổi thông tin sách theo mã sách
    public static void updateBook() {
        int inputId;
        System.out.print("Nhập mã sách mà bạn muốn update: ");
        while (true) {
            inputId = InputMethods.getInteger();
            if (inputId < 1 && inputId > curIndex) {
                System.err.println("Mã sách không tồn tại");
            } else {
                break;
            }
        }
        for (int i = 0; i < curIndex; i++) {
            if (arrBook[i].getBookId() == inputId) {
                arrBook[i].inputData();
                arrBook[i].calProfit();
                System.out.print("Bạn có muốn đổi trạng thái sách không? (Y/N):  ");
                String input = InputMethods.getString();
                if (Pattern.matches("^(Y|y)$", input)) {
                    arrBook[i].setBookStatus(!arrBook[i].isBookStatus());
                } else if (Pattern.matches("^(N|n)$", input)) {
                    arrBook[i].setBookStatus(arrBook[i].isBookStatus());
                }
            }
            System.out.println();
        }
    }
}
