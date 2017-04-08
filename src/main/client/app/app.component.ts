import {Component, ViewEncapsulation} from "@angular/core";
import {Http} from "@angular/http";

export class Human {
    id: number;
    name: string;
}

@Component({
    selector: "my-app",
    styles: [require("./app.styles.scss")],
    template: require("./app.template.html"),
    encapsulation: ViewEncapsulation.None
})


export class AppComponent {
    title = "No way!!";
    hero: Human = {
        id: 1,
        name: "Alice"
    };

    constructor(private http: Http) {
    }

    private logOut() {
        this.http.post("http://localhost:8081/logout",  {})
            .map((res) => {JSON.parse(res.text())})
            .subscribe(
                (res) => {
                    console.log(res);
                },
                err => console.error(err)
            );
    }
}
