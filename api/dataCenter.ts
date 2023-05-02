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
            searchName:name
        }
    })
}

// 批量上传
export const uploadData = (table:string,empno:string,upload: any) => {
    return service({
        url: '/DataCenter/postUploadData',
        method: 'post',
        params:{
            type:table,
            empno:empno,
        },
        data:upload
    })
}

// 更新数据
export const updateData = (table:string, editData:any,empno:string) => {
    // editData.add('type',table)
    editData.type=table
    return service({
        url: '/DataCenter/putUpdateData',
        method: 'put',
        params:editData, empno:empno

    })
    }


// 添加数据
export const addData = (table:string,addData: any,empno:string) => {
    addData.type=table
return service({
    url: '/DataCenter/postNewData',
    method: 'post',
    params:addData,empno:empno
})
}

// 附件上传
export const addFile = (type:string,fileNumber:string,file:any,empno:string) => {
    return service({
        url: '/DataCenter/postUploadFile',
        method: 'post',
        params:{
            type:type,
            fileNumber:fileNumber,
            empno:empno,
        },
        data:file
    })
    }



// 删除文件
export const deleteFile = (type:string,fileNumber:string,attachName: any,empno:string) => {
    return service({
        url: '/DataCenter/deleteFile',
        method: 'delete',
        params:{
            type:type,
            fileNumber:fileNumber,
            empno:empno,
            attachName:attachName
        },
        data: attachName,
    })
}


// 删除数据
export const deleteData = (table:string ,dataName: string,empno:string) => {

    return service({
        url: '/DataCenter/deleteData',
        method: 'delete',
        params: {
            type:table,
            fileNumber:dataName,
            empno:empno,
        }
    })
}
