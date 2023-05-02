<template>
  <div>
    <div>
      <el-button class="buttonSubmit"
        ><div style="position: absolute">
          <svg-icon className="iconMenu" iconName="icon-files"></svg-icon
          >{{ $t('authority.select') }}
        </div>
        <input
          type="file"
          name="file"
          @change="handleFile($event)"
          multiple="multiple"
        />
      </el-button>
      <el-button @click="handleUpload" class="buttonSubmit">
        <svg-icon className="iconMenu" iconName="icon-upload"></svg-icon>
        {{ $t('authority.upload') }}
      </el-button>
      <el-button class="buttonSubmit" style="padding: 0">
        <a :href="prop.url" class="aStyle"
          ><svg-icon className="iconMenu" iconName="icon-download"></svg-icon>
          {{ $t('authority.download') }}</a
        >
      </el-button>
      <ul v-show="fileName">
        <li :class="{ uploadSuccess: flag === 1, uploadFailure: flag === 2 }">
          {{ fileName }}
          <svg-icon
            v-show="flag === 1"
            className="iconMenu2"
            iconName="icon-RectangleCopy"
          ></svg-icon>
        </li>
      </ul>
    </div>
    <div class="tableWrapper">
      <el-table v-if="tableData" :data="tableData" style="width: 100%">
        <el-table-column
          v-for="(value, key) in tableHeader"
          :key="key"
          :label="key"
          :property="key"
        />
      </el-table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { uploadSignupFile } from '@/api/authority.ts'
import { responseHandle } from '@/utils/message.ts'
import { ref } from 'vue'
const prop = defineProps({
  url: {
    default: 'http://10.213.129.26:8000/Excel/Template/權限批量申請.xlsx',
    type: String,
  },
})
// ========================================= 上传配置 =========================================
const flag = ref(0) // 0未上传 1上传成功 2上传失败

// ======================================= 上传前文件处理 =========================================
const fileName = ref()
const formData = new FormData() // 創建formData
const handleFile = (e) => {
  //   console.log(e.target.files[0])
  const file = e.target.files[0] // 拿到文件
  // const formData = new FormData() // 創建formData
  formData.append('file', file) // 文件放进formData
  fileName.value = file.name
  // console.log(formData.get('file'))
  // 变更文件时要清空列表样式
  console.log(flag.value)
  flag.value = 0
  console.log(flag.value)
}
// ========================================= 文件上传 ==============================================
const handleUpload = () => {
  uploadSignupFile(formData, { empno: 'F1333521' }).then((res) => {
    console.log(res)
    if (!responseHandle(res, true)) {
      flag.value = 2
    } else {
      flag.value = 1
      tableData.value = res.data.tableData
    }
  })
}
// ========================================= 模板下载 ==============================================
const downTemplate = () => {}
// ========================================= 上传成功数据显示 =======================================
const tableData = ref()
const tableHeader = ref({
  empno: 'F1333521',
  username: '大O',
  role: 'admin',
  site: 'FOL',
  mfg: 'ALL',
  area: 'ALL',
  building: 'ALL',
  floor: 'ALL',
  email: 'nsd-it-cfd@mail.foxconn.com',
  ext: '78093',
})
</script>

<style lang="scss" scoped>
// 按钮样式
.buttonSubmit {
  color: $font-gray;
  border: 1px solid $font-gray;
  // background-size: 100% 100%;
  // background-image: url('/src/assets/total/button1.png');
  background-color: transparent;
  &:hover {
    background-color: #ffffff80;
    color: $font-color;
    border: 1px solid $font-color;
  }
}
// 常用
.iconMenu {
  color: $font-gray;
  font-size: 18px;
  margin: 0px 5px;
  &:hover {
    color: $font-color;
  }
}
// 常亮
.iconMenu2 {
  margin: 5px;
  color: $font-color;
  font-size: 16px;
  // height: 20px;
  margin: 0px 5px;
}
.uploadStyle {
  color: $font-gray;
  background-size: 100% 100%;
  background-image: url('/src/assets/total/button1.png');
  height: 20px;
  width: 150px;
}

input[type='file'] {
  opacity: 0;
  font-size: 18px;
  width: 100px;
}

.uploadSuccess {
  color: $font-color;
}
.uploadFailure {
  color: #f56c5b;
}
// a标签样式
.aStyle {
  display: inline-block;
  height: 32px;
  line-height: 32px;
  padding: 0 5px;
  color: $font-gray;
  &:hover {
    color: $font-color;
  }
}

.tableWrapper :deep() {
  .el-table {
    color: #fff;
    background: transparent;
    // border: 1px solid green !important;
    --el-table-border-color: none;
    --el-table-row-hover-bg-color: transparent;
    & .cell {
      // 控制表格单元表格
      // word-break: normal;
    }
    & thead {
      // 表头样式
      color: rgb(255, 255, 255);
      background: #222;
      tr {
        background-image: url('/src/assets/authority/tableItemBg.png'); // thead的样式
        background-size: 100% 100%;
      }
    }
    & tbody {
      tr:nth-child(2n) {
        background-image: url('/src/assets/authority/tableItemBg.png'); // tbody单独设置tr的样式
        background-size: 100% 100%;
      }
    }
    .el-table__cell {
      background: transparent;
      //   border-color: #555;
      border: none;
      padding: 5px 3px;
      font-size: 14px;
    }
    .el-table__row {
      background: transparent !important;
      &:hover {
        background-color: transparent !important;
        cursor: pointer;
      }
    }
    @media screen and (max-width: 1200px) {
      .el-table__cell {
        font-size: 12px;
      }
    }
    tr {
      background: transparent;
      //   &:nth-child(1) { 不能在这里写，thead的tr和td是分开计数的
      //     background: red;
      //   }
    }
  }
}
</style>
