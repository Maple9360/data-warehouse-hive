<template>
  <div ref="main" style="width: 100%; height: 100%"></div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref, watch } from 'vue'
import * as echarts from 'echarts'
import variables from '@/style/variablesExport.module.scss'
import { debounce } from '@/utils/debounce.ts'

const props = defineProps({
  name: {
    type: String,
    default: '',
  },
  xAxis: {
    type: Array,
    default: () => [
      //   '2023-02-28',
      //   '2023-03-07',
      //   '2023-03-10',
      //   '2023-03-11',
      //   '2023-03-23',
    ],
  },
  xData: {
    type: Array,
    default: () => [],
  },
})
const main = ref()
let myChart = null
// ===================== init =====================
const init = () => {
  // 基于准备好的dom，初始化echarts实例
  myChart = echarts.init(main.value)
  const option = {
    title: {
      //   text: 'ECharts 入门示例',
      textStyle: {
        color: variables.titleColor,
        fontSize: variables.titleSize,
      },
    },
    grid: {
      bottom: 80,
      left: 30,
      right: 30,
    },
    tooltip: {},
    xAxis: {
      data: props.xAxis,
      axisLine: {
        // 坐标轴线
        show: true,
        lineStyle: {
          color: variables.axisColor,
        },
      },
    },
    yAxis: {
      minInterval: 1,
      splitLine: {
        show: true,
        lineStyle: {
          color: variables.splitLine,
        },
      },
      axisLine: {
        // 坐标轴线
        show: true,
        lineStyle: {
          color: variables.axisColor,
        },
        axisLabel: {
          // 这里是X轴data数据斜着放
          interval: 0,
          // rotate: 15,
          color: variables.axisColor,
          // fontSize: variables.axisSize
        },
      },
    },
    series: [
      {
        name: '人次',
        type: 'line',
        data: props.xData,
        itemStyle: {
          color: variables.fontColor,
        },
      },
    ],
  }
  myChart.setOption(option)
}
// ===================== resize ===================
const chartResize = debounce(() => {
  myChart.resize()
  console.log('chartresize')
})
// ===================== watch =====================
watch(
  () => {
    return props.xData
  },
  () => {
    myChart.hideLoading()
    myChart.clear()
    init()
  }
)
// =================================================
onMounted(() => {
  init()
  window.addEventListener('resize', chartResize)
})

onUnmounted(() => {
  window.removeEventListener('resize', chartResize)
})
</script>

<style lang="scss" scoped></style>
