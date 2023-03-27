<template>
  <div>
    <div class="interval">
      <el-row style="height:20px"></el-row>
      <el-row class="second_row" :gutter="24" style="height:30px">
          <el-col :span="4" :offset="14" style="height: 25px;">
            <el-input v-model="queryInfo.query" placeholder="Please input" @clear="getUserList" clearable
              style="width: 200px"    >
              <template #prepend>
                <el-icon><Filter /></el-icon>
           
              </template>
              <template #append>
                <!-- <el-button :icon="Search" /> -->
                <el-button>
                  <el-icon >
                    <Search />
                  </el-icon>
                </el-button>
              </template>
            </el-input>
          </el-col>
          <el-col :span="2" style="padding-top: 4px; padding-right: 0; padding-left: 0;"><el-icon><UploadFilled /></el-icon> Upload</el-col>
          <el-col :span="2" style="padding-top: 4px; padding-right: 0; padding-left: 0;"><el-icon><Download /></el-icon> Export</el-col>
          <el-col :span="2" style="padding-top: 4px; padding-right: 0; padding-left: 0;">
            <el-button @click="addDialogVisible = true" style="height: 25px;  color: white; background: transparent; border: none;">
              <el-icon>
                <Plus />
              </el-icon>New</el-button>
          </el-col>

        </el-row>
    </div>
    <div class="onePicture">
    
    
      <el-table   :row-style="{height: '40px'}" height="100" :data="tableData" :row-class-name="tableRowClassName" :header-cell-style="{textAlign: 'center'}" :cell-style="{ textAlign: 'center' }" >
        <el-table-column label="No." prop="no" width="75px"></el-table-column>
        <el-table-column label="證書編號" prop="bianhao" width="200px"></el-table-column>
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

        <el-table-column  label="刪除" width="75px">
          <template #default="scope">
            <el-button :icon="Delete" size="small" type="danger" @click="handleDelete(scope.$index, scope.row)"></el-button>
          </template>
        </el-table-column>


      </el-table>

    </div>
    <div class="interval">

    </div>
    <div class="twoPicture">
      <el-table :row-style="{height: '40px'}"  max-height="260" :data="tableData" :row-class-name="tableRowClassName" :header-cell-style="{textAlign: 'center'}" :cell-style="{ textAlign: 'center' }" >

<el-table-column label="No." prop="no" width="75px"></el-table-column>
<el-table-column label="證書編號" prop="bianhao" width="200px"></el-table-column>
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
    <el-button :icon="Delete" size="small" type="danger" @click="handleDelete(scope.$index, scope.row)"></el-button>
  </template>
</el-table-column>

</el-table>

<div class="page_row">
    <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="queryInfo.pagenum"
                        :page-sizes="[1,2,5]"
                        :page-size="queryInfo.pagesize"
                        layout=" total, prev, pager, next,sizes, jumper"
                        :total="total"
                        class="page"
                        
      ></el-pagination>
</div>>

    </div>
    <div class="interval">

    </div>
    <div class="threePicture">
    

    </div>
  </div>
</template>

<!-- <script setup lang="ts"> -->
<script lang="ts" setup>

import { Plus,Search,Filter,UploadFilled, Download,Edit,Delete} from '@element-plus/icons-vue'


const queryInfo = {
  query:'',
  pagenum: 1,
  pagesize: 2
}

let userList = []
let total = 0

interface User {
  date: string
  name: string
  address: string
}

const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]


 // 监听pagesize改变的事件
 function  handleSizeChange(newSize:number ) {
            // console.log(newSize)
          queryInfo.pagesize = newSize
          getUserList()
        }

function   handleCurrentChange(newPage:number ) {
    // console.log(newPage)
    queryInfo.pagenum = newPage
    getUserList()
}




function getUserList() {
            
  userList = tableData
 total = tableData.length      
        }



const tableRowClassName = ({
  row,
  rowIndex
}: {
  row:User,
  rowIndex: number
}) => {
  if (rowIndex % 2 === 1) {
    return 'odd-row'
  } else if (rowIndex % 2 === 0) {
    return 'even-row'
  }
  return ''
}

</script>



<style  lang="scss" scoped>
.el-table {
  
  background-color: transparent !important;
  color: #fff !important;
  // max-height:100px;
  
  
  ::before {//去除底部白线
	 left: 0;
	 bottom: 0;
	 width: 100%;
	 height: 0px;
  }

}





.el-button--small{
    background: transparent;
    border: none;
}

.el-pagination{

  ::v-deep button:disabled{
    background: transparent !important;
  }
  ::v-deep .btn-next {
    background: transparent !important;
    border-color:aqua;
  }

  ::v-deep  .el-pager li{
    background:transparent;
  }

} 

::v-deep .el-pager li.is-active{
  background: transparent;
  color:rgb(3, 177, 177);
}


.el-col{
  color: white;
}

::v-deep .odd-row {
  --el-table-tr-bg-color: #828282;
  
}
::v-deep .even-row {
  --el-table-tr-bg-color: transparent;
 
}

::v-deep .el-table__header-wrapper{
  height: 40px;
  width: 1200px;
  
}

::v-deep .el-table__inner-wrapper{
  margin: 20px 40px 20px 40px;
}


 ::v-deep .el-table th{
  background-color:#828282;
  color: white;
}




.second_row {
  ::v-deep .el-input__wrapper{
  color:white;
  background-color: transparent;
  box-shadow: 0 0 0 0;
  border:none; 
  border-bottom:1px solid #eee;
}

::v-deep .el-input-group{
  height: 20px;
}

::v-deep .el-input-group__prepend{
  width:0px;
  background: transparent;
  box-shadow:0 0 0 0 ;
}

::v-deep .el-input-group__append{
  width:0px;
  background: transparent;
  box-shadow: 0 0 0 0;
  .el-button{
    background-color: transparent;
    padding-right: 10px; 
    padding-left: 10px;
  } 
}
}


.page_row{
  ::v-deep .el-input__wrapper{
  color:white;
  background-color: transparent;
  // box-shadow: 0 0 0 0;
  // border:none; 
  // border-bottom:none;
  // border-color: aqua;





}


::v-deep .el-input{
    --el-input-border-color:rgb(3, 177, 177);
  }
// .el-pager{
//   ::v-deep li.is-active{
//   background:transparent;
//   color:aqua;
// }
// }  
::v-deep .el-input__inner{
  color: #fff;
}



::v-deep .el-pagination__goto{
  color: white;
}
}





.el-button:hover {
    background: #126c9e !important;
}

::v-deep  .el-icon{
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

  // background-size: cover;
  // background-position: center;
  // position: relative;
}

.twoPicture {
  background-image: url("./background/1280x290\ 邊框4px-62-62.png");
  width: 1280px;
  height: 290px;
  background-size: 1280px 290px;

  //   background-size: cover;
  //   background-position: center;
  //   position: relative;
}

.threePicture {
  background-image: url("./background/1280x210\ 邊框4px-62.png");
  width: 1280px;
  height: 210px;
  background-size: 1280px 210px;

  // background-size: cover;
  // background-position: center;
  // position: relative;
}



</style>
