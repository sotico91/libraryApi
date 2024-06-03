import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  searchTerm: string = '';
  books: any[] = [];
  totalElements: number = 0;
  totalPages: number = 0;
  currentPage: number = 0;
  size = 4;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    let params = new HttpParams().set('page', this.currentPage.toString()).set('size', this.size.toString());
    this.http.get<any>('https://libraryapp.fly.dev/api/v1/library/book/listBooks', { params })
      .subscribe(data => {
        this.books = data.content;
        this.totalElements = data.totalElements;
        this.totalPages = data.totalPages;
      }, error => {
        console.error('Error loading books:', error);
      });
  }

  searchBooks(): void {
    let params = new HttpParams().set('page', '0').set('size', this.size.toString()).set('searchTerm', this.searchTerm);
    this.http.get<any>('https://libraryapp.fly.dev/api/v1/library/book/listBooks', { params })
      .subscribe(data => {
        this.books = data.content;
        this.totalElements = data.totalElements;
        this.totalPages = data.totalPages;
        this.currentPage = 0;
      }, error => {
        console.error('Error searching books:', error);
      });
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadBooks();
    }
  }

  prevPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadBooks();
    }
  }

  get filteredBooks(): any[] {
    return this.books.filter(book =>
      book.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.author.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.isbn.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.publisher.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.language.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.genre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.format.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      book.status.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

}