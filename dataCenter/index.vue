<!--应该能申请更改-->

<template>
  <div class="container">

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
              <el-button @click="getCertificateList">
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
          <el-button @click="editCertificateDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Upload</el-button>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel(tableData1)"
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

    <div class="onePicture">


      <el-table  ref="table1" :row-style="{ height: 30 }" height="100" :data="certificateList" :row-class-name="tableRowClassName"
        :header-cell-style="{ textAlign: 'center' }" :cell-style="{ textAlign: 'center' }"  >
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
        <el-table-column label="發證日期" prop="shengxiao" width="150px"></el-table-column>
        <el-table-column label="失效日期" prop="shixiao" width="150px"></el-table-column>
        <el-table-column label="狀態" prop="zhuangtai" width="75px"></el-table-column>
        <el-table-column label="上傳人" prop="uploader" width="100px"></el-table-column>
        <el-table-column label="編輯" prop="edit" width="75px">
          <template #default="scope">
            <el-button :icon="Edit" size="small" @click="showCertificateEditDialog(scope.row.no)"></el-button>
          </template>
        </el-table-column>

        <el-table-column label="刪除" width="75px">
          <template #default="scope">

            <el-button size="small" type="danger" class="delete_button" @click="removeCertificateById(scope.row.no)">
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
          <el-input v-model="queryStandardInfo.query" placeholder="Please input" @clear="getStandardList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>

            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon @click="getStandardList">
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><UploadFilled /></el-icon>  -->
          <el-button @click="editStandardDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Upload</el-button>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel(tableData2)"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addStandardDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>

      </el-row>
    </div>

    <div class="twoPicture">
      <el-table ref="table2" :row-style="{ height: 30 }" max-height="250" :data="standardList" :row-class-name="tableRowClassName"
        :header-cell-style="{ textAlign: 'center' }" :cell-style="{ textAlign: 'center' }">

        <el-table-column label="No." prop="no" width="75px">

        </el-table-column>
        <el-table-column label="Site" prop="site" width="50px">

        </el-table-column>
        <el-table-column label="文件編號" prop="bianhao" width="150px">

        </el-table-column>
        <el-table-column label="文件名稱" prop="mingcheng" width="200px">

        </el-table-column>
        <el-table-column label="版本" prop="banben" width="100px">

        </el-table-column>
        <el-table-column label="發證日期" prop="shengxiao" width="150px">

        </el-table-column>
        <el-table-column label="附件" prop="fujian" width="150px">

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
        <el-table-column label="上傳人" prop="uploader" width="100px"></el-table-column>
        <el-table-column label="編輯" prop="edit" width="75px">
          <template #default="scope">
            <el-button :icon="Edit" size="small" @click="showStandardEditDialog(scope.row.no)"></el-button>
          </template>
        </el-table-column>

        <el-table-column label="刪除" width="75px">
          <template #default="scope">
            <el-button size="small" type="danger" class="delete_button" @click="removeStardardById(scope.row.no)">
              <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </template>
        </el-table-column>

      </el-table>

      <div class="page_row">
        <el-pagination @size-change="handleStandardSizeChange" @current-change="handleStandardCurrentChange"
          v-model:current-page="currentStandardPage" :page-sizes="[1, 2, 5]" v-model:page-size="pageStandardSize"
          layout=" ->,total, prev, pager, next,sizes, jumper" :total="total2" popper-class="pageSelect"></el-pagination>
      </div>

    </div>

    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
        <el-col :span="4" :offset="15" style="height: 25px;padding-top: 6px;  ">
          <el-input v-model="queryFileInfo.query" placeholder="Please input" @clear="getFileList" clearable
            style="width: 200px">
            <template #prepend>
              <el-icon>
                <Filter />
              </el-icon>

            </template>
            <template #append>
              <!-- <el-button :icon="Search" /> -->
              <el-button>
                <el-icon @click="getFileList">
                  <Search />
                </el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 5px;">
          <img src="./icons/上傳.png" class="upload_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><UploadFilled /></el-icon>  -->
          <el-button @click="editFileDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Upload</el-button>

        </el-col>
        <el-col :span="1.5" style="padding-top: 6px; padding-right: 0; padding-left: 10px;">
          <img src="./icons/下載-63.png" class="export_icon" style="width:16px;height: 12px;" />
          <!-- <el-icon><Download /></el-icon>  -->
          <el-button @click="downloadExcel(tableData3)"
            style="height: 25px;  color: white; background: transparent; border: none;">
            Export</el-button>
        </el-col>
        <el-col :span="1.5" style="padding-top: 4px; padding-right: 0; padding-left: 5px;">
          <el-button @click="addFileDialogVisible = true"
            style="height: 25px;  color: white; background: transparent; border: none;">
            <el-icon>
              <Plus />
            </el-icon>New</el-button>
        </el-col>

      </el-row>
    </div>

    <div class="threePicture">



      <el-table ref="table3" :row-style="{ height: 30 }" max-height="170" :data="fileList" :row-class-name="tableRowClassName"
        :header-cell-style="{ textAlign: 'center' }" :cell-style="{ textAlign: 'center' }">

        <el-table-column label="No." prop="no" width="200px"></el-table-column>
        <el-table-column label="Site" prop="site" width="200"></el-table-column>
        <el-table-column label="文件名稱" prop="bianhao" width="240"></el-table-column>
        <el-table-column label="文件編號" prop="biaozhun" width="240"></el-table-column>
        <el-table-column label="附件" prop="fujian" width="200"></el-table-column>
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
        <el-table-column label="編輯" prop="edit" width="60px">
          <template #default="scope">
            <el-button :icon="Edit" size="small" @click="showFileEditDialog(scope.row.no)"></el-button>
          </template>
        </el-table-column>

        <el-table-column label="刪除" width="60px">
          <template #default="scope">
            <el-button size="small" type="danger" class="delete_button" @click="removeFileById(scope.row.no)">
              <img src="./icons/刪除-未選中.png" class="export_icon" style="width:12px;height: 12px;" />
            </el-button>
          </template>
        </el-table-column>

      </el-table>

      <div class="page_row">


        <el-pagination v-model:current-page="currentFilePage" v-model:page-size="pageFileSize" :page-sizes="[1, 2, 5]"
          layout="->, total,  prev, pager, next, sizes, jumper" :total="total3" @size-change="handleFileSizeChange"
          @current-change="handleFileCurrentChange" popper-class="pageSelect" />
      </div>

    </div>

  </div>




  <!-- 添加证书对话框 -->
  <el-dialog v-model="addCertificateDialogVisible" :visible.sync="addCertificateDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addCertificateForm" ref="addFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="addCertificateForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="addCertificateForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="biaozhun">
        <el-input v-model="addCertificateForm.biaozhun"></el-input>
      </el-form-item>
      <el-form-item label="版本" prop="fujian">
        <el-input v-model="addCertificateForm.fujian"></el-input>
      </el-form-item>
      <el-form-item label="登證日期" prop="shengxiao">
        <el-input v-model="addCertificateForm.shengxiao"></el-input>
      </el-form-item>
      <el-form-item label="失效日期" prop="shixiao">
        <el-input v-model="addCertificateForm.shixiao"></el-input>
      </el-form-item>
      <el-form-item label="狀態" prop="zhuangtai">
        <el-input v-model="addCertificateForm.zhuangtai"></el-input>
      </el-form-item>
      <el-form-item label="上傳人" prop="uploader">
        <el-input v-model="addCertificateForm.uploader"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addCertificateDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addCertificate">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑证书对话框-->>
  <el-dialog title="修改用户" v-model="editCertificateDialogVisible" :visible.sync="editCertificateDialogVisible" width="50%"
    @close="editDialogClosed">
    <el-form :model="editCertificateForm" ref="editFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="editCertificateForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="editCertificateForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="biaozhun">
        <el-input v-model="editCertificateForm.biaozhun"></el-input>
      </el-form-item>
      <el-form-item label="版本" prop="fujian">
        <el-input v-model="editCertificateForm.fujian"></el-input>
      </el-form-item>
      <el-form-item label="登證日期" prop="shengxiao">
        <el-input v-model="editCertificateForm.shengxiao"></el-input>
      </el-form-item>
      <el-form-item label="失效日期" prop="shixiao">
        <el-input v-model="editCertificateForm.shixiao"></el-input>
      </el-form-item>
      <el-form-item label="狀態" prop="zhuangtai">
        <el-input v-model="editCertificateForm.zhuangtai"></el-input>
      </el-form-item>
      <el-form-item label="上傳人" prop="uploader">
        <el-input v-model="editCertificateForm.uploader"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="editCertificateDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editCertificateInfo">确 定</el-button>
    </span>
  </el-dialog>


  <!-- 添加标准对话框 -->
  <el-dialog v-model="addStandardDialogVisible" :visible.sync="addStandardDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addStandardForm" ref="addFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="addStandardForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="addStandardForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="mingcheng">
        <el-input v-model="addStandardForm.mingcheng"></el-input>
      </el-form-item>
      <el-form-item label="版本" prop="banben">
        <el-input v-model="addStandardForm.banben"></el-input>
      </el-form-item>
      <el-form-item label="登證日期" prop="shengxiao">
        <el-input v-model="addStandardForm.shengxiao"></el-input>
      </el-form-item>
      <el-form-item label="附件" prop="fujian">
        <el-input v-model="addStandardForm.fujian"></el-input>
      </el-form-item>
      <el-form-item label="狀態" prop="zhuangtai">
        <el-input v-model="addStandardForm.zhuangtai"></el-input>
      </el-form-item>
      <el-form-item label="上傳人" prop="uploader">
        <el-input v-model="addStandardForm.uploader"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addStandardDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addStandard">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑标准对话框-->>
  <el-dialog title="修改用户" v-model="editStandardDialogVisible" :visible.sync="editStandardDialogVisible" width="50%"
    @close="editDialogClosed">
    <el-form :model="editStandardForm" ref="editFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="editStandardForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="editStandardForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="mingcheng">
        <el-input v-model="editStandardForm.mingcheng"></el-input>
      </el-form-item>
      <el-form-item label="版本" prop="banben">
        <el-input v-model="editStandardForm.banben"></el-input>
      </el-form-item>
      <el-form-item label="登證日期" prop="shengxiao">
        <el-input v-model="editStandardForm.shengxiao"></el-input>
      </el-form-item>
      <el-form-item label="附件" prop="fujian">
        <el-input v-model="editStandardForm.fujian"></el-input>
      </el-form-item>
      <el-form-item label="狀態" prop="zhuangtai">
        <el-input v-model="editStandardForm.zhuangtai"></el-input>
      </el-form-item>
      <el-form-item label="上傳人" prop="uploader">
        <el-input v-model="editStandardForm.uploader"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="editStandardDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editStandardInfo">确 定</el-button>
    </span>
  </el-dialog>


  <!-- 添加文件对话框 -->
  <el-dialog v-model="addFileDialogVisible" :visible.sync="addFileDialogVisible" title="添加" width="30%">
    <!-- 内容主体区域 -->
    <el-form :model="addFileForm" ref="addFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="addFileForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="addFileForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="mingcheng">
        <el-input v-model="addFileForm.mingcheng"></el-input>
      </el-form-item>
      <el-form-item label="附件" prop="fujian">
        <el-input v-model="addFileForm.fujian"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addFileDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addFile">
          確認
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑文件对话框-->>
  <el-dialog title="修改用户" v-model="editFileDialogVisible" :visible.sync="editFileDialogVisible" width="50%"
    @close="editDialogClosed">
    <el-form :model="editFileForm" ref="editFormRef" label-width="100px" label-position="top">
      <el-form-item label="Site" prop="site">
        <el-input v-model="editFileForm.site"></el-input>
      </el-form-item>
      <el-form-item label="文件編號" prop="bianhao">
        <el-input v-model="editFileForm.bianhao"></el-input>
      </el-form-item>
      <el-form-item label="文件名稱" prop="mingcheng">
        <el-input v-model="editFileForm.mingcheng"></el-input>
      </el-form-item>
      <el-form-item label="附件" prop="fujian">
        <el-input v-model="editFileForm.fujian"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="editFileDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editFileInfo">确 定</el-button>
    </span>
  </el-dialog>


</template>

<!-- <script setup lang="ts"> -->
<script lang="ts" setup>
import { ref,reactive} from 'vue'
import { Plus, Search, Filter, UploadFilled, Download, Edit, Delete } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';
import * as XLSX from 'xlsx';



const currentFilePage = ref(3)
const pageFileSize = ref(2)

const currentStandardPage = ref(3)
const pageStandardSize = ref(2)

// type Query = Record<string, any>;
type Form = Record<string, any>;

var queryCertificateInfo = ref<Form>(
    {
    query: '',
    pagenum: 1,
    pagesize: 1
  }
)
var queryStandardInfo = ref<Form>(
    {
    query: '',
    pagenum: 3,
    pagesize: 2
  }
)
var queryFileInfo = ref<Form>(
    {
    query: '',
    pagenum: 2,
    pagesize: 1
  }
)

interface Certificate {
  no: string
  site: string
  bianhao: string
  biaozhun: string
  fujian: string
  shengxiao:string
  shixiao:string
  zhuangtai: string
  uploader:string
}

interface Standard {
  no: string
  site: string
  bianhao: string
  mingcheng: string
  banben:string,
  shengxiao:string
  fujian: string
  zhuangtai: string
  uploader:string
}

interface File {
  no: string
  site: string
  mingcheng: string
  bianhao: string
  fujian: string
}

var certificateList: Certificate[] = []
var standardList : Standard[] = []
var fileList : File[] = []



// 添加证书的表单数据
var addCertificateForm = ref<Certificate>(
    {
    no:'',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    shengxiao:'',
    shixiao:'',
    zhuangtai: '',
    uploader:''
  }
)

// 编辑证书的表单数据
var editCertificateForm = ref<Form>({
    no:'',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    shengxiao:'',
    shixiao:'',
    zhuangtai: '',
    uploader:''
})


// 添加标准的表单数据
var addStandardForm = ref<Standard>(
    {
    no:'',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao:'',
    fujian:'',
    zhuangtai: '',
    uploader:''
  }
)

// 编辑标准的表单数据
var editStandardForm = ref<Form>({
    no:'',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao:'',
    fujian:'',
    zhuangtai: '',
    uploader:''
})


// 添加文件的表单数据
var addFileForm = ref<File>(
    {
      no:'',
      site:'',
      mingcheng:'',
      bianhao:'',
      fujian:'',
  }
)

// 编辑文件的表单数据
var editFileForm = ref<Form>({
      no:'',
      site:'',
      mingcheng:'',
      bianhao:'',
      fujian:'',
})


// 控制添加证书对话框的显示与隐藏
const addCertificateDialogVisible = ref(false);
// 控制修改证书对话框的显示与隐藏
var editCertificateDialogVisible = ref(false);


// 控制添加标准对话框的显示与隐藏
const addStandardDialogVisible = ref(false);
// 控制修改标准对话框的显示与隐藏
var editStandardDialogVisible = ref(false);


// 控制添加文件对话框的显示与隐藏
const addFileDialogVisible = ref(false);
// 控制修改文件对话框的显示与隐藏
var editFileDialogVisible = ref(false);



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


const tableData1: Certificate[] = [
  {
    no: '1',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    shengxiao:'',
    shixiao:'',
    zhuangtai: '',
    uploader:'zhagnsan',
  },
  {
    no: '2',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    shengxiao:'',
    shixiao:'',
    zhuangtai: '',
    uploader:'lisi',
  },
  {
    no: '3',
    site: '',
    bianhao: '',
    biaozhun: '',
    fujian: '',
    shengxiao:'',
    shixiao:'',
    zhuangtai: 'wangwu',
    uploader:'',
  },
]

const tableData2: Standard[] = [
  {
    no: '1',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao: '',
    fujian: '',
    zhuangtai: '',
    uploader: 'zhangsan',
  },
  {
    no: '2',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao: '',
    fujian: '',
    zhuangtai: '',
    uploader: 'lisi',
  },
  {
    no: '3',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao: '',
    fujian: '',
    zhuangtai: '',
    uploader: 'wangwu',
  },
  {
    no: '4',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao: '',
    fujian: '',
    zhuangtai: '',
    uploader: 'zhaoliu',
  },
  {
    no: '5',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao: '',
    fujian: '',
    zhuangtai: '',
    uploader: 'qianqi',
  },
  {
    no: '6',
    site: '',
    bianhao: '',
    mingcheng: '',
    banben: '',
    shengxiao: '',
    fujian: '',
    zhuangtai: '',
    uploader: 'zhouba',
  },
]

const tableData3: File[] = [
  {
    no: '1',
    site: '',
    mingcheng: '',
    bianhao: '',
    fujian: 'zhangsan',
  },
  {
    no: '2',
    site: '',
    mingcheng: '',
    bianhao: '',
    fujian: 'lisi',
  },
  {
    no: '3',
    site: '',
    mingcheng: '',
    bianhao: '',
    fujian: 'wangwu',
  },
]


const total2 = ref(0)
const total3 = ref(0)

total2.value = tableData2.length
total3.value = tableData3.length


getCertificateList()
getStandardList()
getFileList()



// 监听pagesize改变的事件
function handleStandardSizeChange(newSize: number) {
  queryStandardInfo.value.pagesize = newSize
  getStandardList()
}

function handleStandardCurrentChange(newPage: number) {
  queryStandardInfo.value.pagenum = newPage
  getStandardList()
}



// 监听pagesize改变的事件
function handleFileSizeChange(newSize: number) {
  queryFileInfo.value.pagesize = newSize
  getFileList()
}

function handleFileCurrentChange(newPage: number) {
  queryFileInfo.value.pagenum = newPage
  getFileList()
}


function getCertificateList() {

  let data = []
  for (let index = (queryCertificateInfo.value.pagenum - 1) * queryCertificateInfo.value.pagesize; index < queryCertificateInfo.value.pagesize * queryCertificateInfo.value.pagenum; index++) {

    data[index] = tableData1[index];

  }
 
  console.log(queryCertificateInfo.value.query.value)
  certificateList = data
  
  certificateList.splice(data.length)

}

function getStandardList() {

var data = []
for (let index = (queryStandardInfo.value.pagenum - 1) * queryStandardInfo.value.pagesize; index < queryStandardInfo.value.pagesize * queryStandardInfo.value.pagenum; index++) {

 
  data[index] = tableData2[index];
}
console.log(queryCertificateInfo.value.query.value)
standardList = data
// standardList.splice(data.length)

}


function getFileList() {

let data = []
for (let index = (queryFileInfo.value.pagenum - 1) * queryFileInfo.value.pagesize; index < queryFileInfo.value.pagesize * queryFileInfo.value.pagenum; index++) {

  data[index] = tableData3[index];

}

console.log(queryCertificateInfo.value.query.value)
fileList = data
fileList.splice(data.length)

}

// 证书增删改查
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


    addCertificateForm.value.no = tableData1.length.toString();
    tableData1.push(addCertificateForm.value)

    addCertificateDialogVisible.value = false


    getCertificateList()
}

function editCertificateInfo() {

  const id = parseInt(editCertificateForm.value.no)
  Object.assign(tableData1[id - 1], editCertificateForm.value)

  editCertificateDialogVisible.value = false
  getCertificateList()

}

function showCertificateEditDialog(id: number) {

  Object.assign(editCertificateForm.value, tableData1[id - 1])
  editCertificateDialogVisible.value = true
}

function removeCertificateById(id: number) {
 
 console.log(id)
 ElMessage.success('删除成功');

 
//  // 弹框询问
//  const confirmResult = await this.$confirm(
//                 '此操作将永久删除该用户, 是否继续?',
//                 '提示', {
//                 confirmButtonText: '确定',
//                 cancelButtonText: '取消',
//                 type: 'warning'
//             }).catch(err => err)

//             if (confirmResult !== 'confirm') {
//                 return this.$message.info('已取消删除')
//             }

//             const { data: res } = await this.$http.delete('users/' + id)
//             if (res.meta.status !== 200) {
//                 return this.$message.error('删除用户失败！')
//             }

//             this.$message.success('删除用户成功！')
//             this.getUserList()

}

// 标准增删改查
function addStandard() {
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


    addStandardForm.value.no = tableData2.length.toString();

    // tableData2 = tableData2.concat(addStandardForm.value)
    tableData2.push(addStandardForm.value)
    // Vue.set(tableData2,tableData2.length,addStandardForm.value)
  
    total2.value = tableData2.length

    addStandardDialogVisible.value = false
    getStandardList()

}

function editStandardInfo() {

  const id = parseInt(editStandardForm.value.no)
  Object.assign(tableData2[id - 1], editStandardForm.value)

  editStandardDialogVisible.value = false
  getStandardList()

}

function showStandardEditDialog(id: number) {
  

  Object.assign(editStandardForm.value, tableData2[id - 1])
  editStandardDialogVisible.value = true
}

function removeStardardById(id: number) {
 
 console.log(id)
 ElMessage.success('删除成功');

 
//  // 弹框询问
//  const confirmResult = await this.$confirm(
//                 '此操作将永久删除该用户, 是否继续?',
//                 '提示', {
//                 confirmButtonText: '确定',
//                 cancelButtonText: '取消',
//                 type: 'warning'
//             }).catch(err => err)

//             if (confirmResult !== 'confirm') {
//                 return this.$message.info('已取消删除')
//             }

//             const { data: res } = await this.$http.delete('users/' + id)
//             if (res.meta.status !== 200) {
//                 return this.$message.error('删除用户失败！')
//             }

//             this.$message.success('删除用户成功！')
//             this.getUserList()

}


// 文件增删改查
function addFile() {
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


    addFileForm.value.no = tableData3.length.toString();
    tableData3.push(addFileForm.value)
    total3.value = tableData3.length

    addFileDialogVisible.value = false
    getFileList()

}

function editFileInfo() {

  const id = parseInt(editFileForm.value.no)
  Object.assign(tableData3[id - 1], editFileForm.value)

  editFileDialogVisible.value = false
  getFileList()

}

function showFileEditDialog(id: number) {

Object.assign(editFileForm.value, tableData3[id - 1])
editFileDialogVisible.value = true
}

function removeFileById(id: number) {
 
 console.log(id)
 ElMessage.success('删除成功');

 
//  // 弹框询问
//  const confirmResult = await this.$confirm(
//                 '此操作将永久删除该用户, 是否继续?',
//                 '提示', {
//                 confirmButtonText: '确定',
//                 cancelButtonText: '取消',
//                 type: 'warning'
//             }).catch(err => err)

//             if (confirmResult !== 'confirm') {
//                 return this.$message.info('已取消删除')
//             }

//             const { data: res } = await this.$http.delete('users/' + id)
//             if (res.meta.status !== 200) {
//                 return this.$message.error('删除用户失败！')
//             }

//             this.$message.success('删除用户成功！')
//             this.getUserList()

}



// 下载
// const tableRef1 = ref<HTMLElement | null>(null);
// const tableRef2 = ref<HTMLElement | null>(null);
// const tableRef3 = ref<HTMLElement | null>(null);

// const downloadExcel = (tableData: any[]) => {
//   let tableRef: any;
//   let fileName = 'default.xlsx';
//   if (tableData === tableData1) {
//     tableRef = tableRef1;
//     fileName = 'table1.xlsx'
//   } else if (tableData === tableData2) {
//     tableRef = tableRef2;
//     fileName = 'table2.xlsx'
//   } else if (tableData === tableData3) {
//     tableRef = tableRef3;
//     fileName = 'table3.xlsx'
//   }
//   const table = tableRef.value?.querySelector('.el-table__body');
//   if (table) {
//     // 从表格中获取数据
//     const data = [];
//     const rows = table.querySelectorAll('.el-table__row');
//     rows.forEach((row) => {
//       const rowData = [];
//       const cells = row.querySelectorAll('.el-table__cell');
//       cells.forEach((cell) => {
//         rowData.push(cell.textContent);
//       });
//       data.push(rowData);
//     });
//     // 将数据转换为 Excel 文件
//     const workbook = XLSX.utils.book_new();
//     const sheet = XLSX.utils.aoa_to_sheet(data);
//     XLSX.utils.book_append_sheet(workbook, sheet, 'Sheet1');
//     const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
//     const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    
//     // 下载文件
//     const link = document.createElement('a');
//     link.href = URL.createObjectURL(blob);
//     link.download = fileName;
//     document.body.appendChild(link);
//     link.click();
//     document.body.removeChild(link);
//   }
// };


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
