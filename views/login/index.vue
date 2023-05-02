<template>
  <div style="color: #fff">test</div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getUserInfo } from '@/api/login.ts'
import { useUserStore } from '@/store/modules/user.ts'
const user = useUserStore()

onMounted(() => {
  console.log('login onmounted')
  const loginFunction = new Promise<void>((resolve, reject) => {
    const route = useRoute()
    const {
      path,
      query: { sglink },
    } = route
    const existToken = () => {
      // session中有无token
      if (sessionStorage.getItem('TOKEN')) {
        return true
      } else {
        return false
      }
    }
    const existLink = () => {
      return sglink ? true : false
    }

    const loginFirst = () => {
      // 第一次登录
      console.log(
        '-------------------------- first login ---------------------------'
      )
      getUserInfo({ md5userid: sglink }).then(
        (res: any) => {
          // console.log(res)
          // console.log(route)
          const rd = res.data
          if (rd.message === 'Login succeeded!') {
            user.id = rd.empNo
            user.name = rd.userName
            user.token = rd.token
            user.setSession() // 存入session
            // console.log(sessionStorage.getItem('id'))

            // ================================ 登录成功 ==============================
            resolve()
            //
          }

          if (rd.status === 'NG') {
            if (
              rd.message ===
              'Login timeout for this job ID, please login again!'
            ) {
              ElMessage({
                showClose: true,
                message:
                  'Session has expired, please login from Smart Guard system!',
                type: 'error',
              })
              location.href = 'https://nsdsg.efoxconn.com/home/home'
            }
          }
        },
        (res) => {
          // 服务器连接不通
        }
      )
    }

    if (existToken()) {
      // 有token
      // token过期在响应拦截器中处理
      console.log('有token')
      user.getUserInfo() // 直接读取session中的token
      // ================================ 刷新成功 ==============================
      router.push({
        path: '/homepage',
      })
    } else {
      // 无token
      if (existLink()) {
        loginFirst()
      } else {
        // sglink不存在，报错
        // console.log('无token, 无sglink')
        ElMessage({
          showClose: true,
          message: 'Please login from Smart Guard system!',
          type: 'error',
        })
        location.href = 'https://nsdsg.efoxconn.com/home/home'
      }
    }
  })
  loginFunction.then(
    () => {
      console.log('resolve')
      router.push({
        path: '/homepage',
      })
    },
    () => {
      alert('Invalid login, please login from Smart Guard system!')
      console.log('reject')
    }
  )
})
</script>

<style scoped></style>
