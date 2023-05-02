
<template>
  <div class="container">
    <!-- interval为选项栏 -->
    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
        <el-col :span="4" :offset="15" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryCertificateInfo.query" placeholder="Please input" @clear="getCertificateList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>
            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button @click="searchData('certificate')">
                <el-icon>
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <el-upload :data={type:certificate} action="#" :show-file-list="false" :before-upload="batchBeforeUploade('certificate')" >
            <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
            <el-button style="height: 25px;  color: white; background: transparent; border: none;" >
              Upload
            </el-button>
          </el-upload>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel('tableData1')"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addCertificateDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>
      </el-row>
    </div>
    <!-- 第一个表 -->
    <div class="onePicture">
      <el-table ref="table1" :row-style="{ height: 30 }" height="100" :data="certificateList"
        :row-class-name="tableRowClassName" :header-cell-style="{ textAlign: 'center' }"
        :cell-style="{ textAlign: 'center' }" >
        <el-table-column label="No." type="index" width="50px"></el-table-column>

        <el-table-column v-for="(item, index) in certificateForm" :key="index" :label="item.label" :prop="item.prop" :width="item.width">
          <template v-if="item.prop ==='attachment' " #default="scope">
            <el-upload :before-remove="attachmentBeforeRemove(scope.row,certificate)" :before-upload="certificateBeforeUpload(scope.row)" v-model:file-list="scope.row.attachment" class="upload-demo" action="#"
              multiple  :on-preview="handlePreview" :on-remove="handleRemove" :limit="3"  >
              <el-button>
                <img src="./icons/文件.png" class="export_icon" style="width:12px;height: 12px;" />
              </el-button>
            </el-upload>
          </template>
          <template v-else-if="item.prop==='edit'" #default="scope">
            <el-button :icon="Edit" size="small" @click="showAllTypeEditDialog('certificate', scope.row)"></el-button>
          </template>
          <template v-else-if="item.prop==='delete'" #default="scope">
            <el-button size="small" type="danger" class="delete_button"
              @click="removeAllTypeById('certificate', scope.row.filenumber)">
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
          <el-input v-model="queryCriterionInfo.query" placeholder="Please input" @clear="getCriterionList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>
            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon @click="searchData('criterion')">
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <el-upload :data={type:criterion} action="#" :show-file-list="false" :before-upload="batchBeforeUploade('criterion')">
            <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
            <el-button style="height: 25px;  color: white; background: transparent; border: none;">
              Upload
            </el-button>
          </el-upload>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel('tableData2')"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addCriterionDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>
      </el-row>
    </div>
  <!-- 第二个表 -->
    <div class="twoPicture">
      <el-table ref="table2" :row-style="{ height: 30 }" height="250" :data="criterionList"
        :row-class-name="tableRowClassName" :header-cell-style="{ textAlign: 'center' }"
        :cell-style="{ textAlign: 'center' }">
        <el-table-column label="No." type="index" width="50px"></el-table-column>
        
        <el-table-column v-for="(item, index) in criterionForm" :key="index" :label="item.label" :prop="item.prop" :width="item.width">
          <template v-if="item.prop ==='attachment'"  #default="scope">
            <el-upload :before-remove="attachmentBeforeRemove(scope.row,certificate)" :before-upload="criterionBeforeUpload(scope.row)" :data={filenumber:scope.row.filenumber,type:criterion} v-model:file-list="scope.row.attachment" class="upload-demo" action="#"
              multiple :on-preview="handlePreview" :on-remove="handleRemove" :limit="3" >
              <el-button>
                <img src="./icons/文件.png" class="export_icon" style="width:12px;height: 12px;" />
              </el-button>
            </el-upload>
          </template>
          <template v-else-if="item.prop ==='edit' " #default="scope">
            <el-button :icon="Edit" size="small" @click="showAllTypeEditDialog('criterion', scope.row)"></el-button>
          </template>

          <template v-else-if="item.prop ==='delete' " #default="scope">
            <el-button size="small" type="danger" class="delete_button"
              @click="removeAllTypeById('criterion', scope.row.filenumber)">
              <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </template>
        </el-table-column>


      </el-table>

      <div class="page_row">
        <el-pagination @size-change="handleCriterionSizeChange" @current-change="handleCriterionCurrentChange"
          v-model:current-page="currentCriterionPage" :page-sizes="[1, 2, 5]" v-model:page-size="pageCriterionSize"
          layout=" ->,total, prev, pager, next,sizes, jumper" :total="total2" popper-class="pageSelect"></el-pagination>
      </div>

    </div>

    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
        <el-col :span="4" :offset="15" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryDocumentInfo.query" placeholder="Please input" @clear="getDocumentList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>

            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon @click="searchData('document')">
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <el-upload :data={type:document} action="#" :show-file-list="false" :before-upload="batchBeforeUploade('document')">
            <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
            <el-button style="height: 25px;  color: white; background: transparent; border: none;">
              Upload
            </el-button>
          </el-upload>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel('tableData3')"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addDocumentDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>

      </el-row>
    </div>
  <!-- 第三个表 -->
    <div class="threePicture">

      <el-table ref="table3" :row-style="{ height: 30 }" height="170" :data="documentList"
        :row-class-name="tableRowClassName" :header-cell-style="{ textAlign: 'center' }"
        :cell-style="{ textAlign: 'center' }">

        <el-table-column label="No." type="index" width="200px"></el-table-column>

        <el-table-column v-for="(item, index) in documentForm" :key="index" :label="item.label" :prop="item.prop" :width="item.width">
          <template v-if="item.prop ==='attachment'" #default="scope">
            <el-upload :before-remove="attachmentBeforeRemove(scope.row,certificate)" :before-upload="documentBeforeUpload(scope.row)"  v-model:file-list="scope.row.attachment" class="upload-demo" action="#"
              multiple :on-preview="handlePreview" :on-remove="handleRemove" :limit="3" >
              <el-button>
                <img src="./icons/文件.png" class="export_icon" style="width:12px;height: 12px;" />
              </el-button>
            </el-upload>
          </template>
        
          <template v-else-if="item.prop ==='edit'" #default="scope">
            <el-button :icon="Edit" size="small" @click="showAllTypeEditDialog('document', scope.row)"></el-button>
          </template>

          <template v-else-if="item.prop ==='delete'" #default="scope">
            <el-button size="small" type="danger" class="delete_button"
              @click="removeAllTypeById('document', scope.row.filenumber)">
              <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="page_row">
        <el-pagination v-model:current-page="currentDocumentPage" v-model:page-size="pageDocumentSize"
          :page-sizes="[1, 2, 5]" layout="->, total,  prev, pager, next, sizes, jumper" :total="total3"
          @size-change="handleDocumentSizeChange" @current-change="handleDocumentCurrentChange"
          popper-class="pageSelect" />
      </div>

    </div>

  </div>

  <!-- 添加证书对话框 -->
  <el-dialog v-model="addCertificateDialogVisible" :visible.sync="addCertificateDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addCertificateForm" ref="addFormRef" label-width="100px" label-position="top">

      <el-form-item v-for="(item, index) in certificateFormItems" :key="index" :label="item.label" :prop="item.prop">
        <el-input v-model="addCertificateForm[item.prop]"></el-input>
      </el-form-item>

    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addCertificateDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addAllTypeData('certificate')">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑证书对话框-->
  <el-dialog title="修改用户" v-model="editCertificateDialogVisible" :visible.sync="editCertificateDialogVisible" width="50%">
    <el-form :model="editCertificateForm" ref="editFormRef" label-width="100px" label-position="top">

      <el-form-item v-for="(item, index) in certificateFormItems" :key="index" :label="item.label" :prop="item.prop">
        <el-input v-if="item.prop==='filenumber'" disabled v-model="editCertificateForm[item.prop]"></el-input>
        <el-input v-else v-model="editCertificateForm[item.prop]"></el-input>
      </el-form-item>

    </el-form>

    <span slot="footer" class="dialog-footer">version
      <el-button @click="editCertificateDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editAllTypeData('certificate')">确 定</el-button>
    </span>
  </el-dialog>

  <!-- 添加标准对话框 -->
  <el-dialog v-model="addCriterionDialogVisible" :visible.sync="addCriterionDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addCriterionForm" ref="addFormRef" label-width="100px" label-position="top">

      <el-form-item v-for="(item, index) in criterionFormItems" :key="index" :label="item.label" :prop="item.prop">
        <el-input v-model="addCriterionForm[item.prop]"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addCriterionDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addAllTypeData('criterion')">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑标准对话框-->>
  <el-dialog title="修改用户" v-model="editCriterionDialogVisible" :visible.sync="editCriterionDialogVisible" width="50%"
    @close="editDialogClosed">

    <el-form :model="editCriterionForm" ref="editFormRef" label-width="100px" label-position="top">
      <el-form-item v-for="(item, index) in criterionFormItems" :key="index" :label="item.label" :prop="item.prop">
        <el-input v-if="item.prop==='filenumber'" disabled v-model="editCriterionForm[item.prop]"></el-input>
        <el-input v-else v-model="editCriterionForm[item.prop]"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="editCriterionDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editAllTypeData('criterion')">确 定</el-button>
    </span>
  </el-dialog>

  <!-- 添加文件对话框 -->
  <el-dialog v-model="addDocumentDialogVisible" :visible.sync="addDocumentDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addDocumentForm" ref="addFormRef" label-width="100px" label-position="top">
      <el-form-item v-for="(item, index) in documentFormItems" :key="index" :label="item.label" :prop="item.prop">
        <el-input v-model="addDocumentForm[item.prop]"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDocumentDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addAllTypeData('document')">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑文件对话框-->>
  <el-dialog title="修改用户" v-model="editDocumentDialogVisible" :visible.sync="editDocumentDialogVisible" width="50%"
    @close="editDialogClosed">
    <el-form :model="editDocumentForm" ref="editFormRef" label-width="100px" label-position="top">
      <el-form-item v-for="(item, index) in documentFormItems" :key="index" :label="item.label" :prop="item.prop">
        <el-input v-if="item.prop==='filenumber'" disabled v-model="editDocumentForm[item.prop]"></el-input>
        <el-input v-else v-model="editDocumentForm[item.prop]"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="editDocumentDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editAllTypeData('document')">确 定</el-button>
    </span>
  </el-dialog>

</template>


<!-- <script setup lang="ts"> -->
<script lang="ts" setup>
import { ref, reactive, onMounted,getCurrentInstance } from 'vue'
import { Plus, Search, Filter, UploadFilled, Download, Edit, Delete, SetUp, Document } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox, ElUpload } from 'element-plus';
import * as XLSX from '../../../node_modules/xlsx';
import axios from 'axios'
import { getData, addData, updateData, deleteData, getSearchData, uploadData, addFile, deleteFile } from '../../api/dataCenter'
import { objectPick } from '@vueuse/shared';

// import {saveAs} from '../../../node_modules/file-saver';
import { saveAs } from 'file-saver';
// ==========
import type { UploadProps, UploadUserFile } from 'element-plus'


// 自定义证书，标准，文件类型
interface Certificate {

site: string
filenumber: string
filename: string
attachment: { name: string, url: string }[]
issuedate: string
expiringdate: string
status: string
username: string
}



interface Criterion {
site: string
filenumber: string
filename: string
ver: string,
issuedate: string
attachment: { name: string, url: string }[]
status: string
username: string
}

interface Document {

site: string
filenumber: string
filename: string
attachment: { name: string, url: string }[]
}


const certificate = 'certificate'
const criterion = 'criterion'
const document = 'document'

const empno = '';

// 文件当前页和每页的数量
const currentDocumentPage = ref(3)
const pageDocumentSize = ref(2)

// 标准当前页和每页的数量
const currentCriterionPage = ref(3)
const pageCriterionSize = ref(2)

// 标的总个数，用于控制分页
const total2 = ref(0)
const total3 = ref(0)

// 用于存储表格的当前显示的列表的数据（当前页数据）
const certificateList = ref<Certificate[]>([])

console.log("certificateList",certificateList)

const criterionList = ref<Criterion[]>([])
const documentList = ref<Document[]>([])

// 表格存放所有表的数据（所有数据）
let tableData1: Certificate[] = []
let tableData2: Criterion[] = []
let tableData3: Document[] = []


total2.value = tableData2.length
total3.value = tableData3.length

// 证书，标准，文件信息（查询，页码，每页个数）
var queryCertificateInfo = ref<any>(
  {
    query: '',
    pagenum: 1,
    pagesize: 1
  }
)
var queryCriterionInfo = ref<any>(
  {
    query: '',
    pagenum: 3,
    pagesize: 2
  }
)
var queryDocumentInfo = ref<any>(
  {
    query: '',
    pagenum: 2,
    pagesize: 1
  }
)

// 添加证书的表单数据
var addCertificateForm = ref<any>(
  {
    site: '',
    filenumber: '',
    filename: '',
    attachment: [],
    issuedate: '',
    expiringdate: '',
    status: '',
    username: ''
  }
)



// 编辑证书的表单数据
var editCertificateForm = ref<any>({
  site: '',
  filenumber: '',
  filename: '',
  attachment: [],
  issuedate: '',
  expiringdate: '',
  status: '',
  username: ''
})

// 添加标准的表单数据
var addCriterionForm = ref<any>(
  {
    site: '',
    filenumber: '',
    filename: '',
    ver: '',
    issuedate: '',
    attachment: [],
    status: '',
    username: ''
  }
)

// 编辑标准的表单数据
var editCriterionForm = ref<any>({
  site: '',
  filenumber: '',
  filename: '',
  ver: '',
  issuedate: '',
  attachment: [],
  status: '',
  username: ''
})

// 添加文件的表单数据
var addDocumentForm = ref<any>(
  {
    site: '',
    filenumber: '',
    filename: '',
  }
)

// 编辑文件的表单数据
var editDocumentForm = ref<any>({
  site: '',
  filenumber: '',
  filename: '',
})


// 控制添加证书对话框的显示与隐藏
const addCertificateDialogVisible = ref(false);
// 控制修改证书对话框的显示与隐藏
var editCertificateDialogVisible = ref(false);

// 控制添加标准对话框的显示与隐藏
const addCriterionDialogVisible = ref(false);
// 控制修改标准对话框的显示与隐藏
var editCriterionDialogVisible = ref(false);

// 控制添加文件对话框的显示与隐藏
const addDocumentDialogVisible = ref(false);
// 控制修改文件对话框的显示与隐藏
var editDocumentDialogVisible = ref(false);

// 删除弹窗
const dialogVisible = ref(false)



// certificate表头
const certificateForm = [
  { label: 'Site', prop: 'site',width:'75px'},
  { label: '證書編號', prop: 'filenumber',width:'150px' },
  { label: '證書標準', prop: 'filename',width:'200px' },
  { label: '證書附件',prop:'attachment',width:'100px'},
  { label: '登證日期', prop: 'issuedate',width:'150px' },
  { label: '失效日期', prop: 'expiringdate' ,width:'150px'},
  { label: '狀態', prop: 'status' ,width:'75px'},
  { label: '上傳人', prop: 'username',width:'100px'},
  { label: '編輯', prop: 'edit',width:'75px'},
  { label: '刪除', prop: 'delete',width:'75px'},
]
// criterion表头
const criterionForm = [
  { label: 'Site', prop: 'site',width:'75px'},
  { label: '文件編號', prop: 'filenumber',width:'150px' },
  { label: '文件名稱', prop: 'filename',width:'200px' },
  { label: '版本', prop: 'ver' ,width:'150px'},
  { label: '登證日期', prop: 'issuedate',width:'150px' },
  {label:'附件',prop:'attachment',width:'100px'},
  { label: '狀態', prop: 'status' ,width:'75px'},
  { label: '上傳人', prop: 'username',width:'100px'},
  { label: '編輯', prop: 'edit',width:'75px'},
  { label: '刪除', prop: 'delete',width:'75px'},
]
// document表头
const documentForm = [
  { label: 'Site', prop: 'site',width:'200'},
  { label: '文件編號', prop: 'filenumber',width:'225' },
  { label: '文件名稱', prop: 'filename',width:'225' },
  {label:'附件',prop:'attachment',width:'200'},
  { label: '編輯', prop: 'edit',width:'75'},
  { label: '刪除', prop: 'delete',width:'75'},
]


// 证书表单项
const certificateFormItems = [
  { label: 'Site', prop: 'site' },
  { label: '文件編號', prop: 'filenumber' },
  { label: '文件名稱', prop: 'filename' },
  // {label:'附件',prop:'attachment'},
  { label: '登證日期', prop: 'issuedate' },
  { label: '失效日期', prop: 'expiringdate' },
  { label: '狀態', prop: 'status' },
  { label: '上傳人', prop: 'username' },
]
// 标准表单项
const criterionFormItems = [
  { label: 'Site', prop: 'site' },
  { label: '文件編號', prop: 'filenumber' },
  { label: '文件名稱', prop: 'filename' },
  { label: '版本', prop: 'ver' },
  { label: '登證日期', prop: 'issuedate' },
  // {label:'附件',prop:'attachment'},
  { label: '狀態', prop: 'status' },
  { label: '上傳人', prop: 'username' },
]
// 文件表单项
const documentFormItems = [
  { label: 'Site', prop: 'site' },
  { label: '文件編號', prop: 'filenumber' },
  { label: '文件名稱', prop: 'filename' },
]


// 初始页面数据
getAllData()

// 奇数行和偶数行的样式class
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

// 监听pagesize改变的事件
function handleCriterionSizeChange(newSize: number) {
  queryCriterionInfo.value.pagesize = newSize
  getCriterionList()
}

function handleCriterionCurrentChange(newPage: number) {
  queryCriterionInfo.value.pagenum = newPage
  getCriterionList()
}

// 监听pagesize改变的事件
function handleDocumentSizeChange(newSize: number) {
  queryDocumentInfo.value.pagesize = newSize
  getDocumentList()
}

function handleDocumentCurrentChange(newPage: number) {
  queryDocumentInfo.value.pagenum = newPage
  getDocumentList()
}

// 初始表格数据
function getAllData() {
  getData().then((res: any) => {

    tableData1 = JSON.parse(res.data.certificate)
    tableData2 = JSON.parse(res.data.criterion)
    tableData3 = JSON.parse(res.data.document)

    total2.value = tableData2.length
    total3.value = tableData3.length

    getCertificateList()
    getCriterionList()
    getDocumentList()
  })
}

// 搜索数据
function searchData(table: string) {

  let queryText = ''

  if (table === 'certificate') {
    queryText = queryCertificateInfo.value.query
  } else if (table === 'criterion') {
    queryText = queryCriterionInfo.value.query
  } else if (table === 'document') {
    queryText = queryDocumentInfo.value.query
  } else {
    console.log("错误")
  }

  // 连接后端
  getSearchData(table, queryText).then((res: any) => {

    let data: any[] = res.data
    

    console.log("324242342423",res.data)


    if (table === 'certificate') {
      certificateList.value = JSON.parse(data.certificate)
      certificateList.value.map((item)=>{
        if(item.attachment === null){
          item.attachment = []
        }
      })
    } else if (table === 'criterion') {
      criterionList.value = JSON.parse(data.criterion)

      criterionList.value.map((item)=>{
        if(item.attachment === null){
          item.attachment = []
        }
      })

    } else if (table === 'document') {
      documentList.value =JSON.parse(data.document)
      documentList.value.map((item)=>{
        if(item.attachment === null){
          item.attachment = []
        }
      })
    } else {
      console.log("错误")
    }
  })

}


// 获取证书表
function getCertificateList() {

  let data: Certificate[] = []
  for (let index = (queryCertificateInfo.value.pagenum - 1) * queryCertificateInfo.value.pagesize; index < queryCertificateInfo.value.pagesize * queryCertificateInfo.value.pagenum; index++) {

    // tableData1[index].attachment = [
    //   {
    //     name: '',
    //     url: 'http://10.124.131.25//uploadFile/2022-12-12/20221212170825894.xls',
    //   },
    // ]

    if (tableData1[index].attachment === null) {
      tableData1[index].attachment=[]
    }

    data[index] = tableData1[index];
    // certificateList.push(tableData1[index])
  }
  // Object.assign(certificateList,data)
  certificateList.value = data

}
// 获取标准表
function getCriterionList() {

  let data: Criterion[] = []
  for (let index = (queryCriterionInfo.value.pagenum - 1) * queryCriterionInfo.value.pagesize; index < queryCriterionInfo.value.pagesize * queryCriterionInfo.value.pagenum; index++) {

    // tableData2[index].attachment = [
    //   {
    //     name: '1',
    //     url: 'https://element-plus.org/images/element-plus-logo.svg',
    //   },
    // ]
    if (tableData2[index].attachment === null) {
      tableData2[index].attachment=[]
    }

    data[index] = tableData2[index];
    // criterionList.push(tableData2[index]);
  }
  //Object.assign(criterionList,data)
  criterionList.value = data
  // criterionList.splice(data.length)
}
// 获取文件表
function getDocumentList() {

  let data: Document[] = []
  for (let index = (queryDocumentInfo.value.pagenum - 1) * queryDocumentInfo.value.pagesize; index < queryDocumentInfo.value.pagesize * queryDocumentInfo.value.pagenum; index++) {

    // tableData3[index].attachment = [
    //   {
    //     name: '',
    //     url: 'https://element-plus.org/images/element-plus-logo.svg',
    //   },
    // ]

    if (tableData3[index].attachment === null) {
      tableData3[index].attachment=[]
    }
    data[index] = tableData3[index];
    // documentList.push(tableData3[index]);
  }
  documentList.value = data
}

// 根据表类型，添加对应数据
function addAllTypeData(table: string) {

  let addFormData: any = ''

  if (table === 'certificate') {

    addFormData = addCertificateForm.value
  } else if (table === 'criterion') {
    addFormData = addCriterionForm.value
  } else if (table === 'document') {
    addFormData = addDocumentForm.value
  } else {
    console.log("错误")
  }


  addData(table, addFormData).then((res: any) => {

    if (res.meta.status !== 201) {
      ElMessage.error('添加用户失败！')
    }
    ElMessage.success('添加用户成功！')

    addCertificateDialogVisible.value = false
    addCriterionDialogVisible.value = false
    addDocumentDialogVisible.value = false

    getCertificateList()
    getCriterionList()
    getDocumentList()

  })

}

// 根据表类型，修改对应数据
function editAllTypeData(table: string) {

  let editFormData: any = ''

  if (table === 'certificate') {
    editFormData = editCertificateForm.value
  } else if (table === 'criterion') {
    editFormData = editCriterionForm.value
  } else if (table === 'document') {
    editFormData = editDocumentForm.value
  } else {
    console.log("错误")
  }


  updateData(table, editFormData).then((res: any) => {

    if (res.meta.status !== 201) {
      ElMessage.error('编辑用户失败！')
    }
    ElMessage.success('编辑用户成功！')

    addCertificateDialogVisible.value = false
    addCriterionDialogVisible.value = false
    addDocumentDialogVisible.value = false

    getCertificateList()
    getCriterionList()
    getDocumentList()

  })

}

// 根据表类型和当前行，显示对应的编辑对话框
function showAllTypeEditDialog(table: string, row: any) {

  if (table === 'certificate') {
    editCertificateForm.value = row
    // Object.assign(editCertificateForm.value, row)
    editCertificateDialogVisible.value = true
  } else if (table === 'criterion') {
    editCriterionForm.value = row
    // Object.assign(editCriterionForm.value, row)
    editCriterionDialogVisible.value = true
  } else if (table === 'document') {
    // Object.assign(editDocumentForm.value, row)
    editDocumentForm.value = row
    editDocumentDialogVisible.value = true
  } else {
    console.log("错误")
  }

}

// 根据表类型和当前行，删除当前行
function removeAllTypeById(table: string, fileName: string) {

  ElMessageBox.confirm(
    `Are you sure to delete this data?`
  ).then(
    () => { deleteData(table, fileName), ElMessage.success('删除成功') },
    () => { ElMessage.error('删除失败') }
  )

}

// 下载表格数据
const downloadExcel = (table: string) => {

  let tableData: any = []
  let documentName = 'default.xlsx';
  if (table === 'tableData1') {
    tableData = tableData1;

    documentName = 'table1.xlsx'
  } else if (table === 'tableData2') {
    tableData = tableData2;

    documentName = 'table2.xlsx'
  } else if (table === 'tableData3') {
    tableData = tableData3;
    documentName = 'table3.xlsx'
  }

  // 将数据转换为 Excel 文件
  const workbook = XLSX.utils.book_new();
  const sheet = XLSX.utils.json_to_sheet(tableData);
  XLSX.utils.book_append_sheet(workbook, sheet, 'Sheet1');
  const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  // const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
  const blob = new Blob([excelBuffer], { type: 'application/octet-stream' });
  saveAs(blob, documentName)
};

// 批量上传
const batchBeforeUploade = (type:string) => (uploadFile:any) => {
    batchUpload(type,uploadFile)
    getAllData()
}

// 批量表格数据上传
function batchUpload(table:string,uploadFile:any) {
    let formDa = new FormData()
    formDa.append('file',uploadFile)
    uploadData(table,formDa).then((res:any)=>{
      console.log("res.data",res.data)
    }).catch((error:any)=>{
      console.log(error)
    })
    
} 


// 附件增加
const certificateBeforeUpload = (row:any) => (file:any) => {
     addAttach('certificate',row,file)
    // return false
}

const criterionBeforeUpload = (row:any) => (file:any) => {
   addAttach('criterion',row,file)
    // return false
}

const documentBeforeUpload = (row:any) => (file:any) => {
   addAttach('document',row,file)
    // return false
}

// 增加附件调用后端接口
function addAttach(table:string,row:any,file:any){
  let formDa = new FormData()
    formDa.append('file',file)
     addFile(table,row.filenumber,formDa).then((res:any)=>{
      console.log("res.data",res.data)
    }).catch((error:any)=>{
      console.log(error)
    })
    getAllData()
}

const attachmentBeforeRemove = (row:any, type:any) => (attachment:any,attachmentList:any) => {
   deleteFile(type,row.filenumber, attachment.name).then((res:any)=>{
      console.log("res.data",res.data)
    }).catch((error:any)=>{
      console.log(error)
    })
    getAllData()
}

// 删除附件
const handleRemove: UploadProps['onRemove'] = (file, uploadFiles) => {
  console.log("uploadFiles", uploadFiles)
}

// 浏览附件
const handlePreview: UploadProps['onPreview'] = (uploadFile) => {
  // console.log(uploadFile.url)
  window.open(uploadFile.url)
  // 点击文件查看
}




</script>


<style  lang="scss" scoped>

.upload-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
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
.pageSelect.el-popper.is-light {
  background-color: rgb(3, 2, 2);

  .el-select-dropdown__item.selected {
    color: aqua;
  }
}
</style>
