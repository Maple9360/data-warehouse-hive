import { service } from '@/utils/request.ts'

// 获取表格数据
export const getData = () => {
return service({
    url: '/DataCenter/getDataCenter',
    method: 'get',
})
}

// 获取表格数据
export const getSearchData = (table:string,name:string) => {
    return service({
        url: '/DataCenter/getSearchData',
        method: 'get',
        params:{
            type:table,
            site:'',
            searchName:name
        }
    })
}

// 批量上传
export const uploadData = (table:string,upload: any) => {
   
    return service({
        url: '/DataCenter/postUploadData',
        method: 'post',
        data:table,upload
    })
}

// 更新数据
export const updateData = (table:string, editData:any) => {
    // editData.add('type',table)
    editData.type=table
    return service({
        url: '/DataCenter/putUpdateData',
        method: 'put',
        params:editData,
    })
    }


// 添加数据
export const addData = (table:string,addData: any) => {
    addData.type=table
return service({
    url: '/DataCenter/postNewData',
    method: 'post',
    params:addData
})
}

// 附件上传
export const addFile = (table:string,fileName:string) => {
    return service({
        url: '/DataCenter/postUploadFile',
        method: 'post',
        data:table,fileName
    })
    }



// 删除文件
export const deleteFile = (fileName: string) => {
    return service({
        url: '/DataCenter/deleteFile',
        method: 'put',
        data: fileName,
    })
}


// 删除数据
export const deleteData = (table:string ,dataName: string) => {

    return service({
        url: '/DataCenter/deleteData',
        method: 'put',
        params: {
            type:table,
            site:'',
            fileNumber:dataName,
        }
    })
}
