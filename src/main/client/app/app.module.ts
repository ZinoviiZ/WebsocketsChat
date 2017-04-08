import { NgModule }      from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { MaterialModule } from '@angular/material';
import { AppComponent }  from "./app.component";
import {RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";

@NgModule({
    imports:      [
        BrowserModule,
        FormsModule,
        MaterialModule,
        RouterModule.forRoot([
            {
                path: 'auth',
                component: LoginComponent
            },
            {
                path: 'registration',
                component: RegistrationComponent
            },

        ])
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        RegistrationComponent
    ],
    bootstrap:    [ AppComponent ]
})

export class AppModule { }