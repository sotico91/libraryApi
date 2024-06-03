import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpClientModule, HttpParams } from '@angular/common/http';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, HttpClientModule],
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  book = {
    title: '',
    author: '',
    isbn: '',
    publisher: '',
    language: '',
    genre: '',
    format: '',
    status: ''
  };
  isEdit = false;
  private baseUrl = 'https://libraryapp.fly.dev/api/v1/library/book';

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const isbn = this.route.snapshot.paramMap.get('isbn');
    if (isbn) {
      this.isEdit = true;
      let params = new HttpParams().set('isbn', isbn);
      this.http.get<any>(this.baseUrl, { params })
        .subscribe(data => {
          this.book = data;
        });
    }
  }

  onSubmit(): void {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    if (this.isEdit) {
      this.http.put(this.baseUrl, this.book, { headers })
        .subscribe(response => {
          console.log('Book updated:', response);
          this.router.navigate(['/']);
        });
    } else {
      this.http.post(this.baseUrl, this.book, { headers })
        .subscribe(response => {
          console.log('Book created:', response);
          this.router.navigate(['/']);
        });
    }
  }
}
