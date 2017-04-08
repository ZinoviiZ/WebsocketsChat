import { Component } from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";

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

        let request = new RequestOptions();
        request.headers = new Headers();
        request.headers.set("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        let body:URLSearchParams = new URLSearchParams();
        body.set("username", this.user.username);
        body.set("password", this.user.password);

        console.log(request);

        this.http.post("http://localhost:8081/login", body.toString(), request) //JSON.stringify(this.user))
            .map((res) => {JSON.parse(res.text())})
            .subscribe(
                (res) => {
                    console.log(res);
                },
                err => { console.error(err) }
            );
    }
}