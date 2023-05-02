<template>
  <div class="tableWrapper">
    <div style="height: 10px"></div>
    <el-table
      ref="multipleTableRef"
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        v-for="info in tableHeader"
        :key="info.key"
        :label="info.label"
        :property="info.key"
        :fixed="info?.fixed"
        :width="info?.width"
      />
      <el-table-column
        :label="$t('authority.follow')"
        fixed="right"
        width="180px"
      >
        <template #default="scope">
          <svg-icon
            @click="emailAlerts(scope.row)"
            :className="emailFlagStyle(scope.row, emailFlag)"
            iconName="icon-fasong"
          ></svg-icon>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref, getCurrentInstance, onMounted, watch } from 'vue'
import { ElTable } from 'element-plus'
import { getLog, getEmail } from '@/api/authority.ts'
import { useUserStore } from '@/store/modules/user.ts'
import { responseHandle } from '@/utils/message.ts'
// ============================= 获取工号 ====================
const user = useUserStore()

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

// ============================= 跟催 ==================================
const emailFlag = ref(true)
const emailFlagStyle = (row, flag) => {
  // 按钮是否可以点击
  const status = row.status
  const arr = ['已駁回', '已驳回', '已签核', '已簽核']
  // console.log(status.indexOf(arr))
  if (arr.indexOf(row.status) !== -1 || emailFlag.value === false) {
    // console.log('按钮不可以点击')
    return 'iconDisabled'
  } else {
    // console.log('按钮可以点击' )
    return 'iconMenu'
  }
}

// const emailFlag = true
const emailAlerts = (row) => {
  const arr = ['已駁回', '已驳回', '已签核', '已簽核']
  if (arr.indexOf(row.status) === -1 && emailFlag.value === true) {
    getEmail({
      email: row.email, // 申请人的邮箱
    }).then((res) => {
      // 跟催成功后按钮不可再次点击
      console.log(res)
      if (res.data.status === 'OK') {
        emailFlag.value = false
      }
      responseHandle(res, true)
    })
  }
}
// =========================== table =======================================
const tableHeader = JSON.parse(globalProperties.$t('authority.tableHeaderLog'))

// const tableData: User[] = [
const tableData = ref([
  {
    empno: '2016-05-03',
    username: 'Tom',
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
  // user.empno
  getLog({ empno: 'X2000957' }).then((res) => {
    // console.log(res)
    tableData.value = res.data.tableData
  })
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
    & .cell {
      // 控制表格单元表格
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
      font-size: 12px;
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

// icon
.iconMenu {
  color: $font-gray;
  font-size: 24px;
  margin: 0px 5px;
  &:hover {
    color: $font-color !important;
    cursor: pointer;
  }
}

.iconDisabled {
  color: $font-gray;
  font-size: 24px;
  margin: 0px 5px;
  &:hover {
    cursor: no-drop;
  }
}
</style>
