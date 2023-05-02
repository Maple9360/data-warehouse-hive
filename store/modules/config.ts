import { defineStore } from 'pinia'
export const useConfigStore = defineStore({
  id: 'config',
  state: () => ({
    lang: 'zh',
  }),
  actions: {
    /** 存储多语言选项 */
    SET_LANG(lang: string) {
      this.lang = lang
      console.log(this.lang)
      localStorage.setItem('LANG', lang)
      location.reload()
    },
  },
})
