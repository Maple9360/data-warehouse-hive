s
<template>
  <!-- 该组件未启用。
  用户以及UI设计所谓的'热力图'，本质上是电子表格画的……
  至于UI单个格子内渐变色的样式，echarts5.0 heatMap未提供任何可行的配置方案，社区也有翻阅，无任何解决方案……
  而从本质上讲，热力图设计成这样也不算'热力'图了，只能说是数据分为两种情况的、样式特别一点的table表格，
  基于以上原因：1.热力图做不成这样，2.本质就是个table，故该模块最终选用table实现 -->
  <div ref="heat1" style="width: 100%; height: 100%"></div>
</template>

<script setup lang="ts">
import * as echarts from 'echarts'
import { ref, watch, onMounted, onUnmounted } from 'vue'
import { debounce } from '@/utils/debounce.ts'
import { spawn } from 'child_process'
import { url } from 'inspector'

type EChartsOption = echarts.EChartsOption
const heat1 = ref()
let myChart = null
let option: EChartsOption

// prettier-ignore
const hours = [
  '12a', '1a', '2a', '3a', '4a', '5a', '6a',
  '7a', '8a', '9a','10a','11a',
  '12p', '1p', '2p', '3p', '4p', '5p',
  '6p', '7p', '8p', '9p', '10p', '11p'
];

// prettier-ignore
const days = [
  'Saturday', 'Friday', 'Thursday',
  'Wednesday', 'Tuesday', 'Monday', 'Sunday'
];

// prettier-ignore
// [value1, value2, value3, value4]，前两位是坐标，第三位是显示的数值，第四位是样式
// const data = [[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]]
const data = [[0,0,5, 123],[0,1,1,1],[0,2,0,0],[0,3,0,0],
[0,4,0,0],[0,5,0,1],[0,6,0,1],[0,7,0,2],[0,8,0, 1],[0,9,0, 1],[0,10,0,2],[0,11,2,0],[0,12,4,0],[0,13,1,0],[0,14,1,1],[0,15,3,0],[0,16,4,0],[0,17,6,1],[0,18,4,0],[0,19,4,1],[0,20,3,0],[0,21,3,1],[0,22,2,1],[0,23,5,2],[1,0,7,2],[1,1,0,1],[1,2,0,1],[1,3,0,2],[1,4,0,2],[1,5,0,1],[1,6,0,0],[1,7,0,2],[1,8,0,1],[1,9,0,2],[1,10,5,0],[1,11,2,1],[1,12,2,2],[1,13,6,1],[1,14,9,1],[1,15,11,0],[1,16,6,0],
[1,17,7,0],[1,18,8,0],[1,19,12,0],[1,20,5,0],[1,21,5,0],[1,22,7,0],[1,23,2,0],[2,0,1,0],[2,1,1,0],[2,2,0,0],[2,3,0,0],[2,4,0,0],[2,5,0,0],[2,6,0,0],[2,7,0,0],[2,8,0,0],[2,9,0,0],[2,10,3,0],[2,11,2,0],[2,12,1,0],[2,13,9,0],[2,14,8,0],[2,15,10,0],[2,16,6,0],[2,17,5,0],[2,18,5,0],[2,19,5,0],[2,20,7,0],[2,21,4,0],[2,22,2,0],[2,23,4,0],[3,0,7,0],[3,1,3,0],[3,2,0,0],[3,3,0,0],[3,4,0,0],[3,5,0,0],[3,6,0,0],[3,7,0,0],[3,8,1,0],[3,9,0,0],[3,10,5,0],[3,11,4,0],[3,12,7,0],[3,13,14,0],[3,14,13,0],[3,15,12,0],[3,16,9,0],[3,17,5,0],[3,18,5,0],[3,19,10,0],[3,20,6,0],[3,21,4,0],[3,22,4,0],[3,23,1,0],[4,0,1,0],[4,1,3,0],[4,2,0,0],[4,3,0,0],[4,4,0,0],[4,5,1,0],[4,6,0,0],[4,7,0,0],[4,8,0,0],[4,9,2,0],[4,10,4,0],[4,11,4,0],[4,12,2,0],[4,13,4,0],[4,14,4,0],[4,15,14,0],[4,16,12,0],[4,17,1,0],[4,18,8,0],[4,19,5,0],[4,20,3,0],[4,21,7,0],[4,22,3,0],[4,23,0,0],[5,0,2,0],[5,1,1,0],[5,2,0,0],[5,3,3,0],[5,4,0,0],[5,5,0,0],[5,6,0,0],[5,7,0,0],[5,8,2,0],[5,9,0,0],[5,10,4,0],[5,11,1,0],[5,12,5,0],[5,13,10,0],[5,14,5,0],[5,15,7,0],[5,16,11,0],[5,17,6,0],[5,18,0,0],[5,19,5,0],[5,20,3,0],[5,21,4,0],[5,22,2,0],[5,23,0,0],[6,0,1,1,],[6,1,0,2,],[6,2,0,0],[6,3,0,0],[6,4,0,0],[6,5,0,0],[6,6,0,0],[6,7,0,0],[6,8,0,0],[6,9,0,0],[6,10,1,0],[6,11,0,0],[6,12,2,0],[6,13,1,0],[6,14,3,0],[6,15,4,0],[6,16,0,0],[6,17,0,0],[6,18,0,0],[6,19,0,2],[6,20,1,2],[6,21,2,2],[6,22,2,2],[6,23,6,2]]
    .map(function (item) {
        return [item[1], item[0], item[2], item[3] || 0]; // item[3] || '-'
    })

// ======================================== init =======================================
const init = () => {
  myChart = echarts.init(heat1.value)
  option = {
    tooltip: {
      position: 'top',
      extraCssText: 'box-shadow: 0 0 10px #fff',
      formatter: function (params) {
        // tooltip自定义样式
        // console.log(params)
        let returnData = ''
        if (params.value[3] !== 0) {
          let itemColor = params.color
          returnData +=
            '<span style="display:inline-block; margin-right: 5px; border-radius: 10px; width: 10px;height:9px;background:' +
            itemColor +
            '"></span>'
          returnData += params.name + ': ' + params.value[3] + '<br>'
          return returnData
          // return ''
        }
      },
    },
    // legend: {
    //   bottom: '0%',
    // },
    grid: {
      height: '70%',
      top: '10%',
      left: '10%',
      right: '5%',
    },
    xAxis: {
      type: 'category',
      position: 'top',
      data: hours,
      axisLabel: {
        color: '#ddd',
      },
      splitArea: {
        show: true,
        // areaStyle: {
        //   color: ['pink', 'blue'],
        // },
      },
    },
    yAxis: {
      type: 'category',
      data: days,
      axisLabel: {
        color: '#ddd',
      },
    },
    visualMap: {
      type: 'piecewise', // 散点
      min: 0,
      // max: 10,
      calculable: true,
      orient: 'horizontal',
      left: 'center',
      bottom: '10%',
      pieces: [
        // 这里判断的是data数组的最后一位，最后一位控制样式
        // { value: 123, label: '123（自定义特殊颜色）', color: 'yellow' }, // [123, 123]
        // { gt: 0, color: 'red' }, // (1500, Infinity]
        // { gt: 0, lte: 1, label: '10 到 200（自定义label）', color: 'green' }, // (0, 1]
        // { lt: 0, color: 'black' },
        { value: 0, color: '#333' }, // 有label报错, value=0时不显示
        { value: 1, color: '#77aaaa' },
        { value: 2, color: 'red' },
        // { lt: 0, color: '#ddd' },
        // { gt: 0, color: 'black' },
      ],
      // 自定义热力图颜色
      // inRange: {
      //   // color: ['#000', 'yellow', 'red'],
      // },
    },
    series: [
      {
        name: 'Punch Card', // 系列数据名字
        type: 'heatmap',
        coordinateSystem: 'cartesian2d',
        data,
        label: {
          normal: {
            show: true,
            formatter: (item) => {
              // 数据为0不显示
              // console.log(item)
              if (item.value[3] === 0) {
                return ''
              }
            },
          },
          // rich: {
          //   width: 18,
          //   height: 18,
          //   align: 'center',
          //   verticalAlign: 'top',
          //   backgroundColor: {
          //     color: 'green',
          //   },
          // },
        },
        itemStyle: {
          // color: ['#000', 'yellow', 'red'],
          borderColor: '#555',
          shadowBlur: 5,
          borderRadius: 5,
          // color写在此处不生效
        },
        emphasis: {
          // 强调
          itemStyle: {
            shadowBlur: 15,
            shadowColor: 'rgba(0, 0, 0, 0.5)', // hover边框
          },
        },
      },
    ],
  }

  option && myChart.setOption(option)
}

// ======================================== resize ======================================
const chartResize = debounce(() => {
  myChart.resize()
  console.log('chartresize')
})
// ======================================== watch ======================================
// watch(
//   () => {
//     return props.xData
//   },
//   () => {
//     myChart.hideLoading()
//     myChart.clear()
//     init()
//   }
// )
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
