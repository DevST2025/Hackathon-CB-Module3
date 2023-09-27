package ra.bussiness;

import ra.util.InputMethods;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float  interest;
    private boolean bookStatus;

    public Book() {
    }

    public Book(int bookId, String bookName, String author, String descriptions, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }


    public void inputData() {
        //Book Name
        System.out.print("Nhập tên sách: ");
        this.bookName = InputMethods.getString();

        //Author
        System.out.print("Nhập tác giả: ");
        this.author = InputMethods.getString();

        //Descriptions
        System.out.print("Nhập mô tả: ");
        this.descriptions = InputMethods.getString();

        //Import Price
        while (true) {
            System.out.print("Nhập giá thu: ");
            this.importPrice = InputMethods.getDouble();
            if (this.importPrice > 0) {
                break;
            } else {
                System.err.println("Giá thu phải lớn hơn 0");
            }
        }

        //Export Price
        while (true) {
            System.out.print("Nhập giá bán: ");
            this.exportPrice = InputMethods.getDouble();
            if (this.exportPrice > this.importPrice * 1.2) {
                break;
            } else {
                System.err.println("Giá bán phải lớn hơn 20% giá trị giá thu");
            }
        }

        //Book Status
        this.bookStatus = true;
    }

    public void calProfit() {
        this.interest = (float) (this.exportPrice - this.importPrice);
    }

    public void increaseId(Book[] arrBook, int curIndex) {
        int maxId = 0;
        for (int i = 0; i < curIndex; i++) {
            if (arrBook[i].getBookId() > maxId) {
                maxId = arrBook[i].getBookId();
            }
        }
        this.bookId = maxId + 1;
    }
    public void displayData() {
        System.out.println("Mã sách: " + bookId);
        System.out.println("Tên sách: " + bookName);
        System.out.println("Tác giả: " + author);
        System.out.println("Mô tả: " + descriptions);
        System.out.println("Giá nhập: " + importPrice);
        System.out.println("Giá xuất: " + exportPrice);
        System.out.println("Lợi nhuận: " + interest);
        System.out.println("Trạng thái: " + (bookStatus ? "Còn hàng" : "Hết hàng"));
    }
}
