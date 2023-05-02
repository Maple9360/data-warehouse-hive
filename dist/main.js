"use strict";
exports.__esModule = true;
var vue_1 = require("vue");
// 重置全局样式
// html,body,#app的样式写在预处理器中不生效
require("./style/reset.scss");
var App_vue_1 = require("./App.vue");
var router_1 = require("./router");
var store_1 = require("./store");
var element_plus_1 = require("element-plus");
require("element-plus/dist/index.css");
// import store from
vue_1.createApp(App_vue_1["default"]).use(element_plus_1["default"]).use(store_1["default"]).use(router_1["default"]).mount('#app');
