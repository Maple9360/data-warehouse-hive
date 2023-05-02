import { service } from '@/utils/request.ts'

export const getHeat = (data: any) => {
  return service({
    url: '/Login/getHeat',
    method: 'get',
    params: {
      ...data,
    },
  })
}
