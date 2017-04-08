import { Component } from "@angular/core";
import {Http} from "@angular/http";

export class User {
    username: string;
    password: string;
}

@Component({
    selector: "auth",
    styles: [require("./login.styles.scss")],
    template: require("./login.template.html")
})


export class LoginComponent {
    private user = new User();

    constructor(private http: Http) {
    }

    private logIn() {
        console.log("AWd");
       this.http.post("http://localhost:8081/login", JSON.stringify(this.user))
            .map((res) => {JSON.parse(res.text())})
            .subscribe(
                (res) => {
                    console.log(res);
                },
                err => console.error(err)
            );
    }
}