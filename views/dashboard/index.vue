<template>
  <div class="page-container">
    test: <div>{{ $t('test') }}</div>
    <!-- views文件下的dashboard页面
    <h4>1.菜单栏链接正在配置</h4>
    <h4>2.开发新页面时，router文件下在主模块下添加路径即可，模块组件可以直接放在该模块下components文件夹下。</h4>
    <h4>3.全局样式和全局样式变量写在style文件中</h4>
    <h4>4.echarts、element-plus为全局引入</h4>
    <h5 style="color: #fff">——分机187791，黄荟霖</h5> -->
    <div style="width: 500px; height: 500px">
      <home-bar></home-bar>
    </div>
  </div>
</template>

<script setup lang="ts">
import homeBar from '@/components/charts/homeBar.vue'
</script>

<style lang="scss" scoped>
.page-container {
  color: $color-green;
  border: 1px solid green;
  height: 900px;
}
</style>
