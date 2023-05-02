import { ElMessage, ElLoading } from 'element-plus'
export const messageHandle = (error) => {
  //   console.log(error)
  let message: string
  if (error && error.response) {
    switch (error.response.status) {
      case 302:
        message = '接口重定向了！'
        break
      case 400:
        message = '参数不正确！'
        break
      case 401:
        message = '您未登录，或者登录已经超时，请先登录！'
        break
      case 403:
        message = '您没有权限操作！'
        break
      case 404:
        message = `请求地址出错: ${error.response.config.url}`
        break
      case 408:
        message = '请求超时！'
        break
      case 409:
        message = '系统已存在相同数据！'
        break
      case 500:
        message = '服务器内部错误！'
        break
      case 501:
        message = '服务未实现！'
        break
      case 502:
        message = '网关错误！'
        break
      case 503:
        message = '服务不可用！'
        break
      case 504:
        message = '服务暂时无法访问，请稍后再试！'
        break
      case 505:
        message = 'HTTP 版本不受支持！'
        break
      default:
        message = '异常问题，请联系管理员！'
        break
    }
    return message
  }
}

export const responseHandle = (res, successInfo: boolean) => {
  // 接口通信成功后
  const rd = res.data
  if (rd.status === 'OK' && successInfo === true) {
    ElMessage({
      message: rd.message,
      type: 'success',
    })
    return true
  }
  if (rd.status === 'NG') {
    ElMessage({
      duration: 0,
      showClose: true,
      message: rd.message,
      type: 'error',
    })
    return false // 提示接口错误
  }
}
