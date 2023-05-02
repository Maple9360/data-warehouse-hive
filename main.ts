import { createApp } from 'vue'
// 重置全局样式
// html,body,#app的样式写在预处理器中不生效
import './style/reset.scss'


import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import ElMessage from 'element-plus'

import 'element-plus/dist/index.css'

// 多语言
import { setupI18n } from './locales/setupI18n'
// 引入iconfont图标
import './assets/iconfont/iconfont.js'
// 全局注册：图标引用简化
import svgIcon from './plugins/svgIcon.vue'
// import store from
// import * as echarts from 'echarts'

const app = createApp(App)
setupI18n(app)
app.component('svgIcon', svgIcon)
app.use(ElementPlus).use(ElMessage).use(store).use(router).mount('#app')

