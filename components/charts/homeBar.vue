<template>
  <div ref="main" style="width: 100%; height: 100%"></div>
</template>

<script setup lang="ts">
// 组件命名请小驼峰：功能页名 + echarts类型
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
import variables from '@/style/variablesExport.module.scss'
console.log(variables)
const main = ref()
const init = () => {
  // 基于准备好的dom，初始化echarts实例
  let myChart = echarts.init(main.value)
  const option = {
    title: {
      text: 'ECharts 入门示例',
      textStyle: {
        color: variables.titleColor,
        fontSize: variables.titleSize,
      },
    },
    tooltip: {},
    xAxis: {
      data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'],
    },
    yAxis: {},
    series: [
      {
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20],
      },
    ],
  }
  myChart.setOption(option)
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped></style>
