<template>
  <div class="tableWrapper">
    <el-table
      ref="multipleTableRef"
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" /> -->
      <el-table-column
        v-for="info in tableHeader"
        :key="info.key"
        :label="info.label"
        :property="info.key"
        :fixed="info?.fixed"
        :width="info?.width"
      />
      <el-table-column label="Operation" fixed="right" width="180px">
        <template #default="scope">
          <el-button
            size="small"
            @click="applyOperation(scope.$index, scope.row, 'YES')"
            >Approve</el-button
          >
          <el-button
            size="small"
            @click="applyOperation(scope.$index, scope.row, 'NO')"
            >Reject</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref, getCurrentInstance } from 'vue'
import { ElTable } from 'element-plus'
// ============================== 多语言 ==================================
const {
  appContext: {
    config: { globalProperties },
  },
} = getCurrentInstance()
console.log(globalProperties.$t('test'))

// =============================== =====================================
interface User {
  empno: string
}

// 单个审核操作
const applyOperation = (index, row, operation) => {
  console.log(index, row, operation)
  //
  // 签核同意
}
// =========================== table =======================================
const tableHeader = JSON.parse(globalProperties.$t('authority.tableHeader'))

// const tableData: User[] = [
const tableData = [
  {
    empno: '2016-05-03',
    name: 'Tom',
    role: 'guest',
    site: 'FOL',
    mfg: '制造一处',
    area: 'D',
    building: '11栋',
    floor: '3楼',
    email: 'xxxxxxxxxx@foxconn.com',
    ext: '560-00000',
    approver: '张三',
    status: 'pass', // boolean？
    follow: 'true', // 是否跟催？
  },
  {
    empno: '2016-05-03',
    name: 'Tom',
    role: 'guest',
    site: 'FOL',
    mfg: '制造一处',
    area: 'D',
    building: '11栋',
    floor: '3楼',
    email: 'xxxxxxxxxx@foxconn.com',
    ext: '560-00000',
    approver: '张三',
    status: 'pass', // boolean？
    follow: 'true', // 是否跟催？
  },
  {
    empno: '2016-05-03',
    name: 'Tom',
    role: 'guest',
    site: 'FOL',
    mfg: '制造一处',
    area: 'D',
    building: '11栋',
    floor: '3楼',
    email: 'xxxxxxxxxx@foxconn.com',
    ext: '560-00000',
    approver: '张三',
    status: 'pass', // boolean？
    follow: 'true', // 是否跟催？
  },
]
</script>
<style lang="scss" scoped>
.tableWrapper :deep() {
    padding: 20px 10px 0px 10px;
    background-image: url('/src/assets/total/table-border (2).png');
    background-size: 100% 100%;
  .el-table {
    color: #fff;
    background: transparent;
    // border: 1px solid green !important;
    --el-table-border-color: none;
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
    }
    tr {
      background: transparent;
      //   &:nth-child(1) { 不能在这里写，thead的tr和td是分开计数的
      //     background: red;
      //   }
    }
    .el-table-fixed-column--right {
      // 右侧固定的
      // background: #22222288; // 优先级高于thead
    }
  }
}
</style>
