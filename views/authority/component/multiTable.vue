<template>
  <div class="tableWrapper">
    <el-table
      ref="multipleTableRef"
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
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
            @click="applyOperation(scope.$index, scope.row, 1)"
            >Approve</el-button
          >
          <el-button
            size="small"
            @click="applyOperation(scope.$index, scope.row, 2)"
            >Reject</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="opStyle">
      <el-button :disabled="multiIf" @click="multiApplyOperation(1)">{{
        $t('authority.approve')
      }}</el-button>
      <el-button :disabled="multiIf" @click="multiApplyOperation(2)">{{
        $t('authority.disapprove')
      }}</el-button>
      <el-button :disabled="multiIf" @click="toggleSelection()">{{
        $t('authority.clearselection')
      }}</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref, getCurrentInstance, onMounted, watch } from 'vue'
import { ElTable } from 'element-plus'
import { putPermission } from '@/api/authority.ts'

const empnoList = [] // 签核的工号
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
  // 0是查询，1是通过，2是驳回
  putPermission({
    empnoList: [row.empno],
    approver: 'F1333521', // 替换为登录人（管理员）
    status: operation,
  }).then((res) => {
    // 签核后更新列表
    console.log(res)
    tableData.value = res.data.tableData
  })

  // putPermission
  // 签核同意
}
// ====================================== 批量签核 ==============================
const multiApplyOperation = (operation) => {
  // 拿到批量选中的值multipleSelection.value
  multipleSelection.value.map((obj) => {
    empnoList.push(obj.empno)
    return obj
  })
  // console.log(empnoList)
  // 调用批量签核接口
  putPermission({ empnoList, approver: 'F1333521', status: operation }).then(
    // 需要替换
    (res) => {
      // console.log(res)
      tableData.value = res.data.tableData
    }
  )
}
// 按钮是否可以点击
const multiIf = computed(() => {
  // multipleSelection.value中有值可以点击
  if (multipleSelection.value.length > 0) {
    return false
  } else {
    return true
  }
})

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<User[]>([])
// 单独选中2、3行--
const toggleSelection = (rows?: User[]) => {
  if (rows) {
    rows.forEach((row) => {
      multipleTableRef.value!.toggleRowSelection(row, undefined)
    })
  } else {
    multipleTableRef.value!.clearSelection()
  }
}
// 选项变动的函数
const handleSelectionChange = (val: User[]) => {
  // 这里的User有操作空间么
  multipleSelection.value = val
}
// =========================== table =======================================
const tableHeader = JSON.parse(globalProperties.$t('authority.tableHeader'))

// const tableData: User[] = [
const tableData = ref([
  // {
  //   empno: '2016-05-03',
  //   name: 'Tom',
  //   role: 'guest',
  //   site: 'FOL',
  //   mfg: '制造一处',
  //   area: 'D',
  //   building: '11栋',
  //   floor: '3楼',
  //   email: 'xxxxxxxxxx@foxconn.com',
  //   ext: '560-00000',
  //   approver: '张三',
  //   // status: 'pass', // boolean？
  //   // follow: 'true', // 是否跟催？
  // },
  // {
  //   empno: '2016-05-03',
  //   name: 'Tom',
  //   role: 'guest',
  //   site: 'FOL',
  //   mfg: '制造一处',
  //   area: 'D',
  //   building: '11栋',
  //   floor: '3楼',
  //   email: 'xxxxxxxxxx@foxconn.com',
  //   ext: '560-00000',
  //   approver: '张三',
  //   status: 'pass', // boolean？
  //   follow: 'true', // 是否跟催？
  // },
  // {
  //   empno: '2016-05-03',
  //   name: 'Tom',
  //   role: 'guest',
  //   site: 'FOL',
  //   mfg: '制造一处',
  //   area: 'D',
  //   building: '11栋',
  //   floor: '3楼',
  //   email: 'xxxxxxxxxx@foxconn.com',
  //   ext: '560-00000',
  //   approver: '张三',
  //   status: 'pass', // boolean？
  //   follow: 'true', // 是否跟催？
  // },
  // {
  //   empno: '2016-05-03',
  //   name: 'Tom',
  //   role: 'guest',
  //   site: 'FOL',
  //   mfg: '制造一处',
  //   area: 'D',
  //   building: '11栋',
  //   floor: '3楼',
  //   email: 'xxxxxxxxxx@foxconn.com',
  //   ext: '560-00000',
  //   approver: '张三',
  //   status: 'pass', // boolean？
  //   follow: 'true', // 是否跟催？
  // },
  // {
  //   empno: '2016-05-03',
  //   name: 'Tom',
  //   role: 'guest',
  //   site: 'FOL',
  //   mfg: '制造一处',
  //   area: 'D',
  //   building: '11栋',
  //   floor: '3楼',
  //   email: 'xxxxxxxxxx@foxconn.com',
  //   ext: '560-00000',
  //   approver: '张三',
  //   status: 'pass', // boolean？
  //   follow: 'true', // 是否跟催？
  // },
  // {
  //   empno: '2016-05-03',
  //   name: 'Tom',
  //   role: 'guest',
  //   site: 'FOL',
  //   mfg: '制造一处',
  //   area: 'D',
  //   building: '11栋',
  //   floor: '3楼',
  //   email: 'xxxxxxxxxx@foxconn.com',
  //   ext: '560-00000',
  //   approver: '张三',
  //   status: 'pass', // boolean？
  //   follow: 'true', // 是否跟催？
  // },
])

onMounted(() => {
  putPermission({ empnoList: [], approver: 'F1333521', status: 0 }).then(
    (res) => {
      console.log(res)
      tableData.value = res.data.tableData
    }
  )
})
// })
</script>
<style lang="scss" scoped>
.tableWrapper :deep() {
  .el-table {
    color: #fff;
    background: transparent;
    // border: 1px solid green !important;
    --el-table-border-color: none;
    & .cell { // 控制表格单元表格
      word-break: normal;
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
    .el-table-fixed-column--right {
      // 右侧固定的
      // background: #22222288; // 优先级高于thead
    }
  }
  .el-checkbox__input.is-checked .el-checkbox__inner {
    // background-image: url('/src/assets/authority/yes.png');
    // background-size: 100% 100%;
    // background: transparent;
    background-color: #222;
    border-color: $font-gray;
  }
  .el-checkbox__input.is-indeterminate .el-checkbox__inner {
    background-color: $font-gray;
    border-color: $font-gray;
  }
}

// 按钮wrapper
.opStyle {
  margin: 20px 0;
  .el-button {
    background: transparent;
    color: $font-gray;
    border-color: $font-gray;
    &.is-disabled {
      color: #888;
      border-color: #888;
    }
    &:hover:not(.is-disabled) {
      color: $font-color;
      border-color: $font-color;
    }
  }
}
</style>
