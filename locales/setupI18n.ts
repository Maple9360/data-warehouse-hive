import { createI18n } from 'vue-i18n' // 引入vue-i18n组件
import messages from './lang/index' // 如果配置少可以不用分en-US、zh-CN等，直接导出一个对象: { en: {},zh: {} }
import { useConfigStore } from '../store/modules/config'
import store from '../store/index' // 不加有挂载问题= =
const config = useConfigStore(store)
config.lang = 'zhtw'

const localeData = {
  legacy: true, // composition API ?
  //   locale: 'zh', 此处命名要和文件统一，不然会报警告,如果是引入index文件中，此处要写index中的引入名
  //   locale: '',
  locale: localStorage.getItem('LANG') || 'zh',
  globalInjection: true, // 有此配置才可以用$t('')
  fallbackLocale: 'en', // 没有翻译的语言，默认回退显示的
  parsePlaceholder: false,
  messages,
}

export const setupI18n = (app:any) => {
  const i18n = createI18n(localeData)
  app.config.globalProperties.$t = i18n.global.t // 手动挂载$t，为了js代码中能用
  app.use(i18n)
}
