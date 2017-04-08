import "zone.js";
import "reflect-metadata";
import "hammerjs";

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule }              from './app/app.module';

platformBrowserDynamic().bootstrapModule(AppModule);

export function main(): Promise<any> {
    return platformBrowserDynamic()
        .bootstrapModule(AppModule)
        //.then(decorateModuleRef)
        .catch((err) => console.error(err));
}

