<template>
  <div>
    <!-- interval为选项栏 -->
    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row :gutter="24" style="height:30px">
        <el-col :span="4">
          <el-select v-model="selectedOption" placeholder="请选择">
            <el-option v-for="option in options" :key="option.value" :label="option.label"
              :value="option.value"></el-option>
          </el-select>
        </el-col>

        <el-col :span="4" :offset="11" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryInfo.query" placeholder="Please input" @clear="getList" clearable style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>
            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button @click="searchData(selectedOption)">
                <el-icon>
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <el-upload action="#" :show-file-list="false" :before-upload="batchBeforeUpload(selectedOption)">
            <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
            <el-button style="height: 25px;  color: white; background: transparent; border: none;">
              {{ $t('dataCenter.upload') }}
            </el-button>
          </el-upload>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel" style="height: 25px;  color: white; background: transparent; border: none;">
            {{ $t('dataCenter.export') }}</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="showAddDialog(tableName)"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>{{ $t('dataCenter.new') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <!-- v-if="selectedOption === 'table1'" -->
    <el-table :v-if="tableSelect(selectedOption)" :data="tableDataList" :row-class-name="tableRowClassName">
      <!-- 表格列定义 -->
      <el-table-column label="No." type="index" :index="getIndex" width="50px"></el-table-column>
      <el-table-column v-for="(item, index) in fieldNames" :key="index" :label="item.label" :prop="item.key"
        :width="item.width">
        <template v-if="item.key === 'attachment' item.isAttachment === 'true'&& " #default="scope">
          <el-upload :before-remove="attachmentBeforeRemove(scope.row, tableName)"
            :before-upload="beforeUpload(scope.row)" v-model:file-list="scope.row.attachment" class="upload-demo"
            action="#" multiple :on-preview="handlePreview" :on-remove="handleRemove" :limit="3">
            <el-button>
              <img src="./icons/文件.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </el-upload>
        </template>
        <template v-else-if="item.key ==='edit' " #default="scope">
          <el-button :icon="Edit" size="small" @click="showEditDialog(tableName, scope.row)"></el-button>
        </template>

        <template v-else-if="item.key ==='delete' " #default="scope">
          <el-button size="small" type="danger" class="delete_button"
            @click="removeById(tableName, scope.row.filenumber)">
            <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination small @size-change="handleSizeChange" @current-change="handleCurrentChange"
        v-model:current-page="currentPage" :page-sizes="[1, 2, 5]" v-model:page-size="pageSize"
        layout=" ->,total, prev, pager, next,sizes, jumper" :total="total" popper-class="pageSelect">
      </el-pagination>
    </div>
  </div>

  <!-- 添加证书对话框 -->
  <el-dialog v-model="addDialogVisible" :visible.sync="addDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addForm" ref="addFormRef" label-width="100px" label-position="top">

      <template v-for="(item, index) in fieldNames">
        <el-form-item v-if="item.key !== 'edit' && item.key !== 'delete'" :key="index" :label="item.label"
          :prop="item.key">
          <el-input v-model="addForm[item.key]"></el-input>
        </el-form-item>
      </template>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">{{ $t('dataCenter.cancel') }}</el-button>
        <el-button type="primary" @click="addData(tableName)">
          {{ $t('dataCenter.confirm') }}
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑证书对话框-->
  <el-dialog title="修改用户" v-model="editDialogVisible" :visible.sync="editDialogVisible" width="50%">
    <el-form :model="editForm" ref="editFormRef" label-width="100px" label-position="top">
      <template v-for="(item, index) in fieldNames">
        <el-form-item v-if="item.key !== 'edit' && item.key !== 'delete'" :key="index" :label="item.label"
          :prop="item.key">
          <el-input :disabled="item.disabled" v-model="editForm[item.key]"></el-input>
        </el-form-item>
      </template>
    </el-form>

    <span slot="footer" class="dialog-footer">version
      <el-button @click="editDialogVisible = false">{{ $t('dataCenter.cancel') }}</el-button>
      <el-button type="primary" @click="editData(tableName)">{{ $t('dataCenter.confirm') }}</el-button>
    </span>
  </el-dialog>
</template>
  
<script setup lang="ts">
import { ref } from 'vue';
import { getTable1Data, getTable2Data } from '@/api';
import type { UploadProps, UploadUserFile } from 'element-plus'
import { Plus, Search, Filter, UploadFilled, Download, Edit, Delete, SetUp, Document } from '@element-plus/icons-vue'

interface Option {
  label: string;
  value: string;
}

const options: Option[] = [
  { label: '表格1', value: 'table1' },
  { label: '表格2', value: 'table2' },
];

const selectedOption = ref<string>(''); // 当前选中的选项
const table1Data = ref<any[]>([]);
const table2Data = ref<any[]>([]);
const tableData = ref<any[]>([]);
// 初始化数据
//   async function init() {
//     table1Data.value = await getTable1Data();
//     table2Data.value = await getTable2Data();
// }  
//  init();

// 表名
const tableName = ref();
// 显示数据
const tableDataList = ref<any[]>([]);

const addForm = ref();
const editForm = ref();

// 页码和页面个数
const currentPage = ref(1)
const pageSize = ref(2)

// 字段名
const fieldNames = ref<any>();
// 数据个数
const total = ref(0)


// total.value = tableData.length

// 控制添加证书对话框的显示与隐藏
const addDialogVisible = ref(false);
// 控制修改证书对话框的显示与隐藏
const editDialogVisible = ref(false);


// 数据
table1Data.value = [
  {
    site: '21',
    filenumber: '21',
    filename: '21',
  },
  {
    site: '22',
    filenumber: '22',
    filename: '22',
  },
  {
    site: '23',
    filenumber: '23',
    filename: '23',
  },
  {
    site: '24',
    filenumber: '24',
    filename: '24',
  },
  {
    site: '25',
    filenumber: '25',
    filename: '25',
  },

  {
    site: '26',
    filenumber: '26',
    filename: '26',
  },
  {
    site: '27',
    filenumber: '27',
    filename: '27',
  },
]

table2Data.value = [
  {
    site: '21',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },
  {
    site: '22',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },
  {
    site: '23',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },
  {
    site: '24',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },

  {
    site: '25',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },
  {
    site: '26',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },
  {
    site: '27',
    filenumber: '21',
    filename: '21',
    attachment: [],
    edit: '21',
    delete: '21'
  },
]

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

const documentFormItems = [
  { "label": "Site", "key": "site" },
  { "label": "fileNumber", "key": "filenumber" },
  { "label": "fileName", "key": "fileanme" }];

const certificateForm = [
  { "label": "Site", "key": "site", " width": "200", "isAttachment": "true", "disabled": "true" },
  { "label": "fileNumber", "key": "filenumber", " width": "225", "isAttachment": "true", "disabled": "true" },
  { "label": "fileName", "key": "filename", " width": "225", "isAttachment": "true", "disabled": "true" },
  { "label": "attachment", "key": "attachment", " width": "200", "isAttachment": "true", "disabled": "true" },
  { "label": "edit", "key": "edit", " width": "75", "isAttachment": "true", "disabled": "true" },
  { "label": "delete", "key": "delete", " width": "75", "isAttachment": "true", "disabled": "true" }];

const tableSelect = (selectedOption: any) => {
  if (selectedOption === "table1") {
    tableData.value = []
    tableData.value = table1Data.value
    fieldNames.value = documentFormItems
    total.value = table1Data.value.length
    getList()


    return true
  } else if (selectedOption === "table2") {
    tableData.value = []
    tableData.value = table2Data.value
    fieldNames.value = certificateForm
    total.value = table1Data.value.length
    getList()
    return true
  }
  return false
}


const getIndex = (index: any) => {
  return (queryInfo.value.pagenum - 1) * queryInfo.value.pagesize + index + 1;
}

// 删除附件
const attachmentBeforeRemove = (row: any, type: any) => (attachment: any, attachmentList: any) => {
  //  deleteFile(type,row.filenumber, attachment.name,empno.value).then((res:any)=>{
  //     console.log("deleteFile.res.data",res.data)
  //   }).catch((error:any)=>{
  //     console.log(error)
  //   })
  //   // getAllData()
}

// 删除附件
const handleRemove: UploadProps['onRemove'] = (file, uploadFiles) => {
  // console.log("uploadFiles", uploadFiles)
}
// 浏览附件
const handlePreview: UploadProps['onPreview'] = (uploadFile) => {

  window.open(uploadFile.url)
  // 点击文件查看
}

// 监听pagesize改变的事件
function handleSizeChange(newSize: number) {
  queryInfo.value.pagesize = newSize
  getList()
}

function handleCurrentChange(newPage: number) {
  queryInfo.value.pagenum = newPage
  getList()
}

const beforeUpload = (row: any) => (file: any) => {
  //  addAttach('criterion',row,file)
  // return false
}


// 初始表格数据
// function getAllData() {
//   getData().then((res: any) => {

//     table2Data = JSON.parse(res.data)
//     total.value = table2Data.length
//     getList()
//   })
// }


var queryInfo = ref<any>(
  {
    query: '',
    pagenum: 1,
    pagesize: 1
  }
)

getList()



const batchBeforeUpload = (type: string) => (uploadFile: any) => {
  // batchUpload(type,uploadFile)
  getList()
}




function showAddDialog(table: string) {
  addForm.value = []
  addDialogVisible.value = true
}

// 根据表类型和当前行，显示对应的编辑对话框
function showEditDialog(table: string, row: any) {
  editForm.value = row
  editDialogVisible.value = true
}

// 根据表类型和当前行，删除当前行
function removeById(table: string, fileName: string) {

  ElMessageBox.confirm(
    `Are you sure to delete this data?`
  ).then(
    () => { deleteData(table, fileName, empno.value), ElMessage.success('删除成功') },
    () => { ElMessage.error('删除失败') }
  )

}



function getList() {
  // let data:any[] = []

  // for (let index = (queryInfo.value.pagenum - 1) * queryInfo.value.pagesize; index < queryInfo.value.pagesize * queryInfo.value.pagenum; index++) {
  //   data[index] = tableData[index];
  // }

  tableDataList.value = tableData.value.slice((queryInfo.value.pagenum - 1) * queryInfo.value.pagesize, queryInfo.value.pagesize * queryInfo.value.pagenum)
}


function searchData(table: string) {
  // 连接后端
  // getSearchData(table, queryInfo.value.query).then((res: any) => {

  // let data: any[] = res.data

  //  if (table === 'certificate') {
  //   certificateList.value = JSON.parse(data.certificate)
  //   certificateList.value.map((item)=>{
  //     if(item.attachment === null){
  //       item.attachment = []
  //     }
  //   })
  // } else if (table === 'criterion') {
  //   criterionList.value = JSON.parse(data.criterion)

  //   criterionList.value.map((item)=>{
  //     if(item.attachment === null){
  //       item.attachment = []
  //     }
  //   })

  // } else if (table === 'document') {
  //   documentList.value =JSON.parse(data.document)
  //   documentList.value.map((item)=>{
  //     if(item.attachment === null){
  //       item.attachment = []
  //     }
  //   })    
  // } else {
  //   console.log("错误")
  // }
  // })
}

// 根据表类型，添加对应数据
function addData(table: string) {

  // addData(table, addFormData,empno.value).then((res: any) => {

  //   if (res.status !== 200) {
  //     ElMessage.error('添加用户失败！')
  //   }else {
  //     ElMessage.success('添加用户成功！')
  //   }


  addDialogVisible.value = false

  //   getCertificateList()
  //   getCriterionList()
  //   getDocumentList()

  // })


}

// 根据表类型，修改对应数据
function editData(table: string) {

  // let editFormData: any = ''

  // if (table === 'certificate') {
  //   editFormData = editCertificateForm.value
  // } else if (table === 'criterion') {
  //   editFormData = editCriterionForm.value
  // } else if (table === 'document') {
  //   editFormData = editDocumentForm.value
  // } else {
  //   console.log("错误")
  // }


  // updateData(table, editFormData,empno.value).then((res: any) => {

  //   if (res.status !== 200) {
  //     ElMessage.error('编辑用户失败！')
  //   }else {
  //     ElMessage.success('编辑用户成功！')
  //   }


  editDialogVisible.value = false


  //   getCertificateList()
  //   getCriterionList()
  //   getDocumentList()

  // })

}



// 下载表格数据
const downloadExcel = () => {


  documentName = 'table.xlsx'
  // 将数据转换为 Excel 文件
  const workbook = XLSX.utils.book_new();
  const sheet = XLSX.utils.json_to_sheet(tableData);
  XLSX.utils.book_append_sheet(workbook, sheet, 'Sheet1');
  const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  // const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
  const blob = new Blob([excelBuffer], { type: 'application/octet-stream' });
  saveAs(blob, documentName)
};







</script>


<style  lang="scss" scoped>
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
  height: 10px;

  ::v-deep button {
    background: transparent !important;
    border: 1px aqua solid;
    height: 15px;
  }

  ::v-deep .el-pager li {
    background: transparent;
  }

  ::v-deep .el-icon {
    color: aqua;
  }

  ::v-deep .el-pagination__total {
    color: black;
  }

  ::v-deep .el-pagination__rightwrapper {
    margin-top: 15px;
    margin-right: 10px;
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

    .el-button {
      background: transparent;
      border: none
    }
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


::v-deep .el-button {
  padding-left: 10px;
  padding-right: 10px;
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

::v-deep .el-upload-list__item {
  width: 60px;
}

::v-deep .el-upload-list {
  text-align: -webkit-center
}

// .upload-demo {
//   ::v-deep .el-upload-list--text {
//     width: 30px;
//   }
// }
</style>

<style lang="scss">
.el-popper.is-light {
  background-color: rgb(3, 2, 2);

  .el-select-dropdown__item.selected {
    color: aqua;
  }
}
</style>
