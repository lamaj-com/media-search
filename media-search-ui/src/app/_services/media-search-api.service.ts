import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Media } from '../_model/media';
import { APP_CONFIG, AppConfig } from '../_utilities/app.config';


@Injectable({ providedIn: 'root' })
export class MediaSearchApiService {

    constructor(@Inject(APP_CONFIG) private config: AppConfig) {
    }

    streamMediaRecords(searchTerm: string): Observable<Media> {
        return new Observable<Media>((observer) => {
            let eventSource = new EventSource(this.config.apiEndpoint + '/search?searchTerm=' + searchTerm);
            eventSource.onmessage = (event) => {
                //console.debug('Received event: ', event);
                let json = JSON.parse(event.data);
                observer.next(new Media(json['title'], json['authors'], json['mediaType']));
            };
            eventSource.onerror = (error) => {
                // readyState === 0 means the remote source closed the connection,
                // so we can safely treat it as a normal situation.
                if (eventSource.readyState === 0) {
                    eventSource.close();
                    observer.complete();
                } else {
                    observer.error('EventSource error: ' + error);
                }
            }
        });
    }







}
