import { defineStore } from 'pinia'
import { userType } from './types'
export const useUserStore = defineStore({id:'user',
  state: (): userType => ({
    username: 'string',
    empno: 'string', // employee number
    role: 'string',
    module: [], //

  }),
  actions: {
    /** 存储用户名 */
    SET_USERNAME(username: string) {
      this.username = username
    },
    /** 存储角色 */ 
    SET_ROLES(role: string) {
      this.role = role
    },
    /** 登入 */
    async loginByUsername(data: Object) {
      // return new Promise<UserResult>((resolve, reject) => {
      //   // getLogin
      // })
    }
  }
})

