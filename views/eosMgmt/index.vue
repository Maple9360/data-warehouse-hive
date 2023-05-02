<template>
  <div class="page-container">
    <div class="searchWrapper">
      <img class="imgButtonStyle" src="/src/assets/total/search.png" alt="" />
      <div style="float: right">
        <!-- <span class="demonstration">With quick options</span> -->
        <el-date-picker
          v-model="date"
          value-format="YYYY-MM-DD"
          type="daterange"
          unlink-panels
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
          :shortcuts="shortcuts"
        />
      </div>
      <el-input
        v-model="search"
        class="w-50 m-2 inputStyle"
        placeholder="Search"
        :prefix-icon="Search"
      />
    </div>
    <div class="chartWrapper">
      <!-- 未启用<heat></heat> -->
      <heat-table
        :data="tableData"
        @handleCellStyle="handleCellStyle"
      ></heat-table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Search } from '@element-plus/icons-vue'
import { onMounted, ref, watch } from 'vue'
// import heat from './components/heatMap.vue'
import heatTable from './components/heatTable.vue'
import { getHeat } from '@/api/eosMgmt.ts'
// =============================== 搜索栏 ===============================
// 搜什么？还要哪些条件
const search = ref()
const date = ref()
watch(
  () => date.value,
  () => {
    console.log(date.value)
  }
)
// 日期快捷选择
const shortcuts = [
  {
    text: 'Last week',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    },
  },
  {
    text: 'Last month',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      return [start, end]
    },
  },
  {
    text: 'Last 3 months',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
      return [start, end]
    },
  },
]
// ============================== 表格组件参数 =============================
const tableData = ref()
// ============================================= getData ===================================
const getData = () => {
  let param = null
  if (date.value) {
    param = { startDate: date.value[0], endDate: date.value[1] }
  }
  getHeat(param).then(
    (res) => {
      console.log(res)
      tableData.value = [
        {
          mfr: 'AA',
          '2c': 4,
          '4c': 0,
          bft: 5,
          'bst/pt': 6,
          fst: 0,
          ict: 9,
          jtag: { history: 5, current: 1 },
          p2c: 1,
          p2c2c: 2,
          pcbvf: 3,
          runin: 0,
        },
        {
          mfr: 'AB',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 2,
          fst: { history: 0, current: 1 },
          ict: 0,
          jtag: 0,
          p2c: 1,
          p2c2c: 0,
          pcbvf: 3,
          runin: 0,
        },
        {
          mfr: 'BB',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: { history: 8, current: 0 },
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'CC',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'DD',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'EE',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'FF',
          '2c': 0,
          '4c': { history:0, current: 3 },
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: { history: 8, current: 0 },
          runin: 0,
        },
        {
          mfr: 'GG',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'HH',
          '2c': { history: 8, current: 0 },
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: { history: 1, current: 1 },
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'II',
          '2c': { history: 1, current: 1 },
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'JJ',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'KK',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'LL',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'MM',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'NN',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: 0,
          pcbvf: 0,
          runin: 0,
        },
        {
          mfr: 'OO',
          '2c': 0,
          '4c': 0,
          bft: 0,
          'bst/pt': 0,
          fst: 0,
          ict: 0,
          jtag: 0,
          p2c: 0,
          p2c2c: { history: 1, current: 1 },
          pcbvf: 0,
          runin: 0,
        },
      ]
      // let rd = res.data
      // tableData.value = rd.tableData
    },
    (res) => {
      console.log(res)
    }
  )
}
onMounted(() => {
  getData()
})
// ============================================= 单元格个性化样式 ============================
const handleCellStyle = ({ row, column, rowIndex, columnIndex }, callback) => {
  let temp = row[column.property]
  const isObj = Object.prototype.toString.call(temp)
  if (isObj === '[object Object]') {
    if (temp?.current !== 0) {
      callback('bgSelectedRed')
    } else {
      callback('bgSelected')
    }
  }
  // 为0时不显示
  // 待更改
  // callback(true/false)的回调函数写法是eslint standard 标准中不允许的写法，而不是es6中的规定
}
</script>

<style lang="scss" scoped>
.page-container {
  // color: $color-green;
  // border: 1px solid green;
}
.searchWrapper :deep() {
  // 内部都是浮动元素，没什么用
  // width: 100%;
  padding-right: 25px;
  height: 32px; // 为了让下方不忽略它
  // input
  .inputStyle {
    width: 100px;
    float: right;
    background: transparent;
  }
  .el-input__wrapper {
    background-color: transparent;
    box-shadow: 0 0 0 1px $font-gray;
    // --el-input-placeholder-color: #79aaa7; 此处省略，全局root设置了
  }
  .el-input__inner {
    color: $font-color;
  }
  .el-input__prefix {
    color: $font-color;
  }

  // date picker
  .el-range-editor.el-input__wrapper {
    margin-left: 10px;
    width: 250px;
  }
  .el-date-editor .el-range-separator {
    color: $font-gray;
  }
  .el-date-editor .el-range-input {
    color: $font-color;
  }
  .imgButtonStyle {
    height: 32px;
    float: right;
    margin-left: 10px;
    &:hover {
      cursor: pointer;
    }
  }
}
.chartWrapper {
  // height: calc(90vh - 97px); // padding的65px, 选择框的32px
}
</style>
