var webpack = require("webpack");
var path = require("path");
var HtmlWebpackPlugin = require("html-webpack-plugin");

function root(p) {
    return path.resolve(__dirname, p);
}

module.exports = {
    entry: {
        "app": "./src/main/client/main"
    },
    output: {
        path: root("src/main/resources/static"),
        filename: "[name].bundle.js"
    },
    module: {
        rules: [
            {
                test: /\.ts/,
                loader: "awesome-typescript-loader",
                exclude: /node_modules/
            },

            {
                test: /\.css$/,
                exclude: /node_modules/,
                use: ['raw-loader']
            },

            {
                test: /\.scss$/,
                exclude: /node_modules/,
                use: [{
                    loader: 'raw-loader'
                }, {
                    loader: 'sass-loader'
                }]
            },

            {
                test: /\.html$/,
                exclude: /node_modules/,
                use: ['raw-loader']
            }
        ]
    },
    resolve: {
        modules: [
            "node_modules",
            root("src/main/resources/static/app")
        ],
        extensions: [".js", ".ts"],
        alias: {

        }
    },
    devtool: "source-map",
    target: "web",
    plugins: [

        new HtmlWebpackPlugin({
            title: "My App",
            filename: root("./src/main/resources/static/index.html"),
            template: root("./src/main/client/index.html")
        }),

        new webpack.optimize.UglifyJsPlugin({
            minimize: true,
            sourceMap: true
        })

    ]
};