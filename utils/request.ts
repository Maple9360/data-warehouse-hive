import axios from 'axios'
import { messageHandle } from './message'
import { ElMessage, ElLoading } from 'element-plus'

const service = axios.create({
  // baseURL: 'http://10.213.129.26:8000/', // 基础请求地址
  baseURL: import.meta.env.VITE_BASE_API,
  timeout: 10000, // 请求超时设置
  withCredentials: false, // 跨域请求是否需要携带 cookie
})

const serviceConfig = {
  useTokenAuthorization: false, // 权限验证开关
}
service.interceptors.request.use((config) => {
  // console.log('请求拦截器1')
  if (serviceConfig.useTokenAuthorization) {
    config.headers.Authorization = sessionStorage.getItem('token') // 请求头携带 token
  }
  if (!config.headers['content-type']) {
    // 如果没有设置请求头
    if (config.method === 'post') {
      // config.headers['content-type'] = 'application/x-www-form-urlencoded' // post 请求
      // config.headers['content-type'] = 'multipart/form-data'
      // config.data = qs.stringify(config.data) // 序列化，后端says she doesn't need.
    } else {
      config.headers['content-type'] = 'application/json' // 默认类型
    }
  }
  // console.log(config)
  return config
  //
})
service.interceptors.response.use(
  (res) => {
    // 与服务器通信成功
    // console.log(res)
    return res
  },
  (err) => {
    // messageHandle函数需要再做更改
    let errorData = messageHandle(err) || 'ERROR'
    ElMessage.error(errorData)
  }
)

export { service }
