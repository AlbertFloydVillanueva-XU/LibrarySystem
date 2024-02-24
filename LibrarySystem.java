//Albert Floyd C. Villanueva
//ITCC 11.1 B 
//Activity 3

import java.util.ArrayList;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private int year;
    private boolean borrowed;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("___________________________________");
            System.out.println("Book \"" + title + "\" is borrowed.");
        } else {
            System.out.println("___________________________________");
            System.out.println("Book \"" + title + "\" is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("___________________________________");
            System.out.println("Book \"" + title + "\" is returned.");
        } else {
            System.out.println("___________________________________");
            System.out.println("Book \"" + title + "\" is not present with user.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + year + ")";
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runtime;
    private boolean borrowed;

    public DVD(String title, String director, int runtime) {
        this.title = title;
        this.director = director;
        this.runtime = runtime;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("___________________________________");
            System.out.println("DVD \"" + title + "\" is borrowed.");
        } else {
            System.out.println("___________________________________");
            System.out.println("DVD \"" + title + "\" is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("___________________________________");
            System.out.println("DVD \"" + title + "\" is returned.");
        } else {
            System.out.println("___________________________________");
            System.out.println("DVD \"" + title + "\" is not present with user.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return title + " directed by " + director + " (" + runtime + " mins)";
    }
}

abstract class LibraryUser {
    abstract void printItemsBorrowed();

    void borrowItem(LibraryItem item) {
        item.borrowItem();
    }

    void returnItem(LibraryItem item) {
        item.returnItem();
    }
}

class Student extends LibraryUser {
    private ArrayList<LibraryItem> borrowedItems = new ArrayList<>();

    @Override
    void printItemsBorrowed() {
        System.out.println("___________________________________");
        System.out.println("Student has borrowed the following items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
    }

    @Override
    void borrowItem(LibraryItem item) {
        super.borrowItem(item);
        borrowedItems.add(item);
    }

    @Override
    void returnItem(LibraryItem item) {
        super.returnItem(item);
        borrowedItems.remove(item);
    }
}

class Teacher extends LibraryUser {
    private ArrayList<LibraryItem> borrowedItems = new ArrayList<>();

    @Override
    void printItemsBorrowed() {
        System.out.println("___________________________________");
        System.out.println("Teacher has borrowed the following items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
    }

    @Override
    void borrowItem(LibraryItem item) {
        super.borrowItem(item);
        borrowedItems.add(item);
    }

    @Override
    void returnItem(LibraryItem item) {
        super.returnItem(item);
        borrowedItems.remove(item);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<DVD> dvds = new ArrayList<>();

        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));

        dvds.add(new DVD("Inception", "Christopher Nolan", 148));
        dvds.add(new DVD("The Shawshank Redemption", "Frank Darabont", 142));

        Student student = new Student();
        Teacher teacher = new Teacher();
        
        //Items are borrowed
        student.borrowItem(books.get(0));
        student.borrowItem(dvds.get(0));

        teacher.borrowItem(books.get(1));
        teacher.borrowItem(dvds.get(1));

        //Items are printed
        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        //Items are returned
        student.returnItem(books.get(0));
        teacher.returnItem(books.get(0));

        //Items are printed again
        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        //Showcasing if the item is already borrowed
        student.borrowItem(dvds.get(0));
        teacher.borrowItem(dvds.get(1));

        //Showcasing if the item is not present when returning
        student.returnItem(books.get(0));
        teacher.returnItem(books.get(0));
    }
}
