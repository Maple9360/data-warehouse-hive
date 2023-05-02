"use strict";
exports.__esModule = true;
exports.constantRoutes = void 0;
var vue_router_1 = require("vue-router");
var index_vue_1 = require("../layout/index.vue");
exports.constantRoutes = [
    {
        path: '/',
        redirect: '/dashboard/index',
        meta: {}
        // component:
    },
    {
        path: '/login',
        component: function () { return Promise.resolve().then(function () { return require('../views/login/index.vue'); }); }
    },
    {
        path: '/authority',
        component: function () { return Promise.resolve().then(function () { return require('../views/authority/index.vue'); }); }
    },
    {
        path: '/dashboard',
        redirect: '/dashboard/index',
        meta: {},
        component: index_vue_1["default"],
        children: [
            {
                path: 'index',
                component: function () { return Promise.resolve().then(function () { return require('../views/dashboard/index.vue'); }); }
            }
        ]
        // component:
    }
];
var router = vue_router_1.createRouter({
    history: vue_router_1.createWebHashHistory(),
    routes: exports.constantRoutes
});
exports["default"] = router;
