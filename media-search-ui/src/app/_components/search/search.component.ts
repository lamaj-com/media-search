import { ChangeDetectorRef, Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { Subscription } from 'rxjs/internal/Subscription';
import { Media } from 'src/app/_model/media';
import { MediaSearchApiService } from 'src/app/_services/media-search-api.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {

  searchValue: string;
  mediaRecords: Media[] = [];
  subscription: Subscription;
  mediaObservable: Observable<Media>;

  constructor(public mediaSearchApiService: MediaSearchApiService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
  }

  search(searchTerm: string): void {
    this.mediaRecords = [];
    this.mediaObservable = this.mediaSearchApiService.streamMediaRecords(searchTerm);
    this.subscription = this.mediaObservable.subscribe(media => {
      this.mediaRecords.push(media);
      this.cdr.detectChanges();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
