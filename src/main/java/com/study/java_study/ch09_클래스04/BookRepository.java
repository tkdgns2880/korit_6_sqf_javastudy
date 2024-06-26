package com.study.java_study.ch09_클래스04;

import java.util.Arrays;

public class BookRepository { // 저장소 -> CRUD
    private int bookId;
    private BookEntity[] books;

    public BookRepository() {
        books = new BookEntity[0];
    }

    public int autoIncrementBookId() {
        return ++bookId;
    }

    private void extendBooks() {

// 기존 배열보다 크가가 하나 더 큰 배열을 생성한다
//        BookEntity[] newBooks = new BookEntity[books.length + 1];
//
//// 기존 배열의 정보를 새로운 배열로 모두 옮긴다. 이때 마지막 인덱스의 값은 반값이다.
//        for (int i = 0; i < books.length; i++) {
//            newBooks[i] = books[i];
//        }books = newBooks;
        books = Arrays.copyOf(books, books.length + 1);
// 기존의 배열이 들어있는 books 변수에 새로운 배열의 주소를 대입한다.

    }

    private int getLastIndex() {
        return books.length - 1;
    }

    public void saveBook(BookEntity book) { // 새로운 책을 하나씩 추가하는 코드
// 배열 확장
        extendBooks();

// 새로운 배열의 비어있는 마지막 인덱스에 매개변수로 받아온 book 객체를 대입한다.
        books[getLastIndex()] = book;
    }

    // 단건조회
    public BookEntity findBookByBookId(int bookId) {
        BookEntity findBook = null;

//선형탐색
        for (BookEntity book : books) {
            if (book.getBookId() == bookId) {
                findBook = book;
                break;
            }
        }
        return findBook;
    }

    // 단건조회
    public BookEntity findBookByBookName(String bookName) {
        BookEntity findBook = null;

        for (BookEntity book : books) {
            if (book.getBookName().equals(bookName)) {
                // equals 를 쓰면 이름이 같아야지 검색이 된다 (정확한 이름을 찾을때 사용)
                findBook = book;
                break;
            }

        }
        return findBook;
    }

    private int getNewArraySize(int option, String searchText) {
        int newArraySize = 0;


        switch (option) {
            case 1: // 통합검색
                for (BookEntity book : books) {
                    if (book.getBookName().contains(searchText)
                            || book.getAuthor().contains(searchText)
                            || book.getPublisher().contains(searchText)) {
                        newArraySize++;
                    }
                }
                break;
            case 2: // 도서명검색
                for (BookEntity book : books) {
                    if (book.getBookName().contains(searchText)) {
                        newArraySize++;
                    }
                }
                break;
            case 3: // 저자명검색
                for (BookEntity book : books) {
                    if (book.getAuthor().contains(searchText)) {
                        newArraySize++;
                    }
                }
                break;
            case 4: // 출판사명검색
                for (BookEntity book : books) {
                    if (book.getPublisher().contains(searchText)) {
                        newArraySize++;
                    }
                }
        }
        return newArraySize;
    }

    // 다건조회
    public BookEntity[] searchBooks(int option, String searchText) {
        int newArraySize = getNewArraySize(option, searchText);

        BookEntity[] searchBooks = new BookEntity[newArraySize];

        int i = 0;
        switch (option) {
            case 1: // 통합검색
                for (BookEntity book : books) {
                    if (book.getBookName().contains(searchText)
                            || book.getAuthor().contains(searchText)
                            || book.getPublisher().contains(searchText)) {
                        searchBooks[i] = book;
                        i++;
                    }
                }
                break;
            case 2: // 도서명검색
                for (BookEntity book : books) {
                    if (book.getBookName().contains(searchText)) {
                        searchBooks[i] = book;
                        i++;
                    }
                }
                break;
            case 3: // 저자명검색
                for (BookEntity book : books) {
                    if (book.getAuthor().contains(searchText)) {
                        searchBooks[i] = book;
                        i++;
                    }
                }
                break;
            case 4: // 출판사명검색
                for (BookEntity book : books) {
                    if (book.getPublisher().contains(searchText)) {
                        searchBooks[i] = book;
                        i++;
                    }
                }
        }
        return searchBooks;
    }

    private int indexOfBookId(int bookId) {
        int findIndex = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getBookId() == bookId) {
                findIndex = i;
                break;
            }
        }
        return findIndex;
    }
    public void deleteBookByBookId(int bookId) {
        int findIndex = indexOfBookId(bookId);
        BookEntity[] newBooks = new BookEntity[books.length - 1];
//        int findIndex = -1;
//        for(int i = 0; i < books.length; i++) {
//            if(books[i].getBookId() == bookId) {
//                findIndex = i;
//                break;
        for(int i = 0; i < newBooks.length; i++) {
            if(i < findIndex) {
                newBooks[i] = books[i];
                continue;
            }
            newBooks[i] = books[i + 1];
        }
        books = newBooks;
    }
}
