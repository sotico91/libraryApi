import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { AppComponent } from './app/app.component';
import { BookListComponent } from './app/components/book-list/book-list.component';
import { BookDetailComponent } from './app/components/book-detail/book-detail.component';
import { BookFormComponent } from './app/components/book-form/book-form.component';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

const routes: Routes = [
  { path: '', component: BookListComponent },
  { path: 'book/:isbn', component: BookDetailComponent },
  { path: 'new-book', component: BookFormComponent },
  { path: 'edit-book/:isbn', component: BookFormComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(), provideAnimationsAsync()
  ]
})
  .catch(err => console.error(err));
