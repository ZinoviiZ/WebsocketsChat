import { Component } from "@angular/core";
import {Http} from "@angular/http";

export class User {
    name: string;
    lastName: string;
    password: string;
    repeatPassword: string;
}

@Component({
    selector: "auth",
    styles: [require("./registration.styles.scss")],
    template: require("./registration.template.html")
})


export class RegistrationComponent {
    private user = new User();

    constructor(private http: Http) {
    }

    private signIn() {
        this.http.post('https://likeittrello.herokuapp.com/rest/login', JSON.stringify(this.user))
            .map((res) => {JSON.parse(res.text())})
            .subscribe(
                (res) => {},
                err => console.error(err)
            );
    }
}