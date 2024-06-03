import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpParams, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-book-detail',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  book: any;
  private baseUrl = 'https://libraryapp.fly.dev/api/v1/library/book';

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {
    const isbn = this.route.snapshot.paramMap.get('isbn');
    let params = new HttpParams().set('isbn', isbn!);
    this.http.get<any>(this.baseUrl, { params })
      .subscribe(data => {
        this.book = data;
      });
  }
}
