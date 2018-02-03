package com.payroll.pdf.business;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String publishedDate;
    private float price;
 
    public Book(String title, String author, String isbn, String publishedDate,
            float price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.price = price;
    }

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public float getPrice() {
		return price;
	}
 
    // getters and setters
 
}