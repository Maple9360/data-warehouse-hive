import { service } from '@/utils/request.ts'

// 获取注册页下拉数据、模板、权限明细
export const getDropdown = (data: any) => {
  return service({
    url: '/Login/getDropdownBoxData',
    method: 'get',
    params: {
      ...data,
    },
  })
}
// 单笔注册
export const postPermissionApply = (data: any) => {
  return service({
    url: '/Login/postAuthorityApply',
    method: 'post',
    data: {
      ...data,
    },
  })
}
// 获取个人权限log
export const getLog = (data: any) => {
  return service({
    url: '/Login/getQuerySigning',
    method: 'get',
    params: {
      ...data,
    },
  })
}
// 管理员查询待签核数据，提交签核状态（签核人工号、被签核人工号、签核状态）
export const putPermission = (data: any) => {
  return service({
    url: '/Login/putSignDetails',
    method: 'put',
    data,
  })
}

// 上传批量注册文件
export const uploadSignupFile = (formData, data) => {
  return service({
    url: '/Login/postUploadFile',
    method: 'post',
    data: formData,
    params: { ...data },
  })
}

// 邮件跟催
export const getEmail = (data: any) => {
  return service({
    url: '/Login/getEmail',
    method: 'get',
    params: {
      ...data,
    },
  })
}
