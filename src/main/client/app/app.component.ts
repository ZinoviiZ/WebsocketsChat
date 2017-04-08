import {Component, ViewEncapsulation} from "@angular/core";

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
}
