<template>
  <div class="app2-wrapper">
    <div class="page-wrapper">
      <div style="color: #fff">People Flow Monitoring</div>
      <div @click="backTo" class="button-style">
        <!-- <img
          class="img-style"
          src="/src/assets/authority/download.png"
          alt=""
          style="height: 30px; transform: rotate(90deg)"
        /> -->
        <img
          class="img-style"
          style="height: 30px"
          src="/src/assets/total/back.png"
        />
      </div>
      <div style="padding: 10px 0px">
        <el-radio-group v-model="range" @change="changeRange">
          <el-radio-button label="day"> 7 days </el-radio-button>
          <el-radio-button label="month">A month </el-radio-button>
          <el-radio-button label="year"> Half year </el-radio-button>
        </el-radio-group>
      </div>
      <!-- 图表 -->
      <div style="height: 45vh">
        <chart-log :x-axis="xAxis" :x-data="xData"></chart-log>
      </div>
      <!-- 表格 -->
      <div>
        <table-log
          :table-data="tableData"
          :table-header="tableHeader"
        ></table-log>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import chartLog from './components/chart.vue'
import tableLog from './components/table.vue'
import { getLog } from '@/api/login.ts'
// ================ back ===================
import { useRouter } from 'vue-router'
import { ref } from 'vue'
const router = useRouter()
const backTo = () => {
  router.push({
    path: '/dashboard/index',
  })
}
// ============================== params ==============================
// const language = localStorage.getItem('LANG') || 'zh-cn' // 语言后台待校准
const language = 'en'
const range = ref('day') // 时间范围 day、month、year
const empno = ref()
// ============================== getData =============================
// tableData.header
const tableHeader = ref([])
const tableData = ref()

const xAxis = ref()
const xData = ref()
const getData = () => {
  getLog({ empno: empno.value, date: range.value, language }).then((res) => {
    console.log(res)
    let rdt = res.data.tableData
    let rdc = res.data.chartData
    tableHeader.value = rdt.header
    tableData.value = JSON.parse(rdt.data)

    xAxis.value = rdc.xAxis
    xData.value = rdc.xData
  })
}
getData()

const changeRange = () => {
  getData()
}
</script>

<style lang="scss" scoped>
// 根组件下第一层
.app2-wrapper {
  width: 100%;
  height: 100%;
  background: rgb(0, 0, 0);
  // background-size: 100% 100%;
  position: relative;
}
.page-wrapper {
  color: $font-gray;
  background: #000;
  background-image: url('/src/assets/background/border-large.png');
  background-size: 100% 100%;
  min-height: 100vh;
  // position: relative;
  padding: 45px;
  box-sizing: border-box;
  :deep() .el-radio-button__inner:hover {
    color: $font-color;
  }
}
// 回退键样式
.button-style {
  position: absolute;
  right: 45px;
  top: 45px;
}
.img-style {
  &:hover {
    height: 35px !important;
  }
}
// range button
.el-radio-button {
  --el-radio-button-checked-bg-color: #31767e;
  --el-radio-button-checked-text-color: #fff;
  --el-radio-button-checked-border-color: transparent;
}
</style>
