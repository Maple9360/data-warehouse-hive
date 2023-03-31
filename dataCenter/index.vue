<!--应该能申请更改-->

<template>
  <div class="container">

    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
        <el-col :span="4" :offset="15" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryInfo.query" placeholder="Please input" @clear="getUserList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>

            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon>
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><UploadFilled /></el-icon>  -->
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Upload</el-button>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>

      </el-row>
    </div>

    <div class="onePicture">


      <el-table :row-style="{ height: 30 }" height="100" :data="userList" :row-class-name="tableRowClassName"
        :header-cell-style="{ textAlign: 'center' }" :cell-style="{ textAlign: 'center' }">
        <el-table-column label="No." prop="no" width="75px"></el-table-column>
        <el-table-column label="Site" prop="site" width="50px"></el-table-column>
        <el-table-column label="證書編號" prop="bianhao" width="150px"></el-table-column>
        <el-table-column label="證書標準" prop="biaozhun" width="200px"></el-table-column>
        <el-table-column label="證書附件" prop="fujian" width="100px"></el-table-column>
        <template>
          <!-- 上传文件 -->
          <el-upload class="upload-demo" drag action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            multiple>
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              Drop file here or <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png files with a size less than 500kb
              </div>
            </template>
          </el-upload>
        </template>
        <el-table-column label="發證日期" prop="dengji" width="150px"></el-table-column>
        <el-table-column label="失效日期" prop="shixiao" width="150px"></el-table-column>
        <el-table-column label="狀態" prop="zhuangtai" width="75px"></el-table-column>
        <el-table-column label="上傳人" prop="name" width="100px"></el-table-column>
        <el-table-column label="編輯" prop="edit" width="75px">
          <template #default="scope">
            <el-button :icon="Edit" size="small" @click="handleEdit(scope.$index, scope.row)"></el-button>
          </template>
        </el-table-column>

        <el-table-column label="刪除" width="75px">
          <template #default="scope">

            <el-button size="small" type="danger" class="delete_button" @click="handleDelete(scope.$index, scope.row)">
              <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </template>
        </el-table-column>


      </el-table>



    </div>

    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
        <el-col :span="4" :offset="15" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryInfo.query" placeholder="Please input" @clear="getUserList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>

            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon>
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><UploadFilled /></el-icon>  -->
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Upload</el-button>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>

      </el-row>
    </div>

    <div class="twoPicture">
      <el-table :row-style="{ height: 30 }" max-height="250" :data="userList" :row-class-name="tableRowClassName"
        :header-cell-style="{ textAlign: 'center' }" :cell-style="{ textAlign: 'center' }">


        <el-table-column label="No." prop="no" width="75px">

        </el-table-column>
        <el-table-column label="Site" prop="site" width="50px">

        </el-table-column>
        <el-table-column label="文件編號" prop="bianhao" width="150px">

        </el-table-column>
        <el-table-column label="文件名稱" prop="biaozhun" width="200px">

        </el-table-column>
        <el-table-column label="版本" prop="fujian" width="100px">

        </el-table-column>
        <el-table-column label="發證日期" prop="dengji" width="150px">

        </el-table-column>
        <el-table-column label="附件" prop="shixiao" width="150px">
          
        </el-table-column>
        <template>
          <!-- 上传文件 -->
          <el-upload class="upload-demo" drag action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            multiple>
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              Drop file here or <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png files with a size less than 500kb
              </div>
            </template>
          </el-upload>
        </template>

        <el-table-column label="狀態" prop="zhuangtai" width="75px"></el-table-column>
        <el-table-column label="上傳人" prop="name" width="100px"></el-table-column>
        <el-table-column label="編輯" prop="edit" width="75px">
          <template #default="scope">
            <el-button :icon="Edit" size="small" @click="showEditDialog(scope.row.no)"></el-button>
          </template>
        </el-table-column>

        <el-table-column label="刪除" width="75px">
          <template #default="scope">
            <el-button size="small" type="danger" class="delete_button" @click="removeUserById(scope.row.id)">
              <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </template>
        </el-table-column>

      </el-table>

      <div class="page_row">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
         v-model:current-page="currentPage" :page-sizes="[1, 2, 5]" v-model:page-size="pageSize"
          layout=" ->,total, prev, pager, next,sizes, jumper" :total="total" popper-class="pageSelect"></el-pagination>
      </div>

    </div>

    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
        <el-col :span="4" :offset="15" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryInfo.query" placeholder="Please input" @clear="getUserList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>

            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon>
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><UploadFilled /></el-icon>  -->
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Upload</el-button>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>

      </el-row>
    </div>

    <div class="threePicture">

 
    

      <el-table :row-style="{ height: 30 }" max-height="170" :data="userList" :row-class-name="tableRowClassName"
        :header-cell-style="{ textAlign: 'center' }" :cell-style="{ textAlign: 'center' }">

        <el-table-column label="No." prop="no" width="240px"></el-table-column>
        <el-table-column label="Site" prop="site" width="240"></el-table-column>
        <el-table-column label="文件名稱" prop="bianhao" width="240"></el-table-column>
        <el-table-column label="文件編號" prop="biaozhun" width="240"></el-table-column>
        <el-table-column label="附件" prop="fujian" width="240"></el-table-column>
        <template>
         
          <el-upload class="upload-demo" drag action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            multiple>
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              Drop file here or <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png files with a size less than 500kb
              </div>
            </template>
          </el-upload>
        </template>

      </el-table>

      <div class="page_row">
        
     
      
      <!-- <el-pagination
      
      v-model:current-page="queryInfo.pagenum"
      v-model:page-size="queryInfo.pagesize"
      :page-sizes="[100, 200, 300, 400]"
      layout=" ->,total, prev, pager, next,sizes, jumper" 
      :total="400"
      :disabled="disabled"
      :background="true"
      popper-class="pageSelect"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      
    /> -->

    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[1, 2, 5]"
      
      layout="->, total,  prev, pager, next, sizes, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
      </div>

    </div>

  </div>


      <!-- 添加对话框 -->  
  <el-dialog v-model="addDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addForm" ref="addFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="addForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="addForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="biaozhun">
        <el-input v-model="addForm.biaozhun"></el-input>
      </el-form-item>
      <el-form-item label="版本" prop="fujian">
        <el-input v-model="addForm.fujian"></el-input>
      </el-form-item>
      <el-form-item label="登證日期" prop="shengxiao">
        <el-input v-model="addForm.shengxiao"></el-input>
      </el-form-item>
      <el-form-item label="失效日期" prop="shixiao">
        <el-input v-model="addForm.shixiao"></el-input>
      </el-form-item>
      <el-form-item label="狀態" prop="zhuangtai">
        <el-input v-model="addForm.zhuangtai"></el-input>
      </el-form-item>
      <el-form-item label="上傳人" prop="name">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>

    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addCertificate">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑对话框-->>
  <el-dialog
            title="修改用户"
            v-model="editDialogVisible"
            width="50%"
            @close="editDialogClosed"
        >
      <el-form :model="editForm" ref="editFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="editForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="editForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="biaozhun">
        <el-input v-model="editForm.biaozhun"></el-input>
      </el-form-item>
      <el-form-item label="版本" prop="fujian">
        <el-input v-model="editForm.fujian"></el-input>
      </el-form-item>
      <el-form-item label="登證日期" prop="shengxiao">
        <el-input v-model="editForm.shengxiao"></el-input>
      </el-form-item>
      <el-form-item label="失效日期" prop="shixiao">
        <el-input v-model="editForm.shixiao"></el-input>
      </el-form-item>
      <el-form-item label="狀態" prop="zhuangtai">
        <el-input v-model="editForm.zhuangtai"></el-input>
      </el-form-item>
      <el-form-item label="上傳人" prop="name">
        <el-input v-model="editForm.name"></el-input>
      </el-form-item>

    </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editCertificateInfo">确 定</el-button>
            </span>
        </el-dialog>



</template>

<!-- <script setup lang="ts"> -->
<script lang="ts" setup>
import { ref } from 'vue'
import { Plus, Search, Filter, UploadFilled, Download, Edit, Delete } from '@element-plus/icons-vue'



// import { ElTable, ElTableColumn, ElButton, ElDialog, ElForm, ElFormItem, ElInput } from 'element-plus';

// interface TableDataItem {
//   name: string;
//   age: number;
// }

// const tableDa = ref<TableDataItem[]>([
//   { name: '张三', age: 18 },
//   { name: '李四', age: 22 },
//   { name: '王五', age: 25 },
// ]);

// const form = ref<TableDataItem>({
//   name: '',
//   age: 0,
// });



// function handleEdit(row: TableDataItem) {
//   form.value = { ...row };
//   editDialogVisible.value = true;
// }

// function handleSave() {
//   const index = tableDa.value.findIndex((item) => item.name === form.value.name);
//   if (index > -1) {
//     Object.assign(tableDa.value[index], form.value);
//   } else {
//     tableDa.value.push(form.value);
//   }
//   dialogVisible.value = false;
// }







const currentPage = ref(3)
const pageSize = ref(2)


var queryInfo = {
        query: '',
        pagenum: 3,
        pagesize: 2
}




// 添加用户的表单数据
const addForm = {
    site:'',
    bianhao:'',
    biaozhun:'',
    fujian:'',
    shengxiao:'',
    shixiao:'',
    zhuangtai:'',
    name:''
}
// 控制修改用户对话框的显示与隐藏

let editForm = {
    // site:'',
    // bianhao:'',
    // biaozhun:'',
    // fujian:'',
    // shengxiao:'',
    // shixiao:'',
    // zhuangtai:'',
    // name:''
}


// 控制添加用户对话框的显示与隐藏
const addDialogVisible = ref(false)
let editDialogVisible = ref(false);



interface User {
  no: string
  site: string
  bianhao: string
  biaozhun: string
  fujian: string
  zhuangtai: string
  date: string
  name: string
  address: string
}


var userList: User[] = []



// const handleEdit = (index: number, row: User) => {
//   console.log(index, row)
// }
// const handleDelete = (index: number, row: User) => {
//   console.log(index, row)
// }

// 监听pagesize改变的事件
const tableRowClassName = ({
  rowIndex
}: {
  // row: User,
  rowIndex: number
}) => {
  if (rowIndex % 2 === 1) {
    return 'odd-row'
  } else if (rowIndex % 2 === 0) {
    return 'even-row'
  }
  return ''
}



const tableData: User[] = [
  {
    no: '1',
    site: '1',
    bianhao: '1',
    biaozhun: '1',
    fujian: '1',
    zhuangtai: '1',
    date: '2016-05-03',
    name: 'Tom1',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    no: '2',
    site: '2',
    bianhao:'2',
    biaozhun:'2',
    fujian:'2',
    zhuangtai: '2',
    date: '2016-05-02',
    name: 'Tom2',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-04',
    name: 'Tom3',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom4',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom5',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom6',
    address: 'No. 189, Grove St, Los Angeles',
  }, {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom7',
    address: 'No. 189, Grove St, Los Angeles',
  }, {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom8',
    address: 'No. 189, Grove St, Los Angeles',
  }, {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom9',
    address: 'No. 189, Grove St, Los Angeles',
  }, {
    no: '',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    zhuangtai: '',
    date: '2016-05-01',
    name: 'Tom10',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

const total = tableData.length

getUserList()




// 监听pagesize改变的事件
function handleSizeChange(newSize: number) {
  queryInfo.pagesize = newSize
  getUserList()
}

function handleCurrentChange(newPage: number) {
  queryInfo.pagenum = newPage
  getUserList()
}

function getUserList() {


  let data = []
  for (let index = (queryInfo.pagenum - 1) * queryInfo.pagesize; index < queryInfo.pagesize * queryInfo.pagenum; index++) {
    console.log("index" + index)
    data[index] = tableData[index];

  }
  userList = data
  userList.splice(data.length)

}

function addCertificate() {
    // this.$refs.addFormRef.validate(async valid => {
    //     if (!valid) return
    //     // 可以发起添加用户的网络请求
    //     const { data: res } = await this.$http.post('users', this.addForm)


    //     if (res.meta.status !== 201) {
    //         this.$message.error('添加用户失败！')
    //     }
    //     this.$message.success('添加用户成功！')
    //     this.addDialogVisible = false
    //     this.getUserList()
    // })
}


function editCertificateInfo() {

}

function showEditDialog(id:number) {
  console.log("23233232")
  console.log(id)
  

  editForm = tableData[id - 1]
  editDialogVisible.value = true
}


function removeUserById(id:number) {

}



</script>



<style  lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
}




::v-deep .el-table {
  border: 0;
  background-color: transparent !important;
  color: #fff !important;
  border-collapse: separate;

  // max-height:100px;
  --el-table-row-hover-bg-color: #b2d3bd;
}

.el-button--small {
  background: transparent;
  border: none;
}

.el-pagination {
  margin-right: 30px;
  height: 25px;

  ::v-deep button {
    background: transparent !important;
    border: 1px aqua solid;
    height: 20px;
  }

  ::v-deep .el-pager li{
    background:transparent;
  }

  ::v-deep .el-icon {
    color: aqua;
  }

  ::v-deep .el-pagination__total {
    color: black;
  }

  ::v-deep .el-pagination__rightwrapper {
    margin-top: 15px;
  }
}


.el-pagination__total {
  color: black;
}

::v-deep .el-pager li.is-active {
  background: transparent;
  color: aqua;
}

.el-col {
  color: white;
}

::v-deep .el-table__header {
  height: 40px;
  width: 1200px;
}

::v-deep .el-table__body {
  height: 30px;
}

::v-deep .el-table__inner-wrapper {
  margin: 20px 40px 20px 40px;
}

::v-deep .el-table th {
  background-color: #828282;
  color: white;

}

::v-deep .el-table tr {
  color: white;
  background: transparent;

  .el-table__cell {
    padding: 0;
    border: 0px;
  }
}

::v-deep .el-table td {
  color: white;
}


::v-deep .el-table__cell:first-of-type {
  border-radius: 10px 0 0 10px;

}

::v-deep .el-table__cell:last-of-type {
  border-radius: 0 10px 10px 0;
}

::v-deep .odd-row {
  background: transparent;

  .el-table__cell {
    height: 38px;
    background-color: #828282;
  }

}

::v-deep .even-row {
  height: 38px;
  --el-table-tr-bg-color: transparent;
}

.second_row {
  ::v-deep .el-input__wrapper {
    color: white;
    background-color: transparent;
    box-shadow: 0 0 0 0;
    border: none;
    border-bottom: 1px solid #eee;
  }

  ::v-deep .el-input-group {
    height: 20px;
  }

  ::v-deep .el-input-group__prepend {
    width: 0px;
    background: transparent;
    box-shadow: 0 0 0 0;
  }

  ::v-deep .el-input-group__append {
    width: 0px;
    background: transparent;
    box-shadow: 0 0 0 0;

    .el-button {
      background-color: transparent;
      padding-right: 5px;
      padding-left: 5px;
    }
  }
}

::v-deep .el-button {
  padding-left: 10px;
  padding-right: 10px;
}

.page_row {
  ::v-deep .el-input__wrapper {
    color: white;
    background-color: transparent;
    height: 20px;
  }

  ::v-deep .el-input {
    --el-input-border-color: aqua;
  }

  ::v-deep .el-input__inner {
    color: #fff;
  }

  ::v-deep .el-pagination__goto {
    color: white;
  }
}

.el-button:hover {
  background: #828282 !important;
}

.delete_button:hover img {
  content: url(./icons/刪除-選中.png);
}



::v-deep .el-icon {
  color: white;
}

.interval {
  width: 1280px;
  height: 50px;
}

.onePicture {
  background-image: url("./background/1280x120-邊框4px.png");
  width: 1280px;
  height: 120px;
  background-size: 1280px 120px;

}

.twoPicture {
  background-image: url("./background/1280x290\ 邊框4px-62-62.png");
  width: 1280px;
  height: 290px;
  background-size: 1280px 290px;

  ::v-deep .el-table__inner-wrapper::before {
    content: none;
  }
}

.threePicture {
  background-image: url("./background/1280x210\ 邊框4px-62.png");
  width: 1280px;
  height: 210px;
  background-size: 1280px 210px;
}
</style>


<style lang="scss">
.pageSelect.el-popper.is-light {
  background-color: rgb(3, 2, 2);

  .el-select-dropdown__item.selected {
    color: aqua;
  }
}
</style>
