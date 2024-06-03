import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'https://libraryapp.fly.dev/api/v1/library/book';

  constructor(private http: HttpClient) { }

  listBooks(page: number, size: number): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    return this.http.get(`${this.baseUrl}/listBooks`, { params });
  }

  getBookByIsbn(isbn: string): Observable<any> {
    let params = new HttpParams().set('isbn', isbn);
    return this.http.get(this.baseUrl, { params });
  }

  updateBook(book: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(this.baseUrl, book, { headers });
  }

  getBooksByAuthor(name: string, page: number, size: number): Observable<any> {
    let params = new HttpParams().set('name', name).set('page', page).set('size', size);
    const headers = new HttpHeaders({ 'accept': 'application/json' });
    return this.http.get(`${this.baseUrl}/author`, { headers, params });
  }

  createBook(book: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.baseUrl, book, { headers });
  }
}