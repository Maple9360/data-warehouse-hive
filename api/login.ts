import { service } from '@/utils/request.ts'

export const getUserInfo = (data: any) => {
  return service({
    url: '/Login/getUserInfo',
    method: 'get',
    params: {
      ...data,
    },
  })
}

export const getLog = (data: any) => {
  return service({
    url: '/Login/getLogs',
    method: 'get',
    params: {
      ...data,
    },
  })
}
