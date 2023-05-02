<template>
  <div class="tableWrapper">
    <el-table
      ref="tableRef"
      :data="props.data"
      style="width: 100%"
      :cell-class-name="cellStyle"
    >
      <el-table-column
        key="mfr"
        label="Mfr P/N"
        property="mfr"
        :filters="filterData"
        :filter-method="filterFunction"
      >
      </el-table-column>
      <el-table-column
        v-for="info in tableHeader"
        :key="info.key"
        :label="info.label"
        :property="info.key"
        :fixed="info?.fixed"
        :width="info?.width"
      >
        <template #default="scoped">
          <div
            v-if="isDouble(scoped.row[scoped.column.property]) === 'double'"
            style="display: flex; justify-content: space-around"
          >
            <div style="height: 46px; line-height: 46px">
              {{
                scoped.row[scoped.column.property].history +
                scoped.row[scoped.column.property].current
              }}
            </div>
            <div>
              <div>
                <span
                  style="
                    display: inline-block;
                    margin-right: 5px;
                    border-radius: 10px;
                    width: 10px;
                    height: 9px;
                    background: red;
                    box-shadow: 0 0 8px #ee9999;
                  "
                ></span>
                {{ scoped.row[scoped.column.property].current }}
              </div>
              <div>
                <span
                  style="
                    display: inline-block;
                    margin-right: 5px;
                    border-radius: 10px;
                    width: 10px;
                    height: 9px;
                    background: green;
                    box-shadow: 0 0 8px #4f904f;
                  "
                ></span>
                {{ scoped.row[scoped.column.property].history }}
              </div>
            </div>
          </div>
          <div v-if="isDouble(scoped.row[scoped.column.property]) === 'solo'">
            <!-- 待更改 -->
            <span v-show="scoped.row[scoped.column.property]?.history">{{
              scoped.row[scoped.column.property]?.history
            }}</span>
            <span v-show="scoped.row[scoped.column.property]?.current">{{
              scoped.row[scoped.column.property]?.current
            }}</span>
          </div>
          <div v-else></div>
          <!-- </div> -->
          <!-- <el-button @click="test(scoped)"></el-button> -->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import { ref, getCurrentInstance, onMounted, watch } from 'vue'
// import { useUserStore } from '@/store/modules/user.ts'
// import { responseHandle } from '@/utils/message.ts'
const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },
})

// ======================================================= filter筛选 =====================================
const filterData = ref([
  // 筛选选项，需提取tableData数据转化（transferData函数）
  //   { text: 'BBBB', value: 'BBBB' },
  //   { text: 'BD9523BCPZ', value: 'BD9523BCPZ' },
  //   { text: 'CD9523BCPZ', value: 'DD9523BCPZ' },
])

const transferData = () => {
  // 处理tableData拿到filterData
  const arr = []
  props.data.map((obj) => {
    arr.push({ text: obj.mfr, value: obj.mfr })
    return obj
  })
  return arr
}

const filterFunction = (value, row, column) => {
  // 筛选选项函数
  const property = column.property
  return row[property] === value
}
// ======================================================== 多语言 =======================================
const {
  appContext: {
    config: { globalProperties },
  },
} = getCurrentInstance()
// console.log(globalProperties.$t('test')) 多语言测试

// ============================================= table =====================================
// const tableHeader = JSON.parse(globalProperties.$t('authority.tableHeaderLog'))
const tableHeader = [
  //   { label: 'Mfr P/N', key: 'mfr' },
  { label: '2C', key: '2c' },
  { label: '4C', key: '4c' },
  { label: 'BFT', key: 'bft' },
  { label: 'BST/PT', key: 'bst/pt' },
  { label: 'FST', key: 'fst' },
  { label: 'ICT', key: 'ict' },
  { label: 'Jtag', key: 'jtag' },
  { label: 'P2C', key: 'p2c' },
  { label: 'P2C2C', key: 'p2c2c' },
  { label: 'PCBVF', key: 'pcbvf' },
  { label: 'Runin', key: 'runin' },
]
// const tableData = ref([
//   {
//     mfr: 'AAA',
//     '2C': 4,
//     '4c': 0,
//     bft: 5,
//     'bst/pt': 6,
//     fst: 0,
//     ict: 9,
//     jtag: { history: 5, current: 1 },
//     p2c: 1,
//     p2c2c: 2,
//     pcbvf: 3,
//     runin: 0,
//   }
// ])
console.log()
// ======================================================= 单元格个性显示：对象、数字、0 三种情况 ======================================
const isDouble = (data) => {
  // 判断data是否是对象，data是单元格内的数据
  // scoped.row[scoped.column.property
  //   console.log(data)
  const temp = Object.prototype.toString.call(data)
  if (temp === '[object Object]') {
    if (data.history !== 0 && data.current !== 0) {
    //   console.log('double')
      return 'double'
    } else if (
      (data.history !== 0 && data.current === 0) ||
      (data.history === 0 && data.current !== 0)
    ) {
    //   console.log('solo')
      return 'solo'
    }
  } else {
    return 'null'
  }
}
// ====================================== 单元格样式个性化 ====================================
const emit = defineEmits(['handleCellStyle'])
const cellStyle = function ({ row, column, rowIndex, columnIndex }) {
  let res = true
  emit('handleCellStyle', { row, column, rowIndex, columnIndex }, (val) => {
    res = val
  })
  return res // res是样式名
}

watch(
  () => {
    return props.data
  },
  () => {
    filterData.value = transferData()
  }
)
onMounted(() => {})
</script>
<style lang="scss" scoped>
.tableWrapper :deep() {
  margin: 25px;
  display: flex;
  justify-content: center;
  .el-table {
    color: #fff;
    background: transparent;
    // border: 1px solid green !important;
    --el-table-border-color: none;
    --el-table-row-hover-bg-color: transparent; // hover这里相当于直接改背景色，而不是覆盖
    & .cell {
      // el-table_cell区别
      // 控制表格单元表格
      // word-break: normal;
      text-align: center;
      padding: 5px;
      border: none;
      font-size: 12px;
    }
    & thead {
      // 表头样式
      color: rgb(255, 255, 255);
      //   background: #222;
      tr {
        // background-image: url('/src/assets/authority/tableItemBg.png'); // thead的样式
        // background-size: 100% 100%;
        .el-table__cell {
          background-image: url('/src/assets/eosMgmt/cell_header.png');
          background-size: 100% 100%;
        }
      }
    }
    & tbody {
      tr:nth-child(2n) {
        // background-image: url('/src/assets/authority/tableItemBg.png'); // tbody单独设置tr的样式
        // background-size: 100% 100%;
      }
      .el-table_1_column_1 {
        background: url('/src/assets/eosMgmt/cell_header1.1.png') no-repeat
          center;
        background-size: 100% 99%;
      }
    }
    .el-table__cell {
      box-sizing: border-box;
      background-color: transparent;
      background: url('/src/assets/eosMgmt/cell.png') no-repeat center;
      background-size: 100% 99%;
      border-radius: 5px;
      //   border-color: #555;
      border: none;
      padding: 5px 3px;
      font-size: 12px;
      &:hover {
        background-image: url('/src/assets/eosMgmt/cell_header.png');
        background-size: 98% 98%;
        // box-shadow: 0 0 10px rgb(44, 44, 44);
        // border-radius: 10px;
      }
      &.bgSelected:not(.el-table_1_column_1) {
        background-image: url('/src/assets/eosMgmt/cell_on.png');
        background-size: 98% 98%;
        &:hover {
        //   background-image: url('/src/assets/eosMgmt/cell.png');
        //   background-size: 98% 98%;
        background: rgba(145, 248, 240, 0.63);
        }
      }
      &.bgSelectedRed:not(.el-table_1_column_1) {
        background-image: url('/src/assets/eosMgmt/cell_off.png');
        background-size: 98% 98%;
        &:hover {
        //   background-image: url('/src/assets/eosMgmt/cell.png');
        //   background-size: 98% 98%;
        background: rgba(250, 121, 121, 0.753);
        }
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
